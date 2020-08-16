package es.modelos.carrito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.modelos.clientes.Cliente;
import es.modelos.pedidos.DetallesPedido;
import es.modelos.pedidos.ModeloPedidos;
import es.modelos.productos.ModeloProductos;
import es.modelos.productos.Producto;


@WebServlet(name = "CarritoController", urlPatterns = { "/carritoWeb" })
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CarritoController() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost (request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "/vistasWeb/articulos.jsp";
		String todo = request.getParameter("todo");
		
		if(todo.equals("add")) {
			List<DetallesPedido> cesta = new ArrayList<DetallesPedido>();
			
			HttpSession session = request.getSession(true);
			if(session.getAttribute("cesta") != null) {
				 cesta = (List<DetallesPedido>)session.getAttribute("cesta");
				 
			} else {
				session.setAttribute("cesta", cesta);
				session.setAttribute("subtotal", new Float(0.00f));
				session.setAttribute("iva", new Float(0.00f));
			}
			
			String id = request.getParameter("id");
			String cantidad = request.getParameter("cantidad");
			
			Producto producto = ModeloProductos.obtenerProducto(id);
			
			boolean existe = false;
			Iterator<DetallesPedido> i = cesta.iterator();
			while (i.hasNext()) {
				DetallesPedido dp = i.next();
				if (dp.getIdProducto().equals(producto.getIdProducto())) {
					existe = true;
					Integer total = Integer.parseInt(dp.getCantidad()) + Integer.parseInt(cantidad);
					dp.setCantidad(total.toString());
				}
			}
			
			if (!existe) {
				//Añado el producto a la cesta
				DetallesPedido detalleProducto = new DetallesPedido();
				detalleProducto.setIdProducto(id);
				detalleProducto.setCantidad(cantidad);
				detalleProducto.setIva(producto.getIva());
				detalleProducto.setNombre(producto.getNombre());
				detalleProducto.setPeso(producto.getPeso());
				detalleProducto.setPrecio(producto.getPrecio());
				detalleProducto.setProductoIdFK(producto.getIdProducto());
				detalleProducto.setImagenData(producto.getImagenData());
				cesta.add(detalleProducto);
				
				Float subtotal = new Float(0.00f);
				Float iva = new Float(0.00f);
				 
				Iterator<DetallesPedido> j = cesta.iterator();
				while (j.hasNext()) {
					DetallesPedido detalles = j.next();
					subtotal = subtotal + (Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()));
					iva = iva + ((Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()) * 21) / 100);
				}
				 
				session.setAttribute("subtotal", subtotal);
				session.setAttribute("iva", iva);
			}
			
			//Cargamos los datos de la Categoría para el jsp de artículos
			request.setAttribute("nombreCategoria", producto.getCategoria().getNombre());
			request.setAttribute("listaProductos", ModeloProductos.obtenerProductoPorCategoria(producto.getCategoria().getIdCategoria()));
		} else if(todo.equals("eliminar")) {
			String id = request.getParameter("id");
			nextPage = "/vistasWeb/pedidos.jsp";
			
			HttpSession session = request.getSession(true);
			List<DetallesPedido> cesta = (List<DetallesPedido>)session.getAttribute("cesta");
			for (int i = 0; i < cesta.size(); i++) {
				DetallesPedido detallesPedido = cesta.get(i);
				if(detallesPedido.getIdProducto().equals(id)) {
					cesta.remove(i);
					break;
				}
			}
			
			Float subtotal = new Float(0.00f);
			Float iva = new Float(0.00f);
			
			Iterator<DetallesPedido> i = cesta.iterator();
			while (i.hasNext()) {
				 DetallesPedido detalles = i.next();
				 subtotal = subtotal + (Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()));
				 iva = iva + ((Float.parseFloat(detalles.getPrecio()) * Integer.parseInt(detalles.getCantidad()) * new Float(0.21)));
			}
			 
			session.setAttribute("subtotal", subtotal);
			session.setAttribute("iva", iva);
		} else if (todo.equals("confirmarCompra")) {
			HttpSession session = request.getSession(false);
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			List<DetallesPedido> cesta = (List<DetallesPedido>) session.getAttribute("cesta");
			Float subtotal = (Float)session.getAttribute("subtotal");
			Float iva = (Float)session.getAttribute("iva");
			if (cliente == null) {
				String mensaje = "Debe iniciar sesión para confirmar la compra.";
				request.setAttribute("mensaje", mensaje);
				nextPage = "/vistasWeb/pedidos.jsp";
			} else if (cesta == null || cesta.size() == 0) {
				String mensaje = "Debe incluir artículos en la cesta.";
				request.setAttribute("mensaje", mensaje);
				nextPage = "/vistasWeb/pedidos.jsp";
			} else {
				Float total = subtotal + iva;
				ModeloPedidos.altaPedido(cliente, total, cesta);
				String mensaje = "Gracias por realizar su pedido";
				request.setAttribute("mensaje", mensaje);
				session.removeAttribute("cesta");
				nextPage = "/vistasWeb/pedidos.jsp";
			}
			
		}
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	

}
