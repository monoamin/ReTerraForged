package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;
import net.minecraft.world.level.ChunkPos;
import org.apache.logging.log4j.core.appender.rolling.action.IfAll;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;

import java.util.*;

public class WeightedGraph {
    private final Map<Int2D, GraphNode> nodes = new HashMap<>();

    public WeightedGraph(long[][] heightMap, ChunkPos chunkPos) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                // Add current node to graph
                addNode(new Int2D(x, z), heightMap[x][z]);
            }
        }

        calculateEdges();
    }

    public WeightedGraph() {

    }

    // Adds a node to the graph
    public void addNode(Int2D node, double weight) {
        //adjacentList.computeIfAbsent(node, key -> new HashSet<>());
        //nodeWeights.put(node, weight);
        nodes.put(node, new GraphNode(node, weight));
    }

    // Adds a directed edge from one node to another with a specific weight
    public void addDirectedEdge(GraphNode one, GraphNode two, double weight) {
        //addNode(one);
        //addNode(two);
        //adjacentList.get(one).add(two);
        //setDirectedEdgeWeight(one, two, weight);
        nodes.get(one.getPosition()).addEdge(two, weight);
    }

    // Sets the weight of a directed edge between two nodes
    public void setDirectedEdgeWeight(Int2D one, Int2D two, double weight) {
        for (GraphEdge edge : nodes.get(one).getEdges()) {
            if (edge.getTarget().getPosition() == two) edge.setWeight(weight);
        }
    }

    // Gets the weight of the edge between two nodes, returns null if the edge does not exist
    public Double getDirectedEdgeWeight(Int2D one, Int2D two) {
        for (GraphEdge edge : nodes.get(one).getEdges()) {
            if (edge.getTarget().getPosition() == two) return edge.getWeight();
        }
        return 0.0d;
    }

    public Double getNodeWeight(GraphNode node) {
        return nodes.get(node.getPosition()).getWeight();
    }

    // Removes a node and all its associated edges
    public void removeNode(Int2D node) {
        nodes.remove(node);
    }

    // Removes a directed edge from one node to another
    public void removeDirectedEdge(Int2D one, GraphNode two) {
        nodes.get(one).removeEdge(two);
    }

    // Gets the neighbors of a given node
    public Set<GraphNode> getNeighbors(GraphNode node) {
        Set<GraphNode> neighbors = new HashSet<>();

        for (GraphEdge edge : nodes.get(node.getPosition()).getEdges()) {
            neighbors.add(nodes.get(edge.getTarget().getPosition()));
        }

        return neighbors;
    }

    // Checks if there is an edge between two nodes
    public boolean hasEdge(GraphNode one, GraphNode two) {
        return nodes.containsKey(one.getPosition()) && nodes.get(one.getPosition()).hasEdges();
    }

    // Returns the nodes in the graph
    public Collection<GraphNode> getAllNodes() {
        return nodes.values();
    }

    // Returns the nodes in the graph
    public GraphNode getNode(Int2D coord) {
        return nodes.get(coord);
    }

    private void calculateEdges() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                Int2D cursor = new Int2D(x, z);
                GraphNode sourceNode = nodes.get(cursor);

                // Collect Moore neighborhood neighbors, ensuring to stay within bounds
                List<GraphNode> neighbors = new ArrayList<>();

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        if (dx == 0 && dz == 0) continue; // Skip the current node itself

                        int neighborX = x + dx;
                        int neighborZ = z + dz;

                        if (neighborX >= 0 && neighborX < 16 && neighborZ >= 0 && neighborZ < 16) {
                            neighbors.add(nodes.get(new Int2D(neighborX, neighborZ)));
                        } else {
                            neighbors.add(sourceNode); // Out-of-bounds neighbors default to sourceNode
                        }
                    }
                }

                // Get the lowest Moore neighbors and add them to the graph
                for (GraphNode lowerNeighbor : GraphNode.getLowestNeighbors(sourceNode, neighbors)) {
                    int yDiff = Math.abs((int) sourceNode.getWeight() - (int) lowerNeighbor.getWeight());
                    if (yDiff > 0) addDirectedEdge(sourceNode, lowerNeighbor, yDiff);
                }
            }
        }
    }

    public WeightedGraph getSubGraph(Int2D elevationMax) {
        WeightedGraph prunedGraph = new WeightedGraph(); // Initialize pruned graph
        GraphNode startNode = this.nodes.get(elevationMax); // Retrieve starting node based on elevationMax
        Set<GraphNode> visitedNodes = new HashSet<>(); // Set of visited nodes to avoid cycles

        // Priority queue for selecting paths based on steepest cumulative descent
        PriorityQueue<PathNode> pq = new PriorityQueue<>((a, b) -> Long.compare(b.cumulativeDescent, a.cumulativeDescent));
        // Start from the maximum elevation node
        pq.add(new PathNode(startNode, 0)); // Start with zero cumulative descent

        while (!pq.isEmpty()) {
            PathNode pathNode = pq.poll();
            GraphNode currentNode = pathNode.node;

            // Avoid reprocessing nodes
            if (visitedNodes.contains(currentNode)) continue;
            visitedNodes.add(currentNode);

            // Add current node to pruned graph
            prunedGraph.addNode(currentNode.getPosition(),1);

            Set<GraphEdge> edges = currentNode.getEdges();

            for (GraphEdge edge : edges) {
                GraphNode targetNode = edge.getTarget();

                long heightDifference = (long) (currentNode.getWeight() - targetNode.getWeight());

                // We're only interested in descending paths
                if (heightDifference > 0) {
                    long newCumulativeDescent = pathNode.cumulativeDescent + heightDifference;

                    // Add this path with updated cumulative descent
                    pq.add(new PathNode(targetNode, newCumulativeDescent));

                    // Add the edge to the pruned graph if it's part of the steepest descent path
                    prunedGraph.addDirectedEdge(currentNode, targetNode, heightDifference);
                }
            }
        }

        return prunedGraph;
    }

    // Helper class to store path information
    class PathNode {
        GraphNode node;
        long cumulativeDescent;

        public PathNode(GraphNode node, long cumulativeDescent) {
            this.node = node;
            this.cumulativeDescent = cumulativeDescent;
        }
    }

}
