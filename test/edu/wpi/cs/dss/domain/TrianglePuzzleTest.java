package edu.wpi.cs.dss.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrianglePuzzleTest {
	
	private static final int AMOUNT_OF_NODES = 10;
	private static final int AMOUNT_OF_EDGES = 18;
	private static final int AMOUNT_OF_TRIANGLES = 6;

	private TrianglePuzzle trianglePuzzle;
	
	@Before
	public void setUp() {
		trianglePuzzle = new TrianglePuzzle();
	}
	
	@Test
	public void testGetters() {
		assertEquals(AMOUNT_OF_TRIANGLES, trianglePuzzle.getCurrent().size());
		assertEquals(AMOUNT_OF_TRIANGLES, trianglePuzzle.getWinning().size());
	}
	
	@Test
	public void testNodes() {
		assertEquals(AMOUNT_OF_NODES, trianglePuzzle.getNodesStream().distinct().count());
	}
	
	@Test
	public void testSelectedNodes() {
		trianglePuzzle.getNodesStream().distinct().forEach(node -> node.setSelected(true));
		assertEquals(AMOUNT_OF_NODES, trianglePuzzle.getSelectedNodesStream().distinct().count());
	}
	
	@Test
	public void testSelectedEdges() {
		trianglePuzzle.getNodesStream().distinct().forEach(node -> node.setSelected(true));
		assertEquals(AMOUNT_OF_EDGES, trianglePuzzle.getSelectedEdgesStream().count());
	}
	
	@Test
	public void testSelectedTriangle() {
		trianglePuzzle.getNodesStream().distinct().forEach(node -> node.setSelected(true));
		assertTrue(trianglePuzzle.getSelectedTriangle().isPresent());
	}
}
