package es.modelos.productos;

import es.modelos.categorias.Categoria;

public class Producto {

	private String idProducto;
	private String nombre;
	private String descripcion;
	private String imagen;
	private String imagenData;
	private String precio;
	private String iva;
	private String rebaja;
	private String stock;
	private String stockMinimo;
	private String sexo;
	private String peso;
	private String IdcategoriaFK;
	private Categoria categoria;

	public Producto() {
	}

	public String getIdcategoriaFK() {
		return IdcategoriaFK;
	}

	public void setIdcategoriaFK(String idcategoriaFK) {
		IdcategoriaFK = idcategoriaFK;
	}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getRebaja() {
		return rebaja;
	}

	public void setRebaja(String rebaja) {
		this.rebaja = rebaja;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getImagenData() {
		return imagenData;
	}

	public void setImagenData(String imagenData) {
		this.imagenData = imagenData;
	}

	public String getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(String stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

}
