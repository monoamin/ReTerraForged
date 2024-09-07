package raccoonman.reterraforged.world.worldgen.rivergen.math;


import java.util.Objects;

public class PPos {
    private long pos;

    public PPos(int x, int z){
        this.pos=pack(x,z);
    }

    public int x(){return this.getX();}
    public int z(){return this.getZ();}

    public static long pack(int x, int z) {
        return ((long) x << 32) | (z & 0xFFFFFFFFL);
    }

    private int getX() {
        return (int) (pos >> 32);
    }

    private int getZ() {
        return (int) pos;
    }

    public PPos add(int x, int z){
        return new PPos(this.getX()+x,this.getZ()+z);
    }

    public PPos mul(int x, int z){
        return new PPos(this.getX()*x,this.getZ()*z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if they are the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class check
        return this.pos == ((PPos) obj).pos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos); // Compute hash based on fields
    }
}
