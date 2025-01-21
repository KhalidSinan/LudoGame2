import java.io.Console;
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
