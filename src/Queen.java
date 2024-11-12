public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за границы доски и неподвижный ход
        if (!chessBoard.checkPos(line) || !chessBoard.checkPos(column) ||
                !chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) return false;

        if (line == toLine && column == toColumn) return false;

        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        if (line == toLine || column == toColumn) {
            int rowStep = Integer.compare(toLine - line, 0);
            int colStep = Integer.compare(toColumn - column, 0);

            int currentRow = line + rowStep;
            int currentCol = column + colStep;
            while (currentRow != toLine || currentCol != toColumn) {
                if (chessBoard.board[currentRow][currentCol] != null) return false;
                currentRow += rowStep;
                currentCol += colStep;
            }

        } else if (rowDiff == colDiff) {

            int rowStep = (toLine - line) > 0 ? 1 : -1;
            int colStep = (toColumn - column) > 0 ? 1 : -1;

            int currentRow = line + rowStep;
            int currentCol = column + colStep;
            while (currentRow != toLine && currentCol != toColumn) {
                if (chessBoard.board[currentRow][currentCol] != null) return false;
                currentRow += rowStep;
                currentCol += colStep;
            }
        } else return false;


        if (chessBoard.board[toLine][toColumn] != null &&
                chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) return false;

        return true;
    }
}
