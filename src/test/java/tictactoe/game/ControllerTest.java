package tictactoe.game;

import org.junit.Test;
import tictactoe.IOHelper;
import tictactoe.core.Player;

import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void greet() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print,3, Player.X);

        controller.greetUser();
        assertEquals("Welcome to Tic Tac Toe!\n", io.out.toString());
    }

    @Test
    public void instructions() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);

        controller.printInstructions();
        assertEquals("Enter a number from 1-9\n", io.out.toString());
    }

    @Test
    public void clearScreen() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);

        controller.printInstructions();
        controller.clearScreen();
        String output = io.out.toString();
        String clearSequence = "[H\u001B[2J";

        assertTrue(output.contains(clearSequence));
    }

    @Test
    public void printBoard() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);

        controller.printBoard();
        String[] expectedLines = {
                " 1 | 2 | 3",
                "---*---*---",
                " 4 | 5 | 6",
                "---*---*---",
                " 7 | 8 | 9"
        };
        String expectedBoard = io.joinLines(expectedLines);
        assertEquals(expectedBoard, io.out.toString());
    }

    @Test
    public void isGameOver() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);

        assertFalse("gameOver should be false if game unfinished", controller.isGameOver());
    }

    @Test
    public void printPlayerGuess() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);

        controller.printPlayerGuess(1, Player.X);

        String[] expectedLines = {
                "Player X took tile 1",
                "Your turn Player O"
        };
        String expected = io.joinLines(expectedLines);
        assertEquals(expected, io.out.toString());
    }

    @Test
    public void nextGuess() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);
        controller.nextGuess(1);

        String[] expectedLines = {
                " X | 2 | 3",
                "---*---*---",
                " 4 | 5 | 6",
                "---*---*---",
                " 7 | 8 | 9",
                "Player X took tile 1",
                "Your turn Player O"
        };
        String expected = io.joinLines(expectedLines);
        assertEquals(expected, io.out.toString());
    }

    @Test
    public void printPlayerXWin() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);
        int[] winMoves = {1, 2, 3, 4, 5, 6, 7};

        for (int move: winMoves) {
            controller.nextGuess(move);
        }

        io.reset();

        controller.printStatus();

        String expected = "Player X won!\n";
        assertEquals(expected, io.out.toString());
    }

    @Test
    public void printDraw() {
        IOHelper io = new IOHelper();
        Controller controller = new Controller(io.print, 3, Player.X);
        int[] drawMoves = {1, 2, 3, 5, 4, 6, 8, 7, 9};

        for (int move: drawMoves) {
            controller.nextGuess(move);
        }

        io.reset();

        controller.printStatus();

        String expected = "It's a draw!\n";
        assertEquals(expected, io.out.toString());
    }
}