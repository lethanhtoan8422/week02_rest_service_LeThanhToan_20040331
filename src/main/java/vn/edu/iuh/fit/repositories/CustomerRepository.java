package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.edu.iuh.fit.db.ConnectionDB;
import vn.edu.iuh.fit.models.Customer;
import java.util.List;

public class CustomerRepository {
    private final EntityManagerFactory entityManagerFactory;

    public CustomerRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public boolean createCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(customer);
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

    public boolean updateCustomer(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(customer);
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

    public boolean deleteCustomer(long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Customer customer = entityManager.find(Customer.class, customerId);
            entityManager.remove(customer);
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

    public Customer getCustomerById(long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;

        try {
            customer = entityManager.find(Customer.class, customerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return customer;
    }

    public List<Customer> getAllCustomers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = null;

        try {
            Query query = entityManager.createQuery("SELECT c FROM Customer c");
            customers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return customers;
    }
}