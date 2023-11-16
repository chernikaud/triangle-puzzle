package edu.wpi.cs.dss.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.dss.model.impl.Edge;
import edu.wpi.cs.dss.model.impl.Node;

public class ModelTest {
	
	private static final int AMOUNT_OF_NODES = 10;
	
	private static final int X1 = 310;
	private static final int Y1 = 60;
	
	private static final int X2 = 240;
	private static final int Y2 = 130;
	
	private static final int X3 = 380;
	private static final int Y3 = 130;
	
    private static final int X5 = 310;
    private static final int Y5 = 200;
	
	private static final int X6 = 450;
	private static final int Y6 = 200;
	
	private Model model;
	
	@Before
	public void setUp() {
		model = new Model();
	}
	
	@Test
	public void testGetters() {
		assertEquals(0, model.getScore());
		assertEquals(0, model.getMoves());
		assertNotNull(model.getTrianglePuzzle());
	}
	
	@Test
	public void testSolution() {
		assertFalse(model.isTrianglePuzzleSolved());
	}
	
	@Test
	public void testUnselectNodes() {
		model.getTrianglePuzzle().getNodesStream().distinct().forEach(node -> node.setSelected(true));
		assertEquals(AMOUNT_OF_NODES, model.getTrianglePuzzle().getSelectedNodesStream().distinct().count());
		
		model.unselect();
		assertEquals(0, model.getTrianglePuzzle().getSelectedNodesStream().distinct().count());
	}
	
	@Test
	public void testSwapTwoEdges() {
		model.reset();
		
		final Supplier<Stream<Node>> nodes = () -> model.getTrianglePuzzle().getNodesStream().distinct();
		final Node node2 = findNode(X2, Y2, nodes);
		final Node node3 = findNode(X3, Y3, nodes);
		final Node node6 = findNode(X6, Y6, nodes);
		
		node2.setSelected(true);
		node3.setSelected(true);
		node6.setSelected(true);
		
		final Supplier<Stream<Edge>> selectedTwoEdges = () -> model.getTrianglePuzzle().getSelectedEdgesStream();
		final Edge edge32 = findEdge(node3, node2, selectedTwoEdges);
		final Edge edge36 = findEdge(node3, node6, selectedTwoEdges);
		
		final Color color32 = edge32.getCurrentColor();
		final Color color36 = edge36.getCurrentColor();
		
		model.swap();
		
		assertEquals(color36, edge32.getCurrentColor());
		assertEquals(color32, edge36.getCurrentColor());
		
		assertEquals(9, model.getScore());
		assertEquals(1, model.getMoves());
		assertEquals(0, model.getTrianglePuzzle().getSelectedNodesStream().count());
	}
	
	@Test
	public void swapThreeEdgesFormingTriangle() {
		model.reset();
		
		final Supplier<Stream<Node>> nodes = () -> model.getTrianglePuzzle().getNodesStream().distinct();
		final Node node1 = findNode(X1, Y1, nodes);
		final Node node2 = findNode(X2, Y2, nodes);
		final Node node3 = findNode(X3, Y3, nodes);
		
		node1.setSelected(true);
		node2.setSelected(true);
		node3.setSelected(true);
		
		final Supplier<Stream<Edge>> selectedTwoEdges = () -> model.getTrianglePuzzle().getSelectedEdgesStream();
		final Edge edge13 = findEdge(node1, node3, selectedTwoEdges);
		final Edge edge32 = findEdge(node3, node2, selectedTwoEdges);
		final Edge edge21 = findEdge(node2, node1, selectedTwoEdges);
		
		final Color color13 = edge13.getCurrentColor();
		final Color color32 = edge32.getCurrentColor();
		final Color color21 = edge21.getCurrentColor();
		
		model.swap();
		
		assertEquals(color13, edge32.getCurrentColor());
		assertEquals(color32, edge21.getCurrentColor());
		assertEquals(color21, edge13.getCurrentColor());
		
		assertEquals(-1, model.getScore());
		assertEquals(1, model.getMoves());
		assertEquals(0, model.getTrianglePuzzle().getSelectedNodesStream().count());
	}
	
	@Test
	public void swapThreeEdgesNotFormingTriangle() {
		model.reset();
		
		final Supplier<Stream<Node>> nodes = () -> model.getTrianglePuzzle().getNodesStream().distinct();
		final Node node2 = findNode(X2, Y2, nodes);
		final Node node3 = findNode(X3, Y3, nodes);
		final Node node5 = findNode(X5, Y5, nodes);
		
		node2.setSelected(true);
		node3.setSelected(true);
		node5.setSelected(true);
		
		final Supplier<Stream<Edge>> selectedTwoEdges = () -> model.getTrianglePuzzle().getSelectedEdgesStream();
		final Edge edge25 = findEdge(node2, node5, selectedTwoEdges);
		final Edge edge32 = findEdge(node3, node2, selectedTwoEdges);
		final Edge edge53 = findEdge(node5, node3, selectedTwoEdges);
		
		final Color color25 = edge25.getCurrentColor();
		final Color color32 = edge32.getCurrentColor();
		final Color color53 = edge53.getCurrentColor();
		
		model.swap();
		
		assertEquals(color25, edge32.getCurrentColor());
		assertEquals(color32, edge53.getCurrentColor());
		assertEquals(color53, edge25.getCurrentColor());
		
		assertEquals(-1, model.getScore());
		assertEquals(1, model.getMoves());
		assertEquals(0, model.getTrianglePuzzle().getSelectedNodesStream().count());
	}
	
	private Node findNode(int x, int y, Supplier<Stream<Node>> nodes) {
		return nodes.get().filter(node -> x == node.getX() && y == node.getY()).findAny().orElseThrow(() -> new RuntimeException("No such node, sorry ..."));
	}
	
	private Edge findEdge(Node node1, Node node2, Supplier<Stream<Edge>> selectedEdges) {
		return selectedEdges.get().filter(edge -> node1.equals(edge.getNode1()) && node2.equals(edge.getNode2())).findAny().orElseThrow(() -> new RuntimeException("No such edge, sorry ..."));
	}
}
