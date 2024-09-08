package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import com.electronwill.nightconfig.core.utils.TransformingList;
import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.GraphNode;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.GraphGeoLayer;

import java.util.ArrayList;
import java.util.List;

public class GraphGeoChunk extends AbstractGeoChunk {
    public WeightedGraph graph;
    public GraphGeoChunk(ChunkPos chunkPos, GraphGeoLayer parentGraphGeoLayer, GeneratorContext context, List<ChunkPos> contextGeoChunks, WeightedGraph graph)
    {
        super(chunkPos, parentGraphGeoLayer, context, contextGeoChunks);
        this.graph = graph;

        // Get neighbor graphs and do stitching
        tryStitch();
    }

    private void tryStitch() {

        for (ChunkPos offset : super.contextGeoChunks) {
            // Calculate the neighbor's chunk position
            ChunkPos neighborChunk = new ChunkPos(super.chunkPos.x + offset.x, super.chunkPos.z + offset.z);

            // Get the neighboring chunk from the parent layer
            GraphGeoChunk neighborGraphGeoChunk = (GraphGeoChunk) super.parentGeoLayer.getOrComputeChunk(neighborChunk, super.generatorContext);

            // If the neighboring chunk exists, proceed with stitching
            if (neighborGraphGeoChunk != null) {
                stitchChunkEdges(this.graph, neighborGraphGeoChunk.graph, offset);
            }
        }
    }

    private void stitchChunkEdges(WeightedGraph currentGraph, WeightedGraph neighborGraph, ChunkPos offset) {
        for (int i = 0; i < 16; i++) { // Assuming chunks are 16x16
            GraphNode sourceNode = null;
            GraphNode neighborNode = null;
            double edgeWeight;

            // Handle the direct neighbors first (right, left, top, bottom)
            if (offset.x == 1 && offset.z == 0) { // Right
                sourceNode = currentGraph.getNode(new Int2D(15, i));
                neighborNode = neighborGraph.getNode(new Int2D(0, i));
            } else if (offset.x == -1 && offset.z == 0) { // Left
                sourceNode = currentGraph.getNode(new Int2D(0, i));
                neighborNode = neighborGraph.getNode(new Int2D(15, i));
            } else if (offset.x == 0 && offset.z == 1) { // Top
                sourceNode = currentGraph.getNode(new Int2D(i, 15));
                neighborNode = neighborGraph.getNode(new Int2D(i, 0));
            } else if (offset.x == 0 && offset.z == -1) { // Bottom
                sourceNode = currentGraph.getNode(new Int2D(i, 0));
                neighborNode = neighborGraph.getNode(new Int2D(i, 15));
            }

            // Handle the corner cases (diagonal neighbors)
            else if (offset.x == 1 && offset.z == 1) { // Top-right diagonal
                sourceNode = currentGraph.getNode(new Int2D(15, 15)); // Top-right corner of current chunk
                neighborNode = neighborGraph.getNode(new Int2D(0, 0)); // Bottom-left corner of neighbor chunk
            } else if (offset.x == -1 && offset.z == 1) { // Top-left diagonal
                sourceNode = currentGraph.getNode(new Int2D(0, 15)); // Top-left corner of current chunk
                neighborNode = neighborGraph.getNode(new Int2D(15, 0)); // Bottom-right corner of neighbor chunk
            } else if (offset.x == 1 && offset.z == -1) { // Bottom-right diagonal
                sourceNode = currentGraph.getNode(new Int2D(15, 0)); // Bottom-right corner of current chunk
                neighborNode = neighborGraph.getNode(new Int2D(0, 15)); // Top-left corner of neighbor chunk
            } else if (offset.x == -1 && offset.z == -1) { // Bottom-left diagonal
                sourceNode = currentGraph.getNode(new Int2D(0, 0)); // Bottom-left corner of current chunk
                neighborNode = neighborGraph.getNode(new Int2D(15, 15)); // Top-right corner of neighbor chunk
            }

            // Calculate the edge weight and stitch nodes
            if (sourceNode != null && neighborNode != null) {
                edgeWeight = sourceNode.getWeight() - neighborNode.getWeight();
                if (edgeWeight < 0) {
                    currentGraph.addDirectedEdge(sourceNode, neighborNode, edgeWeight);
                } else if (edgeWeight > 0) {
                    neighborGraph.addDirectedEdge(neighborNode, sourceNode, edgeWeight);
                }
            }
        }
    }
}
