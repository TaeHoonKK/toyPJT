package toyPJT.toypjt_v100.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toyPJT.toypjt_v100.domain.Todo;
import toyPJT.toypjt_v100.service.TodoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Apicontroller {

    private final TodoService todoService;

    @GetMapping("/todo/selectAll")
    public List<Todo> findTodos(){
        Todo todo = new Todo();
        todo.setContent("sleep");
        todo.setCompleteYn("N");

        todoService.saveTodo(todo);

        return todoService.findTodos();
    }


}
