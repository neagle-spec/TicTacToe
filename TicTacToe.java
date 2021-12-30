import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList <Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList <Integer>();

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		char [][] gameBoard = 		
				{{' ','|', ' ', '|' , ' '},
				{'-', '+', '-', '+', '-'},
				{' ','|', ' ', '|' , ' '},
				{'-', '+', '-', '+', '-'},
				{' ','|', ' ', '|' , ' '}};
		
		printGameBoard(gameBoard);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("\n Choose your position (1-9) ");
			int playerPos = scan.nextInt();
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
				System.out.println("Position taken! Choose again"	);
				playerPos = scan.nextInt();
			}
			
			choosePosition(gameBoard, playerPos, "Player");
			String result = checkWinner();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) +1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				playerPos = scan.nextInt();
			}
			choosePosition(gameBoard, cpuPos, "cpu");
			printGameBoard(gameBoard);	
			
			result = checkWinner();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
		}	
	}
	
	public static void printGameBoard(char[][] gameboard) {
		for(char[] row: gameboard) {
			for(char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
		public static void choosePosition(char [][] gameBoard, int pos, String user) {
			char symbol = ' ';
			
			if(user.equals("Player")) {
				symbol = 'X';
				playerPositions.add(pos);
			}else if(user.equals("cpu")){
				symbol = 'O';
				cpuPositions.add(pos);
			}
			switch(pos) {
				case 1:
					gameBoard[0][0] = symbol;
					break;
				case 2:
					gameBoard[0][2] = symbol;
					break;
				case 3:
					gameBoard[0][4] = symbol;
					break;
				case 4:
					gameBoard[2][0] =symbol;
					break;
				case 5:
					gameBoard[2][2] = symbol;
					break;
				case 6:
					gameBoard[2][4] = symbol;
					break;
				case 7:
					gameBoard[4][0] = symbol;
					break;
				case 8:
					gameBoard[4][2] = symbol;
					break;
				case 9:
					gameBoard[4][4] = symbol;
					break;
				default:
					break;
			}
		}
		public static String checkWinner() {
			List<Integer> topRow = Arrays.asList(1, 2, 3);
			List<Integer> midRow = Arrays.asList(4, 5, 6);
			List<Integer> bottomRow = Arrays.asList(7, 8, 9);
			List<Integer> leftCol = Arrays.asList(1, 4, 7);
			List<Integer> midCol = Arrays.asList(2, 5, 8);
			List<Integer> rightCol = Arrays.asList(3, 6, 9);
			List<Integer> leftDiag = Arrays.asList(2, 5, 9);
			List<Integer> rightDiag= Arrays.asList(7, 5, 3);
			
			@SuppressWarnings("rawtypes")
			List<List> winning = new ArrayList<List>();
			winning.add(topRow);
			winning.add(midRow);
			winning.add(bottomRow);
			winning.add(leftCol);
			winning.add(midCol);
			winning.add(rightCol);
			winning.add(leftDiag);
			winning.add(rightDiag);
			
			for(List<?> l: winning) {
				if(playerPositions.containsAll(l)) {
					return "Congradulations you won!";
				}else if (cpuPositions.containsAll(l)) {
					return "Sorry you lost";
				}else if (playerPositions.size() + cpuPositions.size() == 9) {
					return "CAT";
				}
			}
			
			return "";
		}
	}
