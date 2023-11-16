package edu.wpi.cs.dss.domain;

import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import edu.wpi.cs.dss.model.EdgeSequence;
import edu.wpi.cs.dss.model.impl.Node;
import edu.wpi.cs.dss.model.impl.Triangle;
import edu.wpi.cs.dss.model.impl.Edge;

public class TrianglePuzzle implements Serializable {

    private static final long serialVersionUID = 6982690619260990728L;

    private final List<Triangle> current;
    private final List<Triangle> winning;

    public TrianglePuzzle() {
        this.current = init();
        this.winning = initWinning();
    }

    public List<Triangle> getCurrent() {
        return current;
    }

    public List<Triangle> getWinning() {
        return winning;
    }

    public Stream<Node> getNodesStream() {
        return getEdgesStream().flatMap(edge -> Stream.of(edge.getNode1(), edge.getNode2()));
    }

    public Stream<Node> getSelectedNodesStream() {
        return getNodesStream().filter(Node::isSelected);
    }

    private Stream<Edge> getEdgesStream() {
        return current.stream().flatMap(triangle -> Stream.of(triangle.getEdge1(), triangle.getEdge2(), triangle.getEdge3()));
    }

    public Stream<Edge> getSelectedEdgesStream() {
        return getEdgesStream().filter(Edge::isSelected);
    }

    public Optional<Triangle> getSelectedTriangle() {
        return current.stream().filter(Triangle::isSelected).findFirst();
    }

    private List<Triangle> init() {
        final Node node1 = new Node(310, 60);
        final Node node2 = new Node(240, 130);
        final Node node3 = new Node(380, 130);
        final Node node4 = new Node(170, 200);
        final Node node5 = new Node(310, 200);
        final Node node6 = new Node(450, 200);
        final Node node7 = new Node(100, 270);
        final Node node8 = new Node(240, 270);
        final Node node9 = new Node(380, 270);
        final Node node10 = new Node(520, 270);

        final Edge edge1 = new Edge(node1, node3, Color.RED, EdgeSequence.FIRST);
        final Edge edge2 = new Edge(node3, node2, Color.GREEN, EdgeSequence.SECOND);
        final Edge edge3 = new Edge(node2, node1, Color.RED, EdgeSequence.THIRD);
        final Edge edge4 = new Edge(node2, node5, Color.BLUE, EdgeSequence.FIRST);
        final Edge edge5 = new Edge(node5, node4, Color.GREEN, EdgeSequence.SECOND);
        final Edge edge6 = new Edge(node4, node2, Color.RED, EdgeSequence.THIRD);
        final Edge edge7 = new Edge(node3, node6, Color.RED, EdgeSequence.FIRST);
        final Edge edge8 = new Edge(node6, node5, Color.GREEN, EdgeSequence.SECOND);
        final Edge edge9 = new Edge(node5, node3, Color.BLUE, EdgeSequence.THIRD);
        final Edge edge10 = new Edge(node4, node8, Color.BLUE, EdgeSequence.FIRST);
        final Edge edge11 = new Edge(node8, node7, Color.GREEN, EdgeSequence.SECOND);
        final Edge edge12 = new Edge(node7, node4, Color.RED, EdgeSequence.THIRD);
        final Edge edge13 = new Edge(node5, node9, Color.BLUE, EdgeSequence.FIRST);
        final Edge edge14 = new Edge(node9, node8, Color.GREEN, EdgeSequence.SECOND);
        final Edge edge15 = new Edge(node8, node5, Color.BLUE, EdgeSequence.THIRD);
        final Edge edge16 = new Edge(node6, node10, Color.RED, EdgeSequence.FIRST);
        final Edge edge17 = new Edge(node10, node9, Color.GREEN, EdgeSequence.SECOND);
        final Edge edge18 = new Edge(node9, node6, Color.BLUE, EdgeSequence.THIRD);

        final Triangle triangle1 = new Triangle(edge1, edge2, edge3);
        final Triangle triangle2 = new Triangle(edge4, edge5, edge6);
        final Triangle triangle3 = new Triangle(edge7, edge8, edge9);
        final Triangle triangle4 = new Triangle(edge10, edge11, edge12);
        final Triangle triangle5 = new Triangle(edge13, edge14, edge15);
        final Triangle triangle6 = new Triangle(edge16, edge17, edge18);

        return Arrays.asList(triangle1, triangle2, triangle3, triangle4, triangle5, triangle6);
    }

