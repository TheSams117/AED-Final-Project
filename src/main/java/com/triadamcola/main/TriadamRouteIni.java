/**
 * 
 */
package com.triadamcola.main;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

import com.triadamcola.model.Connexion;
import com.triadamcola.model.User;

/**
 * @author ASUS
 *
 */
public class TriadamRouteIni {
	
	/**
	 * 
	 */
	public static Connexion con;
	
	/**
	 * 
	 */
	public static User user;
	
	/**
	 * 
	 */
	public static String API_KEY;
	
	/**
	 * 
	 */
	public TriadamRouteIni() {
		
		try {
			API_KEY = JOptionPane.showInputDialog("Ingrese la API KEY");
			
			String file = getClass().getResource("/info/connexion.dat").getPath();
			String[] connexion_data = new String[3];
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String read = "";
			int number = 0;
			boolean aux = false;
			
			while ((read = bufferedReader.readLine()) != null && !aux) {
				connexion_data[number] = read.substring(read.indexOf("=")+1, read.length());
				number++;
				if (number >2) {
					aux = true;
				}
			}
			
			con = new Connexion(connexion_data[0], "triadam_routes", connexion_data[1], connexion_data[2]);
			con.connect(false);
			user = new User();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
