package edu.wpi.cs.dss.model.impl;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

import edu.wpi.cs.dss.model.DrawableEntity;

public class Node implements Serializable, DrawableEntity {

    private static final long serialVersionUID = -175726591686534430L;

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private final int x;
    private final int y;
    private boolean selected;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.selected = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void select(int x, int y) {
        if (this.x <= x && x <= this.x + WIDTH && this.y <= y && y <= this.y + HEIGHT) {
            selected = !selected;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);

        if (selected) {
            graphics2D.fillRect(x, y, WIDTH, HEIGHT);
        } else {
            graphics2D.drawRect(x, y, WIDTH, HEIGHT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
