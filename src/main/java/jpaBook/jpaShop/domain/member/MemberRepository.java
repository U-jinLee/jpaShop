package jpaBook.jpaShop.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager entityManager;

    public Long save(Member member) {
        entityManager.persist(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        /*단건 조회 첫 파라미터에 타입, 두번 째에 pk 값을 넣어주면 된다.*/
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll() {
                                        /*table이 아닌 객체에서 찾는다는 점*/
        return entityManager.createQuery("select m from Meber m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
