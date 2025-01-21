
public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK


    public static String getColor(PlayerColor color) {
         return switch (color) {
            case BLUE -> BLUE;
            case RED -> RED;
            case GREEN -> GREEN;
            default -> YELLOW;
        };
    }

    public static String getCellBackgroundByColor(PlayerColor color){
        String result = " ";
        switch (color.index){
            case 1 ->  result = ConsoleColors.GREEN_BACKGROUND;
            case 4 ->  result = ConsoleColors.BLUE_BACKGROUND;
            case 3 ->  result = ConsoleColors.RED_BACKGROUND;
            case 2  -> result = ConsoleColors.YELLOW_BACKGROUND;
        }
        return result;
    }
    public static String getCellByColor(PlayerColor color){
        String result = " ";
        switch (color.ordinal()) {
            case 0 -> result = ConsoleColors.GREEN;
            case 3 -> result = ConsoleColors.BLUE;
            case 2 -> result = ConsoleColors.RED;
            case 1 -> result = ConsoleColors.YELLOW;
        }
        return result;
    }
}