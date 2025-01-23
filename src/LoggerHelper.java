import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerHelper {
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public void loggerHelper(int decisionNumber, int numberOfNodes, List<TreeNode> nodes, long time, long memory) throws IOException {
        FileHandler fh = new FileHandler("./decisions/decision-" + decisionNumber + ".log");
        fh.setFormatter(new MyLoggerFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.ALL);

        logger.log(Level.FINE, "Algorithm: Expectiminimax");
        logger.log(Level.FINE, "Tree Structure:\n");

        for (TreeNode node : nodes) {
            String indent = "  ".repeat(node.getDepth());
            logger.log(Level.FINE, indent + node.getType() + " (Eval: " + node.getEvaluation() + ")\n");
        }

        logger.log(Level.FINE, "Total Nodes: " + numberOfNodes);
        logger.log(Level.FINE, "Time: " + time + " ms");
        logger.log(Level.FINE, "Memory: " + memory + " bytes");

        fh.close();
    }
}