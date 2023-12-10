public class ChessPiece {
    private String type;
    private boolean isWhite;

    public ChessPiece(String type, boolean isWhite) {
        this.type = type;
        this.isWhite = isWhite;
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }
}

class Pawn extends ChessPiece {
    private boolean hasMoved;

    public Pawn(boolean isWhite) {
        super("Pawn", isWhite);
        this.hasMoved = false; // possible fault, check later
    }

    public void move(boolean isTwoMoves) {
        boolean checkWhite = this.getIsWhite(); // possible fault, check later
        boolean checkMove = this.hasMoved;

        if(!isTwoMoves) {
            if(!checkWhite) {
                System.out.println("Black pawn has moved one step");
                this.hasMoved = true;
                return;
            }
            System.out.println("White pawn has moved one step");
            this.hasMoved = true;
            return;
        }

        if(!checkMove){
            if(!checkWhite) {
                System.out.println("Black pawn has moved two steps");
                this.hasMoved = true;
                return;
            }
            System.out.println("White pawn has moved two steps");
            this.hasMoved = true;
            return;
        }
    }

    @Override
    public String toString() {
        boolean checkWhite = this.getIsWhite();
        boolean checkMove = this.hasMoved;
        if(!checkMove) {
            if(!checkWhite)
                return "Black pawn has not yet moved";
            return "White pawn has not yet moved";
        }
        if(!checkWhite)
            return "Black pawn has already moved";
        return "White pawn has already moved";
    }
}