package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraphNode;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;

import java.util.HashMap;
import java.util.Map;

public class GraphChunkMap {
    private final Map<ChunkPos, WeightedGraph> map;
    public GraphChunkMap()
    {
        map = new HashMap<>();
    }

    public void destroy(ChunkPos chunkPos)
    {
        map.remove(chunkPos);
    }

    public void add(WeightedGraph chunkGraph, ChunkPos chunkPos)
    {
        map.put(chunkPos,chunkGraph);

        int xChunk = chunkPos.x;
        int zChunk = chunkPos.z;

        // Check for neighboring chunks
        ChunkPos xpChunk = new ChunkPos(xChunk+1, zChunk);
        ChunkPos xnChunk = new ChunkPos(xChunk-1, zChunk);
        ChunkPos zpChunk = new ChunkPos(xChunk, zChunk+1);
        ChunkPos znChunk = new ChunkPos(xChunk, zChunk-1);

        // Stitch with neighboring chunks if they exist
        if (map.containsKey(xpChunk)) {
            stitchChunks(chunkGraph, map.get(xpChunk), "right"); // Right border stitching
        }
        if (map.containsKey(xnChunk)) {
            stitchChunks(chunkGraph, map.get(xnChunk), "left"); // Left border stitching
        }
        if (map.containsKey(zpChunk)) {
            stitchChunks(chunkGraph, map.get(zpChunk), "top"); // Top border stitching
        }
        if (map.containsKey(znChunk)) {
            stitchChunks(chunkGraph, map.get(znChunk), "bottom"); // Bottom border stitching
        }
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
                sourceNode = currentGraph.getNode(new Vec2(15, i));  // Right edge of the current chunk
                neighborNode = neighborGraph.getNode(new Vec2(0, i));  // Left edge of the neighboring chunk
            } else if (direction.equals("left")) {
                sourceNode = currentGraph.getNode(new Vec2(0, i));  // Left edge of the current chunk
                neighborNode = neighborGraph.getNode(new Vec2(15, i));  // Right edge of the neighboring chunk
            } else if (direction.equals("top")) {
                sourceNode = currentGraph.getNode(new Vec2(i, 15));  // Top edge of the current chunk
                neighborNode = neighborGraph.getNode(new Vec2(i, 0));  // Bottom edge of the neighboring chunk
            } else {
                sourceNode = currentGraph.getNode(new Vec2(i, 0));  // Bottom edge of the current chunk
                neighborNode = neighborGraph.getNode(new Vec2(i, 15));  // Top edge of the neighboring chunk
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

    public boolean exists(ChunkPos chunkPos) {
        return map.containsKey(chunkPos);
    }

    public WeightedGraph get(ChunkPos chunkPos) {
        return map.get(chunkPos);
    }

    public void remove(ChunkPos chunkPos) {
        map.remove(chunkPos);
    }
}
