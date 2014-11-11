package dijkstra.parser;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Alex on 06/11/2014.
 */

public class FileParser {
    public static void parseFile(File file){
        try{
            String line;
            int lineCount = 0;
            InputStream ips=new FileInputStream(file);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);

            while((line = br.readLine()) != null){
                lineCount++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Check whether the file is a valid field or not
     * The file is considered as invalid if :
     * -it contains less than 3 lines
     * -it is not a square, nor a rectangle
     *
     * @param file : file to parse
     * @return boolean
     */
    /*@TODO : Il serait bien d'ajouter un handler d'erreurs, pour que l'utilisateur sache exactement pourquoi le fichier n'est pas parsable
    * @TODO : et éventuellement une vérification des caractères de chaque ligne, parmis ceux autorisés (exceptions personnalisées -> best)*/
    public static boolean  isParseable(String file){
        try{
            //file opening
            String line;
            int lineCount = 0;
            Vector<Integer> lineLengths = new Vector();
            InputStream ips=new FileInputStream(file);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);

            //get line count and length of each line
            while((line = br.readLine()) != null){
                lineCount++;
                lineLengths.add(line.length());
            }

            //line count control
            if(lineCount < 3)
                return false;
            else{
                //verify if each line is the same length
                boolean sameLengthLines = true;
                int firstLineLength = lineLengths.elementAt(0);
                for(int i =1; i < lineLengths.size(); i++){
                    if (lineLengths.elementAt(i) != firstLineLength) {
                        sameLengthLines = false;
                    }
                }
                return sameLengthLines;
            }
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
