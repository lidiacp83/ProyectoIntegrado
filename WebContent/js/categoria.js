function submitEditarCategoria(id) {

	// Cojo el formulario de categorias
	var $form = $('#CategoriaForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/categoriaModificar',
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

function submitVolver() {
	var $form = $('#CategoriaForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/categoriaConsulta',
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

function submitAddCategoria() {
	// Cojo el formulario de categorias
	var $form = $('#CategoriaForm');
	var imagen = document.getElementById('imagen').value;
	var imagenData = document.getElementById('imagenData').src;
	$.ajax({
		type : 'POST',
		url : '/Proyecto/categoriaAlta',
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

function submitEliminarCategoria(id) {
	var $form = $('#CategoriaForm');

	$.ajax({
		type : 'POST',
		url : '/Proyecto/categoriaConsulta',
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

function submitModificarCategoria(id) {
	var $form = $('#CategoriaForm');
	var imagen = document.getElementById('imagen').value;
	var imagenData = document.getElementById('imagenData').src;

		$.ajax({
			type : 'POST',
			url : '/Proyecto/categoriaModificar',
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
