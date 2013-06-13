<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" lang="en-us"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <jsp:include page="_navbar.jsp">
                <jsp:param name="action" value="add"/>
            </jsp:include>
            <h1>Add a person</h1>
            <c:if test="${not empty error}">
                <div class="alert alert-block alert-error">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <form action="/add" method="POST" class="form-horizontal">
                <div class="control-group">
                    <label class="control-label">Name</label>
                    <div class="controls">
                        <input type="text" name="name"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">Address</label>
                    <div class="controls">
                        <input type="text" name="address" class="input-xxlarge"/>
                    </div>
                </div>
                <div class="form-actions">
                    <input type="submit" value="Save" class="btn btn-primary"/>
                </div>
            </form>
        </div>
    </body>
</html>