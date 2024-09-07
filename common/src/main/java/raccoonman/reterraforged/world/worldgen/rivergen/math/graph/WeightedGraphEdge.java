package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;

import net.minecraft.world.phys.Vec2;

public class WeightedGraphEdge {
    private WeightedGraphNode target;
    private double weight;

    public WeightedGraphEdge(WeightedGraphNode targetNode, double weight) {
        this.target = targetNode;
        this.weight = weight;
    }

    public WeightedGraphNode getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
