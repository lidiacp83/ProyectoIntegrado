package es.modelos.pedidos;

public class DetallesPedido {
	private String idProducto;
	private String nombre;
	private String precio;
	private String peso;
	private String iva;
	private String cantidad;
	private String productoIdFK;
	private String pedidoIdFK;
	private String imagenData;

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getProductoIdFK() {
		return productoIdFK;
	}

	public void setProductoIdFK(String productoIdFK) {
		this.productoIdFK = productoIdFK;
	}

	public String getPedidoIdFK() {
		return pedidoIdFK;
	}

	public void setPedidoIdFK(String pedidoIdFK) {
		this.pedidoIdFK = pedidoIdFK;
	}

	public String getImagenData() {
		return imagenData;
	}

	public void setImagenData(String imagenData) {
		this.imagenData = imagenData;
	}

}
