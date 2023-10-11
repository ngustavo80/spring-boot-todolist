package br.com.gustavonascimento.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {
  
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

/*Caso queria definir um nome diferente para a coluna do banco de dados usamos a notation @Column
 * 
 * private String username; (Nesse caso a coluna vai se chamar username)
 * 
 * @Column(name = "usuário")
 * private String username; (Já com essa linha de código, a tabela de banco irá se chamar usuário)
 */