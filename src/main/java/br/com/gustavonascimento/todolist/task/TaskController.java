package br.com.gustavonascimento.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gustavonascimento.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);

    var currentDate = LocalDateTime.now();
    var startDate = taskModel.getStartAt();
    var endDate = taskModel.getEndAt();

    if(currentDate.isAfter(startDate) || currentDate.isAfter(endDate)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início / término deve ser maior que atual.");
    };

    if(startDate.isAfter(endDate)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor que a data de termino");
    };

    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);

    return tasks;
  }

  @PutMapping("/{id}")
  public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
    
    var task = this.taskRepository.findById(id).orElse(null);

    Utils.copyNonNullProperties(taskModel, task);
    
    return this.taskRepository.save(task);
  }
}
