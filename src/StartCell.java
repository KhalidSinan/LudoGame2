import java.io.Console;
import java.util.ArrayList;

public class StartCell extends ColoredCell {

    public StartCell(PlayerColor color) {
        super(color, new ArrayList<>());
    }

    public StartCell(PlayerColor color, ArrayList<PlayStone> listStones) {
        super(color, listStones);
    }

    @Override
    public Cells copy() {
        return new StartCell(getColor(), this.deepCopyStones(this.listStones));
    }

    @Override
    State collide(State state, PlayStone stone) {
        listStones.add(stone);
        return state;
    }

    @Override
    public String toString() {
        if(listStones.size() == 0) return ConsoleColors.getCellByColor(getColor()) + " * " + ConsoleColors.RESET;
        else if(listStones.size() == 1) {
            PlayStone standingStone = listStones.get(0);
            return ConsoleColors.getCellByColor(standingStone.color) + " " + standingStone.num + " " + ConsoleColors.RESET;
        }
        return ConsoleColors.BLACK_BACKGROUND_BRIGHT + " " + listStones.size() + " " + ConsoleColors.RESET;
    }
}
