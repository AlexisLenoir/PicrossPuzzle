package picrossV3;

public class Point 
{
	public int x;
	public  int y;
	public Point(int x, int y)
	{
		this.setX(x);
		this.setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int  y) 
	{
		this.y = y;
	}
	public int nbVoisinsNoirs(int[][] tab)
	{
		int k = 0;
		int m = tab.length;
		int n = tab[0].length;
		if ((tab[((this.x - 1) + m)%m][this.y]==1)) 
		{
			k++;
		}
		if ((tab[(this.x + 1)%m][this.y]==1)) 
		{
			k++;
		}
		if ((tab[this.x][((this.y - 1) + n)%n]==1)) 
		{
			k++;
		}
		if ((tab[this.x][(this.y + 1)%n]==1))
		{
			k++;
		}
		return k;
	}
	
	public void affichePoint()
	{
		System.out.println("x " + x + " y " + y);
	}
}
