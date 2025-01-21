import java.util.ArrayList;
import java.util.List;

public class StartCell extends ColoredCell {
    public StartCell() {
        super('#');
    }

    public StartCell(PlayerColor color) {
        super('#', color);
    }

    public StartCell(char name, ArrayList<PlayStone> listStones) {
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
