package picrossV3;

import java.io.File;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Menu 
{

	private Scene menuScene;
	private Group root;
	private Button boutonJouer;
	private Button boutonQuitter;
	private Text titre;
	private ImageView monroeImage;
	private ImageView gangsterImage;
	
	public Menu() 
	{
		//this.sceneManager = sceneManager;
		
		
		titre = new Text("Picross puzzles");
		titre.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR,70));
		titre.setTranslateX(250);
		titre.setTranslateY(100);
		
		
		
		monroeImage = new ImageView(new Image(Menu.class.getResourceAsStream("images/monroe.jpeg")));
		monroeImage.setSmooth(true);
		monroeImage.setCache(true);
		monroeImage.setTranslateX(10);
		monroeImage.setTranslateY(200);
		
		gangsterImage = new ImageView(new Image(Menu.class.getResourceAsStream("images/gangster.jpeg")));
		gangsterImage.setSmooth(true);
		gangsterImage.setCache(true);
		gangsterImage.setTranslateX(700);
		gangsterImage.setTranslateY(200);
		
		
		boutonJouer = new Button ("JOUER");
		boutonJouer.setPrefSize(200,40);
		boutonJouer.setTranslateX(420);
		boutonJouer.setTranslateY(300);
		
		boutonQuitter = new Button ("QUITTER");
		boutonQuitter.setPrefSize(200,40);
		boutonQuitter.setTranslateX(420);
		boutonQuitter.setTranslateY(440);
		
		boutonQuitter.setOnAction(new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
            		Platform.exit();
            }
        });
		
		boutonJouer.setOnAction(new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
            		SceneManager.goToChargementScene();
            }
        });
		
		
	}
	
	public Scene init(int width, int height) 
	{
		root = new Group();
		menuScene = new Scene(root, width, height, Color.WHITE);
		root.getChildren().add(boutonJouer);
		root.getChildren().add(boutonQuitter);
		root.getChildren().add(titre);
		root.getChildren().add(monroeImage);
		root.getChildren().add(gangsterImage);
	
		
		return menuScene;
	}
}
