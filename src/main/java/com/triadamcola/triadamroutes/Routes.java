/**
 * 
 */
package com.triadamcola.triadamroutes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.triadamcola.data_structures.graph.vertex.Vertex;
import com.triadamcola.data_structures.graph.edge.Edge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.triadamcola.data_structures.graph.graph.Graph;
import com.triadamcola.model.Order;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ASUS
 *
 */
public class Routes {
	
	private static final String API_KEY= "AIzaSyAFv-dr1ML_bELibByV1IepG2F_aWmsJ7Y";

	

	/**
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 * 
	 */
	public Routes() throws ApiException, InterruptedException, IOException {
		Graph<Integer,Integer,Integer,String> graph = generateGraph();
		Queue<Vertex<Integer,Integer,Integer,String>> queue = new LinkedList<>();
		for (Iterator<Vertex<Integer, Integer, Integer, String>> iterator = queue.iterator(); iterator.hasNext();) {
			Vertex<Integer, Integer, Integer, String> vertex = iterator.next();
			System.out.println(vertex.getValue());
			
		}
	}
	
	
	public Response getDistance(String origin, String destinations) throws IOException {
	    OkHttpClient client = new OkHttpClient();
	    String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origin+"&destinations="+destinations+"&key="+ API_KEY;
	    Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
		return response;
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 */
	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		//--------------- for test --------------------------------- 
//		Graph<Integer,Integer,Integer,String> graph = new Routes().generateGraph();
//		 Queue<String> vertex = new Routes().generateDeliveryRoute();
//		 for (Iterator<String> iterator = vertex.iterator(); iterator.hasNext();) {
//			String vertex2 = iterator.next();
//		
//			System.out.println(vertex2);
//			
//		}
//		Integer[][] a = graph.getAdjacencyMatrixWeight();
//		
//		for (int i = 0; i < a.length; i++) {
//			for (int j = 0; j < a.length; j++) {
//				System.out.print(a[i][j]+" ");
//			}
//			System.out.println("");
//		}
		//---------------------------------------------------------
		
	}
	// este metodo genera el grafo
	public Graph<Integer,Integer,Integer,String> generateGraph() throws IOException{
		Graph<Integer,Integer,Integer,String> graph = new Graph<>(Graph.INDIRECTED_GRAPH);
		
		
		
		ArrayList<Order> orders = new ArrayList<>();
		//--------------- for test ---------------------------------
		for (int i = 0; i < 5; i++) {
			orders.add(new Order(i+"", "Orden #"+i, ((int)(Math.random()*(1)-0))+"", i+" Car "+((int)(Math.random()*(20)-5))+" Av "+ ((int)(Math.random()*(20-5)+5))));
		}
		//-----------------------------------------------------------
		for (int i = 0; i < orders.size(); i++) {
			graph.add(i,orders.get(i).getOderAdress());
		}
	
		FileReader fr = new FileReader(getClass().getResource("/adyacencyMatrix/matrix.txt").getFile());
		
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
}
