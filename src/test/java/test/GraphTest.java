/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.triadamcola.data_structures.graph.graph.Graph;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASUS
 */
public class GraphTest {
    
    
    
    
    
    public GraphTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    
 //   	private Graph<String, Integer, Integer, String> graph;
	
//	private void setupStage1() {
//		graph = new Graph<String, Integer, Integer, String>(Graph.DIRECTED_GRAPH);
//		graph.add(0,"0V");
//		graph.add(1,"1V");
//		graph.add(2,"2V");
//		graph.add(3,"3V");
//		graph.add(4,"4V");
//		graph.add(5,"5V");
//		graph.add(6,"6V");
//		graph.add(7,"7V");
//		graph.addEdgeBetweenVertices(1, 2, 0, "", 1);
//		graph.addEdgeBetweenVertices(1, 4, 0, "", 2);
//		graph.addEdgeBetweenVertices(1, 0, 0, "", 3);
//		graph.addEdgeBetweenVertices(3, 2, 0, "", 1);
//		graph.addEdgeBetweenVertices(4, 3, 0, "", 1);
//		graph.addEdgeBetweenVertices(5, 1, 0, "", 1);
//		graph.addEdgeBetweenVertices(5, 2, 0, "", 2);
//		graph.addEdgeBetweenVertices(7, 1, 0, "", 1);
//		graph.addEdgeBetweenVertices(7, 6, 0, "", 2);
//		graph.addEdgeBetweenVertices(0, 3, 0, "", 1);
//		graph.addEdgeBetweenVertices(0, 5, 0, "", 2);
//		graph.addEdgeBetweenVertices(0, 7, 0, "", 3);
//	}
//	
//	private void setupStage2() {
//		graph = new Graph<String, Integer, Integer, String>(Graph.INDIRECTED_GRAPH);
//		graph.add(1,"1V");
//		graph.add(2,"2V");
//		graph.add(3,"3V");
//		graph.add(4,"4V");
//		graph.add(5,"5V");
//		graph.add(6,"6V");
//		graph.add(7,"7V");
//		graph.addEdgeBetweenVertices(1, 7, 65, "", 1);
//		graph.addEdgeBetweenVertices(1, 2, 15, "", 2);
//		graph.addEdgeBetweenVertices(1, 6, 30, "", 3);
//		graph.addEdgeBetweenVertices(1, 4, 25, "", 4);
//		graph.addEdgeBetweenVertices(1, 3, 6, "", 5);
//		
//		graph.addEdgeBetweenVertices(2, 6, 14, "", 1);
//		graph.addEdgeBetweenVertices(2, 5, 13, "", 2);
//		
//		graph.addEdgeBetweenVertices(3, 4, 4, "", 1);
//		
//		graph.addEdgeBetweenVertices(4, 5, 5, "", 1);
//		
//		graph.addEdgeBetweenVertices(5, 6, 8, "", 1);
//		
//		graph.addEdgeBetweenVertices(6, 7, 12, "", 1);
//		
//	
//	}
//	
//	public void testBFS() {
//
//		setupStage1();
//		Hashtable<Integer,Vertex<String, Integer, Integer, String>> expectedOutput = new Hashtable<Integer, Vertex<String,Integer,Integer,String>>();
//		expectedOutput.put(5, new Vertex<String, Integer, Integer, String>(5,"5V"));
//		expectedOutput.put(1, new Vertex<String, Integer, Integer, String>(1,"1V"));
//		expectedOutput.put(2, new Vertex<String, Integer, Integer, String>(2,"2V"));
//		expectedOutput.put(0, new Vertex<String, Integer, Integer, String>(0,"0V"));
//		expectedOutput.put(4, new Vertex<String, Integer, Integer, String>(4,"4V"));
//		expectedOutput.put(3, new Vertex<String, Integer, Integer, String>(3,"3V"));
//		expectedOutput.put(7, new Vertex<String, Integer, Integer, String>(7,"7V"));
//		expectedOutput.put(6, new Vertex<String, Integer, Integer, String>(6,"6V"));
//	//	Hashtable<Integer,Vertex<String, Integer, Integer, String>> outPut = graph.BFS(graph.getVertex(5));
//		Collection<Vertex<String, Integer, Integer, String>> a = expectedOutput.values();
//		Collection<Vertex<String, Integer, Integer, String>> b = outPut.values();
//		Iterator<Vertex<String, Integer, Integer, String>> iteratorB = b.iterator();
//	
//		for (Iterator<Vertex<String, Integer, Integer, String>> iteratorA = a.iterator(); iteratorA.hasNext() ;) {
//			Vertex<String, Integer, Integer, String> vertexA =  iteratorA.next();
//			Vertex<String, Integer, Integer, String> vertexB =  iteratorB.next();
//			if(vertexA.compareTo(vertexB)!=0) {
//				fail();
//			}
//			
//		}
//	}
//	
//	public void testDFS() {
//
//		setupStage1();
//		Hashtable<Integer,Vertex<String, Integer, Integer, String>> expectedOutput = new Hashtable<Integer, Vertex<String,Integer,Integer,String>>();
//		expectedOutput.put(5, new Vertex<String, Integer, Integer, String>(5,"5V"));
//		expectedOutput.put(1, new Vertex<String, Integer, Integer, String>(1,"1V"));
//		expectedOutput.put(0, new Vertex<String, Integer, Integer, String>(0,"0V"));
//		expectedOutput.put(3, new Vertex<String, Integer, Integer, String>(3,"3V"));
//		expectedOutput.put(2, new Vertex<String, Integer, Integer, String>(2,"2V"));
//		expectedOutput.put(7, new Vertex<String, Integer, Integer, String>(7,"7V"));
//		expectedOutput.put(6, new Vertex<String, Integer, Integer, String>(6,"6V"));
//		expectedOutput.put(4, new Vertex<String, Integer, Integer, String>(4,"4V"));
//	//	Hashtable<Integer,Vertex<String, Integer, Integer, String>> outPut = graph.DFS(graph.getVertex(1));
//		Collection<Vertex<String, Integer, Integer, String>> a = expectedOutput.values();
//		Collection<Vertex<String, Integer, Integer, String>> b = outPut.values();
//		Iterator<Vertex<String, Integer, Integer, String>> iteratorB = b.iterator();
//		for (Iterator<Vertex<String, Integer, Integer, String>> iteratorA = a.iterator(); iteratorA.hasNext();) {
//			Vertex<String, Integer, Integer, String> vertexA =  iteratorA.next();
//			Vertex<String, Integer, Integer, String> vertexB =  iteratorB.next();
//			if(vertexA.compareTo(vertexB)!=0) {
//				fail();
//			}
//			
//		}
//	}

}
