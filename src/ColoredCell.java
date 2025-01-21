import java.util.ArrayList;

public abstract class ColoredCell extends Cells {

    private PlayerColor color;

    public ColoredCell(PlayerColor color, ArrayList<PlayStone> listStones) {
        super(" ", listStones);
        this.color = color;
    }

    public ColoredCell(String name, ArrayList<PlayStone> listStones) {
        super(name, listStones);
    }

    public PlayerColor getColor() {
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return ConsoleColors.getColor(color) + name + " " + ConsoleColors.RESET;
    }
}
