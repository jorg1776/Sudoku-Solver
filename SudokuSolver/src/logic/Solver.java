package logic;

import java.util.ArrayList;
import java.util.List;

public class Solver
{
	private static Cell[] cells = new Cell[81];
	private static Cell[][] rows = new Cell[9][9];
	private static List<List<Cell>> groups = new ArrayList<List<Cell>>();
	private static int numberOfSolvedCells = 0;
	
	public static int[] SolvePuzzle(int[] inputValues)
	{
		initializeBoard(inputValues);
		
		for(Cell cell : cells)
		{
			int value = cell.getValue();
			if(value != 0 && cell.getOtherCellsValuePossibilityCleared() == false)
			{
				eliminatePossibilities(cell, value);
			}
		}
		
		while(numberOfSolvedCells != 81)
		{
			//if a value gets added, functions will return true
			boolean valueAdded = checkForLonePossibilites();
			
			if(valueAdded == false)
			{
				valueAdded = checkForSingleInstancesOfPossibility();
			}
		}
		
		int[] solvedValues = new int[81];
		storeSolvedValues(solvedValues);
		
		return solvedValues;
	}

	private static void initializeBoard(int[] inputValues)
	{
		initializeCells(inputValues);
		initializeRows();
		initializeGroups();
	}
	
	private static void initializeCells(int[] inputValues)
	{
		for(int i = 0; i < 81; i++)
		{
			cells[i] = new Cell(i, inputValues[i]);
		}
	}
	
	public static Cell[] getCells() { return cells; }
	
	private static void initializeRows()
	{
		int cellNumber = 0;
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				rows[i][j] = cells[cellNumber];
				cellNumber++;
			}
		}
	}

	public static Cell[][] getRows() { return rows; }
	
	private static void initializeGroups()
	{	
		for(int i = 0; i < 9; i++)
		{
			groups.add(new ArrayList<Cell>());
		}
		
		for(Cell cell : cells)
		{
			int groupNumber = cell.getGroup();
			groups.get(groupNumber).add(cell);
		}
	}
	
	public static List<List<Cell>> getGroups() { return groups; }
	
	private static void eliminatePossibilities(Cell cell, int value)
	{
		clearRowOfPossibility(cell.getRow(), value);
		clearColumnOfPossibility(cell.getColumn(), value);
		clearGroupOfPossibility(cell.getGroup(), value);
		
		cell.otherCellsValuePossibilityCleared();
	}
	
	private static void clearRowOfPossibility(int rowNumber, int possibility)
	{
		for(Cell cell : rows[rowNumber])
		{
			cell.removePossibility(possibility);
		}
	}
	
	private static void clearColumnOfPossibility(int columnNumber, int possibility)
	{
		for(int i = 0; i < 9; i++)
		{
			rows[i][columnNumber].removePossibility(possibility);
		}
	}
	
	private static void clearGroupOfPossibility(int groupNumber, int possibility)
	{
		for(Cell cell : groups.get(groupNumber))
		{
			cell.removePossibility(possibility);
		}
	}

	//if cell has only one possibility, set value to that possibility
	private static boolean checkForLonePossibilites()
	{
		boolean valueAdded = false;
		
		for(Cell cell : cells)
		{
			if(cell.getPossibilites().size() == 1)
			{
				int value = cell.getPossibilites().get(0);
				cell.setValue(value);
				eliminatePossibilities(cell, value);
				valueAdded = true;
			}
		}
		
		return valueAdded;
	}

	//if row/column/group has only one instance of a possibility, set cell with that instance's value to the possibility
	private static boolean checkForSingleInstancesOfPossibility()
	{
		boolean wasValueAdded = false;
		
		for(Cell[] row : rows)
		{
			if(checkInstancesAndSetSingles(row) == true) { wasValueAdded = true; }
		}
		
		for(int columnNumber = 0; columnNumber < 9; columnNumber++)
		{
			List<Cell> column = new ArrayList<Cell>();
			for(int rowNumber = 0; rowNumber < 9; rowNumber++)
			{
				column.add(rows[rowNumber][columnNumber]);
			}
			
			if(checkInstancesAndSetSingles(column.toArray(new Cell[9])) == true) { wasValueAdded = true; }
		}
		
		for(List<Cell> group : groups)
		{
			if(checkInstancesAndSetSingles(group.toArray(new Cell[9])) == true) { wasValueAdded = true; }
		}
		
		return wasValueAdded;
	}
	
	private static boolean checkInstancesAndSetSingles(Cell[] cells)
	{
		boolean wasValueAdded = false;
		
		//int[0] = number of 1's, int[1] = number of 2's, etc..
		int[] numberOfInstances = new int[9];
		
		for(Cell cell : cells)
		{
			countPossibilities(numberOfInstances, cell.getPossibilites());
		}
		
		for(int i = 0; i < 9; i++)
		{
			if(numberOfInstances[i] == 1)
			{
				int value = i + 1;
				for(Cell cell : cells)
				{
					if(cell.getPossibilites().contains(value))
					{
						cell.setValue(value);
						eliminatePossibilities(cell, value);
						wasValueAdded = true;
					}
				}
			}
		}
		
		return wasValueAdded;
	}
	
	private static void countPossibilities(int[] numberOfInstances, List<Integer> possibilities)
	{
		for(int possibility : possibilities)
		{
			switch(possibility)
			{
			case 1:
				numberOfInstances[0]++;
				break;
			case 2:
				numberOfInstances[1]++;
				break;
			case 3:
				numberOfInstances[2]++;
				break;
			case 4:
				numberOfInstances[3]++;
				break;
			case 5:
				numberOfInstances[4]++;
				break;
			case 6:
				numberOfInstances[5]++;
				break;
			case 7:
				numberOfInstances[6]++;
				break;
			case 8:
				numberOfInstances[7]++;
				break;
			case 9:
				numberOfInstances[8]++;
				break;
			}
		}
	}
	
	public static void incrementNumberOfCellsSolved(){ numberOfSolvedCells++; }
	
	private static void storeSolvedValues(int[] storerdValues)
	{
		for(int i = 0; i < 81; i++)
		{
			storerdValues[i] = cells[i].getValue();
		}
	}
}
