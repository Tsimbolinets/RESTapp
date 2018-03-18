<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>RESTful</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body>
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <td></td>
                <td><b>Name</b></td>
                <td><b>Surname</b></td>
                <td><b>Sex</b></td>
                <td><b>Birthday</b></td>
                <td><b>Identnumber</b></td>

            </tr>
            </thead>
            <c:forEach items="${contacts}" var="contact">
                <tr>
                    <td><input type="checkbox" name="toDelete[]" value="${contact.id}" id="checkbox_${contact.id}"/></td>
                    <td>${contact.name}</td>
                    <td>${contact.surname}</td>
                    <td>${contact.sex}</td>
                    <td>${contact.birthday}</td>
                    <td>${contact.identnumber}</td>
                </tr>
            </c:forEach>
        </table>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <button type="button" id="add_contact" class="btn btn-default navbar-btn">Add Contact</button>
                    <button type="button" id="change_contact" class="btn btn-default navbar-btn">Change Contact</button>
                    <button type="button" id="delete_contact" class="btn btn-default navbar-btn">Delete Contact</button>
                    <button type="button" id="orders" class="btn btn-default navbar-btn">Check Orders</button>
                    <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                        <input type="text" class="form-control" name="pattern" placeholder="Search">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </nav>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${allPages ne null}">
                    <c:forEach var="i" begin="1" end="${allPages}">
                        <li><a href="/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>
        <script>
            $('#add_contact').click(function(){
                window.location.href='/contact_add_page';
            });
            $('#change_contact').click(function(){
                window.location.href='/contact_change_page';
            });
            $('#orders').click(function(){
                window.location.href='/orders';
            });
            $('#delete_contact').click(function(){
                var data = { 'toDelete[]' : []};
                $(":checked").each(function() {
                    data['toDelete[]'].push($(this).val());
                });
                $.post("/contact/delete", data, function(data, status) {
                    window.location.reload();
                });
            })
        </script>;
    </body>
</html>