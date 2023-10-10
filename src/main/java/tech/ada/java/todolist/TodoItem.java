package tech.ada.java.todolist;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class TodoItem {

    private String titulo;
    private String descricao;
    private Boolean concluido;
    private LocalDateTime dataHora;
    private UUID id = UUID.randomUUID();

    public TodoItem() {
        this.id = UUID.randomUUID();
    }

    public TodoItem(String titulo, String descricao, LocalDateTime dataHora) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.concluido = false;
    }

    public TodoItem(TodoItemRequest request) {
        this();
        this.titulo = request.getTitulo();
        this.descricao = request.getDescricao();
        this.dataHora = request.getDataHora();
        this.concluido = request.getConcluido();
    }

    public TodoItemResponse toResponse() {
        TodoItemResponse response = new TodoItemResponse(this.titulo, this.descricao, this.concluido, this.dataHora, this.id);
        return response;
    }


    //getters
    //setters
    //construtores:
    //toString

}
