import java.util.Objects;

public class PlayStone {
    PlayerColor color;
    Position position;
    int i;
    boolean isOut;
    boolean isAWin;
    int num;

    public PlayStone(PlayerColor color, int num) {
        this.color = color;
        this.num = num;
        this.position = LudoBoard.playersHomePositions.get(color).get(num);
        this.i = -1;
        this.isOut = true;
        this.isAWin = false;
    }

    public PlayStone(PlayerColor color, int num, int i, boolean isOut, boolean isAWin) {
        this.color = color;
        this.num = num;
        if(i == -1) this.position = LudoBoard.playersHomePositions.get(color).get(num);
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

    public String getStoneOnBoard() {
        char result = 'g';
        switch (color.ordinal()) {
            case 1 -> result = 'y';
            case 2 -> result = 'r';
            case 3 -> result = 'b';
        }
        return String.valueOf(num) + String.valueOf(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayStone playStone)) return false;
        return i == playStone.i &&
                isOut == playStone.isOut &&
                isAWin == playStone.isAWin &&
                num == playStone.num &&
                color == playStone.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, i, isOut, isAWin, num);
    }
}
