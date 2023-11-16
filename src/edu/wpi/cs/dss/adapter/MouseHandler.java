package edu.wpi.cs.dss.adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.domain.Model;
import edu.wpi.cs.dss.util.UpdateButtons;

public class MouseHandler extends MouseAdapter {

	private final Model model;
	private final TrianglePuzzleApp trianglePuzzleApp;

	public MouseHandler(Model model, TrianglePuzzleApp trianglePuzzleApp) {
		this.model = model;
		this.trianglePuzzleApp = trianglePuzzleApp;
	}

	@Override
    public void mousePressed(MouseEvent e) {
		final int x = e.getPoint().x;
		final int y = e.getPoint().y;

		if (!model.isTrianglePuzzleSolved()) {
			model.getTrianglePuzzle().getNodesStream().collect(Collectors.toSet()).forEach(node -> node.select(x, y));
			UpdateButtons.updateButtons(trianglePuzzleApp);
			trianglePuzzleApp.repaint();
		}
	}
}
