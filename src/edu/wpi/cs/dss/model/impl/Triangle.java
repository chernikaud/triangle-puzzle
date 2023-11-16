package edu.wpi.cs.dss.model.impl;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

import edu.wpi.cs.dss.model.DrawableEntity;
import edu.wpi.cs.dss.model.ResettableEntity;

public class Triangle implements Serializable, DrawableEntity, ResettableEntity {

    private static final long serialVersionUID = 938433278063093733L;

    private final Edge edge1;
    private final Edge edge2;
    private final Edge edge3;
    private boolean bonusApplied;

    public Triangle(Edge edge1, Edge edge2, Edge edge3) {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
        this.bonusApplied = false;
    }

    public Edge getEdge1() {
        return edge1;
    }

    public Edge getEdge2() {
        return edge2;
    }

    public Edge getEdge3() {
        return edge3;
    }

    public boolean isBonusApplied() {
        return bonusApplied;
    }

    public void setBonusApplied(boolean bonusApplied) {
        this.bonusApplied = bonusApplied;
    }

    public boolean isOneColorTriangle() {
        final Color firstColor = edge1.getCurrentColor();
        final Color secondColor = edge2.getCurrentColor();
        final Color thirdColor = edge3.getCurrentColor();

        return firstColor.equals(secondColor) && firstColor.equals(thirdColor);
    }

    @Override
    public void reset() {
        Stream.of(edge1, edge2, edge3).forEach(Edge::reset);
    }

    @Override
    public boolean isSelected() {
        return edge1.isSelected() && edge2.isSelected();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Stream.of(edge1, edge2, edge3).forEach(edge -> edge.draw(graphics2D));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(edge1, triangle.edge1) && Objects.equals(edge2, triangle.edge2) && Objects.equals(edge3, triangle.edge3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edge1, edge2, edge3);
    }
}
