package display;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class SolveVerificationWindow extends JFrame
{

	private JPanel contentPane;
	private boolean decision = false;
	
	/**
	 * Launch the application.
	 */
	public static void verifyDecision()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SolveVerificationWindow frame = new SolveVerificationWindow();
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
	public SolveVerificationWindow()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(440, 300, 317, 166);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblIsThePuzzle = new JLabel("Is the puzzle ready to be solved?");
		lblIsThePuzzle.setBounds(73, 33, 181, 14);
		contentPane.add(lblIsThePuzzle);
				
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//SudokuDisplay.solvePuzzle(true);
				dispose();
			}
		});
		btnYes.setBounds(51, 58, 89, 23);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//SudokuDisplay.solvePuzzle(false);
				dispose();
			}
		});
		btnNo.setBounds(165, 58, 89, 23);
		contentPane.add(btnNo);
	}
		
	public boolean getDecision()
	{
		return decision;
	}
}
