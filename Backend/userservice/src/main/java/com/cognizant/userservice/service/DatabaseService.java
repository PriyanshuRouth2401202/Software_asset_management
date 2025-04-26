package com.cognizant.userservice.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Service;
import com.cognizant.userservice.model.User;

@Service
public class DatabaseService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void ensureTableExists(Class<?> entityClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        try {
            // Try querying the entity to check if it exists in the database
            entityManager.createQuery("SELECT COUNT(u) FROM " + entityClass.getSimpleName() + " u").getSingleResult();
            System.out.println("✅ Table for entity '" + entityClass.getSimpleName() + "' exists.");
        } catch (Exception e) {
            // If an exception occurs, assume the table doesn't exist and create it
            entityManager.persist(entityClass.getDeclaredConstructor().newInstance());
            System.out.println("✅ Table for entity '" + entityClass.getSimpleName() + "' created.");
        }
    }
}
