<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" lang="en-us"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List all</title>
        <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="_navbar.jsp">
                <jsp:param name="action" value="list"/>
            </jsp:include>
            <h1>Persons</h1>
            <div class="btn-toolbar">
                <a href="/add" class="btn btn-primary">Add a Person</a>
            </div>
            <h2>Current:</h2>
            <table class="table">
                <c:forEach items="${current}" var="person">
                    <tr>
                        <td><c:out value="${person.name}"/></td>
                        <td>
                            <c:if test="${not empty person.address}">
                                <c:out value="${person.address.address}"/>
                                <c:if test="${not empty person.address.coord}">
                                    (${person.address.coord.latitude}, ${person.address.coord.longitude})
                                </c:if>
                            </c:if>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a href="/show/${person.id}" class="btn">Show</a>
                                <a href="/edit/${person.id}" class="btn">Edit</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty current}">
                    <tr>
                        <td style="text-align: center" colspan="3">
                            <div class="alert">
                            Empty.
                            </div>
                            <a href="/add" class="btn">Add a Person</a></td>
                    </tr>
                </c:if>
            </table>
        </div>
    </body>
</html>