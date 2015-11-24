package edu.usc.sql.graphs;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GraphTest {

	private static Graph testGraph;

	private static Node A = new Node("A");
	private static Node B = new Node("B");
	private static Node C = new Node("C");
	private static Node D = new Node("D");
	private static Node E = new Node("E");
	private static Node F = new Node("F");
	private static Node G = new Node("G");
	private static Node R = new Node("R");
	private static Node Entry = new Node("entry");
	private static Node Exit = new Node("exit");
	

	private static Edge eA = new Edge(Entry,A);
	private static Edge AB = new Edge(A,B);
	private static Edge BC = new Edge(B,C);
	private static Edge CD = new Edge(C,D);
	private static Edge DE = new Edge(D,E);
	private static Edge DF = new Edge(D,F);
	private static Edge Ee = new Edge(E,Exit);
	private static Edge EG = new Edge(E,G);
	private static Edge FG = new Edge(F,G);
	private static Edge Ge = new Edge(G,Exit);
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGraph = new Graph();

		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");
		Node G = new Node("G");
		Node R = new Node("R");
		Node Entry = new Node("entry");
		Node Exit = new Node("exit");
		
		A.setAttribute("label", "Node A");
		A.setAttribute("color", "red");

		Edge E1 = new Edge(Entry,A);
		Edge E2 = new Edge(A,B);
		Edge E3 = new Edge(B,C);
		Edge E4 = new Edge(C,D);
		Edge E5 = new Edge(D,E);
		Edge E6 = new Edge(D,F);
		Edge E7 = new Edge(E,Exit);
		Edge E8 = new Edge(E,G);
		Edge E9 = new Edge(F,G);
		Edge E10 = new Edge(G,Exit);
		
		E2.setAttribute("label", "AB");
		E3.setAttribute("type", "secure");
		
		Entry.addOutEdge(E1);
		A.addInEdge(E1);
		A.addOutEdge(E2);
		B.addInEdge(E2);
		B.addOutEdge(E3);
		C.addInEdge(E3);
		C.addOutEdge(E4);
		D.addInEdge(E4);
		D.addOutEdge(E5);
		D.addOutEdge(E6);
		E.addInEdge(E5);
		E.addOutEdge(E7);
		E.addOutEdge(E8);
		F.addInEdge(E6);
		F.addOutEdge(E9);
		G.addInEdge(E8);
		G.addInEdge(E9);
		G.addOutEdge(E10);
		Exit.addInEdge(E10);
		
		
		testGraph.addNode(A);
		testGraph.addNode(B);
		testGraph.addNode(C);
		testGraph.addNode(D);
		testGraph.addNode(E);
		testGraph.addNode(F);
		testGraph.addNode(G);
		testGraph.addNode(Entry);
		testGraph.addNode(Exit);
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	protected Graph copyGraph() {
		Graph testGraphCopy = new Graph();
		
		A.setAttribute("label", "Node A");
		A.setAttribute("color", "red");
		
		AB.setAttribute("label", "AB");
		BC.setAttribute("type", "secure");
		
		Entry.addOutEdge(eA);
		A.addInEdge(eA);
		A.addOutEdge(AB);
		B.addInEdge(AB);
		B.addOutEdge(BC);
		C.addInEdge(BC);
		C.addOutEdge(CD);
		D.addInEdge(CD);
		D.addOutEdge(DE);
		D.addOutEdge(DF);
		E.addInEdge(DE);
		E.addOutEdge(Ee);
		E.addOutEdge(EG);
		F.addInEdge(DF);
		F.addOutEdge(FG);
		G.addInEdge(EG);
		G.addInEdge(FG);
		G.addOutEdge(Ge);
		Exit.addInEdge(Ge);
		
		testGraphCopy.addNode(A);
		testGraphCopy.addNode(B);
		testGraphCopy.addNode(C);
		testGraphCopy.addNode(D);
		testGraphCopy.addNode(E);
		testGraphCopy.addNode(F);
		testGraphCopy.addNode(G);
		testGraphCopy.addNode(Entry);
		testGraphCopy.addNode(Exit);
		
		return testGraphCopy;
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	
	@Test
	public final void testEquals() {
		Graph testGraphCopy = copyGraph();
		assertEquals(testGraph, testGraphCopy);
	}
	
	@Test
	public final void testNotEqualEdgeSet() {
		Graph testGraphCopy = copyGraph();
		F.outEdges.remove(FG);
		G.outEdges.remove(FG);
		assertFalse(testGraph.equals(testGraphCopy));
	}
	
	@Test
	public final void testNotEqualEdgeAttributes() {
		Graph testGraphCopy = copyGraph();
		AB.setAttribute("label", "BA"); 
		assertFalse(testGraph.equals(testGraphCopy));
	}
	
	@Test
	public final void testNotEqualNodeSet() {
		Graph testGraphCopy = copyGraph();
		testGraphCopy.addNode(R);		
		assertFalse(testGraph.equals(testGraphCopy));
	}
	
	@Test
	public final void testNotEqualNodeAttributes() {
		Graph testGraphCopy = copyGraph();
		A.setAttribute("label", "Node C"); //error
		assertFalse(testGraph.equals(testGraphCopy));
	}
	
	@Test
	public final void testFromDotty() {
		Graph g = null;
		try {
			g = Graph.fromDotty(new File("resources/testGraph.dotty"));
		} catch (FileNotFoundException e) {
			fail("File not found");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
		
		System.out.println(testGraph.toDot());
		System.out.println(g.toDot());
		assertTrue(testGraph.equals(g));
	}

}
