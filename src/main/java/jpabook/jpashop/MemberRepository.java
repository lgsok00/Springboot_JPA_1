package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

  @PersistenceContext // EntityManager 주입
  private EntityManager em;

  public Long save(Member member) {
    em.persist(member);
    return member.getId();  // 커맨드와 쿼리를 분리
  }

  public Member find(Long id) {
    return em.find(Member.class, id);
  }
}
