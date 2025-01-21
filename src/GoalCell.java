import java.util.ArrayList;

public class GoalCell extends ColoredCell {

    public GoalCell() {
        super("*");
    }

    public GoalCell(PlayerColor color) {
        super("*", color);
    }

    public GoalCell(String name, ArrayList<PlayStone> listStones) {
        super(name, listStones);
    }

    @Override
    public Cells copy() {
        return new GoalCell(this.name, this.deepCopyStones(this.listStones));
    }

    @Override
    ArrayList<PlayStone> collide(PlayStone stone) {
        listStones.add(stone);
        stone.isAWin = true;
        return new ArrayList<>();
    }
}