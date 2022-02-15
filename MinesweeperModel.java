import java.util.Random;

public class MinesweeperModel { // [row][column]
    public static final int NUM_ROWS = 10;
    public static final int NUM_SQUARES = (int)Math.pow(MinesweeperModel.NUM_ROWS, 2);

    private final int NUM_MINES = MinesweeperModel.NUM_SQUARES / 6;

    private final int HIDDEN_SQUARE = -1; // these final ints are for clarification on what each value in arrays mean
    private final int FLAGGED_SQUARE = -2;
    private final int BOMB_SQUARE = -3;

    private int[][] hiddenGrid = new int[MinesweeperModel.NUM_ROWS][MinesweeperModel.NUM_ROWS]; // grid with all squares revealed, used as answer key
    private int[][] visibleGrid = new int[MinesweeperModel.NUM_ROWS][MinesweeperModel.NUM_ROWS]; // -1 = unrevealed square, -2 = flagged square, -3 = bombed square, 0 = no mines around space, number = mines surrounding square

    private int minesLeft = NUM_MINES;
    private int numFlagErrors = 0;

    public MinesweeperModel() {

        Random rand = new Random();

        int numMines = 0;
        while(numMines < NUM_MINES) {
            for(int i = 0; i < hiddenGrid.length; i++) {
                for(int j = 0; j < hiddenGrid.length; j++) {
                    if(numMines >= NUM_MINES) // mines requirement filled, exit loops
                        break;
                    if(rand.nextInt(9) + 1 == 3) {
                        if(isBomb(i, j)) // space is already a bomb, skip that space
                            continue;
                        setBomb(i, j);
                        numMines++;
                    }
                }
            }
        }

        for(int i = 0; i < visibleGrid.length; i++) {
            for(int j = 0; j < visibleGrid.length; j++) {
                setHidden(i, j);
            }
        }
    }


    public void setBomb(int row, int column) {
        hiddenGrid[row][column] = BOMB_SQUARE;
        incNumsSurrBomb(row, column);
    }


    public boolean isBomb(int row, int column) { // returns true if visibleGrid[][] square is a bomb, false if not
        return hiddenGrid[row][column] == BOMB_SQUARE;
    }


    public void setFlag(int row, int column) {
        visibleGrid[row][column] = FLAGGED_SQUARE;
    }


    public boolean isFlag(int row, int column) { // returns true if visibleGrid[][] square is a flag, false if not
        return visibleGrid[row][column] == FLAGGED_SQUARE;
    }


    public void setHidden(int row, int column) {
        visibleGrid[row][column] = HIDDEN_SQUARE;
    }


    public boolean isHidden(int row, int column) {
        return visibleGrid[row][column] == HIDDEN_SQUARE; // returns true if visibleGrid[][] square is hidden, false if it is revealed
    }


