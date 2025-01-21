import java.util.ArrayList;
import java.util.TreeMap;

public class ComputerDecision {
    private final State state;
    private final Player player;
    private final int dice;
    private final PlayStone decisionStone;

    public ComputerDecision(State state, Player player, int dice) {
        this.state = state;
        this.player = player;
        this.dice = dice;
        decisionStone = chooseAStone();
    }

    private PlayStone chooseAStone() {
        int playerIndex = State.getPlayerIndex(player);
        ArrayList<PlayStone> movableStones = state.players.get(playerIndex).getMovableStones(state, dice);
        if (!movableStones.isEmpty()) {
            int repeatedTurnsTemp = State.repeatedTurns;
            boolean hasNewTurnTemp = State.hasNewTurn;
            TreeMap<Integer, PlayStone> scores = new TreeMap<>();
            for (PlayStone movableStone : movableStones) {
                State newState = state.move(player, movableStone, dice);
                int score = calculateStoneScore(movableStone, state, newState);
                scores.put(score, movableStone);
            }
            State.hasNewTurn = hasNewTurnTemp;
            State.repeatedTurns = repeatedTurnsTemp;
            return scores.lastEntry().getValue();
        }
        return null;
    }

    private int calculateStoneScore(PlayStone stone, State oldState, State newState) {
        int playerIndex = State.getPlayerIndex(player);
        PlayStone oldStone = oldState.players.get(playerIndex).stones.get(stone.num - 1);
        PlayStone newStone = newState.players.get(playerIndex).stones.get(stone.num - 1);
        int score = oldStone.i;
        if (!oldStone.isAWin && newStone.isAWin) score += 100;
        else if (canKill(oldStone, oldState.grid[newStone.position.x][newStone.position.y].listStones)) score += 60;
        else if (oldStone.isOut && !newStone.isOut) score += 50;
        return score;
    }

    private boolean canKill(PlayStone currStone, ArrayList<PlayStone> stones) {
        for (PlayStone stone : stones) {
            if (currStone.color != stone.color) {
                return true;
            }
        }
        return false;
    }

    public PlayStone getDecisionStone() {
        return decisionStone;
    }

}
