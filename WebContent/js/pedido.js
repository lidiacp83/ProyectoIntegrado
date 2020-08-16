function submitEditarPedido(id) {

	// Cojo el formulario de categorias
	var $form = $('#PedidoForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/pedidoModificar',
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

function submitBuscarPedido() {

	var $form = $('#PedidoForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/pedidoConsulta',
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
	var $form = $('#PedidoForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/pedidoConsulta',
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

function submitModificarPedido() {
	var $form = $('#PedidoForm');
	var idPedido = document.getElementById('pedidoId').value;

	$.ajax({
		type : 'POST',
		url : '/Proyecto/pedidoModificar',
		data : $form.serialize() + "&todo=modificar&id=" + idPedido,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#container').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function submitModificarProducto(idProductoPedido, cantidad) {
	var $form = $('#PedidoForm');
	var idPedido = document.getElementById('pedidoId').value;

	$.ajax({
		type : 'POST',
		url : '/Proyecto/pedidoModificar',
		data : $form.serialize() + "&todo=modificarProducto&idProductoPedido="
				+ idProductoPedido + "&cantidad=" + cantidad + "&id=" + idPedido,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#container').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

function submitEliminarProductoPedido(idProductoPedido, idProducto) {
	var $form = $('#PedidoForm');
	var idPedido = document.getElementById('pedidoId').value;
	
	$.ajax({
		type : 'POST',
		url : '/Proyecto/pedidoModificar',
		data : $form.serialize() + "&todo=eliminarProducto&idProductoPedido="
				+ idProductoPedido + "&id=" + idProducto,
		dataType : 'html',
		success : function(response, textStatus, jqXHR) {
			$('#container').html(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}

$(document)
		.on(
				'click',
				'.edit',
				function() {

					$(this)
							.parent()
							.siblings('td.data')
							.each(
									function() {
										var content = $(this).text();
										if (content != '') {
											var input = '<input style="border: 1px solid #ccc" class=campoCantidad value='
													+ content + '>';
											$(this).html(input);
										}
									});

					$(this).siblings('.save').show();
					$(this).siblings('.delete').hide();
					$(this).hide();
				});

$(document).on(
		'click',
		'.save',
		function() {
			$('.campoCantidad').each(
					function() {
						var idProductoPedido = $(this).parent().parent().find(
								'td.campoId').text();
						var content = $(this).val();
						$(this).html(content);
						$(this).contents().unwrap();

						submitModificarProducto(idProductoPedido, content);
					});

			$(this).siblings('.edit').show();
			$(this).siblings('.delete').show();
			$(this).hide();

		});

$(document).on('click', '.delete', function() {
	var idProductoPedido = $(this).parent().parent().find('td.campoId').text();
	var idPedido = document.getElementById('pedidoId').value;
	$(this).parents('tr').remove();

	submitEliminarProductoPedido(idProductoPedido, idPedido);
});


