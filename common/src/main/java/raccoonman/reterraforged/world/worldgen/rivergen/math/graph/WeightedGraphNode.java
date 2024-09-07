package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;

import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.math.PPos;

import java.util.*;
import java.util.stream.Collectors;

import static raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils.*;

public class WeightedGraphNode {
    private PPos position;
    private double weight;
    private Set<WeightedGraphEdge> edges;

    public WeightedGraphNode(PPos position, double weight) {
        this.position = position;
        this.weight = weight;
        this.edges = new HashSet<>();
    }

    public PPos getPosition() {
        return position;
    }

    public double getWeight() {
        return weight;
    }

    public Set<WeightedGraphEdge> getEdges() {
        return edges;
    }

    public void addEdge(WeightedGraphNode targetNode, double edgeWeight) {
        this.edges.add(new WeightedGraphEdge(targetNode, edgeWeight));
    }

    public void removeEdge(WeightedGraphNode target) {
        edges.removeIf(s -> s.getTarget().equals(target));
    }

    public boolean hasEdges() {
        return !edges.isEmpty();
    }

    public static Collection<WeightedGraphNode> getLowestVonNeumannNeighbors(WeightedGraphNode from, List<WeightedGraphNode> neighborHood) {
        // VonNeumann neighborhood includes 4 directions: north, south, east, west
        // Order of heights in the array:
        // [ypx, ynx, ypz, ynz]

        Vec2[] directions = {
                POS_X,        // East
                NEG_X,    // West
                POS_Z,        // North
                NEG_Z     // South
        };

        int lowest = Integer.MAX_VALUE;
        List<WeightedGraphNode> lowestNeighbors = new ArrayList<>();

        // Find the lowest height value among neighbors
        for (int i = 0; i < 4; i++) {
            if (neighborHood.get(i).getWeight() < lowest) {
                lowest = (int)Math.round(neighborHood.get(i).getWeight());
                lowestNeighbors.clear();  // Clear current list when a new lowest is found
                lowestNeighbors.add(neighborHood.get(i));
            } else if (neighborHood.get(i).getWeight() == lowest) {
                lowestNeighbors.add(neighborHood.get(i));
            }
        }
        return lowestNeighbors;
    }
}