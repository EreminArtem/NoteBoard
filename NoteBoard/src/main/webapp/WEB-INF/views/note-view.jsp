<%--
  Created by IntelliJ IDEA.
  User: Artem Eremin
  Date: 08.01.2019
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="head.jsp">
    <jsp:param name="title" value="NoteBoard"/>
</jsp:include>


<c:forEach var="note" items="${notes}" varStatus="status">
    <jsp:include page="board-card.jsp">
        <jsp:param name="id" value="${notes.id}"/>
        <jsp:param name="boardName" value="${notes.dataId}"/>
    </jsp:include>
</c:forEach>

<jsp:include page="foot.jsp"/>


