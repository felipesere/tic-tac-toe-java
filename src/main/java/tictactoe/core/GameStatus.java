package tictactoe.core;

public enum GameStatus {
    Win,
    Draw,
    NonTerminal;

    private boolean gameOver;

    static {
        Win.gameOver = true;
        Draw.gameOver = true;
        NonTerminal.gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}