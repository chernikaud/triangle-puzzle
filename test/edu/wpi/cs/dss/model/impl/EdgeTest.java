package edu.wpi.cs.dss.model.impl;

import static org.junit.Assert.*;

import edu.wpi.cs.dss.model.EdgeSequence;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class EdgeTest {

	private static final int X1 = 310;
	private static final int Y1 = 60;

	private static final int X2 = 380;
	private static final int Y2 = 130;

	private Edge edge;
	private Node node1;
	private Node node2;
	private Color initialColor;
	private EdgeSequence edgeSequence;

	@Before
	public void setUp() {
		node1 = new Node(X1, Y1);
		node2 = new Node(X2, Y2);
		initialColor = Color.RED;
		edgeSequence = EdgeSequence.FIRST;
		edge = new Edge(node1, node2, initialColor, edgeSequence);
	}

	@Test
	public void testGetters() {
		assertEquals(node1, edge.getNode1());
		assertEquals(node2, edge.getNode2());
		assertEquals(initialColor, edge.getCurrentColor());
		assertEquals(edgeSequence.getValue(), edge.getSequence().getValue());
	}
	
	@Test
	public void testSetters() {
		final Color newColor = Color.BLUE;
		edge.setCurrentColor(newColor);
		assertEquals(newColor, edge.getCurrentColor());
	}
	
	@Test
	public void testReset() {
		edge.reset();
		assertEquals(Color.RED, edge.getCurrentColor());
	}
	
	@Test
	public void testSelection() {
		node1.setSelected(true);
		node2.setSelected(false);
		assertFalse(edge.isSelected());
		
		node1.setSelected(false);
		node2.setSelected(true);
		assertFalse(edge.isSelected());
		
		node1.setSelected(true);
		node2.setSelected(true);
		assertTrue(edge.isSelected());
	}
	
	@Test
	public void testHashCode() {
		assertEquals(edge.hashCode(), edge.hashCode());
	}

	@Test
	public void testEquality() {
		final Node node11 = new Node(X1, Y1);
		final Node node12 = new Node(X2, Y2);
		final Node node21 = new Node(X1 + Y1, Y1);
		final Node node22 = new Node(X1, Y1 + Y2);
		final Edge edge1 = new Edge(node11, node12, Color.RED, EdgeSequence.FIRST);
		final Edge edge2 = new Edge(node21, node12, Color.RED, EdgeSequence.FIRST);
		final Edge edge3 = new Edge(node11, node22, Color.RED, EdgeSequence.FIRST);
		final Edge edge4 = new Edge(node11, node12, Color.GREEN, EdgeSequence.FIRST);
		
		assertEquals(edge, edge);
		assertEquals(edge, edge1);
		assertNotEquals(edge, "");
		assertNotEquals(edge, null);
		assertNotEquals(edge, edge2);
		assertNotEquals(edge, edge3);
		assertNotEquals(edge, edge4);
	}
}
