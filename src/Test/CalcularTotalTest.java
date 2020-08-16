package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.modelos.pedidos.DetallesPedido;
import es.utilidades.Utility;

public class CalcularTotalTest {
	
	List<DetallesPedido> listado;
	
	@Before
	public void setUp() throws Exception {
		listado = new ArrayList<DetallesPedido>();
		DetallesPedido detalle = new DetallesPedido();
		detalle.setCantidad("2");
		detalle.setNombre("Peto");
		detalle.setPrecio("21.90");
		detalle.setIva("21");
		listado.add(detalle);
	}

	@Test
	public void testCalcularSubtotalPedido() {
		assertEquals(Utility.calcularTotalPedido(listado), new Float("52.998"));
	}

}
