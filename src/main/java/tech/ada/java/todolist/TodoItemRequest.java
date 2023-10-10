package tech.ada.java.todolist;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class TodoItemRequest {


    private String titulo;
    private String descricao;
    private Boolean concluido;
    private LocalDateTime dataHora;

    public TodoItemRequest(String titulo, String descricao, LocalDateTime dataHora, Boolean concluido) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.concluido = concluido;
    }
}
