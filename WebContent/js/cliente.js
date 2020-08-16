function submitEditarCliente(id) {

	// Cojo el formulario de clientes
	var $form = $('#ClienteForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/clienteModificar',
		data : $form.serialize() + "&id=" + id,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#container').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function submitEliminarCliente(id) {
	var $form = $('#ClienteForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/clienteConsulta',
		data : $form.serialize() + "&id=" + id + "&todo=eliminar",
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#container').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function submitBuscarCliente() {

	var $form = $('#ClienteForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/clienteConsulta',
		data : $form.serialize() + "&todo=buscar",
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#container').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function submitVolver() {
	var $form = $('#ClienteForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/clienteConsulta',
		data : $form.serialize(),
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#container').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function submitAddCliente() {
	var $form = $('#ClienteForm');

		$.ajax({
			type : 'POST',
			url : '/Proyecto/clienteAlta',
			data : $form.serialize() + "&todo=alta",
			dataType : 'html',
			success : function(response, textStatus, jqXHR) {
				$('#container').html(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error(s):' + textStatus, errorThrown);
			}
		});
}

function submitModificarCliente(id) {
	var $form = $('#ClienteForm');
		$.ajax({
			type : 'POST',
			url : '/Proyecto/clienteModificar',
			data : $form.serialize() + "&todo=modificar&id=" + id,
			dataType : 'html',
			success : function(response, textStatus, jqXHR) {
				$('#container').html(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error(s):' + textStatus, errorThrown);
			}
		});
}

function generarClave(length) {
	var result = '';
	var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	for (var i = 0; i < length; i++) {
		result += characters.charAt(Math.floor(Math.random()
				* characters.length));
	}
	document.getElementById('clave').value = result;
}
