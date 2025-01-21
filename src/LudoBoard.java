import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LudoBoard {
    private final String[][] ludoBoard;
    public static final Map<PlayerColor, Map<Integer, Position>> stoneRoadOnBoardBaseOnColor;
    public static final Map<Integer, Position> blueRoadOnBoard;
    public static final Map<Integer, Position> greenRoadOnBoard;
    public static final Map<Integer, Position> yellowRoadOnBoard;
    public static final Map<Integer, Position> redRoadOnBoard;
    public static final Map<PlayerColor, List<Position>> playersHomePositions;
    public static final Map<Position, Boolean> safetyCellsOnBoard;

    static {
        blueRoadOnBoard = new HashMap<>();
        blueRoadOnBoard.put(0, new Position(12, 5));
        blueRoadOnBoard.put(1, new Position(11, 5));
        blueRoadOnBoard.put(2, new Position(10, 5));
        blueRoadOnBoard.put(3, new Position(9, 5));
        blueRoadOnBoard.put(4, new Position(8, 5));
        blueRoadOnBoard.put(5, new Position(7, 5));
        blueRoadOnBoard.put(6, new Position(7, 4));
        blueRoadOnBoard.put(7, new Position(7, 3));
        blueRoadOnBoard.put(8, new Position(7, 2));
        blueRoadOnBoard.put(9, new Position(7, 1));
        blueRoadOnBoard.put(10, new Position(7, 0));
        blueRoadOnBoard.put(11, new Position(6, 0));
        blueRoadOnBoard.put(12, new Position(5, 0));
        blueRoadOnBoard.put(13, new Position(5, 1));
        blueRoadOnBoard.put(14, new Position(5, 2));
        blueRoadOnBoard.put(15, new Position(5, 3));
        blueRoadOnBoard.put(16, new Position(5, 4));
        blueRoadOnBoard.put(17, new Position(5, 5));
        blueRoadOnBoard.put(18, new Position(4, 5));
        blueRoadOnBoard.put(19, new Position(3, 5));
        blueRoadOnBoard.put(20, new Position(2, 5));
        blueRoadOnBoard.put(21, new Position(1, 5));
        blueRoadOnBoard.put(22, new Position(0, 5));
        blueRoadOnBoard.put(23, new Position(0, 6));
        blueRoadOnBoard.put(24, new Position(0, 7));
        blueRoadOnBoard.put(25, new Position(1, 7));
        blueRoadOnBoard.put(26, new Position(2, 7));
        blueRoadOnBoard.put(27, new Position(3, 7));
        blueRoadOnBoard.put(28, new Position(4, 7));
        blueRoadOnBoard.put(29, new Position(5, 7));
        blueRoadOnBoard.put(30, new Position(5, 8));
        blueRoadOnBoard.put(31, new Position(5, 9));
        blueRoadOnBoard.put(32, new Position(5, 10));
        blueRoadOnBoard.put(33, new Position(5, 11));
        blueRoadOnBoard.put(34, new Position(5, 12));
        blueRoadOnBoard.put(35, new Position(6, 12));
        blueRoadOnBoard.put(36, new Position(7, 12));
        blueRoadOnBoard.put(37, new Position(7, 11));
        blueRoadOnBoard.put(38, new Position(7, 10));
        blueRoadOnBoard.put(39, new Position(7, 9));
        blueRoadOnBoard.put(40, new Position(7, 8));
        blueRoadOnBoard.put(41, new Position(7, 7));
        blueRoadOnBoard.put(42, new Position(8, 7));
        blueRoadOnBoard.put(43, new Position(9, 7));
        blueRoadOnBoard.put(44, new Position(10, 7));
        blueRoadOnBoard.put(45, new Position(11, 7));
        blueRoadOnBoard.put(46, new Position(12, 7));
        blueRoadOnBoard.put(47, new Position(12, 6));
        blueRoadOnBoard.put(48, new Position(11, 6));
        blueRoadOnBoard.put(49, new Position(10, 6));
        blueRoadOnBoard.put(50, new Position(9, 6));
        blueRoadOnBoard.put(51, new Position(8, 6));

        greenRoadOnBoard = new HashMap<>();
        greenRoadOnBoard.put(0, new Position(5, 0));
        greenRoadOnBoard.put(1, new Position(5, 1));
        greenRoadOnBoard.put(2, new Position(5, 2));
        greenRoadOnBoard.put(3, new Position(5, 3));
        greenRoadOnBoard.put(4, new Position(5, 4));
        greenRoadOnBoard.put(5, new Position(5, 5));
        greenRoadOnBoard.put(6, new Position(4, 5));
        greenRoadOnBoard.put(7, new Position(3, 5));
        greenRoadOnBoard.put(8, new Position(2, 5));
        greenRoadOnBoard.put(9, new Position(1, 5));
        greenRoadOnBoard.put(10, new Position(0, 5));
        greenRoadOnBoard.put(11, new Position(0, 6));
        greenRoadOnBoard.put(12, new Position(0, 7));
        greenRoadOnBoard.put(13, new Position(1, 7));
        greenRoadOnBoard.put(14, new Position(2, 7));
        greenRoadOnBoard.put(15, new Position(3, 7));
        greenRoadOnBoard.put(16, new Position(4, 7));
        greenRoadOnBoard.put(17, new Position(5, 7));
        greenRoadOnBoard.put(18, new Position(5, 8));
        greenRoadOnBoard.put(19, new Position(5, 9));
        greenRoadOnBoard.put(20, new Position(5, 10));
        greenRoadOnBoard.put(21, new Position(5, 11));
        greenRoadOnBoard.put(22, new Position(5, 12));
        greenRoadOnBoard.put(23, new Position(6, 12));
        greenRoadOnBoard.put(24, new Position(7, 12));
        greenRoadOnBoard.put(25, new Position(7, 11));
        greenRoadOnBoard.put(26, new Position(7, 10));
        greenRoadOnBoard.put(27, new Position(7, 9));
        greenRoadOnBoard.put(28, new Position(7, 8));
        greenRoadOnBoard.put(29, new Position(7, 7));
        greenRoadOnBoard.put(30, new Position(8, 7));
        greenRoadOnBoard.put(31, new Position(9, 7));
        greenRoadOnBoard.put(32, new Position(10, 7));
        greenRoadOnBoard.put(33, new Position(11, 7));
        greenRoadOnBoard.put(34, new Position(12, 7));
        greenRoadOnBoard.put(35, new Position(12, 6));
        greenRoadOnBoard.put(36, new Position(12, 5));
        greenRoadOnBoard.put(37, new Position(11, 5));
        greenRoadOnBoard.put(38, new Position(10, 5));
        greenRoadOnBoard.put(39, new Position(9, 5));
        greenRoadOnBoard.put(40, new Position(8, 5));
        greenRoadOnBoard.put(41, new Position(7, 5));
        greenRoadOnBoard.put(42, new Position(7, 4));
        greenRoadOnBoard.put(43, new Position(7, 3));
        greenRoadOnBoard.put(44, new Position(7, 2));
        greenRoadOnBoard.put(45, new Position(7, 1));
        greenRoadOnBoard.put(46, new Position(7, 0));
        greenRoadOnBoard.put(47, new Position(6, 0));
        greenRoadOnBoard.put(48, new Position(6, 1));
        greenRoadOnBoard.put(49, new Position(6, 2));
        greenRoadOnBoard.put(50, new Position(6, 3));
        greenRoadOnBoard.put(51, new Position(6, 4));

        yellowRoadOnBoard = new HashMap<>();
        yellowRoadOnBoard.put(0, new Position(0, 7));
        yellowRoadOnBoard.put(1, new Position(1, 7));
        yellowRoadOnBoard.put(2, new Position(2, 7));
        yellowRoadOnBoard.put(3, new Position(3, 7));
        yellowRoadOnBoard.put(4, new Position(4, 7));
        yellowRoadOnBoard.put(5, new Position(5, 7));
        yellowRoadOnBoard.put(6, new Position(5, 8));
        yellowRoadOnBoard.put(7, new Position(5, 9));
        yellowRoadOnBoard.put(8, new Position(5, 10));
        yellowRoadOnBoard.put(9, new Position(5, 11));
        yellowRoadOnBoard.put(10, new Position(5, 12));
        yellowRoadOnBoard.put(11, new Position(6, 12));
        yellowRoadOnBoard.put(12, new Position(7, 12));
        yellowRoadOnBoard.put(13, new Position(7, 11));
        yellowRoadOnBoard.put(14, new Position(7, 10));
        yellowRoadOnBoard.put(15, new Position(7, 9));
        yellowRoadOnBoard.put(16, new Position(7, 8));
        yellowRoadOnBoard.put(17, new Position(7, 7));
        yellowRoadOnBoard.put(18, new Position(8, 7));
        yellowRoadOnBoard.put(19, new Position(9, 7));
        yellowRoadOnBoard.put(20, new Position(10, 7));
        yellowRoadOnBoard.put(21, new Position(11, 7));
        yellowRoadOnBoard.put(22, new Position(12, 7));
        yellowRoadOnBoard.put(23, new Position(12, 6));
        yellowRoadOnBoard.put(24, new Position(12, 5));
        yellowRoadOnBoard.put(25, new Position(11, 5));
        yellowRoadOnBoard.put(26, new Position(10, 5));
        yellowRoadOnBoard.put(27, new Position(9, 5));
        yellowRoadOnBoard.put(28, new Position(8, 5));
        yellowRoadOnBoard.put(29, new Position(7, 5));
        yellowRoadOnBoard.put(30, new Position(7, 4));
        yellowRoadOnBoard.put(31, new Position(7, 3));
        yellowRoadOnBoard.put(32, new Position(7, 2));
        yellowRoadOnBoard.put(33, new Position(7, 1));
        yellowRoadOnBoard.put(34, new Position(7, 0));
        yellowRoadOnBoard.put(35, new Position(6, 0));
        yellowRoadOnBoard.put(36, new Position(5, 0));
        yellowRoadOnBoard.put(37, new Position(5, 1));
        yellowRoadOnBoard.put(38, new Position(5, 2));
        yellowRoadOnBoard.put(39, new Position(5, 3));
        yellowRoadOnBoard.put(40, new Position(5, 4));
        yellowRoadOnBoard.put(41, new Position(5, 5));
        yellowRoadOnBoard.put(42, new Position(4, 5));
        yellowRoadOnBoard.put(43, new Position(3, 5));
        yellowRoadOnBoard.put(44, new Position(2, 5));
        yellowRoadOnBoard.put(45, new Position(1, 5));
        yellowRoadOnBoard.put(46, new Position(0, 5));
        yellowRoadOnBoard.put(47, new Position(0, 6));
        yellowRoadOnBoard.put(48, new Position(1, 6));
        yellowRoadOnBoard.put(49, new Position(2, 6));
        yellowRoadOnBoard.put(50, new Position(3, 6));
        yellowRoadOnBoard.put(51, new Position(4, 6));


        redRoadOnBoard = new HashMap<>();
        redRoadOnBoard.put(0, new Position(7, 12));
        redRoadOnBoard.put(1, new Position(7, 11));
        redRoadOnBoard.put(2, new Position(7, 10));
        redRoadOnBoard.put(3, new Position(7, 9));
        redRoadOnBoard.put(4, new Position(7, 8));
        redRoadOnBoard.put(5, new Position(7, 7));
        redRoadOnBoard.put(6, new Position(8, 7));
        redRoadOnBoard.put(7, new Position(9, 7));
        redRoadOnBoard.put(8, new Position(10, 7));
        redRoadOnBoard.put(9, new Position(11, 7));
        redRoadOnBoard.put(10, new Position(12, 7));
        redRoadOnBoard.put(11, new Position(12, 6));
        redRoadOnBoard.put(12, new Position(12, 5));
        redRoadOnBoard.put(13, new Position(11, 5));
        redRoadOnBoard.put(14, new Position(10, 5));
        redRoadOnBoard.put(15, new Position(9, 5));
        redRoadOnBoard.put(16, new Position(8, 5));
        redRoadOnBoard.put(17, new Position(7, 5));
        redRoadOnBoard.put(18, new Position(7, 4));
        redRoadOnBoard.put(19, new Position(7, 3));
        redRoadOnBoard.put(20, new Position(7, 2));
        redRoadOnBoard.put(21, new Position(7, 1));
        redRoadOnBoard.put(22, new Position(7, 0));
        redRoadOnBoard.put(23, new Position(6, 0));
        redRoadOnBoard.put(24, new Position(5, 0));
        redRoadOnBoard.put(25, new Position(5, 1));
        redRoadOnBoard.put(26, new Position(5, 2));
        redRoadOnBoard.put(27, new Position(5, 3));
        redRoadOnBoard.put(28, new Position(5, 4));
        redRoadOnBoard.put(29, new Position(5, 5));
        redRoadOnBoard.put(30, new Position(4, 5));
        redRoadOnBoard.put(31, new Position(3, 5));
        redRoadOnBoard.put(32, new Position(2, 5));
        redRoadOnBoard.put(33, new Position(1, 5));
        redRoadOnBoard.put(34, new Position(0, 5));
        redRoadOnBoard.put(35, new Position(0, 6));
        redRoadOnBoard.put(36, new Position(0, 7));
        redRoadOnBoard.put(37, new Position(1, 7));
        redRoadOnBoard.put(38, new Position(2, 7));
        redRoadOnBoard.put(39, new Position(3, 7));
        redRoadOnBoard.put(40, new Position(4, 7));
        redRoadOnBoard.put(41, new Position(5, 7));
        redRoadOnBoard.put(42, new Position(5, 8));
        redRoadOnBoard.put(43, new Position(5, 9));
        redRoadOnBoard.put(44, new Position(5, 10));
        redRoadOnBoard.put(45, new Position(5, 11));
        redRoadOnBoard.put(46, new Position(5, 12));
        redRoadOnBoard.put(47, new Position(6, 12));
        redRoadOnBoard.put(48, new Position(6, 11));
        redRoadOnBoard.put(49, new Position(6, 10));
        redRoadOnBoard.put(50, new Position(6, 9));
        redRoadOnBoard.put(51, new Position(6, 8));

        stoneRoadOnBoardBaseOnColor = new HashMap<>();
        stoneRoadOnBoardBaseOnColor.put(PlayerColor.BLUE, blueRoadOnBoard);
        stoneRoadOnBoardBaseOnColor.put(PlayerColor.GREEN, greenRoadOnBoard);
        stoneRoadOnBoardBaseOnColor.put(PlayerColor.YELLOW, yellowRoadOnBoard);
        stoneRoadOnBoardBaseOnColor.put(PlayerColor.RED, redRoadOnBoard);

        playersHomePositions = new HashMap<>();
        ArrayList<Position> blueHomePositions = new ArrayList<>();
        blueHomePositions.add(new Position(11, 0));
        blueHomePositions.add(new Position(11, 1));
        blueHomePositions.add(new Position(12, 0));
        blueHomePositions.add(new Position(12, 1));
        playersHomePositions.put(PlayerColor.BLUE, blueHomePositions);

        ArrayList<Position> greenHomePositions = new ArrayList<>();
        greenHomePositions.add(new Position(0, 0));
        greenHomePositions.add(new Position(0, 1));
        greenHomePositions.add(new Position(1, 0));
        greenHomePositions.add(new Position(1, 1));
        playersHomePositions.put(PlayerColor.GREEN, greenHomePositions);

        ArrayList<Position> yellowHomePositions = new ArrayList<>();
        yellowHomePositions.add(new Position(0, 11));
        yellowHomePositions.add(new Position(0, 12));
        yellowHomePositions.add(new Position(1, 11));
        yellowHomePositions.add(new Position(1, 12));
        playersHomePositions.put(PlayerColor.YELLOW, yellowHomePositions);

        ArrayList<Position> redHomePositions = new ArrayList<>();
        redHomePositions.add(new Position(11, 11));
        redHomePositions.add(new Position(11, 12));
        redHomePositions.add(new Position(12, 11));
        redHomePositions.add(new Position(12, 12));
        playersHomePositions.put(PlayerColor.RED, redHomePositions);

        safetyCellsOnBoard = new HashMap<>();
        safetyCellsOnBoard.put(new Position(5, 0), true);
        safetyCellsOnBoard.put(new Position(5, 3), true);
        safetyCellsOnBoard.put(new Position(3, 5), true);
        safetyCellsOnBoard.put(new Position(3, 7), true);
        safetyCellsOnBoard.put(new Position(0, 7), true);
        safetyCellsOnBoard.put(new Position(5, 9), true);
        safetyCellsOnBoard.put(new Position(7, 9), true);
        safetyCellsOnBoard.put(new Position(7, 12), true);
        safetyCellsOnBoard.put(new Position(9, 7), true);
        safetyCellsOnBoard.put(new Position(9, 5), true);
        safetyCellsOnBoard.put(new Position(12, 5), true);
        safetyCellsOnBoard.put(new Position(7, 3), true);
    }


    public LudoBoard(ArrayList<Player> players) {
        ludoBoard = new String[][]{
                {"  ", "  ", "  ", "..", "..", "--", "--", "#y", "..", "..", "  ", "  ", "  "},
                {"  ", "  ", "  ", "..", "..", "--", "YY", "--", "..", "..", "  ", "  ", "  "},
                {"  ", "  ", "  ", "..", "..", "--", "YY", "--", "..", "..", "  ", "  ", "  "},
                {"..", "..", "..", "..", "..", "++", "YY", "++", "..", "..", "..", "..", ".."},
                {"..", "..", "..", "..", "..", "--", "YY", "--", "..", "..", "..", "..", ".."},
                {"#g", "--", "--", "++", "--", "--", "  ", "--", "--", "++", "--", "--", "--"},
                {"--", "GG", "GG", "GG", "GG", "  ", "  ", "  ", "RR", "RR", "RR", "RR", "--"},
                {"--", "--", "--", "++", "--", "--", "  ", "--", "--", "++", "--", "--", "#r"},
                {"..", "..", "..", "..", "..", "--", "BB", "--", "..", "..", "..", "..", ".."},
                {"..", "..", "..", "..", "..", "++", "BB", "++", "..", "..", "..", "..", ".."},
                {"  ", "  ", "  ", "..", "..", "--", "BB", "--", "..", "..", "  ", "  ", "  "},
                {"  ", "  ", "  ", "..", "..", "--", "BB", "--", "..", "..", "  ", "  ", "  "},
                {"  ", "  ", "  ", "..", "..", "#b", "--", "--", "..", "..", "  ", "  ", "  "},
        };
        setPlayersOnBoard(players);
    }

    public static Cells[][] createBoard(String[][] board) {
        Cells[][] cells = new Cells[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Goal Cells
                if (board[i][j].equals("BB")) cells[i][j] = new GoalCell(PlayerColor.BLUE);
                if (board[i][j].equals("GG")) cells[i][j] = new GoalCell(PlayerColor.GREEN);
                if (board[i][j].equals("RR")) cells[i][j] = new GoalCell(PlayerColor.RED);
                if (board[i][j].equals("YY")) cells[i][j] = new GoalCell(PlayerColor.YELLOW);

                // Start Cells
                if (board[i][j].equals("#b")) cells[i][j] = new StartCell(PlayerColor.BLUE);
                if (board[i][j].equals("#g")) cells[i][j] = new StartCell(PlayerColor.GREEN);
                if (board[i][j].equals("#r")) cells[i][j] = new StartCell(PlayerColor.RED);
                if (board[i][j].equals("#y")) cells[i][j] = new StartCell(PlayerColor.YELLOW);

                // Free Cells
                if (board[i][j].equals("--")) cells[i][j] = new FreeCell();

                // Safety Cells
                if (board[i][j].equals("++")) cells[i][j] = new SafetyCell();

                // Empty Cells
                if (board[i][j].equals("  ") || board[i][j].equals("..")) cells[i][j] = new EmptyCell();
            }
        }
        return cells;
    }

    private void setPlayersOnBoard(ArrayList<Player> players) {
        for (Player player : players) {
            setPlayerOnBoard(player);
        }
    }

    private void setPlayerOnBoard(Player player) {
        ArrayList<PlayStone> playerStones = player.stones;
        for (int i = 0; i < playerStones.size(); i++) {
            PlayStone currStone = playerStones.get(i);
            Position positionOnBoard = stoneRoadOnBoardBaseOnColor.get(currStone.color).get(currStone.i);
            if (currStone.isOut) {
                positionOnBoard = playersHomePositions.get(currStone.color).get(i);
            }
            String currOnBoard = ludoBoard[positionOnBoard.x][positionOnBoard.y];
            if (checkIfStoneExist(currOnBoard)) {
                if (checkIfDifferentColor(currOnBoard, currStone.getStoneOnBoard())) {
                    String stonesOnSafetyCell = getStonesOnSafetyCell(currOnBoard);
                    if (stonesOnSafetyCell == null) {
                        ludoBoard[positionOnBoard.x][positionOnBoard.y] = currStone.getStoneOnBoard();
                    }
                    ludoBoard[positionOnBoard.x][positionOnBoard.y] = '+' + stonesOnSafetyCell;
                    continue;
                }
                ludoBoard[positionOnBoard.x][positionOnBoard.y] = 'w' + currStone.getStoneOnBoard().substring(1, 2);
            } else {
                ludoBoard[positionOnBoard.x][positionOnBoard.y] = currStone.getStoneOnBoard();
            }
        }
    }

    private String getStonesOnSafetyCell(String currOnBoard) {
        String stonesOnSafetyCell;
        if (currOnBoard.charAt(0) == 'w') return null;
        if (Character.isDigit(currOnBoard.charAt(0)) && Character.isAlphabetic(currOnBoard.charAt(1))) {
            stonesOnSafetyCell = "2";
        } else {
            stonesOnSafetyCell = String.valueOf(Integer.parseInt(currOnBoard.substring(1, 2)) + 1);
        }
        return stonesOnSafetyCell;
    }

    private boolean checkIfStoneExist(String cellBoard) {
        return checkIfDigits(cellBoard.substring(0, 1))
                || cellBoard.charAt(0) == 'w'
                || (cellBoard.charAt(0) == '+' && Character.isDigit(cellBoard.charAt(1)));
    }

    private boolean checkIfDifferentColor(String cellBoard, String cell) {
        return !cellBoard.substring(1, 2).equals(cell.substring(1, 2));
    }

    private boolean checkIfDigits(String ch) {
        return ch.equals("1") || ch.equals("2") || ch.equals("3") || ch.equals("4");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String[] row : ludoBoard) {
            for (String cell : row) {
                result.append(ConsoleColors.getDesignedCell(cell));
            }
            result.append('\n');
        }
        return result.toString();
    }
}
