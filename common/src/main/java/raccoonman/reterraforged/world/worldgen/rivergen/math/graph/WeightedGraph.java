package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;
import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.RTFCommon;
import raccoonman.reterraforged.data.worldgen.preset.settings.Preset;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;

import java.util.*;

public class WeightedGraph {
    private final Map<Int2D, GraphNode> nodes = new HashMap<>();
    private Tile tile;
    private Int2D positionMax = new Int2D(0,0); // Fallback to chunk center

    public WeightedGraph(Int2D chunkPos) {

    }

    public Int2D getPositionMax(){
        return positionMax;
    }

    private void tileToNodes(){
        double elevationMax = 0;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                double tilevalue = tile.getCellRaw(x,z).height*320;
                // Add current node to graph
                if (tilevalue > elevationMax) {
                    elevationMax = tilevalue;
                    positionMax = new Int2D(x,z);
                }
                addNode(new Int2D(x, z), tilevalue);
            }
        }
        // Calculate graph Edges
        calculateEdges();
    }

    public static WeightedGraph fromTile(Tile tile, Int2D chunkPos){
        WeightedGraph g = new WeightedGraph(chunkPos);
        g.tile = tile;
        g.tileToNodes();
        return g;
    }

    public WeightedGraph() {

    }

    public void addNode(Int2D node, double weight) {
        nodes.put(node, new GraphNode(node, weight));
    }

    public void addEdge(GraphNode one, GraphNode two, double weight) {
        nodes.get(one.getPosition()).addEdge(two, weight);
    }

    public void setEdgeWeight(Int2D one, Int2D two, double weight) {
        for (GraphEdge edge : nodes.get(one).getEdges()) {
            if (edge.getTarget().getPosition() == two) edge.setWeight(weight);
        }
    }

    // Gets the weight of the edge between two nodes, returns null if the edge does not exist
    public Double getEdgeWeight(Int2D one, Int2D two) {
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
    public void removeEdge(Int2D one, GraphNode two) {
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
                        }
                    }
                }

                for (GraphNode neighbor : neighbors) {
                    double descent = sourceNode.getWeight() - neighbor.getWeight();
                    if (descent > 0.00d) { // For any descent greater than zero
                        addEdge(sourceNode, neighbor, descent);
                    }
                }

            }
        }
    }

    public WeightedGraph getSubGraphFromHighest() {
        GraphNode startNode = this.nodes.get(positionMax); // Retrieve starting node based on elevationMax

        WeightedGraph steepestSubgraph = new WeightedGraph();  // This will store the result graph
        Set<GraphNode> visited = new HashSet<>();  // Set of visited nodes to avoid revisiting
        Queue<PathNode> queue = new LinkedList<>();  // Queue for BFS, stores nodes and cumulative descent

        // Initialize the queue with the starting node and a descent of 0
        queue.add(new PathNode(startNode, 0));

        while (!queue.isEmpty()) {
            PathNode current = queue.poll();
            GraphNode currentNode = current.node;

            // If we've already visited this node, skip it
            if (visited.contains(currentNode)) {
                continue;
            }
            visited.add(currentNode);

            // Add the current node to the result graph
            steepestSubgraph.addNode(currentNode.getPosition(), currentNode.getWeight());

            // Process each edge of the current node
            for (GraphEdge edge : currentNode.getEdges()) {
                GraphNode neighbor = edge.getTarget();
                double edgeWeight = edge.getWeight();
                double newCumulativeDescent = current.cumulativeDescent + edgeWeight;

                // We're only interested in descending edges (positive weight)
                if (edgeWeight > 0 && !visited.contains(neighbor)) {
                    // Add the edge to the result graph
                    steepestSubgraph.addEdge(currentNode, neighbor, edgeWeight);

                    // Add the neighbor to the queue with updated cumulative descent
                    queue.add(new PathNode(neighbor, newCumulativeDescent));
                }
            }
        }

        return steepestSubgraph;  // Return the steepest subgraph
    }

    // Helper class to store the current node and cumulative descent
    private class PathNode {
        GraphNode node;
        double cumulativeDescent;

        public PathNode(GraphNode node, double cumulativeDescent) {
            this.node = node;
            this.cumulativeDescent = cumulativeDescent;
        }
    }
}
