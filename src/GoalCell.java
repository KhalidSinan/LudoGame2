import java.util.ArrayList;

public class GoalCell extends ColoredCell {

    public GoalCell(PlayerColor color) {
        super(color, new ArrayList<>());
    }

    public GoalCell(PlayerColor color, ArrayList<PlayStone> listStones) {
        super(color, listStones);
    }

    @Override
    public Cells copy() {
        return new GoalCell(getColor(), new ArrayList<>(this.listStones));
    }

    @Override
    State collide(State state, PlayStone stone) {
        listStones.add(stone);
        stone.isAWin = true;
        return state;
    }

    @Override
    public String toString() {
        if (listStones.isEmpty())
            return ConsoleColors.getCellBackgroundByColor(getColor()) + "   " + ConsoleColors.RESET;
        PlayStone standingStone = listStones.get(0);
        return ConsoleColors.getCellByColor(standingStone.color) + " " + standingStone.num + " " + ConsoleColors.RESET;
    }
}