package edu.wpi.cs.dss.boundary;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.dss.domain.Model;

public class TrianglePuzzleAppTest {
	
	private static final String SCORE = "0";
	private static final String AMOUNT_OF_MOVES = "0";
	private static final String RESET_BUTTON_TEXT = "Reset";
	private static final String SWAP_EDGES_BUTTON_TEXT = "Swap edges";
	private static final String UNSELECT_ALL_NODES_BUTTON_TEXT = "Unselect all";

	private TrianglePuzzleApp trianglePuzzleApp;
	
	@Before
	public void setUp() {
		final Model model = new Model();
		trianglePuzzleApp = new TrianglePuzzleApp(model);
	}
	
	@Test
	public void testGetters() {
		assertEquals(SCORE, trianglePuzzleApp.getScoreLabel().getText());
		assertEquals(AMOUNT_OF_MOVES, trianglePuzzleApp.getMovesLabel().getText());
		assertEquals(RESET_BUTTON_TEXT, trianglePuzzleApp.getResetButton().getText());
		assertEquals(SWAP_EDGES_BUTTON_TEXT, trianglePuzzleApp.getSwapEdgesButton().getText());
		assertEquals(UNSELECT_ALL_NODES_BUTTON_TEXT, trianglePuzzleApp.getUnselectAllNodesButton().getText());
	}

}
