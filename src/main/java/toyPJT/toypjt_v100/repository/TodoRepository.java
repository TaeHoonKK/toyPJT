package toyPJT.toypjt_v100.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyPJT.toypjt_v100.domain.Todo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    // 저장 또는 업데이트 (ID 유무에 따른 persist/merge 사용)
    public void save(Todo todo){
        if(todo.getId() == null){
            em.persist(todo);
        }else{
            em.merge(todo);
        }
    }

    // 삭제 함수 추가
    public void delete(Long id) {
        Todo todo = em.find(Todo.class, id); // ID로 엔티티를 조회
        if (todo != null) {
            em.remove(todo); // 엔티티가 존재하면 삭제
        }
    }

    public List<Todo> findAll(){
        return em.createQuery("select i from Todo i", Todo.class)
                .getResultList();
    }

    public Todo findOne(Long id){
        return em.find(Todo.class, id);
    }

}
