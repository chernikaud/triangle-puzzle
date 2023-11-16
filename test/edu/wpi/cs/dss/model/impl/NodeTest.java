package edu.wpi.cs.dss.model.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    private static final int X = 310;
    private static final int Y = 60;

    private Node node;

    @Before
    public void init() {
        node = new Node(X, Y);
    }

    @Test
    public void testGetters() {
        assertEquals(X, node.getX());
        assertEquals(Y, node.getY());
        assertFalse(node.isSelected());
    }

    @Test
    public void testSetter() {
        node.setSelected(true);
        assertTrue(node.isSelected());
    }

    @Test
    public void testSelect() {
        node.select(X + Node.WIDTH / 2, Y + Node.HEIGHT / 2);
        assertTrue(node.isSelected());

        node.select(X - Node.WIDTH / 2, Y);
        assertTrue(node.isSelected());
        
        node.select(X, Y - Node.HEIGHT / 2);
        assertTrue(node.isSelected());
        
        node.select(X + 2 * Node.WIDTH, Y);
        assertTrue(node.isSelected());
        
        node.select(X, Y + 2 * Node.HEIGHT);
        assertTrue(node.isSelected());
        
        node.select(X, Y);
        assertFalse(node.isSelected());
    }

    @Test
    public void testEquality() {
        final Node node1 = new Node(X, Y);
        final Node node2 = new Node(X, Y + X);
        final Node node3 = new Node(X + Y, Y);
        
        assertEquals(node, node);
        assertEquals(node, node1);
        assertNotEquals(node, "");
        assertNotEquals(node, null);
        assertNotEquals(node, node2);
        assertNotEquals(node, node3);
    }

    @Test
    public void testHasCode() {
        Node node1 = new Node(X, Y);
        Node node2 = new Node(X + Y, X - Y);
        assertEquals(node.hashCode(), node1.hashCode());
        assertNotEquals(node.hashCode(), node2.hashCode());
    }
}
