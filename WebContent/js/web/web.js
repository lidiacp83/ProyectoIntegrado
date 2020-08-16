$(document).ready(function() {

	$('#cookies').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/cookies.jsp');
		return false;
	});
	
	$('#condiciones').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/condiciones.jsp');
		return false;
	});
	
	$('#devoluciones').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/devoluciones.jsp');
		return false;
	});
	
	$('#devolucionesPie').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/devoluciones.jsp');
		return false;
	});
	
	$('#avisoLegal').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/avisoLegal.jsp');
		return false;
	});
	
	$('#contacto').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/contacto.jsp');
		return false;
	});
	

	$('#contactoPie').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/contacto.jsp');
		return false;
	});
	
	$('#tallas').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/tallas.jsp');
		return false;
	});
	
	$('#tallasPie').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/tallas.jsp');
		return false;
	});
	
	$('#rossita').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/acerca.jsp');
		return false;
	});
	
	$('#inicioSesion').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/inicioSesion.jsp');
		return false;
	});
	
	$('#verCesta').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/pedidos.jsp');
		return false;
	});
	
	$('#articulos').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/articulos.jsp');
		return false;
	});
	
	$('#categorias').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/categorias.jsp');
		return false;
	});
	
	$('#categoriasMiga').click(function() {
		$('#contenedor').load('/Proyecto/vistasWeb/categorias.jsp');
		return false;
	});
			
});

function seleccionarCategoria(id, nombre) {
	$.ajax({
		type : 'POST',
		url : '/Proyecto/categoriaWeb',
		data : "id=" + id + "&nombre=" + nombre,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#contenedor').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function seleccionarProducto(id) {
	$.ajax({
		type : 'POST',
		url : '/Proyecto/productoWeb',
		data : "id=" + id,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#contenedor').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function submitAddClienteWeb() {
	
	var $form = $('#ClienteAltaWebForm');

		$.ajax({
			type : 'POST',
			url : '/Proyecto/clienteAlta',
			data : $form.serialize() + "&todo=altaWeb",
			dataType : 'html',
			success : function(response, textStatus, jqXHR) {
				$('#contenedor').html(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error(s):' + textStatus, errorThrown);
			}
		});
}

function submitLoginWeb() {
	
	var $form = $('#LoginWebForm');

		$.ajax({
			type : 'POST',
			url : '/Proyecto/login',
			data : $form.serialize(),
			dataType : 'html',
			success : function(response, textStatus, jqXHR) {
				$('#contenedor').html(response);
				$('#cabecera').load('/Proyecto/vistasWeb/cabecera.jsp');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error(s):' + textStatus, errorThrown);
			}
		});
}

function submitLoginPopUp() {
	
	var $form = $('#LoginPopUp');

		$.ajax({
			type : 'POST',
			url : '/Proyecto/login',
			data : $form.serialize(),
			dataType : 'html',
			success : function(response, textStatus, jqXHR) {
				$('#contenedor').html(response);
				$('#cabecera').load('/Proyecto/vistasWeb/cabecera.jsp');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error(s):' + textStatus, errorThrown);
			}
		});
}

function logoutWeb(){
	$.ajax({
		type : 'POST',
		url : '/Proyecto/logout',
		success : function(response, textStatus, jqXHR) {
			$('#cabecera').load('/Proyecto/vistasWeb/cabecera.jsp');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}
