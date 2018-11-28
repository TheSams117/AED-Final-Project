/**
 * 
 */
package com.triadamcola.triadamroutes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.triadamcola.data_structures.graph.vertex.Vertex;

import com.google.maps.errors.ApiException;
import com.triadamcola.data_structures.graph.graph.Graph;
import com.triadamcola.model.Order;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author KorKux
 *
 */
public class Routes {
	
	private static final String API_KEY= com.triadamcola.main.TriadamRouteIni.API_KEY;

	/**
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 * 
	 */
	public Routes() throws ApiException, InterruptedException, IOException {
		generateAdyacency();
	}
	
	public void generateAdyacency() {
		try {
			File file = new File(getClass().getResource("/adyacencyMatrix/matrix.txt").getFile());
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			ArrayList<Order> orders = Order.getActive();
			for (int i = 0; i < orders.size(); i++) {
				for (int j = 0; j < orders.size(); j++) {
					if (i == j) {
						bufferedWriter.write("0 ");
					}
					else {
						bufferedWriter.write(getDistance(orders.get(i).getOrderAdress(), orders.get(j).getOrderAdress())+" ");
					}
				}
				bufferedWriter.write("\n");
			}
			bufferedWriter.close();
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static int getDistance(String origin, String destinations) throws IOException {
	    OkHttpClient client = new OkHttpClient();
	    String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origin+"&destinations="+destinations+"&key="+ "AIzaSyAFv-dr1ML_bELibByV1IepG2F_aWmsJ7Y"+"&region=CO";
	    Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        JSONParser parser = new JSONParser();
        Object obj = null;
		try {
			obj = parser.parse(response.body().string());
		} catch (ParseException e) {
			e.printStackTrace();
		}
        JSONObject jsonobj = (JSONObject) obj;

        JSONArray dist = (JSONArray)jsonobj.get("rows");
        JSONObject obj2 = (JSONObject)dist.get(0);
        JSONArray disting = (JSONArray)obj2.get("elements");
        JSONObject obj3 = (JSONObject)disting.get(0);
        JSONObject obj4= (JSONObject)obj3.get("distance");

        
        String data[] = obj4.get("text").toString().split(" ");
        
		return (int) Math.round(Double.parseDouble(data[0]));
	}


	// este metodo genera el grafo
	public Graph<Integer,Integer,Integer,String> generateGraph() throws IOException{
		Graph<Integer,Integer,Integer,String> graph = new Graph<>(Graph.INDIRECTED_GRAPH);
		ArrayList<Order> orders = Order.getActive();
		for (int i = 0; i < orders.size(); i++) {
			graph.add(i,orders.get(i).getOrderAdress());
		}
		FileReader fr = new FileReader(getClass().getResource("/adyacencyMatrix/matrix.txt").getFile().toString());
		BufferedReader br = new BufferedReader(fr);
		String[] line = null;
		int edgeId = 0;
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < graph.getNumberOfVertices(); j++) {
				graph.addEdgeBetweenVertices(i, j, Integer.parseInt(line[j]), 0, edgeId);
				edgeId+=1;
			}		
		}
		br.close();
		return graph;
	}
	
	// Este metodo retorna una cola que indica el orden en que deben de entregarse los pedidos.
	public Queue<String> generateDeliveryRoute() throws IOException{
		Graph<Integer,Integer,Integer,String> graph = generateGraph().PRIM(0);
		Queue<Vertex<Integer,Integer,Integer,String>> queue = graph.BFS(0);
		Queue<String> toVisit = new LinkedList<>();
		
		for (Iterator<Vertex<Integer, Integer, Integer, String>> iterator = queue.iterator(); iterator.hasNext();) {
			Vertex<Integer, Integer, Integer, String> vertex = iterator.next();
			toVisit.add(vertex.getValue());
			
		}
		
		return toVisit;
	}
	
	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		new Routes();
	}
}
