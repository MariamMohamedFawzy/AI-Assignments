import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;



public class Helper {
	
	static final int INITIAL_CELL = 0;
	static final int GOAL_CELL = 1;
	
	static final int CELL_UP = 0;
	static final int CELL_DOWN = 1;
	static final int CELL_RIGHT = 2;
	static final int CELL_LEFT = 3;
	
	static final int CELL_UP_DOWN = 0;
	static final int CELL_RIGHT_LEFT = 1;
	static final int CELL_UP_RIGHT = 2;
	static final int CELL_UP_LEFT = 3;
	static final int CELL_DOWN_RIGHT = 4;
	static final int CELL_DOWN_LEFT = 5;
	
	static final int FIXED = 0;
	static final int MOVABLE = 1;
	
	
	static final int ENQUE_AT_END = 0;
	static final int ENQUE_AT_FRONT = 1;
	static final int ENQUE_AT_FRONT_ITERATIVELY = 2;
	static final int ORDERED_INSERT_HEURISTIC1 = 3;
	static final int ORDERED_INSERT_HEURISTIC2 = 4;
	static final int ORDERED_INSERT_COST_AND_HEURISTIC = 5;

	public static void buildCellWithOneOpening(Celltype[][] finalGrid, int x, int y, int cellType) { 
		int[] possibleOpenings;
		int rows = finalGrid.length; 
		int cols = finalGrid[0].length;
		if (x == 0) { // No down opening
			if (y == 0) { // No left opening
				possibleOpenings = new int[]{CELL_UP, CELL_RIGHT}; // 0 for up, 1 for right
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_UP) {
					if (cellType == INITIAL_CELL) {
					finalGrid[x][y] = Celltype.INITIAL_UP;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_UP;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_RIGHT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_RIGHT;
					}
				}
			} else if (y == cols - 1) { // No right opening
				possibleOpenings = new int[]{CELL_UP, CELL_LEFT}; // 0 for up, 1 for left
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_UP) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_UP;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_UP;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_LEFT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_LEFT;
					}
				}
			} else {
				possibleOpenings = new int[]{CELL_UP, CELL_RIGHT, CELL_LEFT}; // 0 for up, 1 for right, 2 for left
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_UP) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_UP;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_UP;
					}
				} else if (opening == CELL_RIGHT) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_RIGHT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_RIGHT;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_LEFT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_LEFT;
					}
				}
			}
		} else if (x == rows - 1) { // No up opening
			if (y == 0) { // No left opening
				possibleOpenings = new int[]{CELL_DOWN, CELL_RIGHT}; // 0 for down, 1 for right
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_DOWN) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_DOWN;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_DOWN;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_RIGHT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_RIGHT;
					}
				}
			} else if (y == cols - 1) { // No right opening
				possibleOpenings = new int[]{CELL_DOWN, CELL_LEFT}; // 0 for down, 1 for left
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_DOWN) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_DOWN;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_DOWN;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_LEFT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_LEFT;
					}
				}
			} else {
				possibleOpenings = new int[]{CELL_DOWN, CELL_RIGHT, CELL_LEFT}; // 0 for down, 1 for right, 2 for left
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_DOWN) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_DOWN;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_DOWN;
					}
				} else if (opening == CELL_RIGHT) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_RIGHT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_RIGHT;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_LEFT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_LEFT;
					}
				}
			}
		} else {
			if (y == 0) { // No left opening
				possibleOpenings = new int[]{CELL_UP, CELL_DOWN, CELL_RIGHT}; // 0 for up, 1 for down, 2 for right
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_UP) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_UP;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_UP;
					}
				} else if (opening == CELL_DOWN) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_DOWN;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_DOWN;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_RIGHT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_RIGHT;
					}
				}
			} else if (y == cols - 1) { // No right opening
				possibleOpenings = new int[]{CELL_UP, CELL_DOWN, CELL_LEFT}; // 0 for up, 1 for down, 2 for left
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_UP) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_UP;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_UP;
					}
				} else if (opening == CELL_DOWN) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_DOWN;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_DOWN;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_LEFT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_LEFT;
					}
				}
			} else {
				possibleOpenings = new int[]{CELL_UP, CELL_DOWN, CELL_RIGHT, CELL_LEFT}; // 0 for up, 1 for down, 2 for right, 3 for left
				int openingIndex = (int)(Math.random() * possibleOpenings.length);
				int opening = possibleOpenings[openingIndex];
				if (opening == CELL_UP) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_UP;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_UP;
					}
				} else if (opening == CELL_DOWN) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_DOWN;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_DOWN;
					}
				} else if (opening == CELL_RIGHT) {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_RIGHT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_RIGHT;
					}
				} else {
					if (cellType == INITIAL_CELL) {
						finalGrid[x][y] = Celltype.INITIAL_LEFT;
					} else if (cellType == GOAL_CELL) {
						finalGrid[x][y] = Celltype.GOAL_LEFT;
					}
				}
			}
		}
	}
	
	
	public static void buildCellWithTwoOpenings(Celltype[][] finalGrid, int x, int y, int moving) {
		int rows = finalGrid.length;
		int cols = finalGrid[0].length;
		
		if (moving == FIXED) {
			int[] possibleOpenings;
			
			if (x == 0) {
				if (y == 0) {
					finalGrid[x][y] = Celltype.PATH_FIXED_UP_RIGHT;
				} else if (y == cols - 1) {
					finalGrid[x][y] = Celltype.PATH_FIXED_UP_LEFT;
				} else {
					possibleOpenings = new int[] {CELL_RIGHT_LEFT, CELL_UP_LEFT, CELL_UP_RIGHT};
					int indexOfOpenning = (int)(Math.random() * possibleOpenings.length);
					int opening = possibleOpenings[indexOfOpenning];
					if (opening == CELL_RIGHT_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_RIGHT_LEFT;
					} else if (opening == CELL_UP_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_LEFT;
					} else if (opening == CELL_UP_RIGHT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_RIGHT;
					}
				}
			} else if (x == rows - 1) {
				if (y == 0) {
					finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_RIGHT;
				} else if (y == cols - 1) {
					finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_LEFT;
				} else {
					possibleOpenings = new int[] {CELL_RIGHT_LEFT, CELL_DOWN_LEFT, CELL_DOWN_RIGHT};
					int indexOfOpenning = (int)(Math.random() * possibleOpenings.length);
					int opening = possibleOpenings[indexOfOpenning];
					if (opening == CELL_RIGHT_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_RIGHT_LEFT;
					} else if (opening == CELL_DOWN_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_LEFT;
					} else if (opening == CELL_DOWN_RIGHT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_RIGHT;
					}
				}
			} else {
				if (y == 0) {
					possibleOpenings = new int[] {CELL_UP_DOWN, CELL_UP_RIGHT, CELL_DOWN_RIGHT};
					int indexOfOpenning = (int)(Math.random() * possibleOpenings.length);
					int opening = possibleOpenings[indexOfOpenning];
					if (opening == CELL_UP_DOWN) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_DOWN;
					} else if (opening == CELL_UP_RIGHT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_RIGHT;
					} else if (opening == CELL_DOWN_RIGHT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_RIGHT;
					}
				} else if (y == cols - 1) {
					possibleOpenings = new int[] {CELL_UP_DOWN, CELL_UP_LEFT, CELL_DOWN_LEFT};
					int indexOfOpenning = (int)(Math.random() * possibleOpenings.length);
					int opening = possibleOpenings[indexOfOpenning];
					if (opening == CELL_UP_DOWN) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_DOWN;
					} else if (opening == CELL_UP_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_LEFT;
					} else if (opening == CELL_DOWN_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_LEFT;
					}
				} else {
					int opening = (int)(Math.random() * 6);
					if (opening == CELL_UP_DOWN) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_DOWN;
					} else if (opening == CELL_UP_RIGHT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_RIGHT;
					} else if (opening == CELL_DOWN_RIGHT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_RIGHT;
					} else if (opening == CELL_UP_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_UP_LEFT;
					} else if (opening == CELL_DOWN_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_DOWN_LEFT;
					} else if (opening == CELL_RIGHT_LEFT) {
						finalGrid[x][y] = Celltype.PATH_FIXED_RIGHT_LEFT;
					} 
				}
			}
		} else if (moving == MOVABLE) {
			int openning = (int)(Math.random() * 6);
			if (openning == CELL_UP_DOWN) {
				finalGrid[x][y] = Celltype.PATH_MOVABLE_UP_DOWN;
			} else if (openning == CELL_RIGHT_LEFT) {
				finalGrid[x][y] = Celltype.PATH_MOVABLE_RIGHT_LEFT;
			} else if (openning == CELL_UP_RIGHT) {
				finalGrid[x][y] = Celltype.PATH_MOVABLE_UP_RIGHT;
			} else if (openning == CELL_UP_LEFT) {
				finalGrid[x][y] = Celltype.PATH_MOVABLE_UP_LEFT;
			} else if (openning == CELL_DOWN_RIGHT) {
				finalGrid[x][y] = Celltype.PATH_MOVABLE_DOWN_RIGHT;
			} else if (openning == CELL_DOWN_LEFT) {
				finalGrid[x][y] = Celltype.PATH_MOVABLE_DOWN_LEFT;
			}
		}
		
		
	}
	
	
	public static boolean goalTest(Celltype[][] testingGrid, int xInitial, int yInitial) {
		int x = xInitial;
		int y = yInitial;
		int rowsForY = testingGrid.length;
		int colsForX = testingGrid[0].length;
		
		int up = 0;
		int down = 1;
		int right = 2;
		int left = 3;
		
		
		
		int nextDirection = -1;
		// y -> row, x -> col
		
		if (testingGrid[yInitial][xInitial] == Celltype.INITIAL_DOWN) {
				y = y - 1;
			if (y >= 0 && (testingGrid[y][x] == Celltype.BLANK
					|| testingGrid[y][x] == Celltype.BLOCK) ){
				return false;
			}
			if (y >= 0 && (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN )) {
				nextDirection = down;
			} else if (y >= 0 && (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) ){
				nextDirection = left;
			}
			else if (y >= 0 && (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT)) {
				nextDirection = right;
			} else {
				return false;
			}
			
		} else if (testingGrid[yInitial][xInitial] == Celltype.INITIAL_UP) {
			y = y + 1;
			if (y < rowsForY && (testingGrid[y][x] == Celltype.BLANK
					|| testingGrid[x][y] == Celltype.BLOCK) ){
				return false;
			}
			if (y < rowsForY && (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN )) {
				nextDirection = up;
			} else if (y < rowsForY && (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT)) {
				nextDirection = left;
			}
			else if (y < rowsForY && (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT)) {
				nextDirection = right;
			} else {
				return false;
			}
		} else if (testingGrid[yInitial][xInitial] == Celltype.INITIAL_RIGHT) {
			x = x + 1;
			if (x < colsForX && (testingGrid[y][x] == Celltype.BLANK
					|| testingGrid[y][x] == Celltype.BLOCK)) {
				return false;
			}
			if (x < colsForX && (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) ){
				nextDirection = right;
			} else if (x < colsForX && (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) ){
				nextDirection = down;
			}
			else if (x < colsForX && (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT)) {
				nextDirection = up;
			} else {
				return false;
			}
		} else if (testingGrid[yInitial][xInitial] == Celltype.INITIAL_LEFT) {
			x = x - 1;
			if (x >= 0 && (testingGrid[y][x] == Celltype.BLANK
					|| testingGrid[y][x] == Celltype.BLOCK)) {
				return false;
			}
			if (x >= 0 && (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) ){
				nextDirection = left;
			} else if (x >= 0 && (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT)) {
				nextDirection = down;
			}
			else if (x >= 0 && (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) ){
				nextDirection = up;
			} else {
				return false;
			}
		} 
		while (testingGrid[y][x] != Celltype.GOAL_DOWN
				&& testingGrid[y][x] != Celltype.GOAL_UP
				&& testingGrid[y][x] != Celltype.GOAL_RIGHT
				&& testingGrid[y][x] != Celltype.GOAL_LEFT) {
			
			
			if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN) {
				if (nextDirection == up) {
					y = y + 1;
					if (y >= rowsForY) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[x][y] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN ) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
						nextDirection = left;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					}
					else {
						return false;
					}
				} else if (nextDirection == down) {
					y = y - 1;
					if (y < 0) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN ) {
						nextDirection = down;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) {
						nextDirection = left;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				}
			}
			else if (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
				if (nextDirection == right) {
					x = x + 1;
					if (x >= colsForX) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
						nextDirection = down;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				} else if (nextDirection == left) {
					x = x - 1;
					if (x < 0) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
						nextDirection = left;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
						nextDirection = down;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				}
				
			} else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) {
				if (nextDirection == up) {
					y = y + 1;
					if (y >= rowsForY) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[x][y] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN ) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
						nextDirection = left;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				} else if (nextDirection == right) {
					x = x + 1;
					if (x >= colsForX) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
						nextDirection = down;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				}
				
			} else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) {
				if (nextDirection == up) {
					y = y + 1;
					if (y >= rowsForY) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[x][y] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN ) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
						nextDirection = left;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				} else if (nextDirection == left) {
					x = x - 1;
					if (x < 0) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
						nextDirection = left;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
						nextDirection = down;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				}
				
			} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
				if (nextDirection == down) {
					y = y - 1;
					if (y < 0) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN ) {
						nextDirection = down;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) {
						nextDirection = left;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				} else if (nextDirection == right) {
					x = x + 1;
					if (x >= colsForX) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
						nextDirection = down;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				}
				
			} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_LEFT
					|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
				if (nextDirection == down) {
					y = y - 1;
					if (y < 0) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_DOWN 
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_DOWN ) {
						nextDirection = down;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_LEFT) {
						nextDirection = left;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) {
						nextDirection = right;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				} else if (nextDirection == left) {
					x = x - 1;
					if (x < 0) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.BLANK
							|| testingGrid[y][x] == Celltype.BLOCK) {
						return false;
					}
					if (testingGrid[y][x] == Celltype.PATH_FIXED_RIGHT_LEFT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
						nextDirection = left;
					} else if (testingGrid[y][x] == Celltype.PATH_FIXED_DOWN_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
						nextDirection = down;
					}
					else if (testingGrid[y][x] == Celltype.PATH_FIXED_UP_RIGHT
							|| testingGrid[y][x] == Celltype.PATH_MOVABLE_UP_RIGHT) {
						nextDirection = up;
					} else if (testingGrid[y][x] == Celltype.GOAL_DOWN
							|| testingGrid[y][x] == Celltype.GOAL_UP
							|| testingGrid[y][x] == Celltype.GOAL_RIGHT
							|| testingGrid[y][x] == Celltype.GOAL_LEFT) {
						return true;
					} else {
						return false;
					}
				}
			}
			
		}
		return true;
	}
	
	public static boolean isTheSameGrid(Celltype[][] grid1, Celltype[][] grid2) {
		for (int i = 0; i < grid1.length; i++) {
			for (int j = 0; j < grid1[0].length; j++) {
				if (grid1[i][j] != grid2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static Object[] getTheNextGrids(Action[] problem_actions, Celltype[][] grid, ArrayList<Celltype[][]> history) {
		ArrayList<Celltype[][]> result = new ArrayList<Celltype[][]>();
		ArrayList<Action> actions = new ArrayList<Action>();
		Object[] obj = new Object[2];
		int rows = grid.length;
		int cols = grid[0].length;
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				if (grid[i][j] == Celltype.PATH_MOVABLE_UP_DOWN) {
//					// up
//					int tempY = i + 1;
//					if (tempY < rows && grid[tempY][j] == Celltype.BLANK) {
//						Celltype[][] copy = new Celltype[rows][cols];
//						for (int k = 0; k < rows; k++) {
//							for (int h = 0; h < cols; h++) {
//								copy[k][h] = grid[k][h];
//							}
//						}
//						copy[i][j] = Celltype.BLANK;
//						copy[i + 1][j] = Celltype.PATH_MOVABLE_UP_DOWN;
//						for (int f= 0; f < history.size(); f++) {
//							if (isTheSameGrid(copy, history.get(f))) {
//								continue;
//							}
//						}
//						result.add(copy);
//						actions.add(Action.MOVE_UP);
//					} else {
//						tempY = i - 1;
//						// down
//						if (tempY >= 0 && grid[tempY][j] == Celltype.BLANK) {
//							Celltype[][] copy = new Celltype[rows][cols];
//							for (int k = 0; k < rows; k++) {
//								for (int h = 0; h < cols; h++) {
//									copy[k][h] = grid[k][h];
//								}
//							}
//							copy[i][j] = Celltype.BLANK;
//							copy[i - 1][j] = Celltype.PATH_MOVABLE_UP_DOWN;
//							for (int f= 0; f < history.size(); f++) {
//								if (isTheSameGrid(copy, history.get(f))) {
//									continue;
//								}
//							}
//							result.add(copy);
//							actions.add(Action.MOVE_DOWN);
//						}
//					}
//					
//				} else if (grid[i][j] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
//					// right
//					int tempX = j + 1;
//					if (tempX < cols && grid[i][tempX] == Celltype.BLANK) {
//						Celltype[][] copy = new Celltype[rows][cols];
//						for (int k = 0; k < rows; k++) {
//							for (int h = 0; h < cols; h++) {
//								copy[k][h] = grid[k][h];
//							}
//						}
//						copy[i][j] = Celltype.BLANK;
//						copy[i][j + 1] = Celltype.PATH_MOVABLE_RIGHT_LEFT;
//						for (int f= 0; f < history.size(); f++) {
//							if (isTheSameGrid(copy, history.get(f))) {
//								continue;
//							}
//						}
//						result.add(copy);
//						actions.add(Action.MOVE_RIGHT);
//					} else {
//						tempX = j - 1;
//						// left
//						if (tempX >= 0 && grid[i][tempX] == Celltype.BLANK) {
//							Celltype[][] copy = new Celltype[rows][cols];
//							for (int k = 0; k < rows; k++) {
//								for (int h = 0; h < cols; h++) {
//									copy[k][h] = grid[k][h];
//								}
//							}
//							copy[i][j] = Celltype.BLANK;
//							copy[i][j - 1] = Celltype.PATH_MOVABLE_RIGHT_LEFT;
//							for (int f= 0; f < history.size(); f++) {
//								if (isTheSameGrid(copy, history.get(f))) {
//									continue;
//								}
//							}
//							result.add(copy);
//							actions.add(Action.MOVE_LEFT);
//						}
//					}
//				} else if (grid[i][j] == Celltype.PATH_MOVABLE_UP_LEFT) {
//					// up
//					int tempY = i + 1;
//					if (tempY < rows && grid[tempY][j] == Celltype.BLANK) {
//						Celltype[][] copy = new Celltype[rows][cols];
//						for (int k = 0; k < rows; k++) {
//							for (int h = 0; h < cols; h++) {
//								copy[k][h] = grid[k][h];
//							}
//						}
//						copy[i][j] = Celltype.BLANK;
//						copy[i + 1][j] = Celltype.PATH_MOVABLE_UP_LEFT;
//						for (int f= 0; f < history.size(); f++) {
//							if (isTheSameGrid(copy, history.get(f))) {
//								continue;
//							}
//						}
//						result.add(copy);
//						actions.add(Action.MOVE_UP);
//					} else {
//						int tempX = j - 1;
//						// left
//						if (tempX >= 0 && grid[i][tempX] == Celltype.BLANK) {
//							Celltype[][] copy = new Celltype[rows][cols];
//							for (int k = 0; k < rows; k++) {
//								for (int h = 0; h < cols; h++) {
//									copy[k][h] = grid[k][h];
//								}
//							}
//							copy[i][j] = Celltype.BLANK;
//							copy[i][j - 1] = Celltype.PATH_MOVABLE_UP_LEFT;
//							for (int f= 0; f < history.size(); f++) {
//								if (isTheSameGrid(copy, history.get(f))) {
//									continue;
//								}
//							}
//							result.add(copy);
//							actions.add(Action.MOVE_LEFT);
//						}
//					}
//				} else if (grid[i][j] == Celltype.PATH_MOVABLE_UP_RIGHT) {
//					// up
//					int tempY = i + 1;
//					if (tempY < rows && grid[tempY][j] == Celltype.BLANK) {
//						Celltype[][] copy = new Celltype[rows][cols];
//						for (int k = 0; k < rows; k++) {
//							for (int h = 0; h < cols; h++) {
//								copy[k][h] = grid[k][h];
//							}
//						}
//						copy[i][j] = Celltype.BLANK;
//						copy[i + 1][j] = Celltype.PATH_MOVABLE_UP_RIGHT;
//						for (int f= 0; f < history.size(); f++) {
//							if (isTheSameGrid(copy, history.get(f))) {
//								continue;
//							}
//						}
//						result.add(copy);
//						actions.add(Action.MOVE_UP);
//					} else {
//						int tempX = j + 1;
//						// right
//						if (tempX < cols && grid[i][tempX] == Celltype.BLANK) {
//							Celltype[][] copy = new Celltype[rows][cols];
//							for (int k = 0; k < rows; k++) {
//								for (int h = 0; h < cols; h++) {
//									copy[k][h] = grid[k][h];
//								}
//							}
//							copy[i][j] = Celltype.BLANK;
//							copy[i][j + 1] = Celltype.PATH_MOVABLE_UP_RIGHT;
//							for (int f= 0; f < history.size(); f++) {
//								if (isTheSameGrid(copy, history.get(f))) {
//									continue;
//								}
//							}
//							result.add(copy);
//							actions.add(Action.MOVE_RIGHT);
//						}
//					}
//				} else if (grid[i][j] == Celltype.PATH_MOVABLE_DOWN_LEFT) {
//					// down
//					int tempY = i - 1;
//					if (tempY >= 0 && grid[tempY][j] == Celltype.BLANK) {
//						Celltype[][] copy = new Celltype[rows][cols];
//						for (int k = 0; k < rows; k++) {
//							for (int h = 0; h < cols; h++) {
//								copy[k][h] = grid[k][h];
//							}
//						}
//						copy[i][j] = Celltype.BLANK;
//						copy[i - 1][j] = Celltype.PATH_MOVABLE_DOWN_LEFT;
//						for (int f= 0; f < history.size(); f++) {
//							if (isTheSameGrid(copy, history.get(f))) {
//								continue;
//							}
//						}
//						result.add(copy);
//						actions.add(Action.MOVE_DOWN);
//					} else {
//						int tempX = j - 1;
//						// left
//						if (tempX >= 0 && grid[i][tempX] == Celltype.BLANK) {
//							Celltype[][] copy = new Celltype[rows][cols];
//							for (int k = 0; k < rows; k++) {
//								for (int h = 0; h < cols; h++) {
//									copy[k][h] = grid[k][h];
//								}
//							}
//							copy[i][j] = Celltype.BLANK;
//							copy[i][j - 1] = Celltype.PATH_MOVABLE_DOWN_LEFT;
//							for (int f= 0; f < history.size(); f++) {
//								if (isTheSameGrid(copy, history.get(f))) {
//									continue;
//								}
//							}
//							result.add(copy);
//							actions.add(Action.MOVE_LEFT);
//						}
//					}
//				} else if (grid[i][j] == Celltype.PATH_MOVABLE_DOWN_RIGHT) {
//					// down
//					int tempY = i - 1;
//					if (tempY >= 0 && grid[tempY][j] == Celltype.BLANK) {
//						Celltype[][] copy = new Celltype[rows][cols];
//						for (int k = 0; k < rows; k++) {
//							for (int h = 0; h < cols; h++) {
//								copy[k][h] = grid[k][h];
//							}
//						}
//						copy[i][j] = Celltype.BLANK;
//						copy[i - 1][j] = Celltype.PATH_MOVABLE_DOWN_RIGHT;
//						for (int f= 0; f < history.size(); f++) {
//							if (isTheSameGrid(copy, history.get(f))) {
//								continue;
//							}
//						}
//						result.add(copy);
//						actions.add(Action.MOVE_DOWN);
//					} else {
//						int tempX = j + 1;
//						// right
//						if (tempX < cols && grid[i][tempX] == Celltype.BLANK) {
//							Celltype[][] copy = new Celltype[rows][cols];
//							for (int k = 0; k < rows; k++) {
//								for (int h = 0; h < cols; h++) {
//									copy[k][h] = grid[k][h];
//								}
//							}
//							copy[i][j] = Celltype.BLANK;
//							copy[i][j + 1] = Celltype.PATH_MOVABLE_DOWN_RIGHT;
//							for (int f= 0; f < history.size(); f++) {
//								if (isTheSameGrid(copy, history.get(f))) {
//									continue;
//								}
//							}
//							result.add(copy);
//							actions.add(Action.MOVE_RIGHT);
//						}
//					}
//				}
//			}
//		}
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == Celltype.PATH_MOVABLE_DOWN_LEFT
						|| grid[i][j] == Celltype.PATH_MOVABLE_DOWN_RIGHT
						|| grid[i][j] == Celltype.PATH_MOVABLE_UP_DOWN
						|| grid[i][j] == Celltype.PATH_MOVABLE_UP_RIGHT
						|| grid[i][j] == Celltype.PATH_MOVABLE_UP_LEFT
						|| grid[i][j] == Celltype.PATH_MOVABLE_RIGHT_LEFT) {
					// up
					if (i + 1 < rows && grid[i + 1][j] == Celltype.BLANK) {
						Celltype[][] copy = new Celltype[rows][cols];
						for (int k = 0; k < rows; k++) {
							for (int h = 0; h < cols; h++) {
								copy[k][h] = grid[k][h];
							}
						}
						copy[i + 1][j] = grid[i][j];
						copy[i][j] = Celltype.BLANK;
						boolean ok = true;
						for (int f= 0; f < history.size(); f++) {
							if (isTheSameGrid(copy, history.get(f))) {
								ok = false;
								break;
							}
						}
						if (ok) {
							result.add(copy);
							actions.add(problem_actions[3]);
						}
					}
					// down
					if (i - 1 >= 0 && grid[i - 1][j] == Celltype.BLANK) {
						Celltype[][] copy = new Celltype[rows][cols];
						for (int k = 0; k < rows; k++) {
							for (int h = 0; h < cols; h++) {
								copy[k][h] = grid[k][h];
							}
						}
						copy[i - 1][j] = grid[i][j];
						copy[i][j] = Celltype.BLANK;
						boolean ok = true;
						for (int f= 0; f < history.size(); f++) {
							if (isTheSameGrid(copy, history.get(f))) {
								ok = false;
								break;
							}
						}
						if (ok) {
							result.add(copy);
							actions.add(problem_actions[0]);
						}
					}
					// right
					if (j + 1 < cols && grid[i][j + 1] == Celltype.BLANK) {
						Celltype[][] copy = new Celltype[rows][cols];
						for (int k = 0; k < rows; k++) {
							for (int h = 0; h < cols; h++) {
								copy[k][h] = grid[k][h];
							}
						}
						copy[i][j + 1] = grid[i][j];
						copy[i][j] = Celltype.BLANK;
						boolean ok = true;
						for (int f= 0; f < history.size(); f++) {
							if (isTheSameGrid(copy, history.get(f))) {
								ok = false;
								break;
							}
						}
						if (ok) {
							result.add(copy);
							actions.add(problem_actions[2]);
						}
					}
					// left
					if (j - 1 >= 0 && grid[i][j - 1] == Celltype.BLANK) {
						Celltype[][] copy = new Celltype[rows][cols];
						for (int k = 0; k < rows; k++) {
							for (int h = 0; h < cols; h++) {
								copy[k][h] = grid[k][h];
							}
						}
						copy[i][j - 1] = grid[i][j];
						copy[i][j] = Celltype.BLANK;
						boolean ok = true;
						for (int f= 0; f < history.size(); f++) {
							if (isTheSameGrid(copy, history.get(f))) {
								ok = false;
								break;
							}
						}
						if (ok) {
							result.add(copy);
							actions.add(problem_actions[1]);
						}
					}
				}
			}
		}
		
		obj[0] = result;
		obj[1] = actions;
		return obj;
	}
	
	public static int[] helperToGotInitialXY(Celltype [][] grid) {
		int xInitial = -1;
		int yInitial = -1;
		// determine the position of the initial cell
		for (int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == Celltype.INITIAL_DOWN
						|| grid[i][j] == Celltype.INITIAL_UP
						|| grid[i][j] == Celltype.INITIAL_RIGHT
						|| grid[i][j] == Celltype.INITIAL_LEFT) {
						yInitial = i;
						xInitial = j;
				}
			}
		}
		
		return new int[] {xInitial, yInitial};
	}
	
	public static Object makeQ(int strategy) {
		switch(strategy) {
		case ENQUE_AT_END:
			Queue<Node> nodes = new LinkedList<Node>();
			return nodes;
		case ENQUE_AT_FRONT: 
		case ENQUE_AT_FRONT_ITERATIVELY:
			Stack<Node> nodes2 = new Stack<Node>();
			return nodes2;
		case ORDERED_INSERT_HEURISTIC1:
		case ORDERED_INSERT_HEURISTIC2:
		case ORDERED_INSERT_COST_AND_HEURISTIC:
			PriorityQueue<Node> nodes3 = new PriorityQueue<Node>();
			return nodes3;
		}
		return null;
	}
	
	public static void insertNode(Object nodes, Node node, int strategy) {
		switch(strategy) {
		case ENQUE_AT_END:
			Queue<Node> nodes2 = (Queue) nodes;
			nodes2.add(node);
			nodes = nodes2;
			break;
		case ENQUE_AT_FRONT:
		case ENQUE_AT_FRONT_ITERATIVELY:
			Stack<Node> stack = (Stack) nodes;
			stack.push(node);
			nodes = stack;
			break;
		case ORDERED_INSERT_HEURISTIC1:
		case ORDERED_INSERT_HEURISTIC2:
		case ORDERED_INSERT_COST_AND_HEURISTIC:
			PriorityQueue<Node> priorityQueue = (PriorityQueue<Node>) nodes;
			priorityQueue.add(node);
			nodes = priorityQueue;
			break;
		}
	}
	
	public static boolean checkEmpty(Object nodes, int strategy) {
		switch(strategy) {
		case ENQUE_AT_END:
			Queue<Node> nodes2 = (Queue) nodes;
			return nodes2.isEmpty();
		case ENQUE_AT_FRONT:
		case ENQUE_AT_FRONT_ITERATIVELY:
			Stack<Node> stack = (Stack) nodes;
			return stack.isEmpty();
		case ORDERED_INSERT_HEURISTIC1:
		case ORDERED_INSERT_HEURISTIC2:
		case ORDERED_INSERT_COST_AND_HEURISTIC: 
			PriorityQueue<Node> priorityQueue = (PriorityQueue<Node>) nodes;
			return priorityQueue.isEmpty();
		}
		return false;
	}
	
	
	public static Node removeFront(Object nodes, int strategy) {
		switch(strategy) {
		case ENQUE_AT_END:
			Queue<Node> nodes2 = (Queue) nodes;
			return nodes2.poll();
		case ENQUE_AT_FRONT:
		case ENQUE_AT_FRONT_ITERATIVELY:
			Stack<Node> stack = (Stack) nodes;
			return stack.pop();
		case ORDERED_INSERT_HEURISTIC1:
		case ORDERED_INSERT_HEURISTIC2:
		case ORDERED_INSERT_COST_AND_HEURISTIC:
			PriorityQueue<Node> priorityQueue = (PriorityQueue<Node>) nodes;
			return priorityQueue.poll();
		}
		return null;
	}

	public static ArrayList<Node> expandNode(Problem problem, Node node, ArrayList<Celltype[][]> history, int strategy) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		Object[] next = getTheNextGrids((Action[])problem.operators, (Celltype[][]) node.currentState, history);
		ArrayList<Celltype[][]> grids = (ArrayList<Celltype[][]>) next[0];
		ArrayList<Action> actions2 = (ArrayList<Action>) next[1];
		// TODO add cost according to the strategy
		double h = 0;
		Node nextNode = null;
		for (int i = 0; i < grids.size(); i++) {
			switch(strategy) {
			case ENQUE_AT_END:
			case ENQUE_AT_FRONT:
			case ENQUE_AT_FRONT_ITERATIVELY: 
				h = 0;
				nextNode = new Node(node, node.depth + 1, node.cost + problem.pathCost(), grids.get(i), actions2.get(i));
				break;
			case ORDERED_INSERT_HEURISTIC1: 
				double any_cost_to_choose_from[] = {1, 2, 0.5};
				int indexTemp = (int) (Math.random() * any_cost_to_choose_from.length);
				h = any_cost_to_choose_from[indexTemp];
				nextNode = new Node(node, node.depth + 1, node.cost + h, grids.get(i), actions2.get(i));
				break;
			case ORDERED_INSERT_HEURISTIC2: 
				nextNode = new Node(node, node.depth + 1, node.cost + h, grids.get(i), actions2.get(i));
				h = 1;
				break;
			case ORDERED_INSERT_COST_AND_HEURISTIC:
				nextNode = new Node(node, node.depth + 1, node.cost + problem.pathCost() + h, grids.get(i), actions2.get(i));
				h = 1;
				break;
			default: 
				h = 0;
				nextNode = new Node(node, node.depth + 1, node.cost + problem.pathCost(), grids.get(i), actions2.get(i));
				break;
			}
			nodes.add(nextNode);
		}
		return nodes;
	}
	
	
	
	public static String drawGrid(Celltype[][] grid) {
		String current = null;
		String fill = ".......................";
		String representation = "";
		for (int i = grid.length - 1; i >= 0; i--) {
			for (int j = 0; j < grid[0].length; j++) {
				current = String.valueOf(grid[i][j]); // largest one is of length 23
				int len = current.length();
				representation = representation + current + fill.substring(len - 1, 23) + "\t";
			}
			representation += "\n";
		}
		return representation;
	}
	
	
	
	
}
