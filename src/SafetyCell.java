import java.util.ArrayList;
import java.util.List;

public class SafetyCell extends Cells {
    public SafetyCell() {
        super('+');
    }

    public SafetyCell(char name, ArrayList<PlayStone> listStones) {
        super(name, listStones);
    }

    @Override
    public Cells copy() {
        return new SafetyCell(this.name, this.deepCopyStones(this.listStones));
    }

    @Override
    ArrayList<PlayStone> collide(PlayStone stone) {
        listStones.add(stone);
        return new ArrayList<>();
    }
}
