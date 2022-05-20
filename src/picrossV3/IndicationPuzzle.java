package picrossV3;



import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class IndicationPuzzle extends Parent
{
	private Rectangle fond;
	private int width;
	private int height;
	private boolean isLignes;
	private double lengthCase;
	private CaseNumber [][] tabCases;
	
	public IndicationPuzzle (int w, int h, double lengthCase, boolean b, Grille g)
	{
		isLignes = b;
		this.lengthCase = lengthCase;
		width = w;
		height = h;
		fond = new Rectangle (width, height, Color.WHITE);
		this.getChildren().add(fond);
		
		if (isLignes) initTabCaseLignes (g);
		else initTabCaseColonnes (g);
		
	}
	
	public CaseNumber [][] getTabCases ()
	{
		return tabCases;
	}
	
	private void initTabCaseLignes (Grille g)
	{
		ArrayList<ArrayList<Integer>> seqLignes = g.get_SeqLignes();
		int nbColonnes = seqLignes.get(0).size();
		
		for (int i = 1; i < seqLignes.size(); i++)
		{
			if (seqLignes.get(i).size() > nbColonnes)
			{
				nbColonnes = seqLignes.get(i).size();
			}
		}
			
		double widthCase =  width / ((double) nbColonnes);
		int nbLignes = g.get_nbLignes();
			
		System.out.println(" nbLigne " + nbLignes + "nbColonnes" + nbColonnes + "width case "+ widthCase);
			
		tabCases =  new 	CaseNumber [nbLignes][nbColonnes];
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				if (j >= seqLignes.get(i).size()) tabCases[i][j] = new CaseNumber (widthCase, lengthCase, "", true);
				else tabCases[i][j] = new CaseNumber (widthCase, lengthCase, "" + seqLignes.get(i).get(j), true);
				tabCases[i][j].setTranslateY(i*lengthCase);
				tabCases[i][j].setTranslateX(j*widthCase);
				this.getChildren().add(tabCases[i][j]);
			}
		}
			
	}
	
	private void initTabCaseColonnes (Grille g)
	{
		ArrayList<ArrayList<Integer>> seqColonnes = g.get_SeqColonnes();
		
		int nbLignes = seqColonnes.get(0).size();
		for (int i = 1; i < seqColonnes.size(); i++)
		{
			if (seqColonnes.get(i).size() > nbLignes)
			{
				nbLignes = seqColonnes.get(i).size();
			}
		}
			
		double heightCase =  height / ((double) nbLignes);
		
		int nbColonnes = g.get_nbColonnes();
			
		System.out.println(" nbLigne " + nbLignes + "nbColonnes" + nbColonnes + "height case "+ heightCase);
			
		tabCases =  new 	CaseNumber [nbLignes][nbColonnes];
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				if (i >= seqColonnes.get(j).size()) tabCases[i][j] = new CaseNumber (lengthCase, heightCase, "", false);
				else tabCases[i][j] = new CaseNumber (lengthCase, heightCase, "" + seqColonnes.get(j).get(i), false);
				
				tabCases[i][j].setTranslateY(i*heightCase);
				tabCases[i][j].setTranslateX(j*lengthCase);
				this.getChildren().add(tabCases[i][j]);
			}
		}
			
	}
		


	/*
	private void initTabCaseLignes (int nbLignes, int nbColonnes, double widthCase)
	{
		
		System.out.println(" nbLigne " + nbLignes + "nbColonnes" + nbColonnes + "width case "+ widthCase);
		
		tabCases =  new 	CaseNumber [nbLignes][nbColonnes];
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				tabCases[i][j] = new CaseNumber (widthCase, lengthCase);
				tabCases[i][j].setTranslateY(i*lengthCase);
				tabCases[i][j].setTranslateX(j*widthCase);
				this.getChildren().add(tabCases[i][j]);
			}
		}
	}*/

}
