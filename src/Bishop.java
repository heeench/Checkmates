public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;

        if (line == toLine && column == toColumn) return false;

        if (Math.abs(line - toLine) != Math.abs(column - toColumn)) return false;

        int rowStep = (toLine - line) > 0 ? 1 : -1;
        int colStep = (toColumn - column) > 0 ? 1 : -1;

        int currentRow = line + rowStep;
        int currentCol = column + colStep;

        while (currentRow != toLine && currentCol != toColumn) {
            if (chessBoard.board[currentRow][currentCol] != null) return false;
            currentRow += rowStep;
            currentCol += colStep;
        }

        if (chessBoard.board[toLine][toColumn] != null &&
                chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) return false;

        return true;
    }
}
