package picrossV3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;





public class ImagePuzzle 
{
	public static BufferedImage scale(Image source, int width, int height) 
	{ 
	    /* On crée une nouvelle image aux bonnes dimensions. */ 
	    BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); 
	  
	    /* On dessine sur le Graphics de l'image bufferisée. */ 
	    Graphics2D g = buf.createGraphics(); 
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
	    g.drawImage(source, 0, 0, width, height, null); 
	    g.dispose(); 
	  
	    /* On retourne l'image bufferisée, qui est une image. */ 
	    return buf; 
	}
	
	public static BufferedImage grayImage (BufferedImage source)
	{
		ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null); 
	 	BufferedImage imageGrise = op.filter(source,null);
	 	
	 	ColorConvertOp op2 = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_sRGB), null); 
	 	BufferedImage imageGrise2 = op2.filter(imageGrise,null);
	 	
	 	return imageGrise2;
	}
	
	public static BufferedImage  redimImage (BufferedImage  source)
	{
		int w = source.getWidth();
		int h = source.getHeight();
		int l = Math.max(w,h);
		double c = 90/( (double) l);
		int nw = (int) (w*c);
		int nh = (int) (h*c);
		
		
		BufferedImage imagedst = scale(source, nw, nh);
		
		return imagedst;
	}
	
	public static BufferedImage  convertImage (BufferedImage  source)
	{
		/*
		 * Redim  + passage au niveau de gris + filtre median
		 */
		
		BufferedImage imagedst = redimImage(source);
		imagedst = grayImage(imagedst);
		imagedst = filtreMedian (imagedst);
		
		return imagedst;
	}
	
	public static Grille createPuzzle (String nomFichier) throws IOException
	{
		/*
		 *  elle prend en paramètre l'adresse exacte du fichier de l'image, elle lit le fichier , elle appelle les différents traitements = redim, filtre, threshold ...
		 *  elle retourne une gentille grille !
		 */
		
		String inFilename = nomFichier;
		File inputFile = new File(inFilename);
		
		BufferedImage imagesrc = ImageIO.read(inputFile);
	 	
	 	BufferedImage imagedst = ImagePuzzle.convertImage(imagesrc);
	 	
	 	
	 	int [][] imageWB = ImagePuzzle.thresholdImage(imagedst, 140);
	 	
	 	return ImagePuzzle.convertPuzzle2 (imageWB, 140);
	}
	
	public static ArrayList<Grille> createPuzzle2 (String nomFichier, ProgressBar progression) throws IOException
	{
		/*
		 *  elle prend en paramètre l'adresse exacte du fichier de l'image, elle lit le fichier , elle appelle les différents traitements = redim, filtre, threshold ...
		 *  elles retournent différentes grilles, avec différents seuils!
		 */
		
		ArrayList<Grille> res = new ArrayList<Grille>();
		
		String inFilename = nomFichier;
		File inputFile = new File(inFilename);
		
		BufferedImage imagesrc = ImageIO.read(inputFile);
	 	
	 	BufferedImage imagedst1 = ImagePuzzle.convertImage(imagesrc);
	 	BufferedImage imagedst2 = ImagePuzzle.convertImage(imagesrc);
	 	BufferedImage imagedst3 = ImagePuzzle.convertImage(imagesrc);
	 	BufferedImage imagedst4 = ImagePuzzle.convertImage(imagesrc);
	 	BufferedImage imagedst5 = ImagePuzzle.convertImage(imagesrc);
	 	BufferedImage imagedst6 = ImagePuzzle.convertImage(imagesrc);
	 	
	 	
	 	
	 	int [][] imageWB1 = ImagePuzzle.thresholdImage(imagedst1, 150);
	 	res.add(ImagePuzzle.convertPuzzle2 (imageWB1, 150));
	 	
	 	progression.setProgress(0.50);
	 	
	 	
	 	int [][] imageWB2 = ImagePuzzle.thresholdImage(imagedst2, 130);
	 	res.add(ImagePuzzle.convertPuzzle2 (imageWB2,130));
	 	
	
	 	
	 	int [][] imageWB3 = ImagePuzzle.thresholdImage(imagedst3, 120); //seuil 110 et 100 marche pas , 130 marche, 120 marcher
	 	res.add(ImagePuzzle.convertPuzzle2 (imageWB3,120));
	 	
	 	int [][] imageWB4 = ImagePuzzle.thresholdImage(imagedst4, 110);
	 	res.add(ImagePuzzle.convertPuzzle2 (imageWB4,110));
	 	
	 	int [][] imageWB5 = ImagePuzzle.thresholdImage(imagedst5, 100);
	 	res.add(ImagePuzzle.convertPuzzle2 (imageWB5,100));
	 	
	 	int [][] imageWB6 = ImagePuzzle.thresholdImage(imagedst6, 80);
	 	res.add(ImagePuzzle.convertPuzzle2 (imageWB6,80));
	 	
	 	/*
	 	System.out.println("Image qui bugge");
	 	for (int i = 0; i < imageWB3.length; i++)
	 	{
	 		for (int j = 0; j < imageWB3[0].length;j++)
	 		{
	 			System.out.print(imageWB3[i][j]);
	 		}
	 		System.out.println("");
	 	}*/
	 	
	 
	 	
	 	return res;
	}
	
	public static BufferedImage filtreMedian (BufferedImage source)
	{
		/*
		 * Attention le filtre est simplifié, car on suppose ici que l'image donné en argument est en nuance de gris,  i.e niveau rouge = niveau vert = niveau bleu
		 */
		int [] pixel = new int [9];
	    int[] R = new int[9];
	    int[] B = new int[9];
	    int[] G = new int[9];
	    int res;
	    
	    for(int i = 1; i < source.getWidth()-1; i++)
	    {
	    		for(int j = 1;j <source.getHeight()-1;j++)
	    		{
	    			pixel[0] = new Color(source.getRGB(i-1,j-1)).getRed();
	    			pixel[1] = new Color(source.getRGB(i-1,j)).getRed();
	    			pixel[2] = new Color(source.getRGB(i-1,j+1)).getRed();
	    			pixel[3] = new Color(source.getRGB(i,j+1)).getRed();
	    			pixel[4] = new Color(source.getRGB(i+1,j+1)).getRed();
	    			pixel[5] = new Color(source.getRGB(i+1,j)).getRed();
	    			pixel[6] = new Color(source.getRGB(i+1,j-1)).getRed();
	    			pixel[7] = new Color(source.getRGB(i,j-1)).getRed();
	    			pixel[8] = new Color(source.getRGB(i,j)).getRed();
	    			
	    			
	    			Arrays.sort(pixel);
	    			//System.out.println(pixel[4]);
	    			res = (new Color(pixel[4],pixel[4],pixel[4])).getRGB();
	    			/*System.out.println(res);
	    			System.out.println(i);
	    			System.out.println(j);*/
	    			
	    			
	    			Color c = new Color (res);
	    			//System.out.println(c.getBlue());
	    			//System.out.println("before " + source.getRGB(i,j));
	            source.setRGB(i,j,res);
	    		}
	    }
	    return source;
	}
	

	
	
	
	public static int [][] thresholdImage (BufferedImage source, int s)
	{
		int k;
		
		int [][] couleur = new int [source.getHeight()][source.getWidth()];
		
		for(int i = 0; i < source.getHeight(); i++)
	    {
	    		for(int j = 0;j < source.getWidth();j++)
	    		{
	    			k = new Color(source.getRGB(j,i)).getRed();
	    			
	    			if (k > s)
	    			{
		    			
		    			couleur[i][j] = 0;
	    			}
	    			else
	    			{
	    				
		    			couleur[i][j] = 1;
	    			}
	    		}
	    }
		
	
		return couleur;
		
	}
	
	
	public static ArrayList<Object> getSeqLignColon (int [][]couleur)
	{
		
		int nbLignes =  couleur.length;
		int nbColonnes = couleur[0].length;
		int k;
		int l = 0;
		boolean s = false ;// Pour savoir s'il faut enregistrer une séquence
		
		ArrayList<ArrayList<Integer>> indicLignes = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> indicColonnes = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> sequence;
		
		//Init de séquence des lignes 
		
		for(int i = 0; i < nbLignes; i++)
	    {
			sequence = new ArrayList<Integer>();
			
			for(int j = 0;j < nbColonnes;j++)
	    		{
	    			//System.out.println(" i vaut " +i);
	    			//System.out.println("j vaut " +j);
				k = couleur[i][j];
	    			
	    			
	    			
	    			if (k == 1)
	    			{
	    				if ( j == nbColonnes -1)
		    			{
		    				l++;
	    					sequence.add(l);
	    					s = false;
	    					l = 0;
		    			}
	    				else
	    				{
	    					l++;
		    				s = true;
	    				}
	  
	    			}
	    			else if (k == 0)
	    			{
	    				
	    				if (s)
	    				{
	    					sequence.add(l);
	    					s = false;
	    				}
	    				l = 0;
	    			}
	    			//System.out.println(k);
	    		}
			
			indicLignes.add(sequence);
			
	    }
		
		
		
		//Init de séquence des colonnes 
		
		for(int i = 0; i < nbColonnes; i++)
	    {
			sequence = new ArrayList<Integer>();
			
			for(int j = 0; j < nbLignes;j++)
	    		{
	    			k =  couleur[j][i];
	    			
	    			if (k == 1)
	    			{
	    				if ( j == nbLignes -1)
		    			{
		    				l++;
	    					sequence.add(l);
	    					s = false;
	    					l = 0;
		    			}
	    				else
	    				{
	    					l++;
		    				s = true;
	    				}
	  
	    			}
	    			else if (k == 0)
	    			{
	    				
	    				if (s)
	    				{
	    					sequence.add(l);
	    					s = false;
	    				}
	    				l = 0;
	    			}
	    			
	    		}
			
			indicColonnes.add(sequence);
	    }
		
		
		
		ArrayList<Object> res  = new ArrayList<Object>();
		res.add(indicLignes);
		res.add(indicColonnes);
		
		return res;
		
	}
	
	public static String convertGrilletoImage (Grille g, int id)
	{
		BufferedImage res = new BufferedImage(g.get_nbColonnes(), g.get_nbLignes(), BufferedImage.TYPE_INT_RGB);
		
		
		int pixel;
		
		ArrayList<Object> list = g.coloration();
		if ((int)(list.get(0)) == -1)
		{
			return null;
		}
		
		Grille newg = (Grille) (list.get(1));
		int [][] couleur = newg.get_couleur();
		
		for(int i = 0; i <res.getWidth() ; i++)
	    {
	    		for(int j = 0;j < res.getHeight();j++)
	    		{
	    			if (couleur[j][i] == 1)
	    			{
	    				pixel = (new Color(0,0,0)).getRGB();
		    			
		            res.setRGB(i,j,pixel);
	    			}
	    			else
	    			{
	    					pixel = (new Color(255,255,255)).getRGB();
	    					res.setRGB(i,j,pixel);
	    			}
	    		}
	    }
		
		
		
		String name =  "/Users/alexislenoir/eclipse-workspace/PicrossV3/test/" +id+ ".jpg";
		try 
		{
			ImageIO.write(res, "PNG", new File (name));
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
		
		
		
	}
	
	public static Grille convertPuzzle (int [][] couleur, int s)
	{
		
		
		int nbLignes =  couleur.length;
		int nbColonnes = couleur[0].length;
	
		
	
		
		ArrayList<Object> res = getSeqLignColon (couleur);
		ArrayList<ArrayList<Integer>> indicLignes = (ArrayList<ArrayList<Integer>>) (res.get(0));
		ArrayList<ArrayList<Integer>> indicColonnes = (ArrayList<ArrayList<Integer>>) (res.get(1));
		
		Grille g =  new Grille (indicLignes, indicColonnes, nbLignes, nbColonnes);
		g.setSeuil(s);
		
		return g;
	}
	
	
	public static Grille convertPuzzle2 (int [][] couleur, int s)
	{
		
		
		int nbLignes =  couleur.length;
		int nbColonnes = couleur[0].length;
		//System.out.println("bonjourno 2");
		
		
		//La grille ne resout pas partielement
		
			//Debut boucle while true 
		
				
				//(1) condition d'arret, on verifie si on peut resoudre la grille essai avec color partiel
				//(2) on recup les pixel inconnus, colorationPixelsInconnus
				//(3) ordonner les pixels inconnus à  partir de ArrayList Point, il faut couleurs 
				
					// (A) On supprime tous ceux qui sont noir model
					// (B)On regarde ceux qui ont des cases noires voisines, (après trier aussi les autres isolé case blanche)
					// (C)arrayList trier par ordre croissante d'importance
			
				
		
				// (4)on modifie couleur model en retirer le 1er point 
				//(5) on en déduit les nouvelles d'indic de sequences pour l'essai
		
		
		
		
			//Sortie de boucle renvoi essai
		
		ArrayList<Object> res;
		Grille essai;
		
		while (true)
		{
			//System.out.println("bonjourno 3");
			ArrayList<Object> res3 = getSeqLignColon (couleur);
			
			//System.out.println("bonjourno 4");
			
			ArrayList<ArrayList<Integer>> indicLignes = (ArrayList<ArrayList<Integer>>) (res3.get(0));
			ArrayList<ArrayList<Integer>> indicColonnes = (ArrayList<ArrayList<Integer>>) (res3.get(1));
			essai = new Grille (indicLignes, indicColonnes, nbLignes, nbColonnes);
			//System.out.println("grille determiné d'apres sec");
			//essai.affiche_grille();
			
			
			//(1) condition d'arret, on verifie si on peut resoudre la grille essai avec color partiel
			res = essai.colorationPixelsInconnus();
			//System.out.println("bonjourno 5");
			if ( (int) (res.get(0)) == 1)
			{
				//essai.affiche_grille();
				System.out.println("EXIT");
				essai.setSeuil(s);
				return essai;
			}
			if ( (int) (res.get(0)) == -1)
			{
				//essai.affiche_grille();
				System.out.println("ERREUR");
				return essai;
			}
			
			//System.out.println("bonjourno 6");
			//(3) ordonner les pixels inconnus à  partir de ArrayList Point, il faut couleurs 
			ArrayList<Point> lp = (ArrayList<Point>) (res.get(3));
			
			ArrayList<Point> lp2 = classePointsListe(lp, couleur);
			
			
			/*
			System.out.println("les nouveaux points ");
			for (Point p:lp2)
			{
				p.affichePoint();
			}
			*/
			
			// (4)on modifie couleur model en retirer le 1er point
			
			Point p = lp2.get(0);
			couleur[p.x][p.y] = 1;
			
			
			
			
		}
		
		
		
		
	}
	
	
	public static ArrayList<Point> classePointsListe(ArrayList<Point> lp, int[][] tab) 
	{
		ArrayList<Point> lp2 = new ArrayList<Point>();
		ArrayList<Point> lp3 = new ArrayList<Point>();
		
		for(int i=0; i < lp.size(); i++) 
		{
			if (tab[lp.get(i).x][lp.get(i).y] == 0)
			{
				lp2.add(lp.get(i));
			}
		}
		for (int i=0; i < lp2.size(); i++) 
		{
			if (lp2.get(i).nbVoisinsNoirs(tab)==4) 
			{
				lp3.add(lp2.get(i));
			}
		}
		
		for (int i=0; i < lp2.size(); i++) 
		{
			if (lp2.get(i).nbVoisinsNoirs(tab)==3) 
			{
				lp3.add(lp2.get(i));
			}
		}
		
		for (int i=0; i < lp2.size(); i++) 
		{
			if (lp2.get(i).nbVoisinsNoirs(tab)==2) 
			{
				lp3.add(lp2.get(i));
			}
		}
		
		for (int i=0; i < lp2.size(); i++) 
		{
			if (lp2.get(i).nbVoisinsNoirs(tab)==1) 
			{
				lp3.add(lp2.get(i));
			}
		}
		
		for (int i=0; i < lp2.size(); i++) 
		{
			if (lp2.get(i).nbVoisinsNoirs(tab)==0) 
			{
				lp3.add(lp2.get(i));
			}
		}
		

		
		return lp3;
	}
	
	
	
	
	
	
	
	
	
	                    
	/*
	public static Grille convertPuzzle (BufferedImage source)
	{
		/*
		 * On suppose que l'image est noir et blanc 
		 * 
		 * 255 blanc   0 noir
		 *  
		 
		
		int nbLignes =  source.getHeight();
		int nbColonnes = source.getWidth();
		int k;
		int l = 0;
		boolean s = false ;// Pour savoir s'il faut enregistrer une séquence
		
		ArrayList<ArrayList<Integer>> indicLignes = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> indicColonnes = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> sequence;
		
		//Init de séquence des lignes 
		
		for(int i = 0; i < source.getHeight(); i++)
	    {
			sequence = new ArrayList<Integer>();
			
			for(int j = 0;j <source.getWidth();j++)
	    		{
	    			//System.out.println(" i vaut " +i);
	    			//System.out.println("j vaut " +j);
				k = new Color(source.getRGB(j,i)).getRed();
	    			
	    			
	    			
	    			if (k == 0)
	    			{
	    				if ( j == source.getWidth()-1  && s)
		    			{
		    				l++;
	    					sequence.add(l);
	    					s = false;
	    					l = 0;
		    			}
	    				else
	    				{
	    					l++;
		    				s = true;
	    				}
	  
	    			}
	    			else if (k == 255)
	    			{
	    				
	    				if (s)
	    				{
	    					sequence.add(l);
	    					s = false;
	    				}
	    				l = 0;
	    			}
	    			//System.out.println(k);
	    		}
			
			indicLignes.add(sequence);
			
	    }
		
		
		/*Test de affichage
		
		System.out.println("Nb de lignes" + nbLignes);
		System.out.println("Nb de colonnes " + nbColonnes);
		
		for (int i = 0; i < indicLignes.size(); i++)
		{
			for (int j = 0; j < indicLignes.get(i).size(); j++)
			{
				System.out.print(indicLignes.get(i).get(j) + " ");
			}
			
			System.out.println("");
		}
		
		
		//Init de séquence des colonnes 
		
		for(int i = 0; i < source.getWidth(); i++)
	    {
			sequence = new ArrayList<Integer>();
			
			for(int j = 0;j <source.getHeight();j++)
	    		{
	    			k = new Color(source.getRGB(i,j)).getRed();
	    			
	    			if (k == 0)
	    			{
	    				if ( j == source.getHeight()-1  && s)
		    			{
		    				l++;
	    					sequence.add(l);
	    					s = false;
	    					l = 0;
		    			}
	    				else
	    				{
	    					l++;
		    				s = true;
	    				}
	  
	    			}
	    			else if (k == 255)
	    			{
	    				
	    				if (s)
	    				{
	    					sequence.add(l);
	    					s = false;
	    				}
	    				l = 0;
	    			}
	    			
	    		}
			
			indicColonnes.add(sequence);
	    }
		
		/*Test de affichage
		
				System.out.println("Nb de lignes" + nbLignes);
				System.out.println("Nb de colonnes " + nbColonnes);
				
				for (int i = 0; i < indicColonnes.size(); i++)
				{
					for (int j = 0; j < indicColonnes.get(i).size(); j++)
					{
						System.out.print(indicColonnes.get(i).get(j) + " ");
					}
					
					System.out.println("");
				}
		
		
		
		Grille grilleModel = new Grille (indicLignes, indicColonnes, nbLignes, nbColonnes);
		//on met les couleurs à grille model
		Grille essai = new Grille (indicLignes, indicColonnes, nbLignes, nbColonnes);
		
		//Verif grille essai se resout déja partiellement
		ArrayList<Object> res = essai.colorationPixelsInconnus();
		if ( (int) (res.get(0)) == 1)
		{
			return essai;
		}
		
		
		
		//La grille ne resout pas partielement
		
			//Debut boucle while true 
		
				
				//(1) condition d'arret, on verifie si on peut resoudre la grille essai avec color partiel
				//(2) on recup les pixel inconnus, colorationPixelsInconnus
				//(3) ordonner les pixels inconnus à  partir de ArrayList Point, il faut couleurs 
				
					// (A) On supprime tous ceux qui sont noir model
					// (B)On regarde ceux qui ont des cases noires voisines, (après trier aussi les autres isolé case blanche)
					// (C)arrayList trier par ordre croissante d'importance
			
				
		
				// (4)on modifie couleur model en retirer le 1er point 
				//(5) on en déduit les nouvelles d'indic de sequences pour l'essai
		
		
		
		
			//Sortie de boucle renvoi essai
		
		
		
		
		return new Grille (indicLignes, indicColonnes, nbLignes, nbColonnes);
	}*/
	    
	    
}
