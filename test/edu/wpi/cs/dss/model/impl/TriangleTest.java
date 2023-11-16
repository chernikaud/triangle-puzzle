package edu.wpi.cs.dss.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.dss.model.EdgeSequence;

public class TriangleTest {

	private static final int X1 = 310;
	private static final int Y1 = 60;
	
	private static final int X2 = 240;
	private static final int Y2 = 130;
	
	private static final int X3 = 380;
	private static final int Y3 = 130;
	
	private static final Color COLOR_1 = Color.RED;
	private static final Color COLOR_2 = Color.GREEN;
	private static final Color COLOR_3 = Color.BLUE;
	
	private Node node1;
	private Node node2;
	private Node node3;
	
	private Edge edge1;
	private Edge edge2;
	private Edge edge3;
	
	private Triangle triangle;
	
	@Before
	public void setUp() {
        node1 = new Node(X1, Y1);
        node2 = new Node(X2, Y2);
        node3 = new Node(X3, Y3);
        
        edge1 = new Edge(node1, node3, COLOR_1, EdgeSequence.FIRST);
        edge2 = new Edge(node3, node2, COLOR_2, EdgeSequence.SECOND);
        edge3 = new Edge(node2, node1, COLOR_3, EdgeSequence.THIRD);
        
        triangle = new Triangle(edge1, edge2, edge3);
	}
	
	@Test
	public void testGetters() {
		assertEquals(triangle.getEdge1(), edge1);
		assertEquals(triangle.getEdge2(), edge2);
		assertEquals(triangle.getEdge3(), edge3);
	}
	
	@Test
	public void testSetters() {
		triangle.setBonusApplied(true);
		assertTrue(triangle.isBonusApplied());
	}
	
	@Test
	public void testColorEquality() {
		assertFalse(triangle.isOneColorTriangle());
		
		triangle.getEdge2().setCurrentColor(Color.RED);
		assertFalse(triangle.isOneColorTriangle());
		
		triangle.getEdge3().setCurrentColor(Color.RED);
		assertTrue(triangle.isOneColorTriangle());
	}
	
	@Test
	public void testReset() {
		triangle.reset();
		assertEquals(COLOR_1, triangle.getEdge1().getCurrentColor());
		assertEquals(COLOR_2, triangle.getEdge2().getCurrentColor());
		assertEquals(COLOR_3, triangle.getEdge3().getCurrentColor());
	}
	
	@Test
	public void testSelection() {
		triangle.getEdge1().getNode1().setSelected(false);
		triangle.getEdge1().getNode2().setSelected(false);
		triangle.getEdge2().getNode1().setSelected(false);
		triangle.getEdge2().getNode2().setSelected(false);
		assertFalse(triangle.isSelected());
		
		triangle.getEdge1().getNode1().setSelected(true);
		triangle.getEdge1().getNode2().setSelected(true);
		assertFalse(triangle.isSelected());
		
		triangle.getEdge1().getNode1().setSelected(false);
		triangle.getEdge1().getNode2().setSelected(false);
		triangle.getEdge2().getNode1().setSelected(true);
		triangle.getEdge2().getNode2().setSelected(true);
		assertFalse(triangle.isSelected());
		
		triangle.getEdge1().getNode1().setSelected(true);
		triangle.getEdge1().getNode2().setSelected(true);
		assertTrue(triangle.isSelected());
	}
	
	@Test
	public void testHashCode() {
		assertEquals(triangle.hashCode(), triangle.hashCode());
	}
	
	@Test
	public void testEquality() {
		final Edge fakeEdge1 = new Edge(node1, node3, Color.BLACK, EdgeSequence.FIRST);
		final Edge fakeEdge2 = new Edge(node3, node2, Color.BLACK, EdgeSequence.SECOND);
        final Edge fakeEdge3 = new Edge(node2, node1, Color.BLACK, EdgeSequence.THIRD);
		
        final Triangle trueTriangle = new Triangle(edge1, edge2, edge3);
		final Triangle fakeTriangle1 = new Triangle(fakeEdge1, edge2, edge3);
		final Triangle fakeTriangle2 = new Triangle(edge1, fakeEdge2, edge3);
		final Triangle fakeTriangle3 = new Triangle(edge1, edge2, fakeEdge3);
		
		assertEquals(triangle, triangle);
		assertEquals(triangle, trueTriangle);
		
		assertNotEquals(triangle, "");
		assertNotEquals(triangle, null);
		assertNotEquals(triangle, fakeTriangle1);
		assertNotEquals(triangle, fakeTriangle2);
		assertNotEquals(triangle, fakeTriangle3);
	}
}
