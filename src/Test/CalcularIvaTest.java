package Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.modelos.pedidos.DetallesPedido;
import es.utilidades.Utility;

public class CalcularIvaTest {
	
	List<DetallesPedido> listado;
	
	@Before
	public void setUp() throws Exception {
		listado = new ArrayList<DetallesPedido>();
		DetallesPedido detalle = new DetallesPedido();
		detalle.setCantidad("1");
		detalle.setNombre("Vestido");
		detalle.setPrecio("19.90");
		listado.add(detalle);
	}
	
	@Test
	public void testCalcularIvaPedido() {
		assertEquals(Utility.calcularIvaPedido(listado), new Float("4.179"));
	}

}
