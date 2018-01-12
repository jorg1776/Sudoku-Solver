package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import logic.Cell;
import logic.Solver;

class SolverTest
{
	
	@Test
	void initializeCellsTest()
	{
		int[] testCells = new int[81];
		testCells[30] = 5;
		Solver.SolvePuzzle(testCells);
		
		Cell[] cells = Solver.getCells();
		assertEquals(5, cells[30].getValue());
	}
	
	@Test
	void initializeRowsTest()
	{
		int[] testCells = new int[81];
		testCells[30] = 5;
		Solver.SolvePuzzle(testCells);
		
		Cell[][] rows = Solver.getRows();
		assertEquals(5, rows[3][3].getValue());
	}	
	
	@Test
	void initializeGroupsTest()
	{
		int[] testCells = new int[81];
		testCells[30] = 5;
		Solver.SolvePuzzle(testCells);
		
		List<List<Cell>> groups = Solver.getGroups();
		assertEquals(5, groups.get(4).get(0).getValue());
	}	
	
	@Test
	void solvedTest()
	{
		int[] testcells = {0,2,9,0,5,6,0,0,3,
						   3,8,6,0,0,0,9,2,0,
						   4,0,0,9,0,0,6,0,7,
						   0,0,1,0,0,8,2,0,9,
						   0,0,0,0,0,0,0,0,0,
						   2,0,8,6,0,0,3,0,0,
						   1,0,2,0,0,3,0,0,6,
						   0,5,3,0,0,0,7,4,1,
						   6,0,0,4,1,0,8,3,0};
		
		int[] solvedCells = Solver.SolvePuzzle(testcells);
		
		int cellNumber = 0;
		for(int cell : solvedCells)
		{
			System.out.print(cell + " ");
			cellNumber++;
			if((cellNumber % 9) == 0)
			{
				System.out.println();
			}
			
		}
	}
}
