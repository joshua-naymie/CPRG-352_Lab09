package servlets;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.*;
import services.*;

public class UserServlet extends HttpServlet {
    
    private static final
    String EMPTY_FIELDS_MESSAGE = "Please enter all User fields.",
           INVALID_ROLE_MESSAGE = "Role is invalid.",
           USERS_JSP_DIR = "/WEB-INF/users.jsp",
           USER_REDIR = "user",
           USERS_ATT = "users",
           ROLES_ATT = "roles",
           USER_MSG_ATT = "userMessage",
           EMAIL_PARAM = "email";
    
    //------------------------------
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();
        RoleService roleService = new RoleService();

        try {
            HttpSession session = request.getSession();
            
            // using get all method from user services
            request.setAttribute(USERS_ATT, userService.getAll());
            request.setAttribute(ROLES_ATT, roleService.getAll());
            
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            System.err.println("Error Occured retrieving user data");
        }

        getServletContext().getRequestDispatcher(USERS_JSP_DIR).forward(request, response);
    }
    
    //------------------------------

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // get an action from the JSP
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "Add":
                    add(request, response);
                    break;
                    
                case "Edit":
                    edit(request, response);
                    break;
                    
                case "Delete":
                    UserService us = new UserService();
                    us.delete(request.getParameter(EMAIL_PARAM));
                    response.sendRedirect(USER_REDIR);
                    break;
                    
                case "Save":
                    save(request, response);
                    break;

                case "Cancel":
                    response.sendRedirect(USER_REDIR);
                    break;
            }
        } catch (Exception e) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            System.err.println("Error Occured carrying out action:" + action);
        }
    }
    
    //------------------------------

    private void add(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserService userService = new UserService();
            RoleService roleService = new RoleService();

            switch (userService.insert(request.getParameter("user_email"),
                                       true,
                                       request.getParameter("user_firstname"),
                                       request.getParameter("user_lastname"),
                                       request.getParameter("user_password"),
                                       request.getParameter("add_user_roles"))) {
                
                case SUCCESS:
                    response.sendRedirect(USER_REDIR);
                    break;

                case EMPTY_INPUT:
                    request.setAttribute(USER_MSG_ATT, EMPTY_FIELDS_MESSAGE);
                    break;

                case INVALID_ROLE:
                    request.setAttribute(USER_MSG_ATT, INVALID_ROLE_MESSAGE);
                    break;
            }
            
            request.setAttribute(USERS_ATT, userService.getAll());
            request.setAttribute(ROLES_ATT, roleService.getAll());
            
            getServletContext().getRequestDispatcher(USERS_JSP_DIR).forward(request, response);
            return;
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //------------------------------

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            UserService userService = new UserService();
            RoleService roleService = new RoleService();

            User editUser = userService.get(request.getParameter(EMAIL_PARAM));

            try {
                request.setAttribute(USERS_ATT, userService.getAll());
                request.setAttribute(ROLES_ATT, roleService.getAll());
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
                System.err.println("Error Occured retrieving user data");
                throw new Exception();
            }
            
            request.setAttribute("editUser", editUser);

            try {
                getServletContext().getRequestDispatcher(USERS_JSP_DIR).forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //------------------------------

    private void save(HttpServletRequest request, HttpServletResponse response) {

        try {
            UserService userService = new UserService();
            RoleService roleService = new RoleService();
            
            switch(userService.update(request.getParameter("saveuser_email"),
                                      true,
                                      request.getParameter("saveuser_firstname"),
                                      request.getParameter("saveuser_lastname"),
                                      request.getParameter("saveuser_password"),
                                      request.getParameter("edit_user_roles")))
            {
                case SUCCESS:
                    response.sendRedirect(USER_REDIR);
                    break;
                    
                case EMPTY_INPUT:
                    request.setAttribute(USER_MSG_ATT, EMPTY_FIELDS_MESSAGE);
                    break;

                case INVALID_ROLE:
                    request.setAttribute(USER_MSG_ATT, INVALID_ROLE_MESSAGE);
                    break;
            }
            
            request.setAttribute(USERS_ATT, userService.getAll());
            request.setAttribute(ROLES_ATT, roleService.getAll());
            
            getServletContext().getRequestDispatcher(USERS_JSP_DIR).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}