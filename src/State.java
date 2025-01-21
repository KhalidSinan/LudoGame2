import java.util.*;

public class State {
    static boolean hasNewTurn = false;
    static int repeatedTurns = 0;
    int currentPlayer;
    Cells[][] grid; // [13][13]
    ArrayList<Player> players;
    boolean isFinished;

    public State(Cells[][] grid, ArrayList<Player> players) {
        this.grid = grid;
        this.players = players;
//        getNewListStones();
        this.isFinished = isFinished();
    }

    public State(Cells[][] grid, ArrayList<Player> players, int currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.grid = grid;
        this.players = players;
//        getNewListStones();
        this.isFinished = isFinished();
    }

    public State(State state) {
        this.players = deepCopyPlayers(state.players);
        this.grid = deepCopyGrid(state.grid);
        getNewListStones();
        this.currentPlayer = state.currentPlayer;
        this.isFinished = state.isFinished;
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
    private void getNewListStones(){
        for (Player player : players) {
            for (PlayStone stone : player.stones) {
                Position pos;
                if(stone.i == -1) pos = LudoBoard.playersHomePositions.get(stone.color).get(stone.num-1);
                else pos = LudoBoard.stoneRoadOnBoardBaseOnColor.get(stone.color).get(stone.i);
                if(grid[pos.x][pos.y].listStones.contains(stone)){
                    grid[pos.x][pos.y].listStones.remove(stone);
                    grid[pos.x][pos.y].listStones.add(stone);
                }
            }
        }
    }

    private Cells[][] deepCopyGrid(Cells[][] grid) {
        Cells[][] newGrid = new Cells[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newGrid[i][j] = grid[i][j].copy();
            }
        }
        return newGrid;
    }

    public int blockFounded(int diceNumber, PlayStone stone) {
        int step = 0;
        if(stone.i == -1) return diceNumber;
        for (int i = 1; i <= diceNumber; i++) {
            Position pos = LudoBoard.stoneRoadOnBoardBaseOnColor.get(stone.color).get(stone.i + i);
            if (grid[pos.x][pos.y].isBlock(stone.color)) {
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
        PlayStone stone = currentState.players.get(player.playerColor.index-1).stones.get(chosenStone.num-1);
        dice = currentState.blockFounded(dice, stone);
        Position currPosition = stone.position;
//        if(!stone.isOut) currPosition = LudoBoard.stoneRoadOnBoardBaseOnColor.get(stone.color).get(stone.i);
        currentState.grid[currPosition.x][currPosition.y].listStones.remove(stone);
        Position newPosition = LudoBoard.stoneRoadOnBoardBaseOnColor.get(stone.color).get(stone.i + dice);
        if(stone.isOut) newPosition = LudoBoard.stoneRoadOnBoardBaseOnColor.get(stone.color).get(0);
        currentState.grid[newPosition.x][newPosition.y].collide(currentState, stone);
        stone.position = newPosition;
        return currentState;
    }


    public PlayStone chooseAStone(Player player, int dice) {
        if (player.isComputer) return new ComputerDecision(this, player, dice).getDecisionStone();
        else return chooseAStoneByPlayer(player, dice);
    }

    private PlayStone chooseAStoneByPlayer(Player player, int dice) {
        int playerIndex = State.getPlayerIndex(player);
        ArrayList<PlayStone> movableStones = players.get(playerIndex).getMovableStones(this, dice);
        if (!movableStones.isEmpty()) {
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
        }
        return null;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    void switchPlayer() {
        if (State.hasNewTurn) return;
        currentPlayer = (++currentPlayer)%(players.size());
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


//    ArrayList<State> nextStates(State currentState, int dice) {
//        ArrayList<State> possibleMoves = new ArrayList<>();
//        int playerIndex = getPlayerIndex(currentState.statePlayer);
//        ArrayList<PlayStone> movableStones = currentState.players.get(playerIndex).getMovableStones(currentState, dice);
//        if (movableStones.isEmpty()) {
//            return possibleMoves;
//        } else {
//            for (PlayStone playStone : movableStones) {
//                possibleMoves.add(currentState.move(currentState.statePlayer, playStone, dice));
//            }
//            return possibleMoves;
//        }
//    }

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
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return currentPlayer == state.currentPlayer && isFinished == state.isFinished && Arrays.equals(grid, state.grid) && Objects.equals(players, state.players);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(currentPlayer, players, isFinished);
        result = 31 * result + Arrays.hashCode(grid);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Cells[] row : grid) {
            for (Cells cell : row) {
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