
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Main {

	public static void main(String[] args) {
		// Projected created
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		//if not check this play is continue
		while (!chessMatch.getCheckMate()) {
		try {	
			UI.clearScreen();
			UI.printMatch(chessMatch, captured);
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc); //read source of piece movemented, and valid
			
			boolean[][] possibleMoves = chessMatch.possibleMoves(source); //verify its possible move and printing in board
			UI.clearScreen();
			UI.printBoard(chessMatch.getPieces(), possibleMoves);
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc); //read target position of move 
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target); //if valid perform the movement
			if (capturedPiece != null) {
				captured.add(capturedPiece);
			}
			
			// method the promotion special move
			if (chessMatch.getPromoted() != null) {
				System.out.print("Enter piece for promotion (B/N/R/Q): ");
				String type = sc.nextLine().toUpperCase();
				while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
					System.out.print("Invalid value! Enter pieces for promotion (B/N/R/Q: ");
					type = sc.nextLine().toUpperCase();
				}
				
				chessMatch.replacePromotedPiece(type);
			}
			
			}
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			sc.nextLine();
		}
	}
	UI.clearScreen();
	UI.printMatch(chessMatch, captured);
}

}
