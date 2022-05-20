package picrossV3;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager 
{
	private static Stage stage;
	
	public static void  setSceneManager(Stage primaryStage)
	{
		stage = primaryStage;
		stage.show();
	}
	
	public static void goToMenuScene (/*SceneManager sceneManager*/)
	{
		Menu menu = new Menu();
		Scene menuScene = menu.init(Main.SIZEX,Main.SIZEY);
		stage.setScene(menuScene);
	}
	
	public static void goToJeuxScene (/*SceneManager sceneManager*/ Grille g)
	{
		Jeux jeux = new Jeux(g);
		Scene jeuxScene = jeux.init(Main.SIZEX,Main.SIZEY);
		stage.setScene(jeuxScene);
	}
	
	public static void goToChargementScene (/*SceneManager sceneManager*/)
	{
		Chargement chargement = new Chargement();
		Scene chargementScene = chargement.init(Main.SIZEX,Main.SIZEY);
		stage.setScene(chargementScene);
	}
	
	public static void goToSelectionGrilleSeuilScene (ArrayList<Grille> res)
	{
		SelectionGrilleSeuil selection = new SelectionGrilleSeuil(res);
		Scene selectionScene = selection.init(Main.SIZEX,Main.SIZEY);
		stage.setScene(selectionScene);
	}
}
