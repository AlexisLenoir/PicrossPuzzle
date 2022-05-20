package picrossV3;


import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SelectionGrilleSeuil 
{
	private Scene SelectionScene;
	private Group root;
	private ArrayList<Grille> listeGrille;
	private ArrayList<ImageView> listImage;
	private Rectangle [] fonds;
	private Button boutonmenu;
	private  ArrayList<ItemSelectImageSeuil> items;
	

	public SelectionGrilleSeuil (ArrayList<Grille> g)
	{
		listeGrille = g;
		
		int id = 0;
		ImageView imagevw;
		
		//listImage = new ArrayList<ImageView>();
		
		boutonmenu = new Button ("Menu");
		boutonmenu.setPrefSize(200,40);
		
		boutonmenu.setOnAction (new EventHandler<ActionEvent>() {
            
			@Override
            public void handle(ActionEvent event) 
            {
				SceneManager.goToMenuScene();
            }
        });
		
		
		items = new ArrayList<ItemSelectImageSeuil>(); 
		ChooseItem mychooseItem = new ChooseItem();
		
		SelectItem selectItemIn = new SelectItem(true);
		SelectItem selectItemOut = new SelectItem(false);
		
		for (Grille i : listeGrille)
		{
			imagevw = convertImagetoImageView(ImagePuzzle.convertGrilletoImage(i,id));
			if (imagevw == null)
			{
				System.out.println("HELLO");
			}
			items.add(new ItemSelectImageSeuil (imagevw,i));
			items.get(id).setOnMouseClicked(mychooseItem);
			items.get(id).setOnMouseEntered(selectItemIn);
			items.get(id).setOnMouseExited(selectItemOut);
			id++;
			
		}
		
		
		
	}
	
	
	public ImageView convertImagetoImageView(String url)
	{
		File file = new File(url);
		 
		// --> file:/C:/MyImages/myphoto.jpg
		String localUrl;
		
		try 
		{
			localUrl = file.toURI().toURL().toString();
			
			Image image = new Image(localUrl);
			System.out.println(localUrl);
			ImageView res2 = new ImageView( image );
			return res2;
		} catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("ERREUR CONVERSION IMAGEVIEW");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	/*
	public void initTab ()
	{
		/*
		 * on a 6 rectangle qui font 275 de largeur et 300 de hauteur
		 * taille de la fenetre 1100 de largeur et 780 de hauteur
		 *    1100
		 * <-------------->
		 * 100 + 275 + 40 (ecart)
		 * 
		 *
		 *     |80
		 * 780 |+
		 *     |300
		 *     |+
		 *     |40 (ecart)
		 / 
		fonds = new Rectangle [6];
		for (int i = 0; i < fonds.length; i++)
		{
			fonds[i] =new Rectangle();
			fonds[i].setHeight(300);
			fonds[i].setWidth(275);
			fonds[i].setFill(Color.GRAY);
			//fonds[i].setOpacity(0);
		}
		
		
		
		
		for (int i = 0; i< 3; i++)
		{
			fonds[i].setTranslateX(100+i*275+i*40);
			fonds[i].setTranslateY(80);
			items.get(i).setTranslateX(100+i*275+i*40);
			items.get(i).setTranslateY(80);
		}
		
		for (int i = 3 ; i< 6; i++)
		{
			fonds[i].setTranslateY(80+300+40);
			fonds[i].setTranslateX(100+(i-3)*275+(i-3)*40);
			items.get(i).setTranslateX(100+(i-3)*275+(i-3)*40);
			items.get(i).setTranslateY(80+300+40);
		}
		
		
		/*
		for (Rectangle t : fonds)
		{
			System.out.println(" l" +t.getTranslateX());
		}
		
		
		double h, w, maximumCote;
		
		for (int i = 0; i < listImage.size(); i++)
		{
			h = listImage.get(i).getImage().getHeight();
			w = listImage.get(i).getImage().getWidth();
			
			 maximumCote =  Math.max(h, w);
			
			if (maximumCote == w)
			{
				//coef = 225/maximumCote;
				listImage.get(i).setFitWidth(235);
				listImage.get(i).setPreserveRatio(true);
				listImage.get(i).setSmooth(true);
				listImage.get(i).setCache(true);
			}
			else
			{
				//coef = 200/maximumCote;
				listImage.get(i).setFitHeight(200);
				listImage.get(i).setPreserveRatio(true);
				listImage.get(i).setSmooth(true);
				listImage.get(i).setCache(true);
			}
			
			/*System.out.println(" i " +i);
			System.out.println(" x F " + fonds[i].getX());
			System.out.println(" y F " + fonds[i].getY());
			
			System.out.println(" x " + fonds[i].getX()+20);
			System.out.println(" y " + fonds[i].getY()+75);
			listImage.get(i).setTranslateX(fonds[i].getTranslateX()+20);
			listImage.get(i).setTranslateY(fonds[i].getTranslateY()+75);
			
		}
		
		
		
	}

	*/
	
	
	public Scene init(int width, int height) 
	{
		root = new Group();
		
		SelectionScene = new Scene(root, width, height, Color.WHITE);
		
		
		root.getChildren().add(boutonmenu);
		
		
		
		//initTab();
		
		for (int i = 0; i< 3; i++)
		{
			
			items.get(i).setTranslateX(100+i*275+i*40);
			items.get(i).setTranslateY(80);
		}
		
		for (int i = 3 ; i< 6; i++)
		{
			
			items.get(i).setTranslateX(100+(i-3)*275+(i-3)*40);
			items.get(i).setTranslateY(80+300+40);
		}
		
		for (ItemSelectImageSeuil i : items)
		{
			root.getChildren().add(i);
		}
		
		
		return SelectionScene;
	}
	
	
	public class ChooseItem implements EventHandler<MouseEvent>
	{
		
		
		
		

		@Override
		public void handle(MouseEvent event) 
		{
			
			if (event.getSource() instanceof ItemSelectImageSeuil )
			{
				//System.out.println("case");
				ItemSelectImageSeuil i = (ItemSelectImageSeuil) event.getSource();
				Grille g = i.getGrille();
				SceneManager.goToJeuxScene( g);
			}
			
		}
	}
	
	
	
	public class SelectItem implements EventHandler <MouseEvent>
	{
		
		private boolean in;
		
		
		public SelectItem (boolean b)
		{
			in = b;
		}
		
		@Override
		public void handle(MouseEvent event) 
		{
			if (event.getSource() instanceof ItemSelectImageSeuil )
			{
				//System.out.println("case");
				ItemSelectImageSeuil i = (ItemSelectImageSeuil) event.getSource();
				
				if (in)
				{
					i.setFondGreen();
				}
				else
				{
					i.setFondOpaque();
				}
				
			}
	
		}
		
	}
}
