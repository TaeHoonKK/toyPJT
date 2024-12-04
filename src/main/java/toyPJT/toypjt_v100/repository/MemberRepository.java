package toyPJT.toypjt_v100.repository;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyPJT.toypjt_v100.domain.Member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 저장 또는 업데이트 (ID 유무에 따른 persist/merge 사용)
    public void save(Member member){
        if(member.getId() == null){
            em.persist(member);
        }else{
            em.merge(member);
        }
    }

    // 삭제 함수 추가
    public void delete(Long id) {
        Member member = em.find(Member.class, id); // ID로 엔티티를 조회
        if (member != null) {
            em.remove(member); // 엔티티가 존재하면 삭제
        }
    }

    public List<Member> findAll(){
        return em.createQuery("select i from Member i", Member.class)
                .getResultList();
    }

}
