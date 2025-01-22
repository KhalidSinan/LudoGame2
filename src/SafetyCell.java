import java.util.ArrayList;

public class SafetyCell extends Cells {
    public SafetyCell() {
        super("+");
    }

    public SafetyCell(String name, ArrayList<PlayStone> listStones) {
        super(name, listStones);
    }

    @Override
    public Cells copy() {
        return new SafetyCell(this.name, new ArrayList<>(this.listStones));
    }

    @Override
    State collide(State state, PlayStone stone) {
        listStones.add(stone);
        return state;
    }

    @Override
    public String toString() {
        if (listStones.isEmpty()) return ConsoleColors.BLACK_BACKGROUND_BRIGHT + "   " + ConsoleColors.RESET;
        else if (listStones.size() == 1) {
            PlayStone standingStone = listStones.get(0);
            return ConsoleColors.getCellByColor(standingStone.color) + " " + standingStone.num + " " + ConsoleColors.RESET;
        }
        return ConsoleColors.BLACK_BACKGROUND_BRIGHT + " " + listStones.size() + " " + ConsoleColors.RESET;
    }
}
