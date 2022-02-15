import java.util.Scanner;

public class Minesweeper{

    public static boolean gameEnded = false;

    public static void main(String[] args) {
        //new MinesweeperFrame();

        MinesweeperModel grid = new MinesweeperModel();
        Scanner scanner = new Scanner(System.in);

        while(!gameEnded) {
            grid.printVisibleBoard();
            System.out.println("What is your move? (Click = c, Flag = f)");
            String move = scanner.next();
            if(move.equals("f")) {
                System.out.println("Where do you want to flag? (row column)");
                grid.flag(scanner.nextInt() - 1, scanner.nextInt() - 1);
            } else if(move.equals("c")) {
                System.out.println("Where do you want to click? (row column)");
                grid.click(scanner.nextInt() - 1, scanner.nextInt() - 1);
            } else {
                System.out.println("Please only enter c or f.");
            }
        }
        grid.printHiddenBoard();


    }
}
