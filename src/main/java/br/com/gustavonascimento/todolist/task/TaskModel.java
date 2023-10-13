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