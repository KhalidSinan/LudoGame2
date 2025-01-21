public class Main {
    public static void main(String[] args) {
        Game game = new Game(2,1);
        LudoBoard board = new LudoBoard(game.players);
        System.out.println(board);
        game.play();
    }
}