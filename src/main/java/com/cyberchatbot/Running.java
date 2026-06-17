package com.cyberchatbot;

public class Running implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {

            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println(ConsoleUI.YELLOW + "Thread was interrupted" + ConsoleUI.RESET);
            }


            if (i == 5) {
//                System.out.println();
                ConsoleUI.printPrompt();
            }
        }
    }
}
