import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Cells {
    char name;
    ArrayList<PlayStone> listStones;

    public Cells(char name) {
        this.name = name;
        this.listStones= new ArrayList<>();
    }

    public Cells(char name, ArrayList<PlayStone> listStones) {
        this.name = name;
        this.listStones = listStones;
    }

    @Override
    public String toString() {
        return name + " ";
    }

    abstract public Cells copy();

    abstract ArrayList<PlayStone> collide(PlayStone stone);

    protected ArrayList<PlayStone> deepCopyStones(ArrayList<PlayStone> stones) {
        ArrayList<PlayStone> newStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            newStones.add(new PlayStone(stone));
        }
        return newStones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cells cells)) return false;
        return name == cells.name && Objects.equals(listStones, cells.listStones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, listStones);
    }
}
