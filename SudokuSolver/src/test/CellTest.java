package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logic.Cell;

class CellTest
{

	@Test
	void emptyCellTest()
	{
		Cell cell = new Cell(0, 0);
		
		assertEquals(9, cell.getPossibilites().size());
	}
	
	@Test
	void filledCellTest()
	{
		Cell cell = new Cell(0, 5);
		
		assertEquals(5, cell.getValue());
		assertEquals(0, cell.getPossibilites().size());
	}
	
	@Test
	void setValueTest()
	{
		Cell cell = new Cell(0, 0);
		cell.setValue(5);
		
		assertEquals(5, cell.getValue());
		assertEquals(0, cell.getPossibilites().size());
	}

	@Test
	void removePossibilityTest()
	{
		Cell cell = new Cell(0, 0);
		
		assertEquals(9, cell.getPossibilites().size());
		cell.removePossibility(5);
		assertEquals(8, cell.getPossibilites().size());
		
		for(int value : cell.getPossibilites())
		{
			System.out.print(value + " ");
		}
	}
	
	@Test
	void setRowTest()
	{
		Cell cell = new Cell(30, 5);
		
		assertEquals(3, cell.getRow());
	}
	
	@Test
	void setColumnTest()
	{
		Cell cell = new Cell(30, 5);
		
		assertEquals(3, cell.getColumn());
	}
	
	@Test
	void setGroupTest()
	{
		Cell cell = new Cell(30, 5);
		
		assertEquals(4, cell.getGroup());
	}
}
