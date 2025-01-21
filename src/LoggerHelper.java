import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerHelper {
    public static Logger logger = Logger.getLogger(Main.class.getName());

    public void loggerHelper(Map<String, Object> solution, long time, long memory) throws IOException {
        FileHandler fh = new FileHandler("./logs/result.log");
        fh.setFormatter(new MyLoggerFormatter());
        logger.addHandler(fh);
        int visitedSize = (int) solution.get("visitedSize");
        ArrayList<State> path = (ArrayList<State>) solution.get("path");
        logger.setLevel(Level.ALL);
        logger.log(Level.FINE, "Algorithm: Expectiminimax");
        logger.log(Level.FINE, "Visited States: " + visitedSize);
        logger.log(Level.FINE, "Path States: " + path.size());
        logger.log(Level.FINE, "Time: " + time + " milliseconds");
        logger.log(Level.FINE, "Memory Used: " + memory + " bytes");

        fh.close();
    }
}