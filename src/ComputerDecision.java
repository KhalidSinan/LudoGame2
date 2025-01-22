import java.util.ArrayList;

public class ComputerDecision {
    private final State state;
    private final Player player;
    private final int dice;
    private final PlayStone decisionStone;

    private final int alpha = Integer.MIN_VALUE;
    private final int beta = Integer.MAX_VALUE;
    private final int maxDepth = 5;

    public ComputerDecision(State state, Player player, int dice) {
        this.state = state;
        this.player = player;
        this.dice = dice;
        decisionStone = chooseAStone();
    }

    private PlayStone chooseAStone() {
        ArrayList<PlayStone> movableStones = player.getMovableStones(state, dice);

        if (movableStones.isEmpty()) {
            return null;
        }

        Expectiminimax algorithm = new Expectiminimax();
        PlayStone decisionStone = algorithm.solve(state, player, dice, maxDepth, alpha, beta);

        if (decisionStone == null || !movableStones.contains(decisionStone)) {
            decisionStone = movableStones.get(0);
        }
        return decisionStone;
    }

    public PlayStone getDecisionStone() {
        return decisionStone;
    }
}
