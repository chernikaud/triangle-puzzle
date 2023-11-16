package edu.wpi.cs.dss.util;

import java.util.Set;
import java.util.stream.Collectors;

import edu.wpi.cs.dss.boundary.TrianglePuzzleApp;
import edu.wpi.cs.dss.domain.TrianglePuzzle;
import edu.wpi.cs.dss.model.impl.Node;

public class UpdateButtons {

    private UpdateButtons() {

    }

    public static void updateButtons(TrianglePuzzleApp trianglePuzzleApp) {
        final TrianglePuzzle trianglePuzzle = trianglePuzzleApp.getModel().getTrianglePuzzle();
        final Set<Node> selectedNodes = trianglePuzzle.getSelectedNodesStream().collect(Collectors.toSet());

        trianglePuzzleApp.getResetButton().setEnabled(true);
        trianglePuzzleApp.getUnselectAllNodesButton().setEnabled(!selectedNodes.isEmpty());
        trianglePuzzleApp.getSwapEdgesButton().setEnabled(selectedNodes.size() == 3);
    }
}