    public void printHiddenBoard() {
        for(int i = 0; i < hiddenGrid.length; i++) {
            for(int j = 0; j < hiddenGrid.length; j++) {
                if(isBomb(i, j))
                    System.out.print('B');
                else
                    System.out.print(hiddenGrid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    public void printVisibleBoard() { // FIXME: When GUI done, re-make printVisibleBoard() and printHiddenBoard() to adapt to GUI
        for(int i = 0; i < visibleGrid.length; i++) {
            for(int j = 0; j < visibleGrid.length; j++) {
                if(isHidden(i, j))
                    System.out.print("? ");
                else if(isFlag(i, j))
                    System.out.print("F ");
                else
                    System.out.print(visibleGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Mines remaining: " + minesLeft); // FIXME: Don't print out when GUI is done
    }


    public void click(int row, int column) {
        if (isBomb(row, column)) {
            System.out.println("Boom! You lose!"); // FIXME: Don't print out when GUI is done
            Minesweeper.gameEnded = true;
        }
        else {
            if(hiddenGrid[row][column] == 0) {
                visibleGrid[row][column] = 0;
                clickSurrSquares(row, column);
            }
            else {
                visibleGrid[row][column] = hiddenGrid[row][column];
            }
        }
    }


    public void flag(int row, int column) { // TODO: When GUI is done, fix game over/game win to adapt to GUI instead of command line

        if(isFlag(row, column)) { // already flagged
            setHidden(row, column);
            minesLeft++;
        }
        else if(isHidden(row, column)) { // hidden space that is not flagged
            setFlag(row, column);
            minesLeft--;
        }

        if(minesLeft == 0) { // possible game win
            for(int i = 0; i < visibleGrid.length; i++) {
                for(int j = 0; j < visibleGrid.length; j++) {
                    if(isFlag(row, column) != isBomb(row, column)) { // at least one flag in wrong spot, no game win
                        numFlagErrors++;
                    }
                }
            }
            if(numFlagErrors == 0) { // game won
                Minesweeper.gameEnded = true;
                System.out.println("Congrats, you win!");
            }
        }
    }


    public void clickSurrSquares(int row, int column) { // same algorithm as incSurrSquares(), except it clicks surrounding squares instead of incrementing them
        if(row == 0) { // is in top row
            if(column == 0) { // is in top left corner
                click(0, 1);
                click(1, 0);
                click(1, 1);
            } else if(column == MinesweeperModel.NUM_ROWS - 1) { // is in top right corner
                click(0, MinesweeperModel.NUM_ROWS - 2);
                click(1, MinesweeperModel.NUM_ROWS - 2);
                click(1, MinesweeperModel.NUM_ROWS - 1);
            } else { // anywhere else in top row
                for(int i = row; i <= row + 1; i++) {
                    for(int j = column - 1; j <= column + 1; j++) {
                        if(isHidden(i, j)) {
                            click(i, j);
                        }
                    }
                }
            }
        } else if(row == MinesweeperModel.NUM_ROWS - 1) { // is in bottom row
            if(column == 0) { // is in bottom left corner
                click(MinesweeperModel.NUM_ROWS - 2, 0);
                click(MinesweeperModel.NUM_ROWS - 2, 1);
                click(MinesweeperModel.NUM_ROWS - 1, 1);
            } else if(column == MinesweeperModel.NUM_ROWS - 1) { // is in bottom right corner
                click(MinesweeperModel.NUM_ROWS - 2, MinesweeperModel.NUM_ROWS - 2);
                click(MinesweeperModel.NUM_ROWS - 2, MinesweeperModel.NUM_ROWS - 1);
                click(MinesweeperModel.NUM_ROWS - 1, MinesweeperModel.NUM_ROWS - 2);
            } else { // anywhere else in bottom row
                for(int i = row - 1; i <= row; i++) {
                    for(int j = column - 1; j <= column + 1; j++) {
                        if(isHidden(i, j)) {
                            click(i, j);
                        }
                    }
                }
            }
        } else if(column == 0) { // anywhere in left column that is not a corner
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column; j <= column + 1; j++) {
                    if (isHidden(i, j)) {
                        click(i, j);
                    }
                }
            }
        } else if(column == MinesweeperModel.NUM_ROWS - 1) { // anywhere in right column that is not a corner
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column; j++) {
                    if (isHidden(i, j)) {
                        click(i, j);
                    }
                }
            }
        } else { // anywhere in the middle of the board not touching an edge
            for(int i = row - 1; i <= row + 1; i++) {
                for(int j = column - 1; j <= column + 1; j++) {
                    if (isHidden(i, j)) {
                        click(i, j);
                    }
                }
            }
        }
    }


    public void incNumsSurrBomb(int row, int column) {

        if(row == 0) { // is in top row
            if(column == 0) { // is in top left corner
                hiddenGrid[0][1]++;
                hiddenGrid[1][0]++;
                hiddenGrid[1][1]++;
            } else if(column == MinesweeperModel.NUM_ROWS - 1) { // is in top right corner
                hiddenGrid[0][MinesweeperModel.NUM_ROWS - 2]++;
                hiddenGrid[1][MinesweeperModel.NUM_ROWS - 2]++;
                hiddenGrid[1][MinesweeperModel.NUM_ROWS - 1]++;
            } else { // anywhere else in top row
                for(int i = row; i <= row + 1; i++) {
                    for(int j = column - 1; j <= column + 1; j++) {
                        if(isBomb(i, j))
                            continue;
                        hiddenGrid[i][j]++;
                    }
                }
            }
        } else if(row == MinesweeperModel.NUM_ROWS - 1) { // is in bottom row
            if(column == 0) { // is in bottom left corner
                hiddenGrid[MinesweeperModel.NUM_ROWS - 2][0]++;
                hiddenGrid[MinesweeperModel.NUM_ROWS - 2][1]++;
                hiddenGrid[MinesweeperModel.NUM_ROWS - 1][1]++;
            } else if(column == MinesweeperModel.NUM_ROWS - 1) { // is in bottom right corner
                hiddenGrid[MinesweeperModel.NUM_ROWS - 2][MinesweeperModel.NUM_ROWS - 2]++;
                hiddenGrid[MinesweeperModel.NUM_ROWS - 2][MinesweeperModel.NUM_ROWS - 1]++;
                hiddenGrid[MinesweeperModel.NUM_ROWS - 1][MinesweeperModel.NUM_ROWS - 2]++;
            } else { // anywhere else in bottom row
                for(int i = row - 1; i <= row; i++) {
                    for(int j = column - 1; j <= column + 1; j++) {
                        if(isBomb(i, j))
                            continue;
                        hiddenGrid[i][j]++;
                    }
                }
            }
        } else if(column == 0) { // anywhere in left column that is not a corner
            for(int i = row - 1; i <= row + 1; i++) {
                for(int j = column; j <= column + 1; j++) {
                    if(isBomb(i, j))
                        continue;
                    hiddenGrid[i][j]++;
                }
            }
        } else if(column == MinesweeperModel.NUM_ROWS - 1) { // anywhere in right column that is not a corner
            for(int i = row - 1; i <= row + 1; i++) {
                for(int j = column - 1; j <= column; j++) {
                    if(isBomb(i, j))
                        continue;
                    hiddenGrid[i][j]++;
                }
            }
        } else { // anywhere in the middle of the board not touching an edge
            for(int i = row - 1; i <= row + 1; i++) {
                for(int j = column - 1; j <= column + 1; j++) {
                    if(isBomb(i, j))
                        continue;
                    hiddenGrid[i][j]++;
                }
            }
        }
    }
}
