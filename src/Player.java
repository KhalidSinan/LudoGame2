import java.util.*;

public class Player {
    boolean winner;
    boolean isComputer;
    String playerName;
    PlayerColor playerColor;
    ArrayList<PlayStone> stones = new ArrayList<>();

    public Player(PlayerColor playerColor, boolean isComputer) {
        this.winner = false;
        this.isComputer = isComputer;
        this.playerName = " ";
        this.playerColor = playerColor;
        initializePlayerStones();
    }

    public Player(String playerName, PlayerColor playerColor, boolean isComputer) {
        this.winner = false;
        this.isComputer = isComputer;
        this.playerName = playerName;
        this.playerColor = playerColor;
        initializePlayerStones();
    }

    // deep copy constructor
    public Player(Player player) {
        this.winner = player.winner;
        this.isComputer = player.isComputer;
        this.playerName = player.playerName;
        this.playerColor = player.playerColor;
        this.stones = deepCopyStones(player.stones);
    }

    private ArrayList<PlayStone> deepCopyStones(ArrayList<PlayStone> stones) {
        ArrayList<PlayStone> newStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            newStones.add(new PlayStone(stone));
        }
        return newStones;
    }

    private void initializePlayerStones() {
        for (int i = 0; i < 4; i++) {
            stones.add(new PlayStone(playerColor, i + 1));
        }
    }

    public ArrayList<PlayStone> getMovableStones(State state, int dice) {
        ArrayList<PlayStone> movableStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            if (stone.isAWin) continue;
            if (stone.isOut && dice != 6) continue;
            if (stone.i + dice >= 48 && getWinningTileIndex() != stone.i + dice) continue;
            if (state.blockFounded(dice, stone) == 0) continue;
//            System.out.println("block " + state.BlockFounded(dice, stone) + " for stone " + stone.num + " " + stone.color);
            movableStones.add(stone);
        }
        return movableStones;
    }

    public int getWinningTileIndex() {
        ArrayList<PlayStone> winningStones = getStonesWinningInOrder();
        if (winningStones.isEmpty()) return 51;
        PlayStone lastStoneToWin = winningStones.get(0); // the most outside stone
        return lastStoneToWin.i - 1; // winning tile index
    }

    private ArrayList<PlayStone> getStonesWinningInOrder() {
        ArrayList<PlayStone> winningStones = new ArrayList<>();
        for (PlayStone stone : stones) {
            if (stone.isAWin) winningStones.add(stone);
        }
        winningStones.sort(Comparator.comparingInt(stone -> stone.i));
        return winningStones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return winner == player.winner && Objects.equals(playerName, player.playerName) && playerColor == player.playerColor && Objects.deepEquals(stones, player.stones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winner, isComputer, playerName, playerColor, stones);
    }
}
