import java.util.ArrayList;

public class StartCell extends ColoredCell {
    public StartCell() {
        super("#");
    }

    public StartCell(PlayerColor color) {
        super("#", color);
    }

    public StartCell(String name, ArrayList<PlayStone> listStones) {
        super(name, listStones);
    }

    @Override
    public Cells copy() {
        return new StartCell(this.name, this.deepCopyStones(this.listStones));
    }

    @Override
    ArrayList<PlayStone> collide(PlayStone stone) {
        listStones.add(stone);
        return new ArrayList<>();
    }
}
