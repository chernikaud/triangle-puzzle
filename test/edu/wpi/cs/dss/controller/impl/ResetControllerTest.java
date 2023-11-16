package edu.wpi.cs.dss.controller.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.controller.SwingController;
import edu.wpi.cs.dss.domain.Model;

public class ResetControllerTest {
	
	private static final String SCORE = "0";
	private static final String AMOUNT_OF_MOVES = "0";
	
	private static final int TOTAL_AMOUNT_OF_NODES = 10;
	
	private Model model;
	private SwingController resetController;
	private TrianglePuzzleApp trianglePuzzleApp;
	
	@Before
	public void setUp() {
		model = new Model();
		trianglePuzzleApp = new TrianglePuzzleApp(model);
		resetController = new ResetController(model, trianglePuzzleApp);
	}
	
	@Test
	public void testReset() {
		trianglePuzzleApp.getScoreLabel().setText("50");
		trianglePuzzleApp.getMovesLabel().setText("10");
		model.getTrianglePuzzle().getNodesStream().distinct().forEach(node -> node.setSelected(true));
		assertEquals(TOTAL_AMOUNT_OF_NODES, model.getTrianglePuzzle().getSelectedNodesStream().distinct().count());
		
		resetController.process();
		
		assertEquals(SCORE, trianglePuzzleApp.getScoreLabel().getText());
		assertEquals(AMOUNT_OF_MOVES, trianglePuzzleApp.getMovesLabel().getText());
		assertEquals(0, model.getTrianglePuzzle().getSelectedNodesStream().count());
	}
}
