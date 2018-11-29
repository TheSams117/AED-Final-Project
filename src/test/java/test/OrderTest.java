package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.triadamcola.main.TriadamRouteIni;
import com.triadamcola.model.Order;
import com.triadamcola.model.User;

public class OrderTest {

	private TriadamRouteIni triadam;
	private Order orden;
	
	public void setupStage1() {
		triadam = new TriadamRouteIni();
		triadam.user.validate("KorKux", "1234");
	}
	
	public void setupStage2() {
		triadam = new TriadamRouteIni();
		triadam.user.validate("KorKux", "1234");
		
		for (int i = 0; i < 5; i++) {
			orden = new Order();
			orden.setOrderID(""+i);
			orden.setOrderName("Luis"+i);
			orden.setOrderStatus("Activo");
			orden.setOrderAdress("0");
			orden.insert();
		}
	}
	
	@Test
	public void insertTest() {
		setupStage1();
		
		for (int i = 0; i < 5; i++) {
			orden = new Order();
			orden.setOrderID(""+i);
			orden.setOrderName("Luis");
			orden.setOrderStatus("1");
			orden.setOrderAdress("0");
			orden.insert();
			assertTrue(orden.getOrderID().equals(Order.getOrders().get(i).getOrderID()));
		}
	}
	
	@Test
	public void removeTest() {
		setupStage1();
		
		for (int i = 0; i < 20; i++) {
			orden = new Order();
			orden.setOrderID(""+i);
			orden.setOrderName("Luis");
			orden.setOrderStatus("1");
			orden.setOrderAdress("0");
			orden.insert();
			
			assertTrue(orden.getOrderID().equals(Order.getOrders().get(i).getOrderID()));
			
			orden.remove();
			
			assertTrue(Order.getOrders().get(i)== null);
			
		}
	}
	
	@Test
	public void getOrdersTest() {
		setupStage2();
		for (int i = 0; i < 5; i++) {
			assertTrue(Order.getOrders().get(i).getOrderID().equals(""+i));
		}
	}

}
