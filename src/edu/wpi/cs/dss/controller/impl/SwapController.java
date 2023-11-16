package edu.wpi.cs.dss.controller.impl;

import javax.swing.JOptionPane;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.controller.SwingController;
import edu.wpi.cs.dss.domain.Model;
import edu.wpi.cs.dss.util.UpdateButtons;

public class SwapController implements SwingController {

    private final Model model;
    private final TrianglePuzzleApp trianglePuzzleApp;

    public SwapController(Model model, TrianglePuzzleApp trianglePuzzleApp) {
        this.model = model;
        this.trianglePuzzleApp = trianglePuzzleApp;
    }

    @Override
    public void process() {
        model.swap();

        trianglePuzzleApp.getMovesLabel().setText("" + model.getMoves());
        trianglePuzzleApp.getScoreLabel().setText("" + model.getScore());

        final boolean isSolved = model.isTrianglePuzzleSolved();
        if (isSolved) {
        	JOptionPane.showMessageDialog(trianglePuzzleApp, "Congratulations! You have solved this puzzle!\nYour score is: " + model.getScore() + "! You have made " + model.getMoves() + " moves!\nIf you want to solve this puzzle again, click Reset button!");
        }

        UpdateButtons.updateButtons(trianglePuzzleApp);
        trianglePuzzleApp.repaint();
    }
}
