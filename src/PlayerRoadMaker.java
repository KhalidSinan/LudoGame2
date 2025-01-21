import java.util.Map;

public class PlayerRoadMaker {
    static int roadLength = 52;
    private final PlayerColor playerRoadColor;
    private final Cells[] playerRoad;

    public PlayerRoadMaker(PlayerColor playerRoadColor) {
        this.playerRoadColor = playerRoadColor;
        playerRoad = new Cells[roadLength];
        initializePlayerRoad();
    }

    private void initializePlayerRoad() {
        Map<Integer, Cells> ludoGeneralRoad = new LudoGeneralRoad().ludoGeneralRoad;
        for (Map.Entry<Integer, Cells> cell : ludoGeneralRoad.entrySet()) {
            Cells currCell = cell.getValue();
            if (currCell instanceof ColoredCell coloredCell) {
                coloredCell.setColor(playerRoadColor);
            }
            playerRoad[cell.getKey()] = cell.getValue();
        }
    }

    public Cells[] getPlayerRoad() {
        return playerRoad;
    }
}
