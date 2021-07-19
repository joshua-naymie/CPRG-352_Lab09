package services;

import dataaccess.*;
import java.util.*;
import models.*;

public class UserService
{
    private static final
    String EMPTY = "";
    
    /**
     * Retrieves an entry from the User data storage
     * @param email the Primary Key of the user to get
     * @return the User associated with the Primary Key provided
     * @throws Exception Thrown when entry cannot be retrieved
     */
    public User get(String email) throws Exception
    {
        UserDB userDB = new UserDB();
        return userDB.get(email);
    }
    
    //------------------------------
    
    /**
     * Retrieves all entries from the User data storage
     * @return All entries from the User table
     * @throws Exception Thrown when entries cannot be retrieved
     */
    public List<User> getAll() throws Exception
    {
        UserDB userDB = new UserDB();
        return userDB.getAll();
    }
    
    //------------------------------
    
    /**
     * Inserts a new User entry into the User data storage
     * @param email The Email address of the User
     * @param isActive Whether the User's account is active
     * @param firstName The First Name of the User
     * @param lastName The Last Name of the User
     * @param password The User's Password
     * @param role The User's Role
     * @throws Exception Thrown when entry cannot be inserted
     */
    public RequestStatus insert(String email, boolean isActive, String firstName,
                       String lastName, String password, String roleID)
    throws Exception
    {
        if(MiscUtil.hasEmptyValues(new String[] { email, firstName, lastName, password, roleID }))
        {
            return RequestStatus.EMPTY_INPUT;
        }
        
        if(!MiscUtil.stringIsNumber(roleID))
        {
            return RequestStatus.INVALID_ROLE;
        }
        System.out.println("ROLE: " + roleID);
        RoleService roleService = new RoleService();
        Role role = roleService.get(Integer.parseInt(roleID));
        
        if(role == null)
        {
            return RequestStatus.INVALID_ROLE;
        }
        
        
        UserDB userDB = new UserDB();
        
        userDB.insert(email, isActive, firstName, lastName, password, role.getRoleId());
        
        return RequestStatus.SUCCESS;
    }
    
    //---------------
    
    /**
     * Inserts a new User entry into the User data storage
     * @param user The User to insert
     * @throws Exception Thrown when entry cannot be inserted
     */
    public void insert(User user) throws Exception
    {
        insert(user.getEmail(), user.isActive(), user.getFirstName(),
                user.getLastName(), user.getPassword(),
                user.getRole().getRoleName());
    }
    
    //------------------------------
    
    /**
     * Deletes a User entry from the User data storage
     * @param email The Email address of the User to delete
     * @throws Exception Thrown when entry cannot be deleted
     */
    public void delete(String email) throws Exception
    {
        delete(get(email));
    }
    
    //---------------
    
    /**
     * Deletes a User entry from the User data storage
     * @param user The User to delete
     * @throws Exception Thrown when entry cannot be deleted
     */
    public void delete(User user) throws Exception
    {
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
    
    //------------------------------
    
    /**
     * Updates a User in the User data storage
     * @param email The Email address of the User
     * @param isActive Whether the User's account is active
     * @param firstName The First Name of the User
     * @param lastName The Last Name of the User
     * @param password The User's Password
     * @param role The User's Role
     * @throws Exception Thrown when entry cannot be updated
     */
    public RequestStatus update(String email, boolean isActive, String firstName,
                       String lastName, String password, String roleID)
    throws Exception
    {
        if(MiscUtil.hasEmptyValues(new String[] { email, firstName, lastName, password, roleID }))
        {
            return RequestStatus.EMPTY_INPUT;
        }
        
        if(!MiscUtil.stringIsNumber(roleID))
        {
            return RequestStatus.INVALID_ROLE;
        }
        
        RoleService roleService = new RoleService();
        Role role = roleService.get(Integer.parseInt(roleID));
        
        if(role == null)
        {
            return RequestStatus.INVALID_ROLE;
        }
        
        UserDB userDB = new UserDB();
        userDB.update(email, isActive, firstName, lastName, password, role.getRoleId());
        
        return RequestStatus.SUCCESS;
    }
    
    //---------------
    
    /**
     * Updates a User in the User data storage
     * @param user The User to update
     * @throws Exception Thrown when entry cannot be updated
     */
    public void update(User user) throws Exception
    {
        update(user.getEmail(), user.isActive(), user.getFirstName(),
                user.getLastName(), user.getPassword(),
                user.getRole().getRoleName());
    }
}