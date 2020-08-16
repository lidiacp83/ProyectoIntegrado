$(function() {
	var Acordeon = function(el, multiple) {
		this.el = el || {};
		// Se abre el submenú
		this.multiple = multiple || false;

		var desplegarLink = this.el.find('.link');
		desplegarLink.on('click', {
			el : this.el,
			multiple : this.multiple
		}, this.dropdown);
	};

	Acordeon.prototype.dropdown = function(e) {
		var $el = e.data.el, $this = $(this),
		//Esta es la lista de los submenús
		$next = $this.next();

		$next.slideToggle();
		$this.parent().toggleClass('open');

		if (!e.data.multiple) {
			//Muestra solo un menú al mismo tiempo
			$el.find('.submenuItems').not($next).slideUp().parent()
					.removeClass('open');
		}
	}

	var acordeon = new Acordeon($('.acordeon-menu'), false);
})

$(document).ready(function() {

	$('#inicio').click(function() {
		$('#container').load('/Proyecto/inicio');
		return false;
	});

	$('#consultaCliente').click(function() {
		$('#container').load('/Proyecto/clienteConsulta');
		return false;
	});

	$('#addCliente').click(function() {
		$('#container').load('/Proyecto/clienteAlta');
		return false;
	});

	$('#addCategoria').click(function() {
		$('#container').load('/Proyecto/categoriaAlta');
		return false;
	});

	$('#consultaCategoria').click(function() {
		$('#container').load('/Proyecto/categoriaConsulta');
		return false;
	});

	$('#addProducto').click(function() {
		$('#container').load('/Proyecto/productoAlta');
		return false;
	});

	$('#consultaProducto').click(function() {
		$('#container').load('/Proyecto/productoConsulta');
		return false;
	});

	$('#consultaPedido').click(function() {
		$('#container').load('/Proyecto/pedidoConsulta');
		return false;
	});
});

function logoutGestion(){
	$.ajax({
		type : 'POST',
		url : '/Proyecto/logoutGestion',
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('error(s):' + textStatus, errorThrown);
		}
	});
}