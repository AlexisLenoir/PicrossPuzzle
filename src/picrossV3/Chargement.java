package picrossV3;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Chargement 
{
	private SceneManager sceneManager;
	private Scene chargementScene;
	private Group root;
	private HBox hbox;
	private HBox hboxImage;
	private TextField textField;
	private Button summit;
	private Label label;
	private Label label2;
	private Button boutonmenu;
	private Text erreur;
	private ListView<String> listTxt;
	private ListView<String> listImage;
	public ArrayList<Grille> res;
	private ProgressBar progression;
	
	public Chargement (/*SceneManager sceneManager*/)
	{
		
		progression = new ProgressBar(0);
		progression.setTranslateY(350);
		progression.setTranslateX(610);
		progression.setOpacity(1);
		
		//this.sceneManager = sceneManager;
		hbox = new HBox ();
		summit = new Button ("Charger");
		label = new Label ("Nom du fichier: ");
		label.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR,18));
		
		label2 = new Label ("Liste des fichiers disponibles: ");
		label2.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR,18));
		label2.setTranslateX(100);
		label2.setTranslateY(350);
		textField = new TextField ();
		
		hbox.getChildren().addAll(label, textField, summit);
		hbox.setSpacing(10);
		hbox.setTranslateX(100);
		hbox.setTranslateY(250);
		
		
		hboxImage = new HBox();
		Button summitImage = new Button ("Charger");
		Label labelImage = new Label ("Nom de l'image: ");
		labelImage.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR,18));
		TextField textfieldImage = new TextField();
		hboxImage.getChildren().addAll(labelImage, textfieldImage, summitImage);
		hboxImage.setSpacing(10);
		hboxImage.setTranslateX(550);
		hboxImage.setTranslateY(250);
		
		erreur = new Text ("Nom invalide!");
		erreur.setFont(Font.font("Copperplate", FontWeight.BOLD, FontPosture.REGULAR,20));
		erreur.setTranslateX(450);
		erreur.setTranslateY(230);
		erreur.setFill(Color.RED);
		erreur.setOpacity(0);
		
		boutonmenu = new Button ("Menu");
		boutonmenu.setPrefSize(200,40);
		
		
		File fileTxt = new File ("/Users/alexislenoir/eclipse-workspace/PicrossV3/instances");
		final File[] listFichierTxt = fileTxt.listFiles(new FileFilter() {
	    	public boolean accept(File f) {
	                return f.getName().endsWith(".txt") ;
	            }}
	    );
		
		File fileImage= new File ("/Users/alexislenoir/eclipse-workspace/PicrossV3/Images");
		final File[] listFichierImage = fileImage.listFiles(new FileFilter() {
	    	public boolean accept(File f) {
	                return f.getName().endsWith(".jpg") ;
	            }}
	    );
		
		
		listTxt = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList () ;
		
		listImage = new ListView<String>();
		ObservableList<String> itemsA =FXCollections.observableArrayList ();
		
		
		
		/*
		 * Tri a bulle modif, pour trier la liste de fichier
		 */
		int taille = listFichierTxt.length;
		File tmp = new File("");
		
		for(int i = 0; i < taille; i++) 
	    {
	    		for(int j =1 ; j < (taille-i); j++)
	    	 	{
	    	 		String sj = listFichierTxt[j].getName();
	    	 		String sj1 = listFichierTxt[j-1].getName();
	    	 		sj = sj.replace(".txt","");
	    	 		sj1 = sj1.replace(".txt","");
	    	 		
	    	 		try
	    			{
	    	 			//System.out.println(Integer.parseInt(sj1));
	    	 			//System.out.println(Integer.parseInt(sj));
	    	 			if (Integer.parseInt(sj1) > Integer.parseInt(sj))
	    				{
	    					tmp = listFichierTxt[j-1];
	    					listFichierTxt[j-1] = listFichierTxt[j];
	    					listFichierTxt[j] = tmp;
	    				}
	    			}
	    			catch(NumberFormatException e) 
	    			{
	    				System.out.println("Impossible de convertir ce string en int");
	    			}
	    	 		
	    	 	}
	    }
		
		
		
		for (File f : listFichierImage)
		{
			itemsA.add(f.getName());
		}
		
		
		
		listImage.setItems(itemsA);
		listImage.setEditable(true);
		
		
		listImage.setPrefHeight(200);
		listImage.setPrefWidth(200);
		listImage.setTranslateX(600);
		listImage.setTranslateY(400);
		
		Progress p = new Progress(progression);
		listImage.setOnMouseClicked(p);
		
		
		/*
		listImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
			@Override
            public void handle(MouseEvent event) 
            {
				
			
					String name =  "/Users/alexislenoir/eclipse-workspace/PicrossV3/Images/" +listImage.getSelectionModel().getSelectedItem();
					
					//progression.setOpacity(0);
					try
					{
						res = ImagePuzzle.createPuzzle2(name, progression);
						//SceneManager.goToSelectionGrilleSeuilScene(res);
			
			        }
					catch(IOException e1) 
			        {
			    			e1.printStackTrace();
			    			erreur.setOpacity(1);
			        }

            }
        });*/
		
		for (File f : listFichierTxt)
		{
			items.add(f.getName());
		}
		
		listTxt.setItems(items);
		listTxt.setPrefHeight(200);
		listTxt.setPrefWidth(250);
		
		listTxt.setTranslateX(150);
		listTxt.setTranslateY(400);
		
		
		listTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
			@Override
            public void handle(MouseEvent event) 
            {
				String name = listTxt.getSelectionModel().getSelectedItem();
				System.out.println(listTxt.getSelectionModel().getSelectedItem());
				String s = "instances/" + name;
	            Grille g = new Grille (s);
	            
	            if (g.getProblem()) sceneManager.goToJeuxScene(g);
	            else
	            {
	            		erreur.setOpacity(1);
	            }
            }
        });
		
		
		boutonmenu.setOnAction (new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
				sceneManager.goToMenuScene();
            }
        });
		
		summitImage.setOnAction(new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
				
				 if ((textfieldImage.getText() != null && !textfieldImage.getText().isEmpty())) 
				 {
			            //System.out.println(textField.getText());
			            String s = "/Users/alexislenoir/eclipse-workspace/PicrossV3/Images/" +textfieldImage.getText();
			            try
			            {
			            		Grille g = ImagePuzzle.createPuzzle(s);
			            	
			            		
			            		sceneManager.goToJeuxScene( g);
			            }
			            catch(IOException e1) 
			            {
			    			
			    				e1.printStackTrace();
			    				erreur.setOpacity(1);
			    			}
			     } 
				 else 
				 {
			            textField.setPromptText("Aucun nom de fichier");
				 }
				
            }
        });
		
		
		summit.setOnAction (new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
				
				 if ((textField.getText() != null && !textField.getText().isEmpty())) 
				 {
			            System.out.println(textField.getText());
			            String s = "instances/" +textField.getText();
			            Grille g = new Grille (s);
			            
			            if (g.getProblem()) sceneManager.goToJeuxScene(g);
			            else
			            {
			            		erreur.setOpacity(1);
			            }
			     } 
				 else 
				 {
			            textField.setPromptText("Aucun nom de fichier");
				 }
				;
            }
        });
		
	}
	
	public Scene init(int width, int height) 
	{
		root = new Group();
		chargementScene = new Scene(root, width, height, Color.WHITE);
		root.getChildren().add(hbox);
		root.getChildren().add(hboxImage);
		root.getChildren().add(boutonmenu);
		root.getChildren().add(erreur);
		root.getChildren().add(listTxt);
		root.getChildren().add(label2);
		root.getChildren().add(listImage);
		//root.getChildren().add(progression);

		
		return chargementScene;
	}

	public class Progress implements EventHandler<MouseEvent>
	{

		private ProgressBar progression;
		

		public Progress (ProgressBar progression)
		{
			this.progression = progression;
		}
		
		@Override
		public void handle(MouseEvent event) 
		{
			String name =  "/Users/alexislenoir/eclipse-workspace/PicrossV3/Images/" +listImage.getSelectionModel().getSelectedItem();
			
			//progression.setOpacity(0);
			try
			{
				res = ImagePuzzle.createPuzzle2(name, progression);
				SceneManager.goToSelectionGrilleSeuilScene(res);
	
	        }
			catch(IOException e1) 
	        {
	    			e1.printStackTrace();
	    			erreur.setOpacity(1);
	        }
		}
		
	}
}