    private List<Triangle> initWinning() {
        final Node winningNode1 = new Node(310, 60);
        final Node winningNode2 = new Node(240, 130);
        final Node winningNode3 = new Node(380, 130);
        final Node winningNode4 = new Node(170, 200);
        final Node winningNode5 = new Node(310, 200);
        final Node winningNode6 = new Node(450, 200);
        final Node winningNode7 = new Node(100, 270);
        final Node winningNode8 = new Node(240, 270);
        final Node winningNode9 = new Node(380, 270);
        final Node winningNode10 = new Node(520, 270);

        final Edge winningEdge1 = new Edge(winningNode1, winningNode3, Color.RED, EdgeSequence.FIRST);
        final Edge winningEdge2 = new Edge(winningNode3, winningNode2, Color.RED, EdgeSequence.SECOND);
        final Edge winningEdge3 = new Edge(winningNode2, winningNode1, Color.RED, EdgeSequence.THIRD);
        final Edge winningEdge4 = new Edge(winningNode2, winningNode5, Color.BLUE, EdgeSequence.FIRST);
        final Edge winningEdge5 = new Edge(winningNode5, winningNode4, Color.BLUE, EdgeSequence.SECOND);
        final Edge winningEdge6 = new Edge(winningNode4, winningNode2, Color.BLUE, EdgeSequence.THIRD);
        final Edge winningEdge7 = new Edge(winningNode3, winningNode6, Color.GREEN, EdgeSequence.FIRST);
        final Edge winningEdge8 = new Edge(winningNode6, winningNode5, Color.GREEN, EdgeSequence.SECOND);
        final Edge winningEdge9 = new Edge(winningNode5, winningNode3, Color.GREEN, EdgeSequence.THIRD);
        final Edge winningEdge10 = new Edge(winningNode4, winningNode8, Color.GREEN, EdgeSequence.FIRST);
        final Edge winningEdge11 = new Edge(winningNode8, winningNode7, Color.GREEN, EdgeSequence.SECOND);
        final Edge winningEdge12 = new Edge(winningNode7, winningNode4, Color.GREEN, EdgeSequence.THIRD);
        final Edge winningEdge13 = new Edge(winningNode5, winningNode9, Color.RED, EdgeSequence.FIRST);
        final Edge winningEdge14 = new Edge(winningNode9, winningNode8, Color.RED, EdgeSequence.SECOND);
        final Edge winningEdge15 = new Edge(winningNode8, winningNode5, Color.RED, EdgeSequence.THIRD);
        final Edge winningEdge16 = new Edge(winningNode6, winningNode10, Color.BLUE, EdgeSequence.FIRST);
        final Edge winningEdge17 = new Edge(winningNode10, winningNode9, Color.BLUE, EdgeSequence.SECOND);
        final Edge winningEdge18 = new Edge(winningNode9, winningNode6, Color.BLUE, EdgeSequence.THIRD);

        final Triangle winningTriangle1 = new Triangle(winningEdge1, winningEdge2, winningEdge3);
        final Triangle winningTriangle2 = new Triangle(winningEdge4, winningEdge5, winningEdge6);
        final Triangle winningTriangle3 = new Triangle(winningEdge7, winningEdge8, winningEdge9);
        final Triangle winningTriangle4 = new Triangle(winningEdge10, winningEdge11, winningEdge12);
        final Triangle winningTriangle5 = new Triangle(winningEdge13, winningEdge14, winningEdge15);
        final Triangle winningTriangle6 = new Triangle(winningEdge16, winningEdge17, winningEdge18);

        return Arrays.asList(winningTriangle1, winningTriangle2, winningTriangle3, winningTriangle4, winningTriangle5, winningTriangle6);
    }
}
