import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class Engine extends Problem {

	static int[] possibleDimensions = {5, 7, 9};
	
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
	
	int numberOfExpandedNodes = 0;
	
	
	public int getNumberOfExpandedNodes() {
		return numberOfExpandedNodes;
	}


	public void setNumberOfExpandedNodes(int numberOfExpandedNodes) {
		this.numberOfExpandedNodes = numberOfExpandedNodes;
	}


	public static Celltype[][] GenGrid() {
		Celltype[][] finalGrid;
		
		// determine the grid dimension
		int  gridDimensionIndex = (int)(Math.random() * possibleDimensions.length);
		int  gridDimensionIndex2 = (int)(Math.random() * possibleDimensions.length);
		int gridDimension = possibleDimensions[gridDimensionIndex]; // rows
		int gridDimension2 = possibleDimensions[gridDimensionIndex2]; // columns
		finalGrid = new Celltype[gridDimension][gridDimension2];
		for (int i = 0; i < gridDimension; i++) {
			Arrays.fill(finalGrid[i], Celltype.NOT_INITIALIZED_YET);
		}
		// build the grid
		int x,y = -1;
		
		// build the initial cell 
		x = (int)(Math.random() * gridDimension);
		y = (int)(Math.random() * gridDimension2);
		Helper.buildCellWithOneOpening(finalGrid, x, y, INITIAL_CELL); // x -> row, y -> col
		
		// build the goal cell
		x = (int)(Math.random() * gridDimension);
		y = (int) (Math.random() * gridDimension2);
		while (finalGrid[x][y] != Celltype.NOT_INITIALIZED_YET) {
			x = (int)(Math.random() * gridDimension);
			y = (int) (Math.random() * gridDimension2);
		}
		Helper.buildCellWithOneOpening(finalGrid, x, y, GOAL_CELL);
		
		/* The remaining cells = total # cell - 2 = X
		 path tiles = (X / 2) * (2 / 3) = Y
		 blck tiles = (X / 2) * (1 / 3) = Z
		 blank cell = X - Y - Z = X / 2
		 */
		int totalCells = gridDimension * gridDimension;
		int remainingCells = totalCells - 2;
		double numberOfPathTiles = (remainingCells) * (2.0 / 3.0) * (2.0 / 3);
		double numberOfBlockTiles = (remainingCells) * (2.0 / 3.0) * (1.0 / 3);
		int numberOfBlankCells = remainingCells - (int)numberOfPathTiles - (int)numberOfBlockTiles;
		

		for (int i = 0; i < (int)numberOfPathTiles; i++) {
			x = (int)(Math.random() * gridDimension);
			y = (int) (Math.random() * gridDimension2);
			while (finalGrid[x][y] != Celltype.NOT_INITIALIZED_YET) {
				x = (int)(Math.random() * gridDimension);
				y = (int) (Math.random() * gridDimension2);
			}
			int movingState = (int)(Math.random() * 2);
			Helper.buildCellWithTwoOpenings(finalGrid, x, y, movingState);
			
		}
		
		for (int i = 0; i < (int)numberOfBlockTiles; i++) {
			x = (int)(Math.random() * gridDimension);
			y = (int) (Math.random() * gridDimension2);
			while (finalGrid[x][y] != Celltype.NOT_INITIALIZED_YET) {
				x = (int)(Math.random() * gridDimension);
				y = (int) (Math.random() * gridDimension2);
			}
			finalGrid[x][y] = Celltype.BLOCK;
		}
		
		for (int i = 0; i < finalGrid.length; i++) {
			for (int j = 0; j < finalGrid[0].length; j++) {
				if (finalGrid[i][j] == Celltype.NOT_INITIALIZED_YET) {
					finalGrid[i][j] = Celltype.BLANK;
				}
			}
		}
		
		return finalGrid;
	}
	

	public static Object[] Search(Celltype[][] grid, String strategy, boolean visualize) {
		
		Engine engine = new Engine();
		engine.initialState = grid;
		engine.operators = new Action[]{Action.MOVE_DOWN, Action.MOVE_UP, Action.MOVE_RIGHT, Action.MOVE_LEFT};
		Node result = null;
		if (strategy.equals("BF")) {
			result =  generalSearch(engine, ENQUE_AT_END, visualize);
		} else if (strategy.equals("DF")) {
			result = generalSearch(engine, ENQUE_AT_FRONT, visualize);
		} if (strategy.equals("ID")) {
			result = generalSearch(engine, ENQUE_AT_FRONT_ITERATIVELY, visualize);
		} else if (strategy.equals("GR1")) {
			result = generalSearch(engine, ORDERED_INSERT_HEURISTIC1, visualize);
		} else if (strategy.equals("GR2")) {
			result = generalSearch(engine, ORDERED_INSERT_HEURISTIC2, visualize);
		} else if (strategy.equals("AS")) {
			result = generalSearch(engine, ORDERED_INSERT_COST_AND_HEURISTIC, visualize);
		}
		if (result == null) {
			return new Object[] {null, null, engine.getNumberOfExpandedNodes()};
		} else {
			Node current = result;
			String representation = "";
			while (current != null) {
				representation = "\n" + current.actionPerformed + "\n" +
						Helper.drawGrid((Celltype[][]) current.currentState) + representation;
				current = current.parent;
			}
			return new Object[] {representation, result.cost, engine.getNumberOfExpandedNodes()};
		}
	}
	
	
	
	public static Node generalSearch(Engine problem, int strategy, boolean visualize) {
		ArrayList<Celltype [][]> history = new ArrayList<Celltype[][]>();
		
		Object nodes = Helper.makeQ(strategy);
		
		Node node = new Node();
		node.currentState = (Celltype[][]) problem.getInitialState();
		
		Helper.insertNode(nodes, node, strategy);
		int maxDepth = 0;
		//int depthSoFar = 0;
		Node currentNode = null;
		while (true) {
			if (Helper.checkEmpty(nodes, strategy)) {
				if (strategy != ENQUE_AT_FRONT_ITERATIVELY) {
					return null;
				} else {
					maxDepth++;
					//depthSoFar = 0;
					Node node2 = new Node();
					node2.currentState = (Celltype[][]) problem.getInitialState();
					Helper.insertNode(nodes, node, strategy);
					continue;
				}
			}
			currentNode = Helper.removeFront(nodes, strategy);
			if (visualize) {
				System.out.println(Helper.drawGrid((Celltype[][]) currentNode.currentState));
				}
			history.add((Celltype[][]) currentNode.currentState);
			if (problem.goolTest(currentNode)) {
				return currentNode;
			}
			if (strategy != ENQUE_AT_FRONT_ITERATIVELY || (strategy == ENQUE_AT_FRONT_ITERATIVELY && currentNode.depth < maxDepth)) {
				ArrayList<Node> expandedNodes = Helper.expandNode(problem, currentNode, history, strategy);
				problem.numberOfExpandedNodes++;
				for (Node node2 : expandedNodes) {
					Helper.insertNode(nodes, node2, strategy);
				} 
			} 
			
			//depthSoFar++;
		}
		//return null;
	}
	
	
	
//	switch(strategy) {
//	case ENQUE_AT_END:
//		break;
//	case ENQUE_AT_FRONT:
//		break;
//	case ENQUE_AT_FRONT_ITERATIVELY:
//		break;
//	case ORDERED_INSERT_HEURISTIC1:
//		break;
//	case ORDERED_INSERT_HEURISTIC2:
//		break;
//	case ORDERED_INSERT_COST_AND_HEURISTIC:
//		break;
//	}

	@Override
	public Object[] operators() {
		Action[] actions = {Action.MOVE_DOWN, Action.MOVE_LEFT, Action.MOVE_RIGHT, Action.MOVE_UP};
		return actions;
	}


	@Override
	public Object getInitialState() {
		return this.initialState;
	}


	@Override
	public double pathCost() {
	
		return 1;
	}


	@Override
	public boolean goolTest(Node currentNode) {
		Celltype[][] grid = (Celltype[][]) currentNode.currentState;
		int[] initials = Helper.helperToGotInitialXY(grid);
		return Helper.goalTest(grid, initials[0], initials[1]);
	}
	
	
}