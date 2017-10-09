<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>RESUful</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>

    <body>
    <h3 align="center">Order page</h3>
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <td></td>
                <td><b>Contact</b></td>
                <td><b>Date</b></td>
                <td><b>Status</b></td>
                <td><b>Summ</b></td>
                <td><b>Currency</b></td>

            </tr>
            </thead>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td><input type="checkbox" name="toDelete[]" value="${order.id}" id="checkbox_${order.id}"/></td>
                    <td>${order.contact.name}</td>
                    <td>${order.date}</td>
                    <td>${order.status}</td>
                    <td>${order.summ}</td>
                    <td>${order.currency}</td>
                </tr>
            </c:forEach>
        </table>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <button type="button" id="add_order" class="btn btn-default navbar-btn">Add Order</button>
                    <form class="navbar-form navbar-left" role="search" action="/searchcontactorders" method="post">
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
                        <li><a href="/orders/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>
    <h3 align="center"><a href="/" >Main page</a></h3>
        <script>

        $('#add_order').click(function(){
                window.location.href='/order_add_page';
            });
        </script>
    </body>
</html>