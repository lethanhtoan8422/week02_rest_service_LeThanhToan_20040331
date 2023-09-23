package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import vn.edu.iuh.fit.db.ConnectionDB;
import vn.edu.iuh.fit.models.Product;
import java.util.List;

public class ProductRepository {
    private final EntityManagerFactory entityManagerFactory;

    public ProductRepository() {
        entityManagerFactory = ConnectionDB.getInstance().getEntityManagerFactory();
    }

    public boolean createProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(product);
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

    public boolean updateProduct(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(product);
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

    public boolean deleteProduct(long productId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Product product = entityManager.find(Product.class, productId);
            entityManager.remove(product);
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

    public Product getProductById(long productId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Product product = null;

        try {
            product = entityManager.find(Product.class, productId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return product;
    }

    public List<Product> getAllProducts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Product> products = null;

        try {
            Query query = entityManager.createQuery("SELECT p FROM Product p");
            products = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return products;
    }
}