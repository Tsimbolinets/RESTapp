<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Change Contact</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
    <h3 align="center">Contact change page</h3>
        <div class="container">
            <form role="form" class="form-horizontal" action="/contact/change" method="post">
                        <h3>New contact</h3>
                <select class="selectpicker form-control form-group" name="id">
                    <c:forEach items="${contacts}" var="contact">
                        <option value="${contact.id}">${contact.name}</option>
                    </c:forEach>
                </select>
                        <input class="form-control form-group" type="text" name="surname" placeholder="${contact.surname}">
                <select class="selectpicker form-control form-group" name="sex">
                    <option>female</option>
                    <option>man</option>
                </select>
                        <input class="form-control form-group" type="text" name="birthday" placeholder="${contact.birthday}">
                        <input class="form-control form-group" type="text" name="identnumber" placeholder="${contact.identnumber}">
                    <input type="submit" class="btn btn-primary" value="Add">
            </form>
        </div>
    <h3 align="center"><a href="/" >Main page</a></h3>
        <script>
            $('.selectpicker').selectpicker();
        </script>
    </body>
</html>