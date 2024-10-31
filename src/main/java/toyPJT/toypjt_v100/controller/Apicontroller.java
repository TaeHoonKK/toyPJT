package toyPJT.toypjt_v100.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyPJT.toypjt_v100.domain.Todo;
import toyPJT.toypjt_v100.domain.dto.todoDto;
import toyPJT.toypjt_v100.service.TodoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class Apicontroller {

    private final TodoService todoService;

    @GetMapping("/todo/selectAll")
    public List<Todo> findTodos(){
        return todoService.findTodos();
    }

    @PostMapping("/todo/addTodo")
    public ResponseEntity<Todo> addTodo(todoDto tododto){
        Todo todo = new Todo();
        todo.setContent(tododto.getContent());
        todo.setCompleteYn(tododto.getCompleteYn());
        todoService.saveTodo(todo);

        return ResponseEntity.ok()
                .body(todo);
    }

    @PutMapping("/todo/editTodo")
    public ResponseEntity<Map<String, Object>> editTodo(todoDto todoDto){
        try {
            todoService.updateTodo(todoDto.getTodoId(), todoDto.getCompleteYn(), todoDto.getContent());
        }catch (Exception ex){
            Map<String, Object> result = new HashMap<>();
            result.put("resultMessage", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/todo/deleteTodo/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable long id){
        Todo todoServiceOne = todoService.findOne(id);
        todoService.deleteTodo(id);

        return ResponseEntity.ok().body(todoServiceOne);
    }
}
