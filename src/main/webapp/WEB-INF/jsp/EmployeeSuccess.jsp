<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
table,tr,th,td{border:1px solid black;}
</style>
<script>
function deleteEmployee(id){
	if(confirm("do you want to delete employee?")){
		var url="deleteEmp/" + id;
		window.location.href=url;
	}
}

</script>
<title>Insert title here</title>
</head>
<body>
<h1>Employee Details</h1>
<table>
<tr>
    <th>Employee Id</th>
    <th>Name</th>
    <th>Age</th>
    <th>Address</th>
    <th>Gender</th>
    <th>Date of Join</th>
    <th>Email</th>
    <th>Insert Action</th>
    <th>Delete Action</th>
</tr>
<c:forEach items="${employeeList}" var="empl">
<tr>
    <td>${empl.employeeid}</td>
    <td>${empl.name}</td>
    <td>${empl.age}</td>
    <td>${empl.address}</td>
    <td>${empl.gender}</td>
    <td>${empl.doj}</td>
    <td>${empl.email}</td>
   <td><a href="<c:url value='/fetchById/${ empl.employeeid}'/>">Edit</</a></td>
    
    <td><a href="<c:url value='/deleteEmp/${ empl.employeeid}'/>">Delete</</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>