package com.cyberchatbot.ui;

public class ConsoleUI {

    // make the colours constants
    public static final String RESET = "\u001B[0M";
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void printBanner() {
        String logo =
                CYAN + BOLD +
                        // DESIGN THREE
                        "  _____            _               ____         _   \n" +
                        " / ____|          | |             |  _ \\       | |  \n" +
                        "| |     _   _  ___| |__   ___ _ __| |_) | ___  | |_ \n" +
                        "| |    | | | |/ __| '_ \\ / _ \\ '__|  _ < / _ \\ | __|\n" +
                        "| |____| |_| | (__| |_) |  __/ |  | |_) | (_) || |_ \n" +
                        " \\_____|\\__, |\\___|_.__/ \\___|_|  |____/ \\___/  \\__|\n" +
                        "         __/ |                                      \n" +
                        "        |___/   C Y B E R  A W A R E N E S S   B O T    \n" +

                        // DESIGN TWO
//                        "  _____            _               ____   ____  _______ \n" +
//                        " / ____|          | |             |  _ \\ / __ \\|__   __|\n" +
//                        "| |     _   _  ___| |__   ___ _ __| |_) | |  | |  | |   \n" +
//                        "| |    | | | |/ __| '_ \\ / _ \\ '__|  _ <| |  | |  | |   \n" +
//                        "| |____| |_| | (__| |_) |  __/ |  | |_) | |__| |  | |   \n" +
//                        " \\_____|\\__, |\\___|_.__/ \\___|_|  |____/ \\____/   |_|   \n" +
//                        "         __/ |                                          \n" +
//                        "        |___/      C Y B E R   A W A R E   B O T        \n" +

                        // DESIGN ONE
//                        "  _____      _               _____           \n" +
//                        " / ____|    | |             |  __ \\          \n" +
//                        "| |    _   _| |__   ___ _ __| |__) |__   ___  \n" +
//                        "| |   | | | | '_ \\ / _ \\ '__|  ___/ _ \\ / __| \n" +
//                        "| |___| |_| | |_) |  __/ |  | |  | (_) | (__  \n" +
//                        " \\_____\\__, |_.__/ \\___|_|  |_|   \\___/ \\___| \n" +
//                        "        __/ |                                 \n" +
//                        "       |___/   C Y B E R   A W A R E   B O T  \n" +
                        RESET;
        System.out.println(logo);
        System.out.println(GREEN + "===================================================" + RESET);
        System.out.println(YELLOW + " Welcome to CyberBot - your cybersecurity guide! " + RESET);
        System.out.println(GREEN + "===================================================" + RESET);
    }

    public static void printPrompt() {
        System.out.print(BLUE + "[You] : " + RESET);
    }

    public static void printBotResponse(String message) {
        System.out.println(PURPLE + "[CyberBot] : " + RESET + message);
    }

    public static void printError(String message) {
        System.out.print(RED + "[ERROR ❌] : " + message + RESET);
    }
}
