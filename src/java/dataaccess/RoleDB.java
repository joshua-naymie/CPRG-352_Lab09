package dataaccess;

import java.sql.*;
import java.util.*;
import javax.persistence.*;
import models.Role;

public class RoleDB
{
    public List<Role> getAll() throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        try
        {
            return entityManager.createNamedQuery("Role.findAll").getResultList();
        }
        finally
        {
            entityManager.close();
        }
    }

    public Role get(int roleID) throws Exception
    {
        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
        try
        {
            return entityManager.find(Role.class, roleID);
        }
        finally
        {
            entityManager.close();
        }
    }
    
//     public Role getByName(String roleName) throws Exception
//     {
//        EntityManager entityManager = DBUtil.getEMFactory().createEntityManager();
//        try
//        {
//            TypedQuery query = entityManager.createNamedQuery("Role.findByRoleName");
//            query.setParameter("roleName", roleName);
//            
//        }
//        finally
//        {
//            entityManager.close();
//        }
//    }
}