package dataaccess;

import java.sql.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil
{
    private static final
    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Lab9PU");
    
    public static EntityManagerFactory getEMFactory()
    {
        return emFactory;
    }
}