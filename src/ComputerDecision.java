import java.util.ArrayList;
import java.util.TreeMap;

public class ComputerDecision {
    private final State state;
    private final Player player;
    private final int dice;
    private final PlayStone decisionStone;

    private final int alpha = Integer.MAX_VALUE;
    private final int beta = Integer.MIN_VALUE;
    private final int maxDepth = 40;

    public ComputerDecision(State state, Player player, int dice) {
        this.state = state;
        this.player = player;
        this.dice = dice;
        decisionStone = chooseAStone();
    }

    private PlayStone chooseAStone() {
        ArrayList<PlayStone> movableStones = player.getMovableStones(state, dice);

        if (movableStones.isEmpty()) {
            System.out.println("No valid stones to move for player");
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
