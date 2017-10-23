import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Main {
	
	
	public static void main(String[] args) {
		
//		Celltype[][] grid = Engine.GenGrid();
//		
//		System.out.println(Helper.drawGrid(grid));
		
		Celltype[][] grid = new Celltype[3][3];
		for (int i = 0; i < 3; i++) {
			Arrays.fill(grid[i], Celltype.BLANK);
		}
		grid[0][0] = Celltype.INITIAL_RIGHT;
		grid[2][2] = Celltype.GOAL_DOWN;
		grid[0][1] = Celltype.PATH_FIXED_RIGHT_LEFT;
		grid[0][2] = Celltype.PATH_FIXED_UP_LEFT;
		grid[1][1] = Celltype.PATH_MOVABLE_UP_DOWN;
		
		System.out.println(Helper.drawGrid(grid));
		
		Object[] obj = Engine.Search(grid, "BF", true);
		//Object[] obj = Engine.Search(grid, "DF", false);
		
		System.out.println(obj[0]); // the representation of the grids that will form the solution
		System.out.println(obj[1]); // the cost
		System.out.println(obj[2]); // number of nodes expanded

		// ================================================
		
		// To test if the goal test works well.
		
//		Celltype[][] grid = new Celltype[3][3];
//		for (int i = 0; i < 3; i++) {
//			Arrays.fill(grid[i], Celltype.BLANK);
//		}
//		grid[0][0] = Celltype.INITIAL_RIGHT;
//		grid[2][2] = Celltype.GOAL_DOWN;
//		grid[0][1] = Celltype.PATH_FIXED_RIGHT_LEFT;
//		grid[0][2] = Celltype.PATH_FIXED_UP_LEFT;
//		grid[1][2] = Celltype.PATH_MOVABLE_UP_DOWN;
//		
//		System.out.println(Helper.drawGrid(grid));
//	
//		boolean x = Helper.goalTest(grid, 0, 0);
//		System.out.println(x);
	}
	
}
