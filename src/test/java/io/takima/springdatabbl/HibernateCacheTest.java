package io.takima.springdatabbl;

import io.takima.springdatabbl.model.Barman;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;


public class HibernateCacheTest {

    private static SessionFactory sessionFactory;

    @BeforeAll
    public static void setup() {
        // Configure and build the session factory
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testL2Cache() {
        Long barmanId = null;

        // Session 1: Store an entity and close the session
        Session session1 = sessionFactory.openSession();
        Transaction tx1 = session1.beginTransaction();
        Barman barman = TestSetupUtil.getJp();
        session1.persist(barman);
        barmanId = barman.getId();
        tx1.commit();
        session1.close();

        // Clear the cache to ensure the test is valid
        sessionFactory.getCache().evictAllRegions();

        // Session 2: Load the entity and check cache hit
        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();
        Barman cachedBarman = session2.get(Barman.class, barmanId);
        tx2.commit();
        session2.close();

        // Session 3: Load the entity again and check cache hit
        Session session3 = sessionFactory.openSession();
        Transaction tx3 = session3.beginTransaction();
        Barman cachedBarmanAgain = session3.get(Barman.class, barmanId);
        tx3.commit();
        session3.close();

        Assertions.assertNotNull(cachedBarman);
        Assertions.assertEquals("Hibernate Caching", cachedBarman.getName());
        Assertions.assertTrue(sessionFactory.getStatistics().getSecondLevelCacheHitCount() > 0);
    }
}