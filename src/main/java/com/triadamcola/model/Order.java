/**
 * 
 */
package com.triadamcola.model;

import static com.triadamcola.main.TriadamRouteIni.con;
import static com.triadamcola.main.TriadamRouteIni.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author ASUS
 *
 */
public class Order {
	
	private String oderID;
	private String orderName;
	private String orderStatus;
	private String oderAdress;
	/**
	 * 
	 */
	public Order() {
		this("-1","NULL","0","NULL");
	}
	
	public Order(String oderID, String orderName, String orderStatus, String oderAdress) {
		super();
		this.oderID = oderID;
		this.orderName = orderName;
		this.orderStatus = orderStatus;
		this.oderAdress = oderAdress;
	}

	public String getOderID() {
		return oderID;
	}

	public void setOderID(String oderID) {
		this.oderID = oderID;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOderAdress() {
		return oderAdress;
	}

	public void setOderAdress(String oderAdress) {
		this.oderAdress = oderAdress;
	}
	
	public String insert() {
		String msg = "";
		try {
			con.execute("INSERT INTO orders VALUE(DEFAULT, ?,?,?)", new String[] {orderName,orderStatus,orderStatus}, false);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	  public String remove() {
		String msg = "";
	        try
	        {
				con.execute("DELATE FROM orders where order_id = ? ", new String[] {oderID}, false);
	        }
	        catch (SQLException ex)
	        {
	             msg = ex.getMessage();
	        }
	        return msg;
	 }
	  
	 public static ArrayList<Order> getOrders() {
	        ArrayList<Order> productos = new ArrayList<>();
	        try
	        {
	            ResultSet rs = con.execute("SELECT ProCod, ProNom, UniDes, ProEstReg FROM PRODUCTO INNER JOIN UNIDAD ON PRODUCTO.UniCod = UNIDAD.UniCod ORDER BY ProEstReg ASC, ProCod ASC", null, true);
	            while(rs.next())
	            {
	                String code = rs.getString("oder_id");
	                String name = rs.getString("oder_name");
	                String status = rs.getString("oder_status");
	                String adress = rs.getString("order_address");
	                Order producto = new Order(code, name, status, adress);
	                productos.add(producto);
	            }
	        }
	        catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
	        }
	        return productos;
	 }
	  
	 public String active() {
		 String msg = "";
	 	try{
	 	setOrderStatus("1");
	    con.execute("UPDATE order SET order_status = 1 WHERE oder_id = ?", new String[] {oderID}, false);
	 	} catch (SQLException ex){
    	msg = ex.getMessage();
	 	}
	 	return msg;
	 }
	 
    public String desactive() {
	String msg = "";
        try
        {
          setOrderStatus("0");
            con.execute("UPDATE order SET order_status = 1 WHERE order_id = ?", new String[] {oderID}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
    }
    
    public static ArrayList<ArrayList<String>> getActive() {
        ArrayList<ArrayList<String>> orders =  new ArrayList<>();
        try {        
            ResultSet result = con.execute("SELECT order_id, order_name FROM orders WHERE order_status = 1 ", null, true);
            while(result.next()) {
                ArrayList<String> data = new ArrayList<>();
                String code = result.getString("order_id");
                String name = result.getString("order_name");
                data.add(code);
                data.add(name);
                orders.add(data);
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return orders;
    }
}
