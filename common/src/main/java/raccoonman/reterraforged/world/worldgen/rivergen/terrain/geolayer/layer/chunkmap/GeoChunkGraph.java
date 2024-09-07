package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.math.PPos;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraphNode;

import java.util.HashMap;
import java.util.Map;

public class GeoChunkGraph {
    public ChunkPos chunkPos;
    public WeightedGraph graph;
    public GeoChunkGraph(ChunkPos chunkPos, WeightedGraph graph)
    {
        this.chunkPos = chunkPos;
        this.graph = graph;
    }

    private void stitchChunks(WeightedGraph currentGraph, WeightedGraph neighborGraph, String direction) {
        // Determine the border nodes to connect based on the direction
        for (int i = 0; i < 16; i++) { // Assuming the chunks are 16x16
            WeightedGraphNode sourceNode;
            WeightedGraphNode neighborNode;
            double edgeWeight;

            ChunkPos targetChunk;

            // Example for right-edge stitching
            if (direction.equals("right")) {
                sourceNode = currentGraph.getNode(new PPos(15, i));  // Right edge of the current chunk
                neighborNode = neighborGraph.getNode(new PPos(0, i));  // Left edge of the neighboring chunk
            } else if (direction.equals("left")) {
                sourceNode = currentGraph.getNode(new PPos(0, i));  // Left edge of the current chunk
                neighborNode = neighborGraph.getNode(new PPos(15, i));  // Right edge of the neighboring chunk
            } else if (direction.equals("top")) {
                sourceNode = currentGraph.getNode(new PPos(i, 15));  // Top edge of the current chunk
                neighborNode = neighborGraph.getNode(new PPos(i, 0));  // Bottom edge of the neighboring chunk
            } else {
                sourceNode = currentGraph.getNode(new PPos(i, 0));  // Bottom edge of the current chunk
                neighborNode = neighborGraph.getNode(new PPos(i, 15));  // Top edge of the neighboring chunk
            }

            edgeWeight = currentGraph.getNodeWeight(sourceNode) - neighborGraph.getNodeWeight(neighborNode);

            if (edgeWeight < 0) { // If descends, edge towards neighbor
                // Add edges between the current chunk and the neighbor
                currentGraph.addDirectedEdge(sourceNode, neighborNode, edgeWeight);
            } else if (edgeWeight > 0) { // If ascends edge from neighbor
                neighborGraph.addDirectedEdge(neighborNode, sourceNode, edgeWeight);
            }
        }
    }
}
