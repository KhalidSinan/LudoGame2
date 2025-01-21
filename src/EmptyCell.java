import java.util.ArrayList;

public class EmptyCell extends Cells {
    public EmptyCell() {
        super("   ");
    }

    public EmptyCell(String name, ArrayList<PlayStone> listStones) {
        super(name, listStones);
    }

    @Override
    public Cells copy() {
        return new EmptyCell(this.name, this.listStones);
    }

    @Override
    ArrayList<PlayStone> collide(PlayStone stone) {
        return new ArrayList<>();
    }
}