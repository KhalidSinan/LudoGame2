import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComputerDecision {
    private final State state;
    private final Player player;
    private final int dice;
    private final PlayStone decisionStone = null;

    private final int alpha = Integer.MIN_VALUE;
    private final int beta = Integer.MAX_VALUE;
    private final int maxDepth = 5;

    LoggerHelper logger = new LoggerHelper();
    static int decisionNumber = 0;

    public ComputerDecision(State state, Player player, int dice) {
        this.state = state;
        this.player = player;
        this.dice = dice;
    }

    public PlayStone chooseAStone() {
        ArrayList<PlayStone> movableStones = player.getMovableStones(state, dice);

        if (movableStones.isEmpty()) {
            return null;
        }
        Expectiminimax algorithm = new Expectiminimax();
        long start = System.currentTimeMillis();
        PlayStone decisionStone = algorithm.solve(state, player, dice, maxDepth, alpha, beta);
        long end = System.currentTimeMillis();
        loggingResults(Expectiminimax.numberOfNodes, Expectiminimax.nodes, (end - start));
        if (decisionStone == null || !movableStones.contains(decisionStone)) {
            decisionStone = movableStones.get(0);
        }
        return decisionStone;
    }

    public void loggingResults(int numberOfNodes, List<TreeNode> states, long time) {
        long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        decisionNumber++;
        try {
            logger.loggerHelper(decisionNumber, numberOfNodes, states, time, memory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
