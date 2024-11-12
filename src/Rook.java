public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "R";
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

        if (Math.abs(line - toLine) == Math.abs(column - toColumn)) return false;

        if (line != toLine && column != toColumn) return false;


        if (line == toLine) {
            int colStep = (toColumn - column) > 0 ? 1 : -1;
            int currentCol = column + colStep;
            while (currentCol != toColumn) {
                if (chessBoard.board[line][currentCol] != null) return false;
                currentCol += colStep;
            }
        } else {
            int rowStep = (toLine - line) > 0 ? 1 : -1;
            int currentRow = line + rowStep;
            while (currentRow != toLine) {
                if (chessBoard.board[currentRow][column] != null) return false;
                currentRow += rowStep;
            }
        }


        if (chessBoard.board[toLine][toColumn] != null &&
                chessBoard.board[toLine][toColumn].getColor().equals(this.getColor())) return false;

        setCheck(false);

        return true;
    }
}
