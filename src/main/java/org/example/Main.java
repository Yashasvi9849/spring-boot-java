package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;




public class Main {
    public static void main(String[] args) {

        Alien a1 = new Alien();
        a1.setAid(103);
        a1.setAname("Srinij");
        a1.setTech("Python");

        Configuration config = new Configuration();
        config.addAnnotatedClass(org.example.Alien.class);
        config.configure();
        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(a1);
        transaction.commit();
        session.close();
        factory.close();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }
}