<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Artem Eremin
  Date: 25.12.2018
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="NoteBoard"/>
</jsp:include>

<form class="form-horizontal" action='<spring:url value="/loginAction"/>' method="post">
    <div class="form-group">
        <label for="inputEmail3" class="col-sm-2 control-label">Login</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="inputEmail3" placeholder="Login" name="login">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Sign in</button>
        </div>
    </div>
</form>

<jsp:include page="foot.jsp"/>
