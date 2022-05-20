package picrossV3;

import java.io.*;
import java.util.ArrayList;

/*
    Cette classe est une classe outil, elle contient une methode static qui permet à partir d'un fichier texte,
    d'initialiser des ArrayList<ArrayList<Integer>> indicLigne et indicColonne, qui permette de construire une grille
*/


public class Fichier 
{

	
 
    public static boolean lecture_Fichier (String nomFichier, ArrayList<ArrayList<Integer>> indicLigne, ArrayList<ArrayList<Integer>> indicColonne)
    {
        
    		
    		
    		File fichier = new File (nomFichier);
        String lignedamier;
        ArrayList <Integer> sequence;
        
        
        try
        {
        		
            BufferedReader br = new BufferedReader(new FileReader(fichier));
            
           
            
            //Lecture Sequence ligne
            while ((lignedamier = br.readLine()) != null)
            {
            
            	
                if (lignedamier.equals("#")) break; 
            		sequence = new ArrayList<Integer>();
                
                
                if (!lignedamier.equals(""))
                {
                    String[] cellule = lignedamier.split(" ");
                    
                    
                    
                    for (int i = 0; i < cellule.length; i++)
                    {
                        sequence.add( Integer.parseInt(cellule[i]));
                    }
                }
                else
                {
                    
                    
                    sequence.add(0); //Pas de valeurs dans la séquence
                }
                
                
                indicLigne.add(sequence);
            
            
            }
            
            
            //Lecture Sequence Colonne
            while ((lignedamier = br.readLine()) != null)
            {
            		sequence = new ArrayList<Integer>();
                
                if (!lignedamier.equals(""))
                {
                    String[] cellule = lignedamier.split(" ");
                    
                    
                    
                    for (int i = 0; i < cellule.length; i++)
                    {
                        sequence.add(Integer.parseInt(cellule[i]));
                    }
                }
                else
                {
                    
                    
                    sequence.add(0); //Pas de valeurs dans la séquence
                }
                
                
                indicColonne.add(sequence);
            
            }
            
            br.close();
        }
        
        catch (FileNotFoundException e)
        {
            System.out.println("FileNotFoundException");
            return false;
        }
        catch (IOException e)
        {
            System.out.println("IOException");
        }
        
        return true;
    } //Fin de la methode static
    

    
    
}
        

