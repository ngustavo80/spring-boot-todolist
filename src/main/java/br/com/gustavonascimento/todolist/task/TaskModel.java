package br.com.gustavonascimento.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
  
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID Id;
  private UUID idUser;

  @Column(length = 150)
  private String description;
  @Column(length = 50)
  private String title;
  @Column(length = 5)
  private String priority;

  private LocalDateTime startAt;
  private LocalDateTime endAt;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public void setTitle(String title) throws Exception {
    if(title.length() > 50) {
      throw new Exception("O campo título, descrição ou prioridade excedeu o limite de caracteres");
    }
    this.title = title;
  }

  public void setDescription(String description) throws Exception {
    if(description.length() > 150) {
      throw new Exception("O campo descrição excedeu o limite de caracteres");
    }
    this.description = description;
  }

  public void setPriority(String priority) throws Exception {
    if(priority.length() > 5) {
      throw new Exception("O campo prioridade excedeu o limite de caracteres");
    }
    this.priority = priority;
  }

}


/*
 * Modelo da tabela de tarefas
 * 
 * Id
 * Usuário (Id do usuário)
 * Descrição
 * Título
 * Data de Início
 * Data de término
 * Prioridade (baixa, média, alta)
 * 
 */