package edu.wpi.cs.dss.controller.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.controller.SwingController;
import edu.wpi.cs.dss.domain.Model;

public class UnselectControllerTest {
	
	private static final int TOTAL_AMOUNT_OF_NODES = 10;
	
	private Model model;
	private SwingController unselectController;
	
	@Before
	public void setUp() {
		model = new Model();
		unselectController = new UnselectController(model, new TrianglePuzzleApp(model));
	}

	@Test
	public void testUnselect() {
		model.getTrianglePuzzle().getNodesStream().distinct().forEach(node -> node.setSelected(true));
		assertEquals(TOTAL_AMOUNT_OF_NODES, model.getTrianglePuzzle().getSelectedNodesStream().distinct().count());
		
		unselectController.process();
		
		assertEquals(0, model.getTrianglePuzzle().getSelectedNodesStream().distinct().count());
	}
}
