package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;

import java.util.*;

public class WeightedGraph {
    private final Map<Vec2, Set<Vec2>> adjacentList = new HashMap<>();
    private final Map<Vec2, Map<Vec2, Double>> weights = new HashMap<>();
    private final Map<Vec2, Double> nodeWeights = new HashMap<>();

    //TODO: Do this instead
    private final Map<Vec2, WeightedGraphNode> nodes = new HashMap<>();

    public WeightedGraph(long[][] heightMap, ChunkPos chunkPos) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                // Add current node to graph
                addNode(new Vec2(x, z), heightMap[x][z]);
            }
        }
    }

    // Adds a node to the graph
    public void addNode(Vec2 node, double weight) {
        //adjacentList.computeIfAbsent(node, key -> new HashSet<>());
        //nodeWeights.put(node, weight);
        nodes.put(node, new WeightedGraphNode(node, weight));
    }

    // Adds a directed edge from one node to another with a specific weight
    public void addDirectedEdge(WeightedGraphNode one, WeightedGraphNode two, double weight) {
        //addNode(one);
        //addNode(two);
        //adjacentList.get(one).add(two);
        //setDirectedEdgeWeight(one, two, weight);
        nodes.get(one.getPosition()).addEdge(two, weight);
    }

    // Sets the weight of a directed edge between two nodes
    public void setDirectedEdgeWeight(Vec2 one, Vec2 two, double weight) {
        for (WeightedGraphEdge edge : nodes.get(one).getEdges()) {
            if (edge.getTarget().getPosition() == two) edge.setWeight(weight);
        }
    }

    // Gets the weight of the edge between two nodes, returns null if the edge does not exist
    public Double getDirectedEdgeWeight(Vec2 one, Vec2 two) {
        for (WeightedGraphEdge edge : nodes.get(one).getEdges()) {
            if (edge.getTarget().getPosition() == two) return edge.getWeight();
        }
        return 0.0d;
    }

    public Double getNodeWeight(WeightedGraphNode node) {
        return nodes.get(node.getPosition()).getWeight();
    }

    // Removes a node and all its associated edges
    public void removeNode(Vec2 node) {
        nodes.remove(node);
    }

    // Removes a directed edge from one node to another
    public void removeDirectedEdge(Vec2 one, Vec2 two) {
        nodes.get(one).removeEdge(two);
    }

    // Gets the neighbors of a given node
    public Set<WeightedGraphNode> getNeighbors(WeightedGraphNode node) {
        Set<WeightedGraphNode> neighbors = new HashSet<>();

        for (WeightedGraphEdge edge : nodes.get(node.getPosition()).getEdges()) {
            neighbors.add(nodes.get(edge.getTarget().getPosition()));
        }

        return neighbors;
    }

    // Checks if there is an edge between two nodes
    public boolean hasEdge(WeightedGraphNode one, WeightedGraphNode two) {
        return nodes.containsKey(one.getPosition()) && nodes.get(one.getPosition()).hasEdges();
    }

    // Returns the nodes in the graph
    public Collection<WeightedGraphNode> getAllNodes() {
        return nodes.values();
    }

    // Returns the nodes in the graph
    public WeightedGraphNode getNode(Vec2 coord) {
        return nodes.get(coord);
    }

    public void CalculateEdges() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                Vec2 cursor = new Vec2(x, z);
                WeightedGraphNode sourceNode = nodes.get(cursor);

                // Determine neighbors, ensuring to stay within bounds
                WeightedGraphNode px = (x < 15) ? nodes.get(cursor.add(Vec2.UNIT_X)) : sourceNode;
                WeightedGraphNode nx = (x > 0) ? nodes.get(cursor.add(Vec2.NEG_UNIT_X)) : sourceNode;
                WeightedGraphNode pz = (z < 15) ? nodes.get(cursor.add(Vec2.UNIT_Y)) : sourceNode;
                WeightedGraphNode nz = (z > 0) ? nodes.get(cursor.add(Vec2.NEG_UNIT_Y)) : sourceNode;

                // Get the lowest Von Neumann neighbors and add them to the graph
                for (WeightedGraphNode lowerNeighbor : WeightedGraphNode.getLowestVonNeumannNeighbors(nodes.get(cursor), List.of(px, nx, pz, nz))) {
                    int yDiff = Math.abs((int) sourceNode.getWeight() - (int) lowerNeighbor.getWeight());
                    addDirectedEdge(sourceNode, lowerNeighbor, yDiff);
                }

            }
        }
    }
}
