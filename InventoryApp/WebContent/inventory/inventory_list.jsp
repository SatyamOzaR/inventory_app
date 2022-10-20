<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Inventory App</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Inventory App</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/inventory/item_store.jsp"
					class="nav-link">Store</a></li>
			</ul>

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/login/login.jsp"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>

	<div class="row">

		<div class="container">
			<h3 class="text-center">Inventory Items</h3>
			<hr>
			<span class="container text-left">

				<a href="<%=request.getContextPath()%>/items"
					class="btn btn-success">Fetch Items</a>
			</span>
			<span class="container text-left">

				<a href="<%=request.getContextPath()%>/inventory/item_store.jsp"
					class="btn btn-success">Add/Delete Item</a>
			</span>
			<br>
			<table class="table table-bordered mt-4">
				<thead>
					<tr>
						<th>Item ID</th>
						<th>Item Name</th>
						<th>Item Count</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${listItem}">

						<tr>
							<td><c:out value="${list.itemId}" /></td>
							<td><c:out value="${list.itemName}" /></td>
							<td><c:out value="${list.itemCount}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	<div class="row mb-4">

		<div class="container">
			<h3 class="text-center">Inventory Logs</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/logs"
					class="btn btn-success">Fetch Logs</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Item</th>
						<th>Time stamp</th>
						<th>User</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="inventory" items="${listInventory}">

						<tr>
							<td><c:out value="${inventory.item}" /></td>
							<td><c:out value="${inventory.timeStamp}" /></td>
							<td><c:out value="${inventory.username}" /></td>
							<td><c:out value="${inventory.action}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
