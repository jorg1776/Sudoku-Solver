package display;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Solver;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SudokuDisplay extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SudokuDisplay frame = new SudokuDisplay();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SudokuDisplay()
	{
		JTextField[] cells = new JTextField[81];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sudokuLinesPanel = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				drawLines(g);
			}

			private void drawLines(Graphics g)
			{
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(5));
				g.drawLine(0, 120, 360, 120);
				g.drawLine(0, 240, 360, 240);
				g.drawLine(120, 0, 120, 360);
				g.drawLine(240, 0, 240, 360);
			}
		};
		sudokuLinesPanel.setBounds(62, 10, 360, 360);
		sudokuLinesPanel.setBackground(SystemColor.control);
		sudokuLinesPanel.setPreferredSize(new Dimension(360, 360));
		contentPane.add(sudokuLinesPanel);
		
		JPanel sudokuBoardPanel = new JPanel();
		sudokuBoardPanel.setBounds(62, 10, 360, 360);
		sudokuBoardPanel.setPreferredSize(new Dimension(360, 360));
		sudokuBoardPanel.setLayout(new GridLayout(9, 9, 2, 2));
		addCells(sudokuBoardPanel, cells);
		sudokuBoardPanel.setBounds(62, 10, 360, 360);
		contentPane.add(sudokuBoardPanel);
		
		JButton solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				solvePuzzle(true, sudokuBoardPanel);
				
				//SolveVerificationWindow.verifyDecision();
			}
		});
		solveButton.setBounds(199, 381, 89, 23);
		contentPane.add(solveButton);
	}
	
	private void addCells(JPanel sudokuBoardPanel, JTextField[] cells)
	{
		for(JTextField cell : cells)
		{
			cell = new JTextField();
			cell.setHorizontalAlignment(SwingConstants.CENTER);
			sudokuBoardPanel.add(cell);
		}
	}
	
	private int[] cellValues = new int[81];
	
	public void solvePuzzle(boolean decision, JPanel board)
	{
		JTextField[] filledCells = new JTextField[81];
		
		int cellNumber = 0;
		for(Component cell : board.getComponents())
		{
			filledCells[cellNumber] = (JTextField)cell;
			cellNumber++;
		}
		
		if(decision == true && isGatheredInputValid(filledCells) == true)
		{
			int[] solvedPuzzle = Solver.SolvePuzzle(cellValues);
			displayResult(filledCells, solvedPuzzle);
		}
	}
	
	private boolean isGatheredInputValid(JTextField[] cells)
	{
		int cellNumber = 0;
		
		for(JTextField cellTxt : cells)
		{
			if(cellTxt.getText().equals(""))
			{
				cellValues[cellNumber] = 0;
			}
			else if(isInputNumeric(cellTxt.getText()) == true)
			{
				int value = Integer.parseInt(cellTxt.getText());
				cellValues[cellNumber] = value;
			}
			else
			{
				return false;
			}
			
			cellNumber++;
		}
		
		return true;
	}

	private boolean isInputNumeric(String input)
	{
		try  
		  {  
		    int value = Integer.parseInt(input);  
		  }  
		  catch(NumberFormatException nfe)  
		  {  
			  System.out.println("Not a number");
			  return false;  
		  } 
		
		  return true;
	}

	private void displayResult(JTextField[] cells, int[] values)
	{
		for(int i = 0; i < 81; i++)
		{
			cells[i].setText(String.valueOf(values[i]));
		}
	}
}
