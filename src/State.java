import java.util.*;

public class State {
    static boolean hasNewTurn = false;
    static int repeatedTurns = 0;
    Cells[][] grid; // [13][13]
    ArrayList<Player> players;
    Player statePlayer;
    boolean isFinished;

    public State(Cells[][] grid, ArrayList<Player> players) {
        this.grid = grid;
        this.players = players;
        this.isFinished = isFinished();
    }

    public State(Cells[][] grid, ArrayList<Player> players, Player currentPlayer) {
        this.statePlayer = currentPlayer;
        this.grid = grid;
        this.players = players;
        this.isFinished = isFinished();
    }

    public State(State state) {
        this.players = deepCopyPlayers(state.players);
        this.grid = deepCopyGrid(state.grid);
        this.isFinished = state.isFinished;
        this.statePlayer = state.statePlayer;
    }

    public static int getPlayerIndex(Player player) {
        return switch (player.playerColor) {
            case GREEN -> 0;
            case YELLOW -> 1;
            case RED -> 2;
            case BLUE -> 3;
            default -> -1;
        };
    }

    private ArrayList<Player> deepCopyPlayers(ArrayList<Player> players) {
        ArrayList<Player> newPlayers = new ArrayList<>();
        for (Player player : players) {
            newPlayers.add(new Player(player));
        }
        return newPlayers;
    }
//
//    private void getNewListStones(){
//        for (Player player : players) {
//            int playerIndex = player.playerColor.index - 1;
//            for (PlayStone stone : player.stones) {
//                if(stone.i == -1) continue;
//                System.out.println(stone);
//                if(grid[playerIndex][stone.i].listStones.contains(stone)){
//                    grid[playerIndex][stone.i].listStones.remove(stone);
//                    grid[playerIndex][stone.i].listStones.add(stone);
//                }
//            }
//        }
//    }

