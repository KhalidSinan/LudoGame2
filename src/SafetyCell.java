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
        return new SafetyCell(this.name, this.listStones);
    }

    @Override
    State collide(State state, PlayStone stone) {
        listStones.add(stone);
//        state.grid[stone.position.x][stone.position.y].listStones.add(stone);
        return state;
    }

    @Override
    public String toString() {
        if(listStones.size() == 0) return ConsoleColors.BLACK_BACKGROUND_BRIGHT + "   " + ConsoleColors.RESET;
        else if(listStones.size() == 1) {
            PlayStone standingStone = listStones.get(0);
            return ConsoleColors.getCellByColor(standingStone.color) + " " + standingStone.num + " " + ConsoleColors.RESET;
        }
        return ConsoleColors.BLACK_BACKGROUND_BRIGHT + " " + listStones.size() + " " + ConsoleColors.RESET;
    }
}
