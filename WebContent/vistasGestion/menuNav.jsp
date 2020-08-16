<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul class="acordeon-menu">
	<li>
		<div class="cabeceraMenu">
			<img class="logo" src="img/logo.png" title="Rossita Paris">
		</div> <img src="img/cabeceraEstrellas.png" style="width: 100%; height: 10%"></img>
	</li>

	<li>
		<div class="link">
			<i class="fa fa-home" aria-hidden="true"></i> <a href="#" id="inicio">MENÚ
				INICIO</a>
		</div>
	</li>

	<li>
		<div class="link">
			<i class="fa fa-address-card" aria-hidden="true"></i> <span
				class="optionMenu">Clientes</span> <i class="fa fa-caret-right"
				aria-hidden="true"></i>
		</div>
		<ul class="submenuItems">
			<li><a href="#" id="addCliente">A&ntilde;adir clientes</a></li>
			<li><a id="consultaCliente">Listado clientes</a></li>
		</ul>
	</li>

	<li>
		<div class="link">
			<i class="fa fa-clipboard-list" aria-hidden="true"></i> <span
				class="optionMenu">Productos</span> <i class="fa fa-caret-right"
				aria-hidden="true"></i>
		</div>
		<ul class="submenuItems">
			<li><a href="#" id="addProducto">A&ntilde;adir producto</a></li>
			<li><a href="#" id="consultaProducto">Listado productos</a></li>
		</ul>
	</li>

	<li>
		<div class="link">
			<i class="fa fa-images" aria-hidden="true"></i> <span
				class="optionMenu">Categor&iacute;as</span> <i
				class="fa fa-caret-right" aria-hidden="true"></i>
		</div>
		<ul class="submenuItems">
			<li><a href="#" id="addCategoria">A&ntilde;adir
					categor&iacute;a</a></li>
			<li><a href="#" id="consultaCategoria">Listado
					categor&iacute;as</a></li>
		</ul>
	</li>

	<li>
		<div class="link">
			<i class="fa fa-box-open" aria-hidden="true"></i> <span
				class="optionMenu">Pedidos</span> <i class="fa fa-caret-right"
				aria-hidden="true"></i>
		</div>
		<ul class="submenuItems">
			<li><a href="#" id="consultaPedido">Listado pedidos</a></li>
		</ul>
	</li>

	<li>
		<div class="link">
			<a href="/Proyecto/loginGestion" onclick="logoutGestion();"><i
				class="fa fa-power-off" aria-hidden="true"></i> <span
				class="optionMenu">Cerrar Sesión</span></a>
		</div>
	</li>
</ul>


