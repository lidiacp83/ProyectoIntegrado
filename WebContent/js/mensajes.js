function mensajeAlert(mensaje) {
	$.alert({
		icon : 'fas fa-exclamation-triangle',
		closeIcon : true,
		closeIconClass : 'fa fa-times',
		boxWidth : '30%',
		useBootstrap : false,
		theme : 'black',
		animationSpeed : 200,// 0.2 seconds
		title : 'Información:',
		content : mensaje,
	});
}

function mensajeConfirm(mensaje, metodo) {
	$.confirm({
		icon : 'fas fa-exclamation-triangle',
		closeIcon : true,
		closeIconClass : 'fa fa-times',
		boxWidth : '30%',
		useBootstrap : false,
		theme : 'black',
		animationSpeed : 200,// 0.2 seconds
		title : '¡ATENCIÓN!',
		content : mensaje,
		buttons : {
			confirmar : metodo,
			cancelar : function() {
				this.close();
			},
		}
	});

}
