package edu.wpi.cs.dss;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.domain.Model;

public class Main {

	public static void main(String[] args) {
		final Model model = new Model();
		final TrianglePuzzleApp trianglePuzzleApp = new TrianglePuzzleApp(model);

		trianglePuzzleApp.setVisible(true);
	}

}
