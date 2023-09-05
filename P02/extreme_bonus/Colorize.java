
public class Colorize {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_TANGERINE = "\033[0;93m";
    public static final String ANSI_MAGENTA = "\u001B[1;95m";
    public static final String ANSI_CYAN = "\u001B[1;96m";
    public static final String ANSI_VERMILION = "\u001B[1;91m";
    

    public static String colorizer(String color, String stringToColorize) {

        String coloredString;

        switch (color) {
            case "TANGERINE":
                coloredString = ANSI_TANGERINE + stringToColorize + ANSI_RESET;
                break;

            case "MAGENTA":
                coloredString = ANSI_MAGENTA + stringToColorize + ANSI_RESET;
                break;

            case "CYAN":
                coloredString = ANSI_CYAN + stringToColorize + ANSI_RESET;
                break;

            case "VERMILION":
                coloredString = ANSI_VERMILION + stringToColorize + ANSI_RESET;
                break;

            default:
                return stringToColorize;
        }
        return coloredString;
    }
}
