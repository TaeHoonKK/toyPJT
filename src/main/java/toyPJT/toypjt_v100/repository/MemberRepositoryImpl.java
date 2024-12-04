package toyPJT.toypjt_v100.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyPJT.toypjt_v100.domain.Member;

@Repository
public interface MemberRepositoryImpl extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
