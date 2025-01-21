import java.util.ArrayList;

public abstract class ColoredCell extends Cells {

    private PlayerColor color;

    public ColoredCell(String name) {
        super(name);
    }

    public ColoredCell(String name, PlayerColor color) {
        super(name);
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
