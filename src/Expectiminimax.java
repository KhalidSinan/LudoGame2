import java.util.*;

public class Expectiminimax {
    public static int numberOfNodes;
    public static List<TreeNode> nodes = new ArrayList<>();

    public PlayStone solve(State state, Player currentPlayer, int dice, int depth, int alpha, int beta) {
        numberOfNodes = 0;
        nodes.clear();
        return maxMove(state, currentPlayer, depth, alpha, beta, dice, 0);
//            return minMove(state, currentPlayer, depth, alpha, beta, dice, 0);
    }

    private PlayStone maxMove(State state, Player currentPlayer, int depth, int alpha, int beta, int dice, int currentDepth) {
        if (depth <= 0 || state.checkFinished()) {
            return null;
        }
        numberOfNodes++;
        int maxEval = Integer.MIN_VALUE;
        PlayStone bestStone = null;
        ArrayList<PlayStone> movableStones = currentPlayer.getMovableStones(state, dice);
        for (PlayStone stone : movableStones) {
            int eval = chanceNode(state, stone, currentPlayer, depth - 1, alpha, beta, currentDepth + 1);
            nodes.add(new TreeNode("MaxNode", currentDepth, eval));
            if (eval > maxEval) {
                maxEval = eval;
                bestStone = stone;
            }
            // Alpha-beta pruning
            alpha = Math.max(alpha, maxEval);
            if (beta <= alpha) {
                break;
            }
        }
        return bestStone;
    }

    private PlayStone minMove(State state, Player currentPlayer, int depth, int alpha, int beta, int dice, int currentDepth) {
        if (depth <= 0 || state.checkFinished()) {
            return null;
        }
        numberOfNodes++;
        int minEval = Integer.MAX_VALUE;
        PlayStone bestStone = null;
        ArrayList<PlayStone> movableStones = currentPlayer.getMovableStones(state, dice);
        for (PlayStone stone : movableStones) {
            int eval = chanceNode(state, stone, currentPlayer, depth - 1, alpha, beta, currentDepth + 1);
            nodes.add(new TreeNode("MinNode", currentDepth, eval));
            if (eval < minEval) {
                minEval = eval;
                bestStone = stone;
            }
            // Alpha-beta pruning
            beta = Math.min(beta, minEval);
            if (beta <= alpha) {
                break;
            }
        }
        return bestStone;
    }

    private int chanceNode(State state, PlayStone stone, Player currentPlayer, int depth, int alpha, int beta, int currentDepth) {
        numberOfNodes++;
        double expectedValue = 0.0;
        for (int dice = 1; dice <= 6; dice++) {
            if (stone.i + dice > 51) continue;
            State clonedState = state.move(currentPlayer, stone, dice);
            clonedState.switchPlayer();
            Player nextPlayer = clonedState.getCurrentPlayer();

            int eval = 0;
            if (nextPlayer.playerColor == currentPlayer.playerColor) {
                if (maxMove(clonedState, nextPlayer, depth, alpha, beta, dice, currentDepth + 1) != null) {
                    eval = evaluateState(stone, state, clonedState, currentPlayer);
                }
            } else {
                if (minMove(clonedState, nextPlayer, depth, alpha, beta, dice, currentDepth + 1) != null) {
                    eval = evaluateState(stone, state, clonedState, currentPlayer);
                }
            }
            expectedValue += (1.0 / 6) * eval;
        }
        int eval = (int) expectedValue;
        nodes.add(new TreeNode("ChanceNode", currentDepth, eval));
        return eval;
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
