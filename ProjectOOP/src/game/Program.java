package game;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        GameWindow gameWindow = new GameWindow();
        Thread mainThread = new Thread(gameWindow);
        mainThread.run();
    }
}