    private Cells[][] deepCopyGrid(Cells[][] grid) {
        Cells[][] newGrid = new Cells[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newGrid[i][j] = grid[i][j].copy();
            }
        }
        return newGrid;
    }

    Map<String, Integer> intersectionWithStep(PlayStone stone, int step) {
        Map<String, Integer> intersection = new HashMap<>();

        for (PlayerColor color : PlayerColor.values()) {
            if (stone.color == color) continue;
            int colorIndex = color.getStartingPosition();
            int offset = stone.color.getStartingPosition() - colorIndex;
            int notKnownColorIndex = (stone.i + step + 12 * (offset < 0 ? (offset + 4) : offset)) % 48;
            intersection.put(color.name(), notKnownColorIndex);
        }

        return intersection;
    }

    Map<String, Boolean> stonesIntersectedWith(PlayStone stone, int step) {
        Map<String, Boolean> intersectedWith = new HashMap<>();
        Map<String, Integer> positions = intersectionWithStep(stone, step);
        String[] colors = positions.keySet().toArray(new String[0]);
        Integer[] indexPositions = positions.values().toArray(new Integer[0]);
        for (int index = 0; index < colors.length; index++) {
            int tempIndex = indexPositions[index];
            int colorIndex = getPlayerIndexByString(colors[index]);
            for (PlayStone currStone : grid[colorIndex][tempIndex].listStones) {
                if (!currStone.color.equals(stone.color)) {
                    intersectedWith.put(colors[index], true);
                    break;
                }
            }
        }
        return intersectedWith;
    }

    public int BlockFounded(int diceNumber, PlayStone stone) {
        int step = 0;
        for (int i = stone.i + 1; i <= diceNumber + stone.i; i++) {
            if (grid[stone.color.index - 1][i].listStones.size() >= 2) {
                return step;
            }
            step++;
        }
        return diceNumber;
    }

    public State move(Player player, PlayStone chosenStone, int dice) {
        if (State.repeatedTurns == 3) {
            repeatedTurns = 0;
            hasNewTurn = false;
            return this;
        }
        addTurn(dice);
        State currentState = new State(this);
        int playerIndex = getPlayerIndex(player);
        for (int i = 0; i < currentState.players.get(playerIndex).stones.size(); i++) {
            if (currentState.players.get(playerIndex).stones.get(i).equals(chosenStone)) {
                PlayStone currentStone = currentState.players.get(playerIndex).stones.get(i);
                if (currentStone.i == -1) {
                    currentStone.isOut = false;
                    currentStone.i = 0;
                    break;
                } else {
                    dice = currentState.BlockFounded(dice, currentStone);
                    currentState.handleBoardMovement(currentStone, playerIndex, dice);
                    break;
                }
            }
        }
        return currentState;
    }

    private void handleBoardMovement(PlayStone currentStone, int playerIndex, int dice) {
        Map<String, Integer> positions = intersectionWithStep(currentStone, dice);
        Map<String, Integer> prevPositions = intersectionWithStep(currentStone, 0);
        String[] colors = positions.keySet().toArray(new String[0]);
        Integer[] indexPositions = positions.values().toArray(new Integer[0]);
        Integer[] prevIndexPositions = prevPositions.values().toArray(new Integer[0]);
        for (int index = 0; index < colors.length; index++) {
            int tempIndex = indexPositions[index];
            int colorIndex = getPlayerIndexByString(colors[index]);
            int prevIndex = prevIndexPositions[index];
            grid[colorIndex][prevIndex].listStones.remove(currentStone);
            grid[colorIndex][tempIndex].listStones.remove(currentStone);
            currentStone.i += dice;
//            grid[colorIndex][tempIndex].collide(currentStone);
            currentStone.i -= dice;
        }
        grid[playerIndex][currentStone.i].listStones.remove(currentStone);
        currentStone.i += dice;
//        ArrayList<PlayStone> stones = grid[playerIndex][currentStone.i].collide(currentStone);
//        if (!stones.isEmpty()) {
//            for (PlayStone stone : stones) {
//                int colorIndex = getPlayerIndexByString(String.valueOf(stone.color));
//                players.get(colorIndex).stones.get(stone.num - 1).i = -1;
//                players.get(colorIndex).stones.get(stone.num - 1).isOut = true;
//            }
//        }
    }

    public PlayStone chooseAStone(Player player, int dice) {
        if (player.isComputer) return new ComputerDecision(this, player, dice).getDecisionStone();
        else return chooseAStoneByPlayer(this, player, dice);
    }

    private PlayStone chooseAStoneByPlayer(State currentState, Player player, int dice) {
        int playerIndex = State.getPlayerIndex(player);
        ArrayList<PlayStone> movableStones = currentState.players.get(playerIndex).getMovableStones(currentState, dice);
        if (movableStones.isEmpty()) {
            return null;
        } else {
            System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "choose a stone to move : " + ConsoleColors.RESET);
            String color = ConsoleColors.getColor(player.playerColor);
            for (PlayStone movableStone : movableStones) {
                System.out.print(color + movableStone.num + "  " + ConsoleColors.RESET);
            }
            System.out.println();
            Scanner input = new Scanner(System.in);
            int chosen = input.nextInt();
            for (PlayStone movableStone : movableStones) {
                if (movableStone.num == chosen) {
                    return movableStone;
                }
            }
            return null;
        }
    }

    public boolean checkFinished() {
        for (Player player : players) {
            int winningStones = 0;
            for (PlayStone stone : player.stones) {
                if (stone.isAWin) winningStones++;
            }
            if (winningStones == 4) {
                isFinished = true;
                return true;
            }
        }
        isFinished = false;
        return false;
    }

    private int getPlayerIndexByString(String color) {
        return switch (color) {
            case "GREEN" -> 0;
            case "YELLOW" -> 1;
            case "RED" -> 2;
            case "BLUE" -> 3;
            default -> -1;
        };
    }

    private void addTurn(int dice) {
        if (dice != 6) {
            repeatedTurns = 0;
            hasNewTurn = false;
            return;
        }
        repeatedTurns++;
        hasNewTurn = true;
    }


    ArrayList<State> nextStates(State currentState, int dice) {
        ArrayList<State> possibleMoves = new ArrayList<>();
        int playerIndex = getPlayerIndex(currentState.statePlayer);
        ArrayList<PlayStone> movableStones = currentState.players.get(playerIndex).getMovableStones(currentState, dice);
        if (movableStones.isEmpty()) {
            return possibleMoves;
        } else {
            for (PlayStone playStone : movableStones) {
                possibleMoves.add(currentState.move(currentState.statePlayer, playStone, dice));
            }
            return possibleMoves;
        }
    }

    public boolean isFinished() {
        for (Player player : players) {
            int winningStones = 0;
            for (PlayStone stone : player.stones) {
                if (stone.isAWin) winningStones++;
            }
            if (winningStones == 4) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State state)) return false;
        return isFinished == state.isFinished && Objects.deepEquals(players, state.players) && Objects.equals(statePlayer, state.statePlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, statePlayer, isFinished);
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Cells[] row : grid){
            for (Cells cell: row) {
                board.append(cell.toString());
            }
            board.append('\n');
        }
        return board.toString();
    }
}

// @Override
// public String toString() {
// return " ";
// }

// Map<String, Integer> intersection(PlayStone stone) {
// Map<String, Integer> intersection = new HashMap<>();
// for (PlayerColor color : PlayerColor.values()) {
// if (stone.color == color)
// intersection.put(color.name(), stone.i);
// int colorIndex = color.getStartingPosition();
// int offset = stone.color.getStartingPosition() - colorIndex;
// int notKnownColorIndex = (stone.i + 12 * (offset < 0 ? (offset + 4) :
// offset)) % 48;
// intersection.put(color.name(), notKnownColorIndex);
// }
// return intersection;
// }

// List<Integer> cellsPosition = new ArrayList<>();
// for (int i = stone.i + 1; i <= diceNumber + stone.i; i++) {
// Map<String, Integer> positions = intersectionWithStep(stone, diceNumber);
// cellsPosition.add(positions.get(stone.color.name()));
// }
// for (Integer listPosition : cellsPosition) {
// for (int i = 0; i < 4; i++) {
// if (grid[listPosition][i].listStones.size() >= 2) {
// return true;
// }
// }
// }
// return false;