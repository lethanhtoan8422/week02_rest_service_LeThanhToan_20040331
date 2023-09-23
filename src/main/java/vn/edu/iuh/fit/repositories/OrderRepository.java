package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.edu.iuh.fit.db.ConnectionDB;
import vn.edu.iuh.fit.models.Order;
import java.util.List;

public class OrderRepository {
    private final EntityManagerFactory entityManagerFactory;

    public OrderRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public boolean createOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public boolean updateOrder(Order order) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public boolean deleteOrder(long orderId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Order order = entityManager.find(Order.class, orderId);
            entityManager.remove(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public Order getOrderById(long orderId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Order order = null;

        try {
            order = entityManager.find(Order.class, orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return order;
    }

    public List<Order> getAllOrders() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Order> orders = null;

        try {
            Query query = entityManager.createQuery("SELECT o FROM Order o");
            orders = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return orders;
    }
}