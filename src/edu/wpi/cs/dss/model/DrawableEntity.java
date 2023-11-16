package edu.wpi.cs.dss.model;

import java.awt.*;

public interface DrawableEntity {
    boolean isSelected();
    void draw(Graphics2D graphics2D);
}
