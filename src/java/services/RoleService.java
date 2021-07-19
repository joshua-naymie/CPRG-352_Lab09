package services;

import models.*;
import dataaccess.*;
import java.util.*;

public class RoleService
{
    /**
     * Gets all roles from the Role data storage
     * @return All roles
     * @throws Exception Thrown when roles cannot be retrieved
     */
    public List<Role> getAll() throws Exception
    {
        RoleDB roleDB = new RoleDB();
        return roleDB.getAll();
    }
    
    public Role get(int roleID) throws Exception
    {
        RoleDB roleDB = new RoleDB();
        return roleDB.get(roleID);
    }
    
//    public Role getByName(String roleName) throws Exception
//    {
//        RoleDB roleDB = new RoleDB();
//        return roleDB.getByName(roleName);
//    }
}