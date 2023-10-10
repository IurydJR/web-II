package tech.ada.java.todolist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo-itens") //http://localhost:8080/todo-itens/
public class TodoRestControler {

    //cadastrar
    //listar todos
    //detalhar por id
    //substituir
    //marcar como concluido
    //excluir

    private final List<TodoItem> todoItemList = new ArrayList <>();

    public TodoRestControler() {
        this.todoItemList.addAll(List.of(
            new TodoItem("Tarefa aula 1", "Completar nossa API", LocalDateTime.of(2023, 10, 15, 19, 0)),
            new TodoItem("Ler um livro - técnico", "Ler o livro Código Limpo", LocalDateTime.of(2023, 10, 31, 19, 0)),
            new TodoItem("Ler um livro - cotidiano", "O poder do hábito", LocalDateTime.of(2023, 10, 31, 19, 0)),
            new TodoItem("Ver um filme", "Matrix", LocalDateTime.of(2023, 10, 17, 22, 0)),
            new TodoItem("Ver um filme", "A Origem", LocalDateTime.of(2023, 10, 25, 19, 0)),
            new TodoItem("Finalizar o projeto do projeto", "Nosso projeto de netflix", LocalDateTime.of(2023, 10, 18, 19, 0)),
            new TodoItem("Ir ao café", "Tomar um cafezinho e apreciar a rua", LocalDateTime.of(2023, 10, 18, 9, 0)),
            new TodoItem("Dar uma volta", "Andar pela cidade como um turista", LocalDateTime.of(2023, 10, 19, 10, 0)),
            new TodoItem("Levar o cachorro pra passear", "Viva os doguinhos!", LocalDateTime.of(2023, 10, 16, 10, 0)),
            new TodoItem("Pizza e cerveja", "Completar nossa API", LocalDateTime.of(2023, 10, 18, 19, 0))
        ));
    }

    @GetMapping //GET http://localhost:8080/todo-itens/
    public List<TodoItem> listarTodos() {
        return this.todoItemList;
    }

    @GetMapping("/{id}") // path variable
    public TodoItemResponse getPorId(@PathVariable UUID id) {
        //GET http://localhost:8080/todo-itens/
        return this.todoItemList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(item -> item.toResponse())
                .orElseThrow(() -> new NoSuchElementException("Item não encontrado"));
    }

    @GetMapping(params = {"descricao"}) //query parameters
    public List<TodoItemResponse> buscarPorDescricao(@RequestParam String descricao) {
        //GET http://localhost:8080/todo-itens?descricaao=
        return this.todoItemList.stream()
                .filter(item -> item.getDescricao().contains(descricao))
                .map(TodoItem::toResponse)
                .collect(Collectors.toList());
    }
    @PostMapping //GET http://localhost:8080/todo-itens/id
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItemResponse cadastrar(@RequestBody TodoItemRequest request) {
        TodoItem novoItem = new TodoItem(request);
        this.todoItemList.add(novoItem);
        return novoItem.toResponse();
    }
}
