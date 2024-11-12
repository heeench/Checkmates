public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (chessBoard.checkPos(toColumn) || chessBoard.checkPos(toLine) && column == toColumn) {
            if (line == 1 && (toLine - line == 2 || toLine - line == 1) && column == toColumn) return true;
            else if (line == 6 && (line - toLine == 2 || line - toLine == 1) && column == toColumn) return true;
            else if (getColor().equals("White") && toLine - line == 1 && column == toColumn) return true;
            else return (getColor().equals("Black") && line - toLine == 1 && column == toColumn);
        } else return false;
    }
}
