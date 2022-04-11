import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class ConnectFour
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int length;
        int height;
        int numPlayers;
        ArrayList<String> names = new ArrayList<String>();


        System.out.println("How many columns do you want your board to be?");
        length = collectInput(4, 20);
        System.out.println("How many rows do you want your board to be?");
        height = collectInput(4, 20);
        System.out.println("How many players will be playing (2-4)");
        numPlayers = collectInput(2, 4);

        for (int i = 0; i < numPlayers; i++)
        {
            System.out.println("Enter player " + (i + 1) + "'s name below");
            String name = scanner.nextLine().trim();
            names.add(name);
        }

        Board board = new Board(height, length);
        int i = 0;
        Player current = Player.values()[0];

        displayBoard(board);
        while (!board.validateWin(current)) {
            current = Player.values()[i];
            System.out.println(names.get(i) + "'s turn. Enter a column number [0-" + (board.length() - 1) + "]: ");
            int col = collectInput(0, length - 1);
            if(board.isColumnFull(board.height() - 1, col))
            {
                System.out.println("Column is full. Try again.");
                continue;
            }
            board.placePiece(current, col);
            displayBoard(board);

            if (board.validateWin(current)) {
                System.out.println("Player " + names.get(i) + " Won!");
                System.out.println("Would you like to play again? (1 = Yes/2-9 == No)");
                int playAgain = Integer.parseInt(scanner.nextLine());
                if (playAgain == 1) {
                    main(args);
                } else {
                    break;
                }
            }
            if (board.isFull(board.length())) {
                System.out.println("Draw!");
                break;
            }

            i++;
            if (i == numPlayers)
            {
                i = 0;
            }
        }
    }

    private static int collectInput(int min, int max)
    {
        while (true) {
            int input; // Collect input then check if it is in bounds
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please try again.");
                continue;
            }
            checkBounds(input, min, max);
            return input;
        }
    }
    public static void checkBounds(int input, int min, int max)
    {
        while (true) {
            if (input > max || input < min) {
                System.out.println("Invalid input, please try again.");
                collectInput(min, max);
                continue;
            }
            return;
        }
    }
    
    public static void displayBoard (Board board)
    {
        for (int row = board.height() - 1; row >= 0; row--) {
            System.out.print("|");
            for (int col = 0; col < board.length(); col++) {
                Player player = board.returnPiece(row, col);
                char symbol = player == Player.One ? 'X'
                        : player == Player.Two ? 'O'
                        : player == Player.Three ? 'V'
                        : player == Player.Four ? 'M'
                        : ' ';
                System.out.print(symbol);
                System.out.print("|");
            }
            System.out.println();
        }
        for (int i = 0; i <= board.length() - 1; i++) {
            System.out.print(" ");
            System.out.print(i);
        }
        System.out.println();
    }
}

