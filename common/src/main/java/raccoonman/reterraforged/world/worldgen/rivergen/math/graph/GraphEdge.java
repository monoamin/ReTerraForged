package raccoonman.reterraforged.world.worldgen.rivergen.math.graph;

public class GraphEdge {
    private GraphNode target;
    private double weight;

    public GraphEdge(GraphNode targetNode, double weight) {
        this.target = targetNode;
        this.weight = weight;
    }

    public GraphNode getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
