package logic;

import java.util.ArrayList;
import java.util.List;

public class Cell
{
	private int cellNumber;
	private int rowNumber;
	private int columnNumber;
	private int groupNumber;
	private int value;
	private List<Integer> possibilities = new ArrayList<Integer>();
	private boolean otherCellsValuePossibilityCleared = false;
	
	public Cell(int numberOfCell, int inputValue)
	{
		cellNumber = numberOfCell;
		setRowColumnAndGroup(cellNumber);
		
		if(inputValue == 0)
		{
			value = 0;
			for(int i = 1; i < 10; i++)
			{
				possibilities.add(i);
			}
		}
		else
		{
			setValue(inputValue);
		}
	}
	
	private void setRowColumnAndGroup(int cellNumber)
	{
		rowNumber = setRow(cellNumber);
		columnNumber = setColumn(cellNumber, rowNumber);
		groupNumber = setGroup(rowNumber, columnNumber);
	}

	public int getCellNumber() { return cellNumber; }
	
	private int setRow(int cellNumber)
	{
		if(cellNumber < 9) { return 0; }
		else if(cellNumber < 18) { return 1; }
		else if(cellNumber < 27) { return 2; }
		else if(cellNumber < 36) { return 3; }
		else if(cellNumber < 45) { return 4; }
		else if(cellNumber < 54) { return 5; }
		else if(cellNumber < 63) { return 6; }
		else if(cellNumber < 72) { return 7; }
		else { return 8; }
	}
	
	public int getRow() { return rowNumber; }
	
	private int setColumn(int cellNumber, int rowNumber)
	{
		return cellNumber - (9 * rowNumber);
	}
	
	public int getColumn() { return columnNumber; }
	
	private int setGroup(int rowNumber, int columnNumber)
	{
		if(rowNumber < 3)
		{
			if(columnNumber < 3) { return 0; }
			else if(columnNumber < 6) { return 1; }
			else { return 2; }
		}
		else if(rowNumber < 6)
		{
			if(columnNumber < 3) { return 3; }
			else if(columnNumber < 6) { return 4; }
			else { return 5; }
		}
		else
		{
			if(columnNumber < 3) { return 6; }
			else if(columnNumber < 6) { return 7; }
			else { return 8; }
		}
	}

	public int getGroup() { return groupNumber; }
	
	public void setValue(int valueToSet)
	{
		value = valueToSet;
		Solver.incrementNumberOfCellsSolved();
		possibilities.clear();
	}
	
	public int getValue(){ return value; }
	
	public List<Integer> getPossibilites(){ return possibilities; }

	public void removePossibility(int possibility)
	{
		if(possibilities.contains(possibility))
		{
			int indexOfPossibility = possibilities.indexOf(possibility);
			possibilities.remove(indexOfPossibility);
		}
	}

	public boolean getOtherCellsValuePossibilityCleared(){ return otherCellsValuePossibilityCleared; }
	
	public void otherCellsValuePossibilityCleared(){ otherCellsValuePossibilityCleared = true; }
}
