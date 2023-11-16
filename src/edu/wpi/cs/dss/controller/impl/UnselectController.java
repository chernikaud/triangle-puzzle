package edu.wpi.cs.dss.controller.impl;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.controller.SwingController;
import edu.wpi.cs.dss.domain.Model;
import edu.wpi.cs.dss.util.UpdateButtons;

public class UnselectController implements SwingController {

    private final Model model;
    private final TrianglePuzzleApp trianglePuzzleApp;

    public UnselectController(Model model, TrianglePuzzleApp trianglePuzzleApp) {
        this.model = model;
        this.trianglePuzzleApp = trianglePuzzleApp;
    }

    @Override
    public void process() {
        model.unselect();
        UpdateButtons.updateButtons(trianglePuzzleApp);
        trianglePuzzleApp.repaint();
    }
}
