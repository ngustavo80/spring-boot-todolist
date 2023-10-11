package br.com.gustavonascimento.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public UserModel create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null) {
            System.out.println("Usuário já existe");
            return null;
        }

        var userCreated = this.userRepository.save(userModel);
        return userCreated;
    }
}

/*
 * Tipos de dados
 * String
 * Integer (int)
 * Double (números casas decimais)
 * Float (também números com casas decimais, só diferencia a quantidade de números aceitos)
 * char (caracteres)
 * Date (data)
 * void (quando queremos que o método nãop tenha nenhum retorno)
 * Etc
 */

/*
 * Modificadores
 * public
 * private
 * protected
 */