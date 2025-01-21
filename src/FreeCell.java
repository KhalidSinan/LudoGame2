import java.util.ArrayList;
import java.util.List;

public class FreeCell extends Cells {
    public FreeCell() {
        super('-');
    }

    public FreeCell(char name, ArrayList<PlayStone> listStones) {
        super(name, listStones);

    }

    @Override
    public Cells copy() {
        return new FreeCell(this.name, this.listStones);
    }

    @Override
    ArrayList<PlayStone> collide(PlayStone stone) {
        if (!listStones.isEmpty() && !stone.color.equals(listStones.get(0).color)) {
            State.hasNewTurn = true;
            ArrayList<PlayStone> newStones = new ArrayList<>(listStones);
            listStones.clear();
            return newStones;
        }
        listStones.add(stone);
        return new ArrayList<>();
    }
}