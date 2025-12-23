package com.example.tictactoe;

public class CellModel {
    private final int row;
    private final int col;
    private char value; // ' ' or 'X' or 'O'

    public CellModel(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = ' ';
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public char getValue() { return value; }
    public void setValue(char value) { this.value = value; }

    public boolean isEmpty() { return value == ' '; }
    public void clear() { value = ' '; }
}
