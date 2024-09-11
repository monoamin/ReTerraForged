package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;

import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;

import java.util.*;

import static raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils.*;

public class GraphNode {
    private Int2D position;
    private double weight;
    private Set<GraphEdge> edges;

    public GraphNode(Int2D position, double weight) {
        this.position = position;
        this.weight = weight;
        this.edges = new HashSet<>();
    }

    public Int2D getPosition() {
        return position;
    }

    public double getWeight() {
        return weight;
    }

    public Set<GraphEdge> getEdges() {
        return edges;
    }

    public void addEdge(GraphNode targetNode, double edgeWeight) {
        this.edges.add(new GraphEdge(targetNode, edgeWeight));
    }

    public void removeEdge(GraphNode target) {
        edges.removeIf(s -> s.getTarget().equals(target));
    }

    public boolean hasEdges() {
        return !edges.isEmpty();
    }

    public static Collection<GraphNode> getLowestNeighbors(GraphNode from, List<GraphNode> neighborHood) {
        int lowest = Integer.MAX_VALUE;
        List<GraphNode> lowestNeighbors = new ArrayList<>();

        // Find the lowest height value among neighbors
        //for (int i = 0; i < neighborHood.size(); i++) {
        for (GraphNode n: neighborHood) {
            if (n.getWeight() < lowest) {
                lowest = (int)Math.round(n.getWeight());
                lowestNeighbors.clear();  // Clear current list when a new lowest is found
                lowestNeighbors.add(n);
            } else if (n.getWeight() == lowest) {
                lowestNeighbors.add(n);
            }
        }
        return lowestNeighbors;
    }
}