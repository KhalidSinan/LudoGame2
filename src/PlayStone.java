import java.util.Objects;

public class PlayStone {
    public PlayerColor color;
    public Position position;
    public int i;
    public boolean isOut;
    public boolean isAWin;
    public int num;

    public PlayStone(PlayerColor color, int num) {
        this.color = color;
        this.num = num;
        this.position = LudoBoard.playersHomePositions.get(color).get(num - 1);
        this.i = -1;
        this.isOut = true;
        this.isAWin = false;
    }

    public PlayStone(PlayerColor color, int num, int i, boolean isOut, boolean isAWin) {
        this.color = color;
        this.num = num;
        if (i == -1) this.position = LudoBoard.playersHomePositions.get(color).get(num - 1);
        else this.position = LudoBoard.stoneRoadOnBoardBaseOnColor.get(color).get(i);
        this.i = i;
        this.isOut = isOut;
        this.isAWin = isAWin;
    }

    // deep copy constructor
    public PlayStone(PlayStone playStone) {
        this.color = playStone.color;
        this.num = playStone.num;
        this.position = new Position(playStone.position);
        this.i = playStone.i;
        this.isOut = playStone.isOut;
        this.isAWin = playStone.isAWin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayStone playStone)) return false;
        return i == playStone.i && isOut == playStone.isOut && isAWin == playStone.isAWin && num == playStone.num && color == playStone.color && Objects.equals(position, playStone.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position, i, isOut, isAWin, num);
    }
}
