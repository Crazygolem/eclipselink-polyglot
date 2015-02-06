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
        em.flush(); // Forces generation of an ID and sets it in the entity
        long oneId = one.getId();

        List<Many> manies = Arrays.asList(
                new Many().setName("0").setOne(one),
                new Many().setName("1").setOne(one),
                new Many().setName("2").setOne(one)
        );

        one.setManies(manies); // Will be persisted on flush / commit due to the `cascade` attribute of the relation

        em.getTransaction().commit();
        em.close();

        return oneId;
    }

    private void readOne(long oneId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        One one = em.find(One.class, oneId);
        one.getManies().size(); // Force fetch lazy collection
        System.out.println("Read from the database:\n\t" + one);

        em.getTransaction().commit();
        em.close();
    }

    private void readManies(long oneId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        One one = em.find(One.class, oneId);

        List<Many> manies = em.createQuery(
                "select m from Many m",
                Many.class)
                .getResultList();
        for (Many m : manies)
            System.out.println(m + "\t-->\t" + m.getOne());

        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        Main main = new Main();
        long id = main.create();
        main.readOne(id);
        main.readManies(id);
    }
}
