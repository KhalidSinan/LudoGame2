import java.util.ArrayList;

public class Expectiminimax {

    public PlayStone solve(State state, Player currentPlayer, int dice, int depth, int alpha, int beta) {
        if (depth == 0 || state.checkFinished()) {
            return null;
        }

        if (currentPlayer.isComputer) {
            return maxMove(state, currentPlayer, depth, alpha, beta, dice);
        } else {
            return minMove(state, currentPlayer, depth, alpha, beta, dice);
        }
    }

    private PlayStone maxMove(State state, Player currentPlayer, int depth, int alpha, int beta, int dice) {
        int maxEval = Integer.MIN_VALUE;
        PlayStone bestStone = null;

        ArrayList<State> nextStates = state.nextStates(dice);

        for (State nextState : nextStates) {
            PlayStone chosenStone = nextState.chooseAStone(currentPlayer, dice);
            if(chosenStone == null) continue;
            int eval = evaluateState(chosenStone, state, nextState, currentPlayer);
            if (eval > maxEval) {
                maxEval = eval;
                bestStone = chosenStone;
            }
            // Alpha-beta pruning
            alpha = Math.max(alpha, eval);
            if (beta <= alpha) {
                break;
            }
        }
        return bestStone;
    }

    private PlayStone minMove(State state, Player currentPlayer, int depth, int alpha, int beta, int dice) {
        int minEval = Integer.MAX_VALUE;
        PlayStone bestStone = null;

        ArrayList<State> nextStates = state.nextStates(dice);

        for (State nextState : nextStates) {
            PlayStone chosenStone = nextState.chooseAStone(currentPlayer, dice);
            if(chosenStone == null) continue;
            int eval = evaluateState(chosenStone, state, nextState, currentPlayer);
            if (eval < minEval) {
                minEval = eval;
                bestStone = chosenStone;
            }
            // Alpha-beta pruning
            beta = Math.min(beta, eval);
            if (beta <= alpha) {
                break;
            }
        }
        return bestStone;
    }

    private int evaluateState(PlayStone stone, State oldState, State newState, Player currentPlayer) {
        int playerIndex = State.getPlayerIndex(currentPlayer);
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
}
