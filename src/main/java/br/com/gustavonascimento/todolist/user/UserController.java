package br.com.gustavonascimento.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel) {
        System.out.println(userModel.getName());
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