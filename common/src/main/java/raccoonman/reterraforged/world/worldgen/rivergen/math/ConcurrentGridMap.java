package raccoonman.reterraforged.world.worldgen.rivergen.math;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map.Entry;

public class ConcurrentGridMap<V> extends ConcurrentHashMap<Int2D, V> {
    // Method to get Neumann neighbors within a given Manhattan distance
    public List<Entry<Int2D, V>> getNeumannNeighbors(Int2D coord, int distance) {
        List<Entry<Int2D, V>> neighbors = new ArrayList<>();

        // Get all Int2Ds within the Manhattan distance for Neumann neighbors (up, down, left, right)
        for (int dx = -distance; dx <= distance; dx++) {
            for (int dz = -distance; dz <= distance; dz++) {
                // Manhattan distance condition for Neumann neighbors
                if (Math.abs(dx) + Math.abs(dz) <= distance && (dx == 0 || dz == 0) && !(dx == 0 && dz == 0)) {
                    Int2D neighborCoord = new Int2D(coord.x() + dx, coord.z() + dz);
                    if (this.containsKey(neighborCoord)) {
                        neighbors.add(new AbstractMap.SimpleEntry<>(neighborCoord, this.get(neighborCoord)));
                    }
                }
            }
        }
        return neighbors;
    }

    // Method to get Moore neighbors within a given Manhattan distance
    public List<Entry<Int2D, V>> getMooreNeighbors(Int2D coord, int distance) {
        List<Entry<Int2D, V>> neighbors = new ArrayList<>();

        // Get all Int2Ds within the Manhattan distance for Moore neighbors (all surrounding cells)
        for (int dx = -distance; dx <= distance; dx++) {
            for (int dz = -distance; dz <= distance; dz++) {
                // Moore neighbors include all positions within the Manhattan distance, except the center
                if (!(dx == 0 && dz == 0)) {
                    Int2D neighborCoord = new Int2D(coord.x() + dx, coord.z() + dz);
                    if (this.containsKey(neighborCoord)) {
                        neighbors.add(new AbstractMap.SimpleEntry<>(neighborCoord, this.get(neighborCoord)));
                    }
                }
            }
        }
        return neighbors;
    }
}