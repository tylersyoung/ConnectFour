import java.util.Scanner;
import java.util.InputMismatchException;
public class Board
{
    // [0, 0] is bottom left corner
    private Player[][] board;
    private int height;
    private int length;
    
    public Board(int height, int length)
    {
        board = new Player[height][length];
        this.length = length;
        this.height = height;
    }

    public void placePiece (Player player, int col)
    {
        for (int row = 0; row <= length - 1; row++)
        {
            if (board[row][col] == null)
            {
                board[row][col] = player;
                break;
            }
        }
    }

    public Player returnPiece (int row, int column)
    {
        return board[row][column];
    }

    public boolean isColumnFull (int row, int col)
    {
        return returnPiece(row, col) != null;
    }

    public boolean isFull (int length)
    {
        for (int col = 0; col < length - 1; col++)
        {
            if (!isColumnFull(height - 1, col))
            {
                return false;
            }
        }

        return true;
    }

    public boolean validateWin(Player player)
    {
        for (int row = 0; row < length; row++) //across
        {
            for (int col = 0;col < length - 3; col++)
            {
                if (returnPiece(row, col) == player &&
                        returnPiece(row, col + 1) == player &&
                        returnPiece(row, col + 2) == player &&
                        returnPiece(row, col + 3) == player)
                {
                    return true;
                }
            }
        }

        for(int row = 0; row < length - 3; row++) //up and down
        {
            for(int col = 0; col < length; col++)
            {
                if (returnPiece(row, col) == player &&
                        returnPiece(row + 1, col) == player &&
                        returnPiece(row + 2, col) == player &&
                        returnPiece(row + 3, col) == player)
                {
                    return true;
                }
            }
        }
        for (int row = 3; row < length; row++) //diagonal going up
        {
            for (int col = 0; col < length - 3; col++)
            {
                if (returnPiece(row, col) == player &&
                        returnPiece(row - 1, col + 1) == player &&
                        returnPiece(row - 2, col + 2) == player &&
                        returnPiece(row - 3, col + 3) == player)
                {
                    return true;
                }
            }
        }
        for (int row = 0; row < length - 3; row++) //diagonal going down
        {
            for (int col = 0; col < length - 3; col++)
            {
                if (returnPiece(row, col) == player   &&
                        returnPiece(row + 1, col + 1) == player &&
                        returnPiece(row + 2, col + 2) == player &&
                        returnPiece(row + 3, col + 3) == player)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public int height()
    {
        return height;
    }
    public int length()
    {
        return length;
    }
}
