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
	
	private String orderID;
	
	private String orderName;
	
	private String orderStatus;
	
	private String orderAdress;
	
	/**
	 * 
	 */
	public Order() {
		this("-1","NULL","0","NULL");
	}
	
	public Order(String orderID, String orderName, String orderStatus, String orderAdress) {
		super();
		this.orderID = orderID;
		this.orderName = orderName;
		this.orderStatus = orderStatus;
		this.orderAdress = orderAdress;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
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

	public String getOrderAdress() {
		return orderAdress;
	}

	public void setOrderAdress(String orderAdress) {
		this.orderAdress = orderAdress;
	}
	
	public String insert() {
		String msg = "";
		try {
			con.execute("INSERT INTO orders VALUE(DEFAULT, ?,?,?)", new String[] {orderName,orderStatus,orderAdress}, false);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	  public String remove() {
		String msg = "";
	        try
	        {
				con.execute("DELATE FROM orders where order_id = ? ", new String[] {orderID}, false);
	        }
	        catch (SQLException ex)
	        {
	             msg = ex.getMessage();
	        }
	        return msg;
	 }
	  
	 public static ArrayList<Order> getOrders() {
	        ArrayList<Order> orders = new ArrayList<>();
	        try
	        {
	            ResultSet rs = con.execute("SELECT * FROM orders ORDER BY order_name", null, true);
	            while(rs.next())
	            {
	                String code = rs.getString("order_id");
	                String name = rs.getString("order_name");
	                String status = rs.getString("order_status");
	                String adress = rs.getString("order_address");
	                Order order = new Order(code, name, status, adress);
	                orders.add(order);
	            }
	        }
	        catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
	        }
	        return orders;
	 }
	  
	 public String active() {
		 String msg = "";
	 	try{
	 	setOrderStatus("1");
	    con.execute("UPDATE order SET order_status = 1 WHERE order_id = ?", new String[] {orderID}, false);
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
            con.execute("UPDATE order SET order_status = 1 WHERE order_id = ?", new String[] {orderID}, false);
        }
        catch (SQLException ex)
        {
             msg = ex.getMessage();
        }
        return msg;
    }
    
    
    public static ArrayList<Order> getActive() {
        ArrayList<Order> orders =  new ArrayList<>();
        try {        
            ResultSet result = con.execute("SELECT * FROM orders WHERE order_ = 1 ORDER BY order_name ASC ", null, true);
            while(result.next()) {
                ArrayList<String> data = new ArrayList<>();
                String code = result.getString("order_id");
                String name = result.getString("order_name");
                String status = result.getString("order_status");
                String address = result.getString("order_address");
                Order order = new Order(code, name, status, address);
                orders.add(order);
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión a la base de datos.\nConfigure la conexión correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return orders;
    }
}
