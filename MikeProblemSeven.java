package practiceProblems;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class MikeProblemSeven {
	private String[][] mines;
	private String[][] spots;
	private boolean win;
	
	public MikeProblemSeven()
	{
		win = true;
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("MineIn.txt"));
			int length = Integer.parseInt(reader.readLine());
			mines = new String[length][length];
			spots = new String[length][length];
			for (int row = 0; row < mines.length; row++)
			{
				String line = reader.readLine();
				for (int col = 0; col < mines[row].length; col++)
				{
					mines[row][col] = line.charAt(col) + "";
				}
			}
			for (int row = 0; row < spots.length; row++)
			{
				String line = reader.readLine();
				for (int col = 0; col < spots[row].length; col++)
				{
					spots[row][col] = line.charAt(col) + "";
				}
			}
			reader.close();
		}
		catch(Exception e)
		{
			System.out.println("crap");
		}
	}
	
	public void fill()
	{
		for (int row = 0; row < spots.length; row++)
		{
			for (int col = 0; col < spots[row].length; col++)
			{
				char check = spots[row][col].charAt(0);
				if (check == 'x')
				{
					if (mines[row][col].equals("*"))
						win = false;
					else
						mines[row][col] = "" + check(row, col);
				}
			}
		}
		mines();
	}
	
	private void mines()
	{
		if (win)
		{
			for (int row = 0; row < mines.length; row++)
			{
				for (int col = 0; col < mines[row].length; col++)
				{
					char check = mines[row][col].charAt(0);
					if (check == '*')
						mines[row][col] = '.' + "";
				}
			}
		}
	}
	
	private int check(int row, int col)
	{
		int numMines = 0;
		for (int r = row - 1; r < row + 2; r++)
		{
			for (int c = col - 1; c < col + 2; c++)
			{
				if (0 <= r && r < mines.length &&
					0 <= c && c < mines[r].length)
				{
					if (mines[r][c].equals("*"))
						numMines++;
				}
			}
		}
		return numMines;
	}
	
	public void print()
	{
		
		for (int r = 0; r < mines.length; r++)
		{
			String line = "";
			for (int c = 0; c < mines[r].length - 1; c++)
			{
				line += mines[r][c] + " ";
			}
			System.out.println(line + mines[r][mines[r].length - 1]);
		}
		//*/
		/*
		for (int r = 0; r < spots.length; r++)
		{
			String line = "";
			for (int c = 0; c < spots[r].length - 1; c++)
			{
				line += spots[r][c] + " ";
			}
			System.out.println(line + spots[r][spots[r].length - 1]);
		}//*/
	}
	
	public void write() throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter("MineOut.txt"));
		for (int r = 0; r < mines.length; r++)
		{
			String line = "";
			for (int c = 0; c < mines[r].length - 1; c++)
			{
				line += mines[r][c] + "";
			}
			writer.write(line + mines[r][mines[r].length - 1]);
			writer.newLine();
		}
		writer.close();
	}
	
	public static void main (String[] args) throws Exception
	{
		MikeProblemSeven ps = new MikeProblemSeven();
		ps.fill();
		ps.write();
	}
}
