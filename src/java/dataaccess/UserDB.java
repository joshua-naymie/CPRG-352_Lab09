package dataaccess;

import java.sql.*;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;
import services.RoleService;

public class UserDB {

    public List<User> getAll() throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        try
        {
            return entityManager.createNamedQuery("User.findAll").getResultList();
        }
        finally
        {
            entityManager.close();
        }
    }

    public User get(String email) throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        try
        {
            return entityManager.find(User.class, email);
        }
        finally
        {
            entityManager.close();
        }
    }

    public void insert(User user) throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        RoleService roleService = new RoleService();
        
        try
        {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        }
        catch(Exception exception)
        {
            transaction.rollback();
            throw exception;
        }
        finally
        {
            entityManager.close();
        }
    }

    public void insert(String email, boolean isActive, String firstName, String lastName, String password, int roleID) throws Exception
    {
        RoleService roleService = new RoleService();
        insert(new User(email, isActive, firstName, lastName, password, roleService.get(roleID)));
    }

    public void update(User user) throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try
        {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        }
        catch(Exception exception)
        {
            transaction.rollback();
            throw exception;
        }
        finally
        {
            entityManager.close();
        }
    }

    public void update(String email, boolean isActive, String firstName, String lastName, String password, int roleID) throws Exception
    {
        RoleService roleService = new RoleService();
        update(new User(email, isActive, firstName, lastName, password, roleService.get(roleID)));
    }

    public void delete(User user) throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try
        {
            transaction.begin();
            entityManager.remove(entityManager.merge(user));
            transaction.commit();
        }
        catch(Exception exception)
        {
            transaction.rollback();
            throw exception;
        }
        finally
        {
            entityManager.close();
        }
    }
}