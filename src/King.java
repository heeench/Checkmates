public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;


        if (line == toLine && column == toColumn) return false;


        if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) return false;


        if (isUnderAttack(chessBoard, toLine, toColumn)) return false;


        ChessPiece destinationPiece = chessBoard.board[toLine][toColumn];

        if (destinationPiece != null && destinationPiece.getColor().equals(this.getColor())) return false;

        setCheck(false);

        return true;
    }


    public boolean isUnderAttack(ChessBoard chessBoard, int line, int col) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessBoard.board[i][j] != null && !chessBoard.board[i][j].getColor().equals(this.getColor())) {
                    if (chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, col)) {
                        return true;
                    }
                }
            }
        }
        return false;  // Клетка не под атакой
    }
}
