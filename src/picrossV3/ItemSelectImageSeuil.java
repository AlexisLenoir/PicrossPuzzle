package picrossV3;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ItemSelectImageSeuil extends Parent
{
	private Rectangle fond;
	private ImageView image;
	private Text seuil;
	private Text difficulte;
	private Grille grille;
	
	public ItemSelectImageSeuil (ImageView i, Grille g)
	{
		fond = new Rectangle();
		fond.setHeight(300);
		fond.setWidth(275);
		fond.setFill(Color.GRAY);
		fond.setOpacity(0);
		
		grille = g;
		
		this.image = i;
		
		double h,w, maximumCote;
		
		h = image.getImage().getHeight();
		w = image.getImage().getWidth();
		
		 maximumCote =  Math.max(h, w);
		
		if (maximumCote == w)
		{
			
			image.setFitWidth(235);
			image.setPreserveRatio(true);
			image.setSmooth(true);
			image.setCache(true);
		}
		else
		{
			image.setFitHeight(200);
			image.setPreserveRatio(true);
			image.setSmooth(true);
			image.setCache(true);
		}
		
		image.setTranslateX(20);
		image.setTranslateY(75);
		
		
		
		seuil = new Text ("Seuil: " + grille.getSeuil());
		seuil.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR,18));
		seuil.setTranslateX(20);
		seuil.setTranslateY(20);
		difficulte = new Text ("Difficulté: ");
		difficulte.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR,18));
		difficulte.setTranslateX(20);
		difficulte.setTranslateY(40);
		this.getChildren().add(fond);
		this.getChildren().add(image);
		this.getChildren().add(seuil);
		//this.getChildren().add(difficulte);
		}
	
	public Grille getGrille()
	{
		return grille;
	}
	
	public void setFondGreen()
	{
		fond.setOpacity(1);
		fond.setFill(Color.GREENYELLOW);
	}
	
	public void setFondOpaque()
	{
		fond.setOpacity(0);
	}
}
