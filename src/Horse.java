public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (line == toLine && column == toColumn || !chessBoard.checkPos(toColumn) || !chessBoard.checkPos(toLine)) return false;
        else return ((Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1) ||
                (Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 2));
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
