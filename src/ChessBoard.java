public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {

        if (checkPos(startLine) && checkPos(startColumn)) {
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                System.out.println("Фигура не принадлежит текущему игроку.");
                return false;
            }

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                ChessPiece tempPiece = board[endLine][endColumn];
                board[endLine][endColumn] = board[startLine][startColumn];
                board[startLine][startColumn] = null;

                if (isKingInCheck(nowPlayer)) {
                    System.out.println("Король оказался под шахом после хода. Ход отменен.");
                    board[startLine][startColumn] = board[endLine][endColumn];
                    board[endLine][endColumn] = tempPiece;
                    return false;
                }

                this.nowPlayer = this.nowPlayer.equals("White") ? "Black" : "White";
                return true;
            }
        }
        return false;
    }

    public boolean isKingInCheck(String playerColor) {
        int kingRow = -1, kingCol = -1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getSymbol().equals("K") && board[i][j].getColor().equals(playerColor)) {
                    kingRow = i;
                    kingCol = j;
                    break;
                }
            }
        }

        if (kingRow == -1) return false;


        King kingPiece = (King) board[kingRow][kingCol];
        return kingPiece.isUnderAttack(this, kingRow, kingCol);
    }


    public boolean isCheckmate(String playerColor) {
        if (!isKingInCheck(playerColor)) return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getColor().equals(playerColor)) {
                    for (int row = 0; row < 8; row++) {
                        for (int col = 0; col < 8; col++) {
                            if (board[i][j].canMoveToPosition(this, i, j, row, col)) {
                                ChessPiece tempPiece = board[row][col];
                                board[row][col] = board[i][j];
                                board[i][j] = null;

                                boolean inCheck = isKingInCheck(playerColor);

                                board[i][j] = board[row][col];
                                board[row][col] = tempPiece;

                                if (!inCheck) return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }


    public boolean castling0() {
        boolean canMove = true;
        String playerColor = nowPlayerColor();

        int row = playerColor.equals("White") ? 0 : 7;

        if (board[row][4] == null || board[row][0] == null) return false;
        if (!board[row][4].getSymbol().equals("K") || !board[row][0].getSymbol().equals("R")) return false;
        if (!board[row][4].getColor().equals(playerColor) || !board[row][0].getColor().equals(playerColor)) return false;

        if (board[row][4].isCheck() || board[row][0].isCheck()) return false;

        for (int j = 1; j < 4; j++) {
            if (board[row][j] != null) {
                canMove = false;
                break;
            }
        }

        if (isKingInCheck(playerColor)) return false;
        if (willKingPassThroughCheck(row, 2) || willKingPassThroughCheck(row, 3)) return false;

        if (canMove) {
            board[row][2] = board[row][4];
            board[row][4] = null;
            board[row][3] = board[row][0];
            board[row][0] = null;

            this.nowPlayer = this.nowPlayer.equals("White") ? "Black" : "White";
            return true;
        }
        return false;
    }

    public boolean castling7() {
        boolean canMove = true;
        String playerColor = nowPlayerColor();

        int row = playerColor.equals("White") ? 0 : 7;

        if (board[row][4] == null || board[row][7] == null) return false;
        if (!board[row][4].getSymbol().equals("K") || !board[row][7].getSymbol().equals("R")) return false;
        if (!board[row][4].getColor().equals(playerColor) || !board[row][7].getColor().equals(playerColor)) return false;

        if (board[row][4].isCheck() || board[row][7].isCheck()) return false;

        for (int j = 5; j < 7; j++) {
            if (board[row][j] != null) {
                canMove = false;
                break;
            }
        }

        if (isKingInCheck(playerColor)) return false;
        if (willKingPassThroughCheck(row, 5) || willKingPassThroughCheck(row, 6)) return false;

        if (canMove) {
            board[row][6] = board[row][4];
            board[row][4] = null;
            board[row][5] = board[row][7];
            board[row][7] = null;

            this.nowPlayer = this.nowPlayer.equals("White") ? "Black" : "White";
            return true;
        }
        return false;
    }

    private boolean willKingPassThroughCheck(int row, int column) {
        ChessPiece king = board[row][4];
        board[row][column] = king;
        board[row][4] = null;
        boolean inCheck = isKingInCheck(king.getColor());
        board[row][4] = king;
        board[row][column] = null;
        return inCheck;
    }
}
