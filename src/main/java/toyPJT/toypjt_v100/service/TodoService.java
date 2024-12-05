package toyPJT.toypjt_v100.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyPJT.toypjt_v100.domain.Todo;
import toyPJT.toypjt_v100.repository.TodoRepository;
import toyPJT.toypjt_v100.repository.TodoRepositoryImpl;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoRepositoryImpl todoRepositoryimpl;

    @Transactional
    public Long saveTodo(Todo todo){
        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional
    public void updateTodo(Long id, String complYn, String content){
        Todo todo = todoRepository.findOne(id);
        if(todo == null){
            throw new IllegalArgumentException("not found : " + id);
        }
        todo.setCompleteYn(complYn);
        todo.setContent(content);
    }

    @Transactional
    public void deleteTodo(Long id){
        todoRepository.delete(id);
    }

    public List<Todo> findByMember_id(Long member_id){
        return todoRepositoryimpl.findByMember_id(member_id);
    }

    public List<Todo> findTodos(){
        return todoRepository.findAll();
    }

    public Todo findOne(Long id){
        return todoRepository.findOne(id);
    }
}
