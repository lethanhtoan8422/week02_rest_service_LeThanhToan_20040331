package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.edu.iuh.fit.db.ConnectionDB;
import vn.edu.iuh.fit.models.OrderDetail;
import java.util.List;

public class OrderDetailRepository {
    private final EntityManagerFactory entityManagerFactory;

    public OrderDetailRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public boolean createOrderDetail(OrderDetail orderDetail) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(orderDetail);
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

    public boolean updateOrderDetail(OrderDetail orderDetail) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(orderDetail);
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

    public boolean deleteOrderDetail(long orderDetailId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            OrderDetail orderDetail = entityManager.find(OrderDetail.class, orderDetailId);
            entityManager.remove(orderDetail);
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

    public OrderDetail getOrderDetailById(long orderDetailId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        OrderDetail orderDetail = null;

        try {
            orderDetail = entityManager.find(OrderDetail.class, orderDetailId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return orderDetail;
    }

    public List<OrderDetail> getAllOrderDetails() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<OrderDetail> orderDetails = null;

        try {
            Query query = entityManager.createQuery("SELECT od FROM OrderDetail od");
            orderDetails = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return orderDetails;
    }
}