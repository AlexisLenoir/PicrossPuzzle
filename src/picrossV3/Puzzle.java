package picrossV3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Puzzle extends Parent
{
	private Rectangle fond;
	private int width = 800;
	private int height = 500;
	private Grille grille;
	private Case [][] tabCases;
	private double lengthCase;
	private int [][] couleurJeu;
	private CaseNumber [][] tabIndicCaseLigne;
	private CaseNumber [][] tabIndicCaseColonne;
	
	public Puzzle (Grille g)
	{
		fond  = new Rectangle (width,height,Color.WHITE);
		this.getChildren().add(fond);
		
		grille = g;
		initTabCase (grille.get_nbLignes(), grille.get_nbColonnes());
		
		
		couleurJeu = new int [grille.get_nbLignes()][grille.get_nbColonnes()];
		
		for (int i = 0; i < grille.get_nbLignes(); i++)
		{
			for (int j = 0; j < grille.get_nbColonnes(); j++)
			{
				couleurJeu[i][j] = 0;
			}
		}
		
		
	}
	
	public void initCaseNumber ( CaseNumber [][] l, CaseNumber [][] c)
	{
		tabIndicCaseLigne = l;
		tabIndicCaseColonne = c;
	}
	
	
	public int [][] getCouleurJeu ()
	{
		return couleurJeu;
	}
	
	public double getLengthCase ()
	{
		return lengthCase;
	}
	
	public void colorPuzzle (Grille g)
	{
		int [][] couleur = g.get_couleur();
		for (int i = 0; i < g.get_nbLignes(); i++)
		{
			for (int j = 0; j < g.get_nbColonnes(); j++)
			{
				if (couleur[i][j] == 1) tabCases [i][j].colorCase();
				else tabCases [i][j].colorCaseWhite();
			}
		}
	}
	
	private void initTabCase ( int nbLignes, int nbColonnes)
	{
		
		/*double maxNbLignesAndColonnes = Math.max(nbLignes, nbColonnes);
		double lengthCase = height/maxNbLignesAndColonnes;
		System.out.println( " taille case vaut " + lengthCase + " max vaut " +maxNbLignesAndColonnes );
		*/
		
		double lengthCaseHeight = height /( (double) nbLignes);
		double lengthCaseWidth = width / ( (double) nbColonnes);
		lengthCase = Math.min (lengthCaseHeight,lengthCaseWidth);
		
		
		
		tabCases = new Case[nbLignes][nbColonnes];
		
		ColorCase myColorCase = new ColorCase();
		SelectCase mySelectCaseIn = new SelectCase(true);
		SelectCase mySelectCaseOut = new SelectCase(false);
		
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				tabCases[i][j] = new Case (lengthCase,i,j);
				tabCases[i][j].setTranslateY(i*lengthCase);
				tabCases[i][j].setTranslateX(j*lengthCase);
				this.getChildren().add(tabCases[i][j]);
				
				tabCases[i][j].setOnMouseClicked( myColorCase);
				tabCases[i][j].setOnMouseEntered(mySelectCaseIn);
				tabCases[i][j].setOnMouseExited(mySelectCaseOut);
			}
		}
	}
	
	
	public class SelectCase implements EventHandler <MouseEvent>
	{
		private int i;
		private int j;
		private boolean in;
		
		
		public SelectCase (boolean b)
		{
			in = b;
		}
		
		@Override
		public void handle(MouseEvent event) 
		{
			if (event.getSource() instanceof Case )
			{
				//System.out.println("case");
				Case c = (Case) event.getSource();
				this.i = c.getI();
				this.j = c.getJ();
			}
			
			
			
			if (in)
			{
				
				//if (tabCases[i][j].getCouleur()  != 1) tabCases[i][j].selectCase();
				
				for (int k = 0; k < tabCases[i].length; k++)
				{
					if (tabCases[i][k].getCouleur()  != 1) tabCases[i][k].selectCase();
				}
				
				for (int k = 0; k < tabCases.length; k++)
				{
					if (tabCases[k][j].getCouleur()  != 1) tabCases[k][j].selectCase();
				}
				
				
				for (int k = 0; k < tabIndicCaseLigne[i].length; k++)
				{
					tabIndicCaseLigne[i][k].selectCase();
				}
				
				for (int k = 0; k < tabIndicCaseColonne.length; k++)
				{
					tabIndicCaseColonne[k][j].selectCase();
				}
				
			}
			else
			{
				//if (tabCases[i][j].getCouleur() != 1) tabCases[i][j].colorCaseWhite();
				
				for (int k = 0; k < tabCases[i].length; k++)
				{
					if (tabCases[i][k].getCouleur()  != 1) tabCases[i][k].colorCaseWhite();
				}
				
				for (int k = 0; k < tabCases.length; k++)
				{
					if (tabCases[k][j].getCouleur()  != 1) tabCases[k][j].colorCaseWhite();
				}
				
				for (int k = 0; k < tabIndicCaseLigne[i].length; k++)
				{
					tabIndicCaseLigne[i][k].colorCaseWhite();;
				}
				
				for (int k = 0; k < tabIndicCaseColonne.length; k++)
				{
					tabIndicCaseColonne[k][j].colorCaseWhite();
				}
			}
			
		}
		
	}
	
	public class ColorCase implements EventHandler<MouseEvent>
	{
		private int i;
		private int j;
		
		
		public ColorCase ()
		{
		
		}

		@Override
		public void handle(MouseEvent event) 
		{
			if (event.getSource() instanceof Case [][]) System.out.println("tab case");
			if (event.getSource() instanceof Case )
			{
				//System.out.println("case");
				Case c = (Case) event.getSource();
				this.i = c.getI();
				this.j = c.getJ();
			}
			
			
			if (tabCases[i][j].getCouleur() != 1)
			{
				tabCases[i][j].colorCase();
				couleurJeu[i][j] = 1;
				
			}
			else
			{
				tabCases[i][j].colorCaseWhite();
				couleurJeu[i][j] = 0;	
			}
			//Mettre a jour la grille
		}
	}

}
