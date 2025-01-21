import java.util.ArrayList;

public class FreeCell extends Cells {
    public FreeCell() {
        super("-");
    }

    public FreeCell(String name, ArrayList<PlayStone> listStones) {
        super(name, listStones);
    }

    @Override
    public Cells copy() {
        return new FreeCell(this.name, this.listStones);
    }

    @Override
    State collide(State state, PlayStone stone) {
        if (!listStones.isEmpty() && !stone.color.equals(listStones.get(0).color)) {
            State.hasNewTurn = true;
//            state.grid[stone.position.x][stone.position.y].listStones.clear();
            listStones.clear();
        }
        listStones.add(stone);
//        state.grid[stone.position.x][stone.position.y].listStones.add(stone);
        return state;
    }

    @Override
    public String toString() {
        if(listStones.size() == 0) return ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET;
        else if(listStones.size() == 1) {
            PlayStone standingStone = listStones.get(0);
            String stoneColor = ConsoleColors.getCellByColor(standingStone.color);
            return  stoneColor + " " + standingStone.num + " " + ConsoleColors.RESET;
        }
        String wallColor = ConsoleColors.getCellBackgroundByColor(listStones.get(0).color);
        return wallColor + "   " + ConsoleColors.RESET;
    }
}