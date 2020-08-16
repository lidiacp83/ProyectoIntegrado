<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rossita París</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Titillium+Web:wght@300&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<link rel="stylesheet" href="css/web/main.css">
<link rel="stylesheet" href="css/web/articulos.css">
<link rel="stylesheet" href="css/web/loginWeb.css">
<link rel="stylesheet" href="css/web/footer.css">
<link rel="stylesheet" href="css/web/contacto.css">
<link rel="stylesheet" href="css/loginWeb.css">
</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="gridInicio col-md-12">
				<div id="cabecera">
					<jsp:include page="cabecera.jsp"></jsp:include>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="gridInicio col-md-12">
				<div id="contenedor">
					<div class="container-fluid">
						<!--  SLIDER  -->
						<div class="slider">
							<jsp:include page="slide.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="gridInicio col-md-12">
				<div id="pie">
					<jsp:include page="footer.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script src="js/web/addCesta.js"></script>
<script src="js/web/web.js"></script>
</body>
</html>