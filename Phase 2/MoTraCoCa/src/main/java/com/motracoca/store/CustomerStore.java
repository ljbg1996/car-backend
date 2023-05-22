package com.motracoca.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import com.motracoca.entities.CustomerEntity;

public class CustomerStore {
    private EntityManagerFactory entityManagerFactory;

    public CustomerStore() {
        entityManagerFactory = Persistence.createEntityManagerFactory("your_persistence_unit_name");
    }

    public void createCustomer(CustomerEntity customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public CustomerEntity getCustomerById(long customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CustomerEntity customer = null;

        try {
            customer = entityManager.find(CustomerEntity.class, customerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return customer;
    }

    public void updateCustomer(CustomerEntity customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deleteCustomer(CustomerEntity customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            CustomerEntity managedCustomer = entityManager.find(CustomerEntity.class, customer.getId());
            if (managedCustomer != null)
                entityManager.remove(managedCustomer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive())
                transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void close() {
        entityManagerFactory.close();
    }
}
