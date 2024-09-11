package raccoonman.reterraforged.world.worldgen.rivergen.math;


import java.util.Objects;

public class Int2D {
    private long pos;

    public Int2D(int x, int z){
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
    public Int2D add(int x, int z){return new Int2D(x()+x,z()+z);}
    public Int2D add(Int2D n){return new Int2D(x()+n.x(),z()+n.z());}
    public Int2D sub(Int2D n){return new Int2D(x()-n.x(),z()-n.z());}
    public Int2D mul(int x, int z){return new Int2D(x()*x,z()*z);}
    public Int2D mul(Int2D n){return new Int2D(x()*n.x(),z()*n.z());}
    public Int2D div(Int2D n){return new Int2D((int)(x()/n.x()),(int)(z()/n.z()));}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if they are the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class check
        return this.pos == ((Int2D) obj).pos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos); // Compute hash based on fields
    }
}
