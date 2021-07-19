<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" href="styles/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Lora&family=Work+Sans:wght@200&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/7eb48072cc.js" crossorigin="anonymous"></script>
        <title>Users Page</title>
    </head>
    <body>
        <form method="POST" action="">

            <div id="addUserDiv">
                <form method="POST">
                    <h1>Add User</h1>
                <input type="text" name="user_email" value="${email_attribute}" placeholder="Email"><br>
                <input type="text" name="user_firstname" value="${firstname_attribute}" placeholder="First Name"><br>
                <input type="text" name="user_lastname" value="${lastname_attribute}" placeholder="Last Name"><br>
                <input type="text" name="user_password" value="${password_attribute}" placeholder="Password"><br>
                <select name="add_user_roles" id="add_userRoles">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.getRoleId()}">${role.getRoleNameCapitalized()}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="Add" name="action">
                <p>${userMessage}</p>
                </form>
            </div>

            <div id="manageUserDiv">
                <center><h1>Manage Users</h1></center>
                <table>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>

                    <c:forEach items="${users}" var="user">
                        <tr>
                            <form method="POST">
                                <input name="email" type="hidden" value="${user.email}">
                                <td name="tableEmail" value="${user.email}">${user.email}</td>
                                <td name="tableFirstName" value="${user.firstName}">${user.firstName}</td>
                                <td name="tableLastName" value="${user.lastName}">${user.lastName}</td>
                                <td name="action" value="edit"><button type="submit" value="Edit" name="action"><i class="fas fa-pen"></i></button></td>
                                <td name="action" value="delete"><button type="submit" value="Delete" name="action"><i class="fas fa-times"></i></button></td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div id="editUserDiv">
                <h1>Edit User</h1>
                <form method="POST">
                    <input class="emailInput" type="text" name="saveuser_email" value="${editUser.getEmail()}" readonly="readonly" tabindex="-1"><br>
                    <input type="text" name="saveuser_firstname" value="${editUser.getFirstName()}"><br>
                    <input type="text" name="saveuser_lastname" value="${editUser.getLastName()}"><br>
                    <input type="text" name="saveuser_password" value="${editUser.getPassword()}"><br>
                    <select name="edit_user_roles" id="editUserRoles">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.getRoleId()}" <c:if test="${editUser.getRole().getRoleId() == role.getRoleId()}">selected="selected"</c:if>>${role.getRoleNameCapitalized()}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <input type="submit" value="Save" name="action"><br>
                    <input type="submit" value="Cancel" name="action">
                </form>
            </div>
        </form>
    </body>
</html>