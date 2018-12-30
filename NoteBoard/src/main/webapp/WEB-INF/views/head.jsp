<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Artem Eremin
  Date: 26.12.2018
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>${param.title}</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style><%@include file="/WEB-INF/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"%></style>
    <style><%@include file="/WEB-INF/styles/style.css"%></style>

    <intercept-url pattern="/resources/**" access="permitAll" />
</head>
<body>
