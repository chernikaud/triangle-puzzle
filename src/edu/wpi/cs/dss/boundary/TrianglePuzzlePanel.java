package edu.wpi.cs.dss.boundary;

import java.awt.*;

import javax.swing.JPanel;

import edu.wpi.cs.dss.domain.Model;
import edu.wpi.cs.dss.model.impl.Triangle;

public class TrianglePuzzlePanel extends JPanel {

	private static final long serialVersionUID = 5690976308909064901L;

	private final Model model;

	public TrianglePuzzlePanel(Model model) {
		this.model = model;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		if (model == null) {
			return;
		}

		final Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setStroke(new BasicStroke(3));

		for (Triangle triangle : model.getTrianglePuzzle().getCurrent()) {
			triangle.draw(graphics2D);
		}
	}
}
