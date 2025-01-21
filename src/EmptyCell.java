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
    State collide(State state, PlayStone stone) {
        return state;
    }

    @Override
    public String toString() {
        if(listStones.size() == 1) {
            PlayStone standingStone = listStones.get(0);
            return ConsoleColors.getCellByColor(standingStone.color) + " " + standingStone.num + " " + ConsoleColors.RESET;
        }
        return ConsoleColors.BLACK_BACKGROUND + "   " + ConsoleColors.RESET;
    }
}