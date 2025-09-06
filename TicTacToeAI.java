import java.util.Scanner;

public class TicTacToeAI {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + board[i][0] + " " + board[i][1] + " " + board[i][2] + " |");
        }
        System.out.println("---------");
    }

    public static boolean isMovesLeft(char[][] b) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (b[i][j] == ' ')
                    return true;
        return false;
    }

    public static int evaluate(char[][] b) {
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == 'O') return +10;
                else if (b[row][0] == 'X') return -10;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == 'O') return +10;
                else if (b[0][col] == 'X') return -10;
            }
        }
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == 'O') return +10;
            else if (b[0][0] == 'X') return -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == 'O') return +10;
            else if (b[0][2] == 'X') return -10;
        }
        return 0;
    }

    public static int minimax(char[][] b, int depth, boolean isMax) {
        int score = evaluate(b);

        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (!isMovesLeft(b)) return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (b[i][j] == ' ') {
                        b[i][j] = 'O';
                        best = Math.max(best, minimax(b, depth + 1, false));
                        b[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (b[i][j] == ' ') {
                        b[i][j] = 'X';
                        best = Math.min(best, minimax(b, depth + 1, true));
                        b[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    public static int[] findBestMove(char[][] b) {
        int bestVal = -1000;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[i][j] == ' ') {
                    b[i][j] = 'O';
                    int moveVal = minimax(b, 0, false);
                    b[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tic-Tac-Toe! You are X, AI is O.");
        printBoard();

        while (true) {
            System.out.print("Enter your move (row and column: 0 1 2): ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (board[row][col] != ' ') {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            board[row][col] = 'X';
            printBoard();

            if (evaluate(board) == -10) {
                System.out.println("You win!");
                break;
            }
            if (!isMovesLeft(board)) {
                System.out.println("It's a draw!");
                break;
            }

            int[] bestMove = findBestMove(board);
            board[bestMove[0]][bestMove[1]] = 'O';
            System.out.println("AI played at: " + bestMove[0] + " " + bestMove[1]);
            printBoard();

            if (evaluate(board) == 10) {
                System.out.println("AI wins!");
                break;
            }
            if (!isMovesLeft(board)) {
                System.out.println("It's a draw!");
                break;
            }
        }
        sc.close();
    }
}
