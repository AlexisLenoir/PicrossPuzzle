package picrossV3;

import java.util.ArrayList;



public class Grille {
	
	private int couleur [][];
	String nomFichier;
	private ArrayList<ArrayList<Integer>> indicLignes ;
	private ArrayList<ArrayList<Integer>> indicColonnes;
	private int nbLignes;
	private int nbColonnes;
	private ArrayList<Integer> modifLignes;
	private ArrayList<Integer> modifColonnes;
	private boolean problemLecture;
	private int seuil;
	 // valeur entre -1, 0 et 1 permettant de dire si tout est colorié (1), si c'est pas fini (0) ou si c'est pas possible (-1)

	
	
	
	public Grille (ArrayList<ArrayList<Integer>> indicLignes,  ArrayList<ArrayList<Integer>> indicColonnes,int nbLignes,int nbColonnes)
	{
		modifLignes = new ArrayList<Integer>();
		modifColonnes = new ArrayList<Integer>();
		//this.indicLignes = new ArrayList<ArrayList<Integer>>();
		//this.indicColonnes = new ArrayList<ArrayList<Integer>>();
		
		this.indicLignes = indicLignes;
		this.indicColonnes = indicColonnes;
		
		this.nbLignes = nbLignes;
		this.nbColonnes = nbColonnes;
		
		couleur = new int [nbLignes][nbColonnes];
		
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				couleur [i][j] = 0;
			}
		}
		
		
		
	}
	
	public Grille (String nomFichier)
	{
		
		this.nomFichier = nomFichier;
		modifLignes = new ArrayList<Integer>();
		modifColonnes = new ArrayList<Integer>();
		indicLignes = new ArrayList<ArrayList<Integer>>();
		indicColonnes = new ArrayList<ArrayList<Integer>>();
		
		problemLecture = Fichier.lecture_Fichier(nomFichier,indicLignes,indicColonnes );
		
		nbLignes = indicLignes.size();
		nbColonnes = indicColonnes.size();
		
		couleur = new int [nbLignes][nbColonnes];
		
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				couleur [i][j] = 0;
			}
		}
		
	}
	
	public int getSeuil()
	{
		return seuil;
	}
	
	public void setSeuil (int s)
	{
		seuil = s;
	}
	public String getNomFicher()
	{
		return nomFichier;
	}
	
	public boolean getProblem()
	{
		return problemLecture;
	}
	
	public Grille clone()
	{
		Grille g =  new  Grille (indicLignes, indicColonnes, nbLignes, nbColonnes);
		
		//g.nbLignes = nbLignes;
		//g.nbColonnes = nbColonnes;
		
		for (int i = 0; i < g.nbLignes; i++)
		{
			for (int j = 0; j < g.nbColonnes; j++)
			{
				g.couleur [i][j] = couleur [i][j] ;
			}
		}
		
		return g;
	}
	
	public Grille clonetxt()
	{
		Grille g = new Grille (nomFichier);
		
		g.nbLignes = nbLignes;
		g.nbColonnes = nbColonnes;
		
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				g.couleur [i][j] = couleur [i][j] ;
			}
		}
		
		return g;
	}
	
	
	public void affiche_couleur ()
	{
		for (int i = 0; i < nbLignes; i++)
		{
			for (int j = 0; j < nbColonnes; j++)
			{
				if (couleur [i][j] == 1)
				{
					System.out.print(" x ");
				}
				else
				{
					System.out.print("  ");
				}
				
			}
			
			System.out.println();
		}
	}
	
	public ArrayList<ArrayList<Integer>> get_SeqLignes()
	{
		return indicLignes;
	}
	
	public ArrayList<ArrayList<Integer>> get_SeqColonnes()
	{
		return indicColonnes;
	}
	
	
	
	public int get_nbLignes ()
	{
		return nbLignes;
	}
	
	public int get_nbColonnes()
	{
		return nbColonnes;
	}
	
	public int[][] get_couleur()
	{
		return couleur;
	}
	
	public void setCouleur (int [][] c)
	{
		couleur = c;
	}
	
	//Cette methode permet de verifier que la lecture s'est déroulé sans encombre
	public void affiche_grille()
	{
		System.out.println("Le nombre de ligne est "+ nbLignes);
		System.out.println("Le nombre de colonne est "+ nbColonnes);
		
		
		for (int i = 0; i < nbLignes;i++)
		{
			System.out.print("la sequence de la ligne "+i+" est ");
			for (int j = 0; j < indicLignes.get(i).size(); j++)
			{
				System.out.print(indicLignes.get(i).get(j)+" ");
			}
			
			System.out.println("");
			
		}
		
		System.out.println("");
		
		for (int i = 0; i < nbColonnes;i++)
		{
			System.out.print("la sequence de la colonne "+i+" est ");
			for (int j = 0; j < indicColonnes.get(i).size(); j++)
			{
				System.out.print(indicColonnes.get(i).get(j)+" ");
			}
			
			System.out.println("");
			
		}
		
		
		for (int i = 0; i < couleur.length; i++)
		{
			for (int j = 0; j < couleur[i].length; j++)
			{
				System.out.print(couleur[i][j] + " ");
			}
			
			System.out.println("");
		}
	}
	
	public boolean calcul_T_j_l_ligne(int j, int l, int rang) // 4 3 2
    {
		int l2 = l -1; //Pourquoi l2 ?
		
		// rang = 1, si ligne 2
		
		
       
        if (l == 0)
        {
            
        		for (int i = 0; i <= j; i++)
            {
                if (couleur[rang][i] == 1)
                {
                	 	//System.out.println(" reeeeppp rang" + rang + " i " +i);
                		return false;
                }
            }
	        return true;
        }
        
        
        
        
        if (j < (indicLignes.get(rang).get(l2)) - 1) return false;
        
        
        if ( j == (indicLignes.get(rang).get(l2)) - 1)
        {
            if (l != 1) return false;
	            
            else
            {
            		
                for (int i = 0; i <= j; i++)
                {
                    if (couleur[rang][i] == -1) 
                    	{
                    		
                    		return false;
                    	}
                    
                }
	            return true;
            }
        }



        else // On a forcément j > seq[l]
        {
            boolean test = true;
	            
            
            
            for (int i = j +1 -  (indicLignes.get(rang)).get(l2); i <= j ; i++ )
            {
                if (couleur[rang][i] == -1)
                {
                    test = false;
                    break;
                }
            }
	        
	        if (couleur [rang][j - (indicLignes.get(rang)).get(l2) ] == 1) test = false; // La case de separation doit etre blanche    	
	       
            //if (couleur [rang][j - (indicLignes.get(rang)).get(l2) -1] == 1) test = false; // La case de separation doit etre blanche
	            
	        	
	        if (couleur[rang][j] != 1)
	        {
	        		if (test)
	        		{
	        			
	        			return calcul_T_j_l_ligne(j-indicLignes.get(rang).get(l2) - 1, l-1, rang) || calcul_T_j_l_ligne(j-1,l, rang);
	        			// - 1 pour case de separation
	        		}
	        
	        		else
	        		{
	        			return calcul_T_j_l_ligne(j-1,l, rang);
	        		}
	        }
	        else if (test)
	        {
	        		return calcul_T_j_l_ligne(j-indicLignes.get(rang).get(l2) - 1, l-1, rang);
	        }
	        else
	        {
	        		return false;
	        }
        }

    }
	
	
	public boolean calcul_T_j_l_colonne(int j, int l, int rang) 
	{
		 int l2 = l - 1;
		 
		 //System.out.println ("Appel T_j_l (" + j + ", "+ l+" )");
		 
	      if (l == 0)
	      {
	    	  	for (int i = 0; i <= j; i++)
	    	  	{
	    	  		if (couleur[i][rang] == 1)
	            {
	    	  			//System.out.println(" youpi ahahah 1" );
	    	  			return false;
	            }
	        }
	            
	            return true;
	       }
	      
	       //System.out.println(l2);
	       if (j < (indicColonnes.get(rang)).get(l2) - 1)
	    	   {
	    	   		//System.out.println(" youpi ahahah 2" );
	    	   		return false;
	    	   }
       
	       if ( j == (indicColonnes.get(rang)).get(l2)-1)
	       {
	           if (l != 1)
	        	   {
	        	   		//System.out.println(" youpi ahahah 2" );
	        	   		return false;
	        	   }
	            
	           else
	           {
	        	   		for (int i = 0; i <= j; i++)
	                {
	                    if (couleur[j][rang] == -1) 
	                    	{
	                    			//System.out.println(" youpi ahahah 3" );
	                    			return false;
	                    	}
	                }
	            
	                return true;
	            }
	        }



	        else // On a forcément j > seq[l]
	        {
	        		boolean test = true;
	            
	            
	        		for (int i = j + 1  -  (indicColonnes.get(rang)).get(l2); i <= j ; i++ )
	            {
	                if (couleur[i][rang] == -1)
	                {
	                    test = false;
	                    break;
	                }
	            }
	            
	            if (couleur [j - (indicColonnes.get(rang)).get(l2)][rang] == 1) test = false; // La case de separation doit etre blanche
	            
	            	if (couleur [j][rang] != 1)
	            	{
	            		if (test)
	            		{
	            			//System.out.println (" on fait deux appels j vaut " + j + " et l vaut " + l);
	            			//System.out.println("jkjl" +indicColonnes.get(rang).get(l2));
	            			//System.out.println("rang " + rang );
	            			return calcul_T_j_l_colonne(j-(indicColonnes.get(rang)).get(l2) - 1, l-1, rang) || calcul_T_j_l_colonne(j-1,l, rang);
	            			// - 1 pour case de separation
	            		}
	        
	            		else
	            		{
	            			//System.out.println ("On fait un appels et j vaut " + j + " et l vaut " + l);
	            			return calcul_T_j_l_colonne(j-1,l, rang); //Faux
	            		}
	            	}
	            	
	            	else if  (test)
	            	{
	            		return calcul_T_j_l_colonne(j-(indicColonnes.get(rang)).get(l2) - 1, l-1, rang);
	            	}
	            	
	            	else
	            	{
	            		//System.out.println(" youpi ahahah 4" );
	            		return false;
	            	}
	        }
	}
	
	
	public int color_Ligne(int rang)
    {
    
        if (!calcul_T_j_l_ligne(nbColonnes - 1,(indicLignes.get(rang)).size(),rang))
        {
        		return -1;
        }
        
        for (int i = 0; i < nbColonnes; i++)
        {
            if (couleur[rang][i] == 0)
            {
                couleur[rang][i] = -1;
                if (calcul_T_j_l_ligne(nbColonnes - 1, (indicLignes.get(rang)).size(),rang))
                {
                    couleur[rang][i]= 1 ;
                    //System.out.println("coucou 1 et i =" + i );
                    if (calcul_T_j_l_ligne(nbColonnes - 1,(indicLignes.get(rang)).size(), rang))
                    {
                    		//System.out.println("coucou 2 et i =" + i );
                    		couleur[rang][i]=0;
                    }
                    else
                    	{
                    		couleur[rang][i] = -1;
                    		modifColonnes.add(i);
                    	}
                }
                else
                {
                    couleur[rang][i] = 1;
                    modifColonnes.add(i);
						
                }
            }
        }
        
        return 1;
		
	}
	
	
	public int color_Colonne(int rang)
	{
		if (calcul_T_j_l_colonne(nbLignes-1,(indicColonnes.get(rang)).size(), rang)) 
		{
			for (int i=0;i<nbLignes;i++)
			{
				if (couleur[i][rang]==0)
				{
					couleur[i][rang]=-1;
					if (calcul_T_j_l_colonne(nbLignes-1,(indicColonnes.get(rang)).size(), rang))
					{
						couleur[i][rang]= 1 ;
						if (calcul_T_j_l_colonne(nbLignes-1,(indicColonnes.get(rang)).size(), rang))
						{
							couleur[i][rang]=0;
						}
						else
						{
							couleur[i][rang]=-1;
							
							modifLignes.add(i);
						}
							
					}
					else 
					{
						couleur[i][rang]=1;
						modifLignes.add(i);
					}
				}
			}
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
	
	
	public ArrayList<Object> coloration ()
	{
		Grille g = this.clone();
		int i,j, ok;
		ArrayList<Object> res = new ArrayList<Object>();
		ArrayList<Integer> LignesAVoir = new ArrayList<Integer> ();
		ArrayList<Integer> ColonnesAVoir = new ArrayList<Integer> ();
		
		for (int k = 0; k < nbLignes; k++)
		{
			LignesAVoir.add(k);
		}
		for (int l = 0; l < nbColonnes; l++)
		{
			ColonnesAVoir.add(l);
		}
		
		while ( !LignesAVoir.isEmpty() || !ColonnesAVoir.isEmpty() )
		{
			
			//Lignes
			if (!LignesAVoir.isEmpty())
			{
				i = LignesAVoir.remove(0);
			
				ok = g.color_Ligne(i);
			
				if (ok == -1)
				{
					res.add(-1);
					res.add(null);
					return res;
				}
				for (int k = 0; k < g.modifColonnes.size(); k++)
				{
					ColonnesAVoir.add(g.modifColonnes.remove(k));
				}
			}
			
			
			//Colonnes
			
			if (!ColonnesAVoir.isEmpty())
			{
				j = ColonnesAVoir.remove(0);
			
				ok = g.color_Colonne(j);
			
				if (ok == -1)
				{
					res.add(-1);
					res.add(null);
					return res;
				}
				for (int k = 0; k < g.modifLignes.size(); k++)
				{
					LignesAVoir.add(g.modifLignes.remove(k));
				}
			}
			
		}
		
		boolean allColor = true;
		
		for (int k = 0; k < g.nbLignes; k++)
		{
			for (int l = 0; l < g.nbColonnes; l++)
			{
				if (g.couleur[k][l] == 0)
				{
					allColor = false;
					break;
				}
			}
			
			if (!allColor)
			{
				break;
			}
		}
		
		if (allColor)
		{
			res.add(1);
			res.add(g);
			return res;
		}
		
		res.add(0);
		res.add(g);
		return res;
	}
	

	//@SuppressWarnings("deprecation")
	public ArrayList<Object> colorierEtPropager (int x, int y, int c)
	{
		Grille g = this.clone();
		int i,j, ok;
		ArrayList<Object> res = new ArrayList<Object>();
		ArrayList<Integer> LignesAVoir = new ArrayList<Integer> ();
		ArrayList<Integer> ColonnesAVoir = new ArrayList<Integer> ();
		
		g.couleur[x][y] = c;
		LignesAVoir.add(x);
		ColonnesAVoir.add(y);
		
		
		while ( !LignesAVoir.isEmpty() || !ColonnesAVoir.isEmpty() )
		{
			
			//Lignes
			if (!LignesAVoir.isEmpty())
			{
				i = LignesAVoir.remove(0);
			
				ok = g.color_Ligne(i);
			
				if (ok == -1)
				{
					
					res.add(-1);
					
					
					
					res.add(null);
					return res;
				}
				
				
				for (int k = 0; k < g.modifColonnes.size(); k++)
				{
					ColonnesAVoir.add(g.modifColonnes.remove(k));
				}
			}
			
			
			//Colonnes
			
			if (!ColonnesAVoir.isEmpty())
			{
				j = ColonnesAVoir.remove(0);
			
				ok = g.color_Colonne(j);
			
				if (ok == -1)
				{
					
					res.add(-1);
					res.add(null);
					return res;
				}
				for (int k = 0; k < g.modifLignes.size(); k++)
				{
					LignesAVoir.add(g.modifLignes.remove(k));
				}
			}
			
		}
		
		boolean allColor = true;
		
		for (int k = 0; k < g.nbLignes; k++)
		{
			for (int l = 0; l < g.nbColonnes; l++)
			{
				if (g.couleur[k][l] == 0)
				{
					allColor = false;
					break;
				}
			}
			
			if (!allColor)
			{
				break;
			}
		}
		
		if (allColor)
		{
			res.add(1);
			res.add(g);
			return res;
		}
		
		res.add(0);
		res.add(g);
		return res;	
	}
	
	
	public ArrayList<Object> enum_rec(int k, int c) 
	{
		ArrayList<Object> a1 = new ArrayList<Object>();
		if (k == nbColonnes * nbLignes) 
		{
			a1.add(1);
			System.out.println("hello chef");
			return a1;
		}
		int i = k/nbColonnes;
		int j = k%nbColonnes;
		
		a1 = this.colorierEtPropager(i, j, c);
		
		
		if ( (int) (a1.get(0)) == -1) 
		{
			//a1.set(1, null);
			
			return a1;
		}
		if ((int)a1.get(0) == 1) 
		{
			return a1;
		}
		
		//System.out.println("bob 3 et k vaut " +k);
		
		Grille nw = (Grille) a1.get(1);
		//nw.affiche_grille();
		do
		{
			k++;
			//System.out.println(" k vaut " + k);
			if (k  == nbColonnes * nbLignes ) break;
			i = k/nbColonnes;
			j = k%nbColonnes;
			//System.out.println("i vaut " + i);
			//System.out.println("j vaut " +j);
			
			
		}while ( ((Grille)a1.get(1)).couleur[i][j] != 0);
		
		
		
		if ((int)((Grille)a1.get(1)).enum_rec(k,-1).get(0) == 1) return ((Grille)a1.get(1)).enum_rec(k,-1);
		else return ((Grille)a1.get(1)).enum_rec(k,1);
	}
	
	public ArrayList<Object> enumeration()
	{
		ArrayList<Object> a1 = new ArrayList<Object>();
		a1 = this.coloration();
		
		if ((int)a1.get(0) == -1) 
		{
			a1.set(0, -1);
			return a1;
		}
		/*
		else if ((int)a1.get(0) == 1)
		{
			return a1;
		}*/
		
		System.out.println(" bob " + (int) (a1.get(0)));
		Grille nw = (Grille) a1.get(1);
		
		//nw.affiche_grille(); 
		
		System.out.println("hu " + (int) (nw.enum_rec(0,-1).get(0) ));
		
	
		
		if ( (int) (nw.enum_rec(0,-1).get(0) )== 1) return nw.enum_rec(0,-1);
		else return nw.enum_rec(0,1);
	}
	
	public Grille adaptGrilleColorPartiel ()
	{	
		/*
		 * Cette méthode transforme une grille obtenue à l'aide d'une image, dont le tableau couleur est entièrement renseigné, en une 
		 * grille resoluble par le solveur partiel, c'est cette nouvelle grille qui est renvoyé.
		 */
		Grille g = this.clone();
		//recup les indic de seq et met le tableau couleur tout 0
		return g;
	}
	
	public ArrayList<Object> colorationPixelsInconnus ()
	{
		
		
		ArrayList<Object> res = this.coloration();
		
		if ((int)(res.get(0))==1)
		{
			return res;
		}
		if ((int)(res.get(0))== -1)
		{
			return res;
		}
		
		
		Grille g = (Grille)(res.get(1));
		int nbInconnus = 0;
		
		ArrayList<Point> ListInconnus = new ArrayList<Point>();
		
		//System.out.println("nouvelle grille");
		//g.affiche_grille();
		
		for (int m=0; m<g.nbLignes; m++) 
		{
			for (int n=0; n<g.nbColonnes;n++) 
			{
				if (g.couleur[m][n] == 0)
				{
					ListInconnus.add(new Point(m,n));
					nbInconnus++;
				}
			}
		}
		/*System.out.println("nb de Inconnu" + nbInconnus);
		for (Point p : ListInconnus)
		{
			p.affichePoint();
		}*/
		res.add(nbInconnus);
		res.add(ListInconnus);
		return res;
	}
	
}

	

