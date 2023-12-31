package tech.ada.java.todolist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


public record TodoItemResponse(String titulo, String descricao, Boolean concluido, LocalDateTime dataHora, UUID id) {


}
