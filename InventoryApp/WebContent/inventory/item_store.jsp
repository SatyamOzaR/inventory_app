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
				<a href="https://www.javaguides.net" class="navbar-brand">Inventory App</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/inventory/inventory_list.jsp"
					class="nav-link">Inventory</a></li>
			</ul>

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/login/login.jsp"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="<%=request.getContextPath()%>/store" method="post">
				<div class="form-group">
				  <select name="item" class="h4">
				    <option value="Beet" class="h4">Beet</option>
				    <option value="Broccoli" class="h4">Broccoli</option>
				    <option value="Cabbage" class="h4">Cabbage</option>
				    <option value="Capsicum" class="h4">Capsicum</option>
				    <option value="Carrot" class="h4">Carrot</option>
				    <option value="Corn" class="h4">Corn</option>
				    <option value="Eggplant" class="h4">Eggplant</option>
				    <option value="Potato" class="h4">Potato</option>
				    <option value="Spinach" class="h4">Spinach</option>
				    <option value="Tomato" class="h4">Tomato</option>
				  </select>
				  </div>
				  <button type="submit" class="btn btn-primary">Add Item</button>
				</form>
			</div>
		</div>
	</div>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="<%=request.getContextPath()%>/delete" method="post">
				<div class="form-group">
				  <select name="item" class="h4">
				    <option value="Beet" class="h4">Beet</option>
				    <option value="Broccoli" class="h4">Broccoli</option>
				    <option value="Cabbage" class="h4">Cabbage</option>
				    <option value="Capsicum" class="h4">Capsicum</option>
				    <option value="Carrot" class="h4">Carrot</option>
				    <option value="Corn" class="h4">Corn</option>
				    <option value="Eggplant" class="h4">Eggplant</option>
				    <option value="Potato" class="h4">Potato</option>
				    <option value="Spinach" class="h4">Spinach</option>
				    <option value="Tomato" class="h4">Tomato</option>
				  </select>
				  </div>
				  <button type="submit" class="btn btn-primary">Delete Item</button>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
