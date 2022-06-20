package boardgame;

public class Piece {
	
	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	//acessivel somente para classes dentro de seu pacote
	protected Board getBoard() {
		return board;
	}	

}
