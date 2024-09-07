package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;
import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.rivergen.math.PPos;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;

import java.util.*;

public class WeightedGraph {
    private final Map<PPos, WeightedGraphNode> nodes = new HashMap<>();

    public WeightedGraph(long[][] heightMap, ChunkPos chunkPos) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                // Add current node to graph
                addNode(new PPos(x, z), heightMap[x][z]);
            }
        }

        calculateEdges();
    }

    // Adds a node to the graph
    public void addNode(PPos node, double weight) {
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
    public void setDirectedEdgeWeight(PPos one, PPos two, double weight) {
        for (WeightedGraphEdge edge : nodes.get(one).getEdges()) {
            if (edge.getTarget().getPosition() == two) edge.setWeight(weight);
        }
    }

    // Gets the weight of the edge between two nodes, returns null if the edge does not exist
    public Double getDirectedEdgeWeight(PPos one, PPos two) {
        for (WeightedGraphEdge edge : nodes.get(one).getEdges()) {
            if (edge.getTarget().getPosition() == two) return edge.getWeight();
        }
        return 0.0d;
    }

    public Double getNodeWeight(WeightedGraphNode node) {
        return nodes.get(node.getPosition()).getWeight();
    }

    // Removes a node and all its associated edges
    public void removeNode(PPos node) {
        nodes.remove(node);
    }

    // Removes a directed edge from one node to another
    public void removeDirectedEdge(PPos one, WeightedGraphNode two) {
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
    public WeightedGraphNode getNode(PPos coord) {
        return nodes.get(coord);
    }

    private void calculateEdges() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                PPos cursor = new PPos(x, z);
                WeightedGraphNode sourceNode = nodes.get(cursor);

                // Determine neighbors, ensuring to stay within bounds
                WeightedGraphNode px = (x < 15) ? nodes.get(cursor.add(1,0)) : sourceNode;
                WeightedGraphNode nx = (x > 0) ? nodes.get(cursor.add(-1,0)) : sourceNode;
                WeightedGraphNode pz = (z < 15) ? nodes.get(cursor.add(0,1)) : sourceNode;
                WeightedGraphNode nz = (z > 0) ? nodes.get(cursor.add(0,-1)) : sourceNode;

                // Get the lowest Von Neumann neighbors and add them to the graph
                for (WeightedGraphNode lowerNeighbor : WeightedGraphNode.getLowestVonNeumannNeighbors(nodes.get(cursor), List.of(px, nx, pz, nz))) {
                    int yDiff = Math.abs((int) sourceNode.getWeight() - (int) lowerNeighbor.getWeight());
                    if (yDiff > 0) addDirectedEdge(sourceNode, lowerNeighbor, yDiff);
                }

            }
        }
    }
}
