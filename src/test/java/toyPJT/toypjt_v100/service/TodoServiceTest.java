package toyPJT.toypjt_v100.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import toyPJT.toypjt_v100.domain.Todo;
import toyPJT.toypjt_v100.repository.TodoRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired TodoService todoService;
    @Autowired TodoRepository todoRepository;

    @Test
    @DisplayName("투두 저장")
    void saveTodo() {
        //given
        Todo todo = new Todo();
        todo.setContent("Sleep");
        todo.setCompleteYn("N");

        //when
        Long todoId = todoService.saveTodo(todo);
        Todo findTodo = todoService.findOne(todoId);

        //then
        assertEquals(todoId, findTodo.getId());
        assertEquals(findTodo.getContent(), "Sleep");
        assertEquals(findTodo.getCompleteYn(), "N");
    }

    @Test
    @DisplayName("투두 업데이트")
    void updateTodo() {
        //given
        Todo todo = new Todo();
        todo.setContent("sleep");
        todo.setCompleteYn("N");

        //when
        Long todoId = todoService.saveTodo(todo);
        Todo findone = todoService.findOne(todoId);

        todoService.updateTodo(findone.getId(), "Y", findone.getContent());
        Todo findtwo = todoService.findOne(findone.getId());

        //then
        assertEquals(findtwo.getCompleteYn(),"Y");
        assertEquals(findtwo.getContent(), findone.getContent());
    }

    @Test
    @DisplayName("투두 삭제")
    void deleteTodo() {
        //given
        Todo todo1 = new Todo();
        todo1.setContent("sleep");
        todo1.setCompleteYn("N");

        Todo todo2 = new Todo();
        todo2.setContent("sleep");
        todo2.setCompleteYn("N");

        //when
        Long todoId1 = todoService.saveTodo(todo1);
        Long todoId2 = todoService.saveTodo(todo2);

        todoService.deleteTodo(todoId1);

        Todo findTodo = todoService.findOne(todoId1);
        Todo findTodo2 = todoService.findOne(todoId2);

        //then
        assertEquals(findTodo, null);
        assertEquals(findTodo2.getId(), todoId2);
    }

}