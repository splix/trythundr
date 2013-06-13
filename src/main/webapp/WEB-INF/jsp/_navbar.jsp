<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="navbar">
  <div class="navbar-inner">
    <ul class="nav">
      <li <c:if test="${param.action eq 'list'}">class="active"</c:if>><a href="/">List all</a></li>
      <li <c:if test="${param.action eq 'add'}">class="active"</c:if>><a href="/add">Add new</a></li>
      <c:if test="${param.action eq 'show'}">
          <li class="active"><a href="#">Show Person</a></li>
      </c:if>
        <c:if test="${param.action eq 'edit'}">
            <li class="active"><a href="#">Edit Person</a></li>
        </c:if>
    </ul>
  </div>
</div>