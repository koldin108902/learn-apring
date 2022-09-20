import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);
            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setName("회원1");
            member1.setAge(20);
            member1.changeTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("회원2");
            member2.setAge(20);
            member2.changeTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setName("회원3");
            member3.setAge(20);
            member3.changeTeam(teamB);
            em.persist(member3);


            em.flush();
            em.clear();

            List resultList = em.createNamedQuery("findByUserName")
                    .setParameter("username", "회원1")
                    .getResultList();

            for (Object o : resultList) {
                System.out.println("o = " + o);
            }

            tx.commit();
        } catch (NonUniqueResultException e) {

            System.out.println("single result에 list가 발견됨");
            System.out.println("e = " + e);
        } catch (Exception e) {
            System.out.println("[error] with:"+e);
        } finally {
            em.close();
        }

        emf.close();
    }
}
