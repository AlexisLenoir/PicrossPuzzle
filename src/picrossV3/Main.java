package picrossV3;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application
{
	public static final String TITLE = "Picross puzzles";
	public static final int SIZEX = 1100;
	public static final int SIZEY = 780;
	
	public static void main (String [] args)
	{
		Application.launch(args);
	}
	
	public void start (Stage primaryStage)
	{
		primaryStage.setTitle(TITLE);
		primaryStage.setResizable(true);
		
		
		/*
		 * Music
		 
		String musicFileJeux = "Music/tchaikovsky-june-barcarolle-op37-no-6.mp3";
		Media soundJeux = new Media(new File(musicFileJeux).toURI().toString());
		MediaPlayer music = new MediaPlayer(soundJeux);
		music.play();
		music.setCycleCount(MediaPlayer.INDEFINITE);
		*/
		
    		SceneManager.setSceneManager(primaryStage);
    		SceneManager.goToMenuScene();
	}
	
}
