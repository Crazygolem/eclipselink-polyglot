import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("database-pu");

    private long create() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        One one = new One().setName(new Date().toString());
        em.persist(one);

        List<Many> manies = Arrays.asList(
                new Many().setName("0").setOne(one),
                new Many().setName("1").setOne(one),
                new Many().setName("2").setOne(one)
        );

        one.setManies(manies);
        for (Many m : manies)
            em.persist(m);

        em.getTransaction().commit();
        long oneId = one.getId();
        em.close();
        return oneId;
    }

    private void read(long oneId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        One one = em.find(One.class, oneId);
        one.getManies().size(); // Force fetch lazy collection
        System.out.println(one);

        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        long id = main.create();
        main.read(id);
    }
}
