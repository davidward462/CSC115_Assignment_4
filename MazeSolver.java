/*
 * MazeSolver.java
 *
 * UVic CSC 115, Spring 2017
 *
 * Purpose:
 *   class that contains a single public static method called
 *   "findPath". To involve the method one must have already created
 *   an instance of Maze. The method must return a path through the
 *   maze (if it exists) in the format shown within the Assignment #3
 *   description.
 *
 * Note: You are free to add to this class whatever other methods will
 * help you in writing a solution to A#3 part 2.
 */
 
public class MazeSolver {
	
	//mark location as visited
	public static void addVisited (MazeLocation l, boolean[][] arr)
	{
		//get row and col
		int row = l.getRow();
		int col = l.getCol();
		arr[row][col] = true;
	}
	
	//return true if location is unvisited, false otherwise
	public static boolean isUnvisited (MazeLocation l, boolean[][] arr)
	{
		//get row and col
		int row = l.getRow();
		int col = l.getCol();
		if (arr[row][col] == true){
			return false;
		}
		return true;//location not in visited array
	}
	
	//returns true if out of bounds
	public static boolean isOutOfBounds (MazeLocation l, Maze m)
	{
		int row = l.getRow();
		int col = l.getCol();
		if (row >= 0 && col >= 0 && row < m.getNumRows() && col < m.getNumCols()) 
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	
    public static String findPath(Maze maze) 
	{
		//result string
		String s = "";
		//create stack 
		StackRefBased<MazeLocation> stack = new StackRefBased<MazeLocation>();
		
		//maze size
		int mazeRows = maze.getNumRows();
		int mazeCols = maze.getNumCols();
		//Keep track of locations visited so far with a 2D-boolean array
		boolean[][] visited = new boolean[mazeRows][mazeCols];
		
		for (int i = 0; i < mazeRows; i++){
			for (int j = 0; j < mazeCols; j++){
				visited[i][j] = false;
			}
		}
		
		//start, end
		MazeLocation start = maze.getEntry();
		MazeLocation end = maze.getExit();
		//Push the start location onto a new stack
		stack.push(start);
		try
		{
			//While (the stack is not empty && stack top is not the finish location)
			while (stack.isEmpty() == false && !stack.peek().equals(end))
			{
				//System.out.println("While");
				//Use the location at the top of the stack as the current location
				MazeLocation current = stack.peek();
				//System.out.println(current);
				
				//Mark the current location as visited
				addVisited(current, visited);
				
				MazeLocation down = new MazeLocation(current.getRow()+1, current.getCol());
				MazeLocation right = new MazeLocation(current.getRow(), current.getCol()+1);
				MazeLocation up = new MazeLocation(current.getRow()-1, current.getCol());
				MazeLocation left = new MazeLocation(current.getRow(), current.getCol()-1);
				
				//If there is an unvisited location next to the current location
				
				if ( (isOutOfBounds(right, maze)==false) && (isUnvisited(right, visited) == true) && (maze.isWall(right.getRow(), right.getCol()) == false))
				{
					//push
					stack.push(right);
				}
				else if ( (isOutOfBounds(down, maze)==false)&&(isUnvisited(down, visited) == true) && (maze.isWall(down.getRow(), down.getCol()) == false))
				{
					//push
					stack.push(down);
				}
				else if ( (isOutOfBounds(up, maze)==false)&&(isUnvisited(up, visited) == true) && (maze.isWall(up.getRow(), up.getCol()) == false))
				{
					//push
					stack.push(up);
				}
				else if ( (isOutOfBounds(left, maze)==false)&&(isUnvisited(left, visited) == true) && (maze.isWall(left.getRow(), left.getCol()) == false))
				{
					//push
					stack.push(left);
				}
				else//if there is no valid move
				{
					stack.pop();
				}	
			}
			
			//reverse order of items in stack before appending
			StackRefBased<MazeLocation> newStack = new StackRefBased<MazeLocation>();
			
			while (stack.isEmpty() == false)
			{
				newStack.push(stack.pop());
			}
			while(newStack.isEmpty() == false)
			{
				s += newStack.pop(); 
				if (newStack.isEmpty()==false)
				{
					s += " ";
				}
			}
		}
		catch (StackEmptyException e)//If stack is empty
		{
				//There is no path from start to finish
		}
		return s;	
    }
}
