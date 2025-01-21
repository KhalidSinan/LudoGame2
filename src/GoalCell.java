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
    State collide(State state, PlayStone stone) {
        listStones.add(stone);
        stone.isAWin = true;
//        state.grid[stone.position.x][stone.position.y].listStones.add(stone);
//        state.grid[stone.position.x][stone.position.y].listStones.get(0).isAWin=true;
        return state;
    }

    @Override
    public String toString() {
        if(listStones.size() == 0) return ConsoleColors.getCellBackgroundByColor(getColor()) + "   " + ConsoleColors.RESET;
        PlayStone standingStone = listStones.get(0);
        return ConsoleColors.getCellByColor(standingStone.color) + " " + standingStone.num + " " + ConsoleColors.RESET;
    }
}