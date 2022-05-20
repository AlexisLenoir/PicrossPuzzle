package picrossV3;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Case extends Parent
{
	private Rectangle fond;
	private double length;
	private int i;
	private int j;
	private int type;
	
	public Case (double length, int i, int j)
	{
		fond = new Rectangle (length,length,Color.WHITE);
		fond.setStroke(Color.BLACK);
		this.getChildren().add(fond);
		this.i = i;
		this.j = j;
		type = 0;
	}
	
	public int getI()
	{
		return i;
	}
	
	public int getJ()
	{
		return j;
	}
	
	public int getCouleur()
	{
		// 0 blanc 1 noire 2 vert
		return type;
	}
	public void colorCase ()
	{
		fond.setFill(Color.BLACK);
		type = 1;
	}
	
	public void colorCaseWhite()
	{
		fond.setFill(Color.WHITE);
		type = 0;
	}
	
	public void selectCase ()
	{
		fond.setFill(Color.GREENYELLOW);
		type = 2;
	}
}
