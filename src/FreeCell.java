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
        return new FreeCell(this.name, new ArrayList<>(this.listStones));
    }

    @Override
    public State collide(State state, PlayStone stone) {
        if (!listStones.isEmpty() && !stone.color.equals(listStones.get(0).color)) {
            State.hasNewTurn = true;
            sendStonesToHome(listStones, state);
        }
        listStones.add(stone);
        return state;
    }

    public void sendStonesToHome(ArrayList<PlayStone> stones, State state) {
        stones.forEach(currStone -> {
            currStone.isOut = true;
            currStone.i = -1;
            currStone.position = LudoBoard.playersHomePositions.get(currStone.color).get(currStone.num - 1);
            state.grid[currStone.position.x][currStone.position.y].listStones.add(currStone);
        });
        stones.clear();
    }

    @Override
    public boolean isBlock(PlayerColor color) {
        if (listStones.size() <= 1) {
            return false;
        }
        for (int i = 0; i < listStones.size(); i++) {
            if (listStones.get(i).color == color) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (listStones.isEmpty()) return ConsoleColors.WHITE_BACKGROUND + "   " + ConsoleColors.RESET;
        else if (listStones.size() == 1) {
            PlayStone standingStone = listStones.get(0);
            String stoneColor = ConsoleColors.getCellByColor(standingStone.color);
            return stoneColor + " " + standingStone.num + " " + ConsoleColors.RESET;
        }
        String wallColor = ConsoleColors.getCellBackgroundByColor(listStones.get(0).color);
        return wallColor + "   " + ConsoleColors.RESET;
    }
}