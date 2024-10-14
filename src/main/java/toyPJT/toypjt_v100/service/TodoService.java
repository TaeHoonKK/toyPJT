package toyPJT.toypjt_v100.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyPJT.toypjt_v100.domain.Todo;
import toyPJT.toypjt_v100.repository.TodoRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public void saveTodo(Todo todo){
        todoRepository.save(todo);
    }

    @Transactional
    public void updateTodo(Long id, String complYn, String content){
        Todo todo = todoRepository.findOne(id);
        todo.setCompleteYn(complYn);
        todo.setContent(content);
    }

    @Transactional
    public void deleteTodo(Long id){
        todoRepository.delete(id);
    }

    public List<Todo> findTodos(){
        return todoRepository.findAll();
    }

    public Todo findOne(Long id){
        return todoRepository.findOne(id);
    }
}
