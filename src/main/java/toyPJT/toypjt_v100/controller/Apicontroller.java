package toyPJT.toypjt_v100.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyPJT.toypjt_v100.domain.Member;
import toyPJT.toypjt_v100.domain.Todo;
import toyPJT.toypjt_v100.domain.dto.todoDto;
import toyPJT.toypjt_v100.service.MemberService;
import toyPJT.toypjt_v100.service.TodoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class Apicontroller {

    private final TodoService todoService;
    private final MemberService memberService;

    @GetMapping("/todo/selectAll")
    public ResponseEntity<List<Todo>>  findTodos(){
        Member member = memberService.getLoggedInMember();
        return ResponseEntity.ok().body(todoService.findByMember_id(member.getId()));
    }

    @PostMapping("/todo/addTodo")
    public ResponseEntity<Todo> addTodo(@RequestBody todoDto tododto){
        Todo todo = new Todo();
        todo.setContent(tododto.getContent());
        todo.setCompleteYn(tododto.getCompleteYn());
        todo.setCreatedDate(tododto.getCreatedDate());

        Member member = memberService.getLoggedInMember();
        todo.setMember(member);

        todoService.saveTodo(todo);

        return ResponseEntity.ok()
                .body(todo);
    }

    @PutMapping("/todo/editTodo")
    public ResponseEntity<Map<String, Object>> editTodo(@RequestBody todoDto todoDto){
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
