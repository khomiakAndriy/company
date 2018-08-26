<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Employees list</title>
</head>
<body>


<div style="align-content: center">
    <table>
        <thead style="background-color: lightgrey">
        <tr>
            <th>View</th>
            <th>Edit</th>
            <th>Employee id</th>
            <th>Employee name</th>
            <th>Employee active</th>
            <th>Employee department</th>
            <th>Delete</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="employee" items="${employees}">
            <c:url var="viewLink" value="/showEmployeeInfo">
                <c:param name="employeeId" value="${employee.id}"/>
            </c:url>
            <c:url var="editLink" value="/edit">
                <c:param name="employeeId" value="${employee.id}"/>
            </c:url>
            <c:url var="deleteLink" value="/delete">
                <c:param name="employeeId" value="${employee.id}"/>
            </c:url>
            <tr>
                <td><a href="${viewLink}">View</a></td>
                <td><a href="${editLink}">Edit</a></td>
                <td> ${employee.id}</td>
                <td> ${employee.name}</td>
                <td> ${employee.active}</td>
                <td> ${employee.department.name}</td>
                <td><a href="${deleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<c:forEach var="i" begin="1" end="10">
    <c:url var="pageNumberLink" value="/showPageNumber">
        <c:param name="pageNumber" value="${i-1}"/>
    </c:url>
    <a href="${pageNumberLink}">${i}</a>
</c:forEach>

<br/>
Edit form:
<form:form action="${pageContext.request.contextPath}/saveEmployee" modelAttribute="employee" method="post">
    <form:hidden path="id"/>
    <label>Employee name:</label>
    <form:input path="name" disabled="${disableFields}"/>
    <form:errors path="name" cssStyle="color: red"/>
    <br/>

    <label>Employee active:</label>
    <form:checkbox path="active" disabled="${disableFields}"/>
    <br/>

    <label>Employee department:</label>
    <form:select path="department.id" disabled="${disableFields}">
        <form:options items="${departments}" itemValue="id"  />
    </form:select>
    <br/>

    <input type="submit" value="Save"/>
    <input type="reset" value="Cancel"/>
</form:form>

<br/>
<form action="${pageContext.request.contextPath}/search" method="get">
    <input type="text" name="searchText" placeholder="Search here..." required>
    <button type="submit">Search</button>
</form>

</body>
</html>