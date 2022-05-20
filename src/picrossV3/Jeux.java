package picrossV3;

import java.util.ArrayList;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Jeux 
{
	private SceneManager sceneManager;
	private Scene jeuxScene;
	private Group root;
	private int Xfix;
	private int Yfix;
	private Puzzle monPuzzle;
	private IndicationPuzzle indicLignesPuzzle;
	private IndicationPuzzle indicColonnesPuzzle;
	private Button boutonSolution;
	private Button boutonMenu;
	private Button boutonCorrection;
	private Grille g;
	private Text victoire;
	private Text perdu;
	private Text nomFile;
	
	
	public Jeux (/*SceneManager sceneManager*/Grille g)
	{
		//this.sceneManager = sceneManager;
		this.g = g;
		
		Xfix = 50 + 200;
		Yfix = 40 + 200;
		
		//Grille g = new Grille ("instances/4.txt");
		monPuzzle = new Puzzle(g);
		monPuzzle.setTranslateX(Xfix);
		monPuzzle.setTranslateY(Yfix);
		
		indicLignesPuzzle = new IndicationPuzzle(200,500, monPuzzle.getLengthCase(),true,g);
		indicLignesPuzzle.setTranslateX(50);
		indicLignesPuzzle.setTranslateY(Yfix);
		
		indicColonnesPuzzle = new IndicationPuzzle(800,200, monPuzzle.getLengthCase(),false,g);
		indicColonnesPuzzle.setTranslateX(Xfix);
		indicColonnesPuzzle.setTranslateY(40);
		
		
		monPuzzle.initCaseNumber(indicLignesPuzzle.getTabCases(), indicColonnesPuzzle.getTabCases());
		
		boutonSolution = new Button("Solution");
		boutonSolution.setPrefSize(100,15);
		boutonSolution.setTranslateX(50);
		boutonSolution.setTranslateY(80);
		
		
		
		
		boutonSolution.setOnAction(new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
				ArrayList<Object> res = g.enumeration();
				//g.affiche_grille();
				
				int r = (int) res.get(0);
				Grille nw;
				if (r == 1)
				{	
					System.out.println("coucou1");
					nw = (Grille ) res.get(1);
					//nw.affiche_grille();
					monPuzzle.colorPuzzle(nw);
				}
            }
        });
		
		
		boutonMenu = new Button("Menu");
		boutonMenu.setPrefSize(100,15);
		boutonMenu.setTranslateX(50);
		boutonMenu.setTranslateY(40);
		boutonMenu.setOnAction(new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
				sceneManager.goToMenuScene();
            }
        });
		
		
		
		victoire = new Text ("Victoire");
		victoire.setTranslateX(900);
		victoire.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,35));
		victoire.setTranslateY(300);
		victoire.setOpacity(0);
		victoire.setFill(Color.GREEN);
		
		perdu = new Text ("Perdu !");
		perdu.setTranslateX(900);
		perdu.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,35));
		perdu.setTranslateY(300);
		perdu.setOpacity(0);
		perdu.setFill(Color.RED);
		
		nomFile = new Text (g.getNomFicher());
		nomFile.setFont(Font.font("copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		nomFile.setTranslateX(50);
		nomFile.setTranslateY(200);
		
		
		boutonCorrection = new Button("Correction");
		boutonCorrection.setPrefSize(100,15);
		boutonCorrection.setTranslateX(50);
		boutonCorrection.setTranslateY(120);
		
		boutonCorrection.setOnAction(new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
				int [][] couleurA = monPuzzle.getCouleurJeu();
				int [][] couleurB;
				
				ArrayList<Object> res = g.coloration();
				//g.affiche_grille();
				
				
				int r = (Integer) res.remove(0);
				Grille nw;
				
				boolean vict = false;
				
				if (r == 1)
				{	
					nw = (Grille ) res.remove(0);
					couleurB = nw.get_couleur();
					
					vict = true;
					//System.out.println("A");
					
					for (int i = 0; i < couleurA.length; i++)
					{
						for (int j = 0; j < couleurA[i].length ; j++)
						{
							
							//System.out.print(" A " + couleurA[i][j]  +"B " +couleurB[i][j]);
							
							if (couleurA[i][j] != couleurB[i][j] && couleurB[i][j] != -1)
							{
								vict =  false;
								break;
							}
						}
						
						if (!vict)
						{
							break;
						}
					}
					System.out.println("");
					
				}
				
				if (vict)
				{
					victoire.setOpacity(1);
					perdu.setOpacity(0);
				}
				else 
				{
					perdu.setOpacity(1);
					victoire.setOpacity(0);
				}
				
				
				
            }
        });
		
		
		
		
		
	}
	
	public Scene init(int width, int height) 
	{
		root = new Group();
		jeuxScene = new Scene(root, width, height, Color.WHITE);
		root.getChildren().add(monPuzzle);
		root.getChildren().add(indicLignesPuzzle);
		root.getChildren().add(indicColonnesPuzzle);
		root.getChildren().add(boutonSolution);
		root.getChildren().add(boutonMenu);
		root.getChildren().add(boutonCorrection);
		root.getChildren().add(victoire);
		root.getChildren().add(perdu);
		root.getChildren().add(nomFile);
		
		
		return jeuxScene;
	}
	


}
