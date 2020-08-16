package es.modelos.categorias;

public class Categoria {

	private String idCategoria;
	private String nombre;
	private String imagen;
	private String imagenData;

	public Categoria() {
	}
	public String getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getImagenData() {
		return imagenData;
	}
	public void setImagenData(String imagenData) {
		this.imagenData = imagenData;
	}
}
