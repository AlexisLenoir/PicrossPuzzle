package picrossV3;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CaseNumber extends Parent
{
	private Rectangle fondNoir;
	private Rectangle fondBlanc;
	private Line ligne1; // 1 et 2 pour les indications des lignes
	private Line ligne2;
	private Line ligneA;
	private Line ligneB;
	private Text myNumber;
	
	public CaseNumber (double width, double height,String number, boolean estLignes)
	{
		fondBlanc = new Rectangle (width, height, Color.WHITE);
		
		myNumber = new Text (0,height*0.8,number);
		double tailleFont = Math.min(width, height) - 2;
		myNumber.setFont(new Font(tailleFont));
		
		this.getChildren().add(fondBlanc);
		this.getChildren().add(myNumber);
		
		if (estLignes)
		{
			ligne1 = new Line (0,0,width,0);
			ligne1.setFill(Color.BLACK);
			ligne2 = new Line (0, height,width,height);
			ligne2.setFill(Color.BLACK);
			this.getChildren().add(ligne1);
			this.getChildren().add(ligne2);
		}
		else
		{
			ligneA = new Line (0,0,0,height);
			ligneA.setFill(Color.BLACK);
			ligneB = new Line (width, 0,width,height);
			ligneB.setFill(Color.BLACK);
			this.getChildren().add(ligneA);
			this.getChildren().add(ligneB);
		}
		
	
	}
	public void colorCaseWhite()
	{
		fondBlanc.setFill(Color.WHITE);
		
	}
	
	public void selectCase ()
	{
		fondBlanc.setFill(Color.GREENYELLOW);
		
	}
	
}
