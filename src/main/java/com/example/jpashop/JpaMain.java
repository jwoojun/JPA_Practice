//package com.example.jpashop;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class JpaMain {
//    public static void main(String args[]) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory();
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            tx.commit();
//        } catch (Exception ex) {
//            tx.rollback();
//        } finally {
//            em.close();
//        }
//
//        emf.close();
//    }
//
//}
