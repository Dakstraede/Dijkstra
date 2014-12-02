package dijkstra.parser;

import dijkstra.graph.Graph;
import dijkstra.graph.Node;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Alex on 06/11/2014.
 */

public class FileParser {
    public static Graph parseFile(String file){
        try{
            String line; //current line during file reading
            int lineCount = 0;
            int lineLength = 0;
            StringBuilder content = new StringBuilder(); //content of the file, in one String
            InputStream ips=new FileInputStream(file);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);

            /*filling the StringBuilder*/
            while((line = br.readLine()) != null){
                lineCount++;
                lineLength = line.length();
                content.append(line);
            }

            br.close(); //stream closing

            /*filling an array with the StringBuilder characters*/
            char[][] fileToArray = new char[lineLength][lineCount];
            for(int j = 0; j < lineCount; j++){
                for(int i = 0; i < lineLength; i++)
                    fileToArray[i][j] = content.charAt(i+j*lineLength);
            }


            /*Final Graph object construction*/
            char current;
            int index = 0;
            Graph graph = new Graph(lineLength, lineCount);
            for(int j = 0; j < lineCount; j++) {
                for (int i = 0; i < lineLength; i++) {
                    current = fileToArray[i][j];
                    System.out.print(current);
                    switch (current) {
                        case '*':
                            graph.addWall(new Node(index, Node.WALL), index);
                            break;

                        case 'G':
                            graph.addGrass(new Node(index, Node.GRASS), index);
                            break;

                        case 'D':
                            graph.addDoor(new Node(index, Node.DOOR), index);
                            break;

                        case 'A':
                            graph.addExit(new Node(index, Node.CHEESE), index);
                            break;

                        default:
                            graph.addGround(new Node(index, Node.GROUND), index);
                            break;
                    }
                    index++;
                }
                System.out.println();
            }
            return graph;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Check whether the file is a valid field or not
     * The file is considered as invalid if :
     * -it contains less than 3 lines
     * -it is not a square, nor a rectangle
     * -it is not closed by walls all around the field
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
            StringBuilder content = new StringBuilder();
            int lineCount = 0;
            Vector<Integer> lineLengths = new Vector<Integer>();

            InputStream ips=new FileInputStream(file);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);

            //get line count and length of each line
            while((line = br.readLine()) != null){
                content.append(line);
                lineCount++;
                lineLengths.add(line.length());
            }

            //line count control
            if(lineCount < 3)
                return false;
            else {
                //verify if each line is the same length
                boolean sameLengthLines = true;
                int firstLineLength = lineLengths.elementAt(0);
                for (int i = 1; i < lineLengths.size(); i++) {
                    if (lineLengths.elementAt(i) != firstLineLength) {
                        sameLengthLines = false;
                    }
                }
                //enclosure control and return in one line -> this is beautiful coding
                return (sameLengthLines && hasEnclosure(content, firstLineLength, lineCount));
            }
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method checks if the field is well closed by '*'. Controls on lines length must have been done.
     * @param fileContent : the content of the file, just put into a StringBuffer (without '\n' characters)
     * @param lineLength : the length of each line of the file (so must be the same for each)
     * @param lineCount : the number of lines of the file
     * @return
     */
    // @TODO : la mise en tableau n'est pas la méthode la plus optimisée, en jouant sur les index, nous pouvons parvenir au même résultat sans utiliser de tableau (-> si temps suffisant, optimiser)
    private static boolean hasEnclosure(StringBuilder fileContent, int lineLength, int lineCount){
        char fileToArray[][] = new char[lineLength][lineCount];
        boolean wallFound = true;
        int i, j;

        //filling the array
        for(j = 0; j < lineCount; j++){
            for(i = 0; i < lineLength; i++)
                fileToArray[i][j] = fileContent.charAt(i+j*lineLength);
        }

        //check of the entire first line
        i = 0;
        while (i < lineLength && wallFound){
            if(fileToArray[i][0] != '*') {
                wallFound = false;
            }
            i++;
        }

        //check of the entire last line
        i=0;
        while (i < lineLength && wallFound){
            if(fileToArray[i][lineCount-1] != '*') {
                wallFound = false;
            }
            i++;
        }

        //check of the beginning and the end of all the other lines
        j=1;
        while(j < lineCount && wallFound){
            if(fileToArray[0][j] != '*' || fileToArray[lineLength-1][j] != '*'){
                wallFound = false;
            }
            j++;
        }

        return wallFound;
    }
}
