package edu.wpi.cs.dss.domain;

import java.awt.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.wpi.cs.dss.model.impl.Triangle;
import edu.wpi.cs.dss.model.impl.Edge;

public class Model implements Serializable {

	private static final long serialVersionUID = 4402797725308618008L;

	private final TrianglePuzzle trianglePuzzle;

	private int score;
	private int moves;
	
	public Model() {
		score = 0;
		moves = 0;
		trianglePuzzle = new TrianglePuzzle();
	}

	public int getScore() {
		return score;
	}

	public int getMoves() {
		return moves;
	}

	public TrianglePuzzle getTrianglePuzzle() {
		return trianglePuzzle;
	}

	public void reset() {
		score = 0;
		moves = 0;
		unselect();
		trianglePuzzle.getCurrent().forEach(Triangle::reset);
	}

	public void unselect() {
		trianglePuzzle.getNodesStream().collect(Collectors.toSet()).forEach(node -> node.setSelected(false));
	}

	public void swap() {
		final List<Edge> selectedEdges = trianglePuzzle.getSelectedEdgesStream().collect(Collectors.toList());

		if (selectedEdges.size() == 2) {
			swapTwoEdges(selectedEdges);
		} else {
			swapThreeEdges(selectedEdges);
		}

		unselect();
		updateStatistics();
	}

	public boolean isTrianglePuzzleSolved() {
		final List<Triangle> currentTriangles = trianglePuzzle.getCurrent();
		final List<Triangle> winningTriangles = trianglePuzzle.getWinning();
		return currentTriangles.equals(winningTriangles);
	}

	private void updateStatistics() {
		score--;
		moves++;

		trianglePuzzle.getCurrent().stream().filter(triangle -> !triangle.isBonusApplied() && triangle.isOneColorTriangle()).forEach(triangle -> {
			triangle.setBonusApplied(true);
			score = score + 10;
		});
	}

	private void swapTwoEdges(List<Edge> selectedEdges) {
		final Edge edge1 = selectedEdges.get(0);
		final Edge edge2 = selectedEdges.get(1);
		final Color color1 = edge1.getCurrentColor();
		final Color color2 = edge2.getCurrentColor();

		edge1.setCurrentColor(color2);
		edge2.setCurrentColor(color1);
	}

	private void swapThreeEdges(List<Edge> selectedEdges) {
		final Optional<Triangle> maybeSelectedTriangle = trianglePuzzle.getSelectedTriangle();

		if (maybeSelectedTriangle.isPresent()) {
			final Triangle selectedTriangle = maybeSelectedTriangle.get();
			rotateTriangleClockwise(selectedTriangle.getEdge1(), selectedTriangle.getEdge2(), selectedTriangle.getEdge3());
		} else {
			final List<Edge> sortedSelectedEdges = selectedEdges.stream().sorted(Comparator.comparingInt(edge -> edge.getSequence().getValue())).collect(Collectors.toList());
			rotateTriangleClockwise(sortedSelectedEdges.get(0), sortedSelectedEdges.get(1), sortedSelectedEdges.get(2));
		}
	}

	private void rotateTriangleClockwise(Edge edge1, Edge edge2, Edge edge3) {
		final Color color1 = edge1.getCurrentColor();
		final Color color2 = edge2.getCurrentColor();
		final Color color3 = edge3.getCurrentColor();

		edge1.setCurrentColor(color3);
		edge2.setCurrentColor(color1);
		edge3.setCurrentColor(color2);
	}
}
