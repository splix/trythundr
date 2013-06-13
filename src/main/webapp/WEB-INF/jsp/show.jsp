<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><c:out value="${person.name}"/></title>
    <meta charset="utf-8" lang="en-us"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/css/app.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="_navbar.jsp">
        <jsp:param name="action" value="show"/>
    </jsp:include>
    <h1><c:out value="${person.name}"/></h1>
    <c:if test="${not empty person.address}">
        <div class="row">
            <div class="span2">Address:</div>
            <div class="span6">
                <c:out value="${person.address.address}"/>
                <c:if test="${not empty person.address.coord}">
                    (${person.address.coord.latitude}, ${person.address.coord.longitude})
                </c:if>
            </div>
            <div class="span1">
                <a href="/edit/${person.id}" class="btn">Edit</a>
            </div>
        </div>
        <c:if test="${not empty person.address.coord}">
        <hr/>
        <div class="row">
            <div id="map-canvas" style="height: 400px"></div>
        </div>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script>
            var map;
            function initialize() {
                var coord = new google.maps.LatLng(${person.address.coord.latitude}, ${person.address.coord.longitude});
                var mapOptions = {
                    zoom: 14,
                    center: coord,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                map = new google.maps.Map(document.getElementById('map-canvas'),
                        mapOptions);
                marker = new google.maps.Marker({
                    map: map,
                    draggable: true,
                    animation: google.maps.Animation.DROP,
                    position: coord
                });
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
        </c:if>
    </c:if>
</div>
</body>
</html>