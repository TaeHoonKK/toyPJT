package toyPJT.toypjt_v100.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toyPJT.toypjt_v100.domain.Todo;

import java.util.List;

public interface TodoRepositoryImpl extends JpaRepository<Todo, Long> {
    List<Todo> findByMember_id(Long member_id);
}
