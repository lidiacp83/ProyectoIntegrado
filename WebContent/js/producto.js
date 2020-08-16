function submitEditarProducto(id) {

	// Cojo el formulario de categorias
	var $form = $('#ProductoForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/productoModificar',
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

function submitBuscarProducto() {

	var $form = $('#ProductoForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/productoConsulta',
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

function submitEliminarProducto(id) {
	var $form = $('#ProductoForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/productoConsulta',
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

function submitVolver() {
	var $form = $('#ProductoForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/productoConsulta',
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

function submitAddProducto() {

	// Cojo el formulario de categorias
	var $form = $('#ProductoForm');
	var imagen = document.getElementById('imagen').value;
	var imagenData = document.getElementById('imagenData').src;
		$.ajax({
			type : 'POST',
			url : '/Proyecto/productoAlta',
			data : $form.serialize() + "&todo=alta&imagen=" + imagen
					+ "&imagenData=" + imagenData,
			dataType : 'html',
			success : function(response, textStatus, jqXHR) {
				$('#container').html(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error(s):' + textStatus, errorThrown);
			}
		});
}

function submitModificarProducto(id) {
	var $form = $('#ProductoForm');
	var imagen = document.getElementById('imagen').value;
	var imagenData = document.getElementById('imagenData').src;

		$.ajax({
			type : 'POST',
			url : '/Proyecto/productoModificar',
			data : $form.serialize() + "&todo=modificar&id=" + id + "&imagen=" + imagen
					+ "&imagenData=" + imagenData,
			dataType : 'html',
			success : function(response, textStatus, jqXHR) {
				$('#container').html(response);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log('error(s):' + textStatus, errorThrown);
			}
		});
}

// FUNCIÓN PREVISUALIZACIÓN IMAGEN

$('#imagen').on('change', function(ev) {
	var f = ev.target.files[0];
	var fr = new FileReader();

	fr.onload = function(ev2) {
		console.dir(ev2);
		$('#imagenData').attr('src', ev2.target.result);
	};

	fr.readAsDataURL(f);
});

function cambiar() {
	var pdrs = document.getElementById('imagen').files[0].name;
	document.getElementById('info').innerHTML = pdrs;
}
