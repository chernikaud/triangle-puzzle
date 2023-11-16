package edu.wpi.cs.dss.controller.impl;

import static org.junit.Assert.assertEquals;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.controller.SwingController;
import edu.wpi.cs.dss.domain.Model;
import edu.wpi.cs.dss.model.impl.Node;

public class SwapControllerTest {
	
	private static final int X2 = 240;
	private static final int Y2 = 130;
	
	private static final int X3 = 380;
	private static final int Y3 = 130;
	
	private static final int X6 = 450;
	private static final int Y6 = 200;
	
	private static final String SCORE = "9";
	private static final String AMOUNT_OF_MOVES = "1";
	
	private Model model;
	private SwingController swapController;
	private TrianglePuzzleApp trianglePuzzleApp;
	
	@Before
	public void setUp() {
		model = new Model();
		trianglePuzzleApp = new TrianglePuzzleApp(model);
		swapController = new SwapController(model, trianglePuzzleApp);
	}
	
	@Test
	public void testSwap() {
		final Supplier<Stream<Node>> nodes = () -> model.getTrianglePuzzle().getNodesStream().distinct();
		final Node node2 = findNode(X2, Y2, nodes);
		final Node node3 = findNode(X3, Y3, nodes);
		final Node node6 = findNode(X6, Y6, nodes);
		
		node2.setSelected(true);
		node3.setSelected(true);
		node6.setSelected(true);
		
		swapController.process();
		
		assertEquals(SCORE, trianglePuzzleApp.getScoreLabel().getText());
		assertEquals(AMOUNT_OF_MOVES, trianglePuzzleApp.getMovesLabel().getText());
		assertEquals(0, model.getTrianglePuzzle().getSelectedNodesStream().count());
	}

	private Node findNode(int x, int y, Supplier<Stream<Node>> nodes) {
		return nodes.get().filter(node -> x == node.getX() && y == node.getY()).findAny().orElseThrow(() -> new RuntimeException("No such node, sorry ..."));
	}
}
