function addCart(idProducto, cantidad) {
	$.ajax({
		type : 'POST',
		url : '/Proyecto/carritoWeb',
		data : "todo=add&id=" + idProducto + "&cantidad=" + cantidad,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#contenedor').html(response);
			$('#cabecera').load('/Proyecto/vistasWeb/cabecera.jsp')
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function eliminarProductoCesta(idProducto) {
	$.ajax({
		type : 'POST',
		url : '/Proyecto/carritoWeb',
		data : "todo=eliminar&id=" + idProducto,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#contenedor').html(response);
			$('#cabecera').load('/Proyecto/vistasWeb/cabecera.jsp')
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function confirmarCompra(){
	$.ajax({
		type : 'POST',
		url : '/Proyecto/carritoWeb',
		data : "todo=confirmarCompra",
		success : function(response, textStatus, jqXHR) {
			$('#contenedor').html(response);
			$('#cabecera').load('/Proyecto/vistasWeb/cabecera.jsp');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}