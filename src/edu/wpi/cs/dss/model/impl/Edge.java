package edu.wpi.cs.dss.model.impl;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

import edu.wpi.cs.dss.model.DrawableEntity;
import edu.wpi.cs.dss.model.EdgeSequence;
import edu.wpi.cs.dss.model.ResettableEntity;

public class Edge implements Serializable, DrawableEntity, ResettableEntity {

    private static final long serialVersionUID = -8813389750765127562L;

    private final Node node1;
    private final Node node2;
    private final Color initialColor;
    private final EdgeSequence sequence;

    private Color currentColor;

    public Edge(Node node1, Node node2, Color color, EdgeSequence sequence) {
        this.node1 = node1;
        this.node2 = node2;
        this.sequence = sequence;
        this.initialColor = color;
        this.currentColor = color;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public EdgeSequence getSequence() {
        return sequence;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public void reset() {
        this.currentColor = initialColor;
    }

    @Override
    public boolean isSelected() {
        return node1.isSelected() && node2.isSelected();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Stream.of(node1, node2).forEach(node -> node.draw(graphics2D));

        graphics2D.setColor(currentColor);
        if (EdgeSequence.FIRST.equals(sequence)) {
            drawFirstTriangleEdge(graphics2D);
        } else if (EdgeSequence.SECOND.equals(sequence)) {
            drawSecondTriangleEdge(graphics2D);
        } else {
            drawThirdTriangleEdge(graphics2D);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(node1, edge.node1) && Objects.equals(node2, edge.node2) && Objects.equals(currentColor, edge.currentColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node1, node2, currentColor);
    }

    private void drawFirstTriangleEdge(Graphics2D graphics2D) {
        graphics2D.drawLine(node1.getX() + Node.WIDTH, node1.getY() + Node.HEIGHT, node2.getX(), node2.getY());
    }

    private void drawSecondTriangleEdge(Graphics2D graphics2D) {
        graphics2D.drawLine(node1.getX(), node1.getY() + Node.HEIGHT / 2, node2.getX() + Node.WIDTH, node2.getY() + Node.HEIGHT / 2);
    }

    private void drawThirdTriangleEdge(Graphics2D graphics2D) {
        graphics2D.drawLine(node1.getX() + Node.WIDTH, node1.getY(), node2.getX(), node2.getY() + Node.HEIGHT);
    }
}
