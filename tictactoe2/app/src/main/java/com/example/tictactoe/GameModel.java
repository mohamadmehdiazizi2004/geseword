package com.example.tictactoe;

public class GameModel {

    public enum State { PLAYING, X_WON, O_WON, DRAW }

    private final CellModel[][] board = new CellModel[3][3];
    private char currentPlayer; // 'X' or 'O'
    private State state;

    private int scoreX = 0;
    private int scoreO = 0;

    private int[][] winningCells = null; // each item: {r,c}

    public GameModel() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = new CellModel(r, c);
            }
        }
        newRound();
    }

    public void newRound() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].clear();
            }
        }
        currentPlayer = 'X';
        state = State.PLAYING;
        winningCells = null;
    }

    public void resetAll() {
        scoreX = 0;
        scoreO = 0;
        newRound();
    }

    public CellModel getCell(int r, int c) { return board[r][c]; }
    public char getCurrentPlayer() { return currentPlayer; }
    public State getState() { return state; }

    public int getScoreX() { return scoreX; }
    public int getScoreO() { return scoreO; }

    public int[][] getWinningCells() { return winningCells; }

    public boolean play(int r, int c) {
        if (state != State.PLAYING) return false;
        if (!board[r][c].isEmpty()) return false;

        board[r][c].setValue(currentPlayer);

        int[][] line = findWinningLine(currentPlayer);
        if (line != null) {
            winningCells = line;
            if (currentPlayer == 'X') {
                state = State.X_WON;
                scoreX++;
            } else {
                state = State.O_WON;
                scoreO++;
            }
        } else if (isBoardFull()) {
            state = State.DRAW;
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        return true;
    }

    private int[][] findWinningLine(char p) {
        // rows
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getValue() == p && board[r][1].getValue() == p && board[r][2].getValue() == p) {
                return new int[][]{{r,0},{r,1},{r,2}};
            }
        }
        // cols
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getValue() == p && board[1][c].getValue() == p && board[2][c].getValue() == p) {
                return new int[][]{{0,c},{1,c},{2,c}};
            }
        }
        // diagonals
        if (board[0][0].getValue() == p && board[1][1].getValue() == p && board[2][2].getValue() == p) {
            return new int[][]{{0,0},{1,1},{2,2}};
        }
        if (board[0][2].getValue() == p && board[1][1].getValue() == p && board[2][0].getValue() == p) {
            return new int[][]{{0,2},{1,1},{2,0}};
        }

        return null;
    }

    private boolean isBoardFull() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].isEmpty()) return false;
            }
        }
        return true;
    }
}
