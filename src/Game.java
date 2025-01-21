import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings({"ConvertToTryWithResources", "resource"})

public class Game {
    ArrayList<Player> players;
    ArrayList<State> states;

    public Game(int playersNumber) {
        this.players = getInitialPlayers(playersNumber);
        Cells[][] initialGrid = LudoBoard.createBoard();
        putStonesOnHome(initialGrid);
        states = new ArrayList<>();
        states.add(new State(initialGrid, players));
    }

    public Game(int playersNumber, Integer... computerIndexes) {
        this.players = getInitialPlayers(playersNumber);
        createComputerPlayers(computerIndexes);
        Cells[][] initialGrid = LudoBoard.createBoard();
        putStonesOnHome(initialGrid);
        states = new ArrayList<>();
        states.add(new State(initialGrid, players));
    }


    private ArrayList<Player> getInitialPlayers(int playersNumber) {
        ArrayList<Player> initPlayers = new ArrayList<>();
        PlayerColor[] playerColors = PlayerColor.values();
        for (int i = 0; i < playersNumber; i++) {
            initPlayers.add(new Player(playerColors[i], false));
        }
        return initPlayers;
    }

    private void createComputerPlayers(Integer... computerIndexes) {
        for (Integer index : computerIndexes) {
            players.get(index).isComputer = true;
        }
    }

    private void putStonesOnHome(Cells[][] initialGrid){
        for (Player player : players) {
            for (PlayStone stone : player.stones) {
                Position homePosition = stone.position;
                initialGrid[homePosition.x][homePosition.y].listStones.add(stone);
            }
        }
    }

    int dice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    boolean win() {
        return getCurrentState().checkFinished();
    }

    List<Integer> throwing() {
        List<Integer> dices = new ArrayList<>();
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "First throw :" + ConsoleColors.RESET);
        for (int i = 0; i < players.size(); i++) {
            int dice = dice();
            System.out.println("Player " + players.get(i).playerColor + " : " + dice);
            if (!dices.contains(dice))
                dices.add(dice);
        }
        System.out.println();
        return dices;
    }

    Player firstPlayer() {
        List<Integer> dices = throwing();
        while (dices.size() < players.size()) {
            dices = throwing();
        }
        for (int i = 0; i < dices.size(); i++) {
            if (Objects.equals(dices.get(i), Collections.max(dices)))
                return players.get(i);
        }
        return null;
    }

    void firstMove() {
        Player firstPlayer = firstPlayer();
        State firstState = new State(states.get(0).grid, players, firstPlayer.playerColor.index-1);
        System.out.println(firstState);
        int dice = 0;
        while (dice != 6) {
            dice = dice();
            System.out.println(ConsoleColors.getColor(
                    firstPlayer.playerColor) + firstPlayer.playerColor + " : " + dice + ConsoleColors.RESET);
            if (dice == 6)
                break;
            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                firstState.switchPlayer();
                firstPlayer = firstState.getCurrentPlayer();
            }
        }
        PlayStone chosenStone = firstState.chooseAStone(firstPlayer, dice);
        State newState = firstState.move(firstPlayer, chosenStone, dice);
        System.out.println(newState);
        states.add(newState);
    }

    public void play() {
        State lastState;
        Player currentPlayer;
        firstMove();
        State.hasNewTurn = false;
        State.repeatedTurns = 0;
        while (!win()) {
            lastState = getCurrentState();
            lastState.switchPlayer();
            currentPlayer = lastState.getCurrentPlayer();
            int dice = dice();
            if (State.repeatedTurns == 2) {
                System.out.println("You cannot play 3 consecutive turns");
                State.hasNewTurn = false;
                State.repeatedTurns = 0;
                continue;
            }
            System.out.println(ConsoleColors.getColor(
                    currentPlayer.playerColor) + currentPlayer.playerColor + " : " + dice + ConsoleColors.RESET);
            PlayStone chosenStone = lastState.chooseAStone(currentPlayer, dice);
            if (chosenStone != null) {
                State newState = lastState.move(currentPlayer, chosenStone, dice);
                System.out.println(newState);
                states.add(newState);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Game Ended");
        System.out.println(ConsoleColors.getColor(
                getCurrentState().getCurrentPlayer().playerColor) +
                getCurrentState().getCurrentPlayer().playerColor + " WON " + ConsoleColors.RESET);
    }

    
    public State getCurrentState() {
        return states.get(states.size() - 1);
    }


}

// switch (state.currentPlayer.playerColor) {
// case GREEN -> state.players.get(1);
// case YELLOW -> state.players.get(2);
// case RED -> state.players.get(3);
// case BLUE -> state.players.get(0);
// default -> null;
// }