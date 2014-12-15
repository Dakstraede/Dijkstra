package dijkstra.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

import dijkstra.graph.Graph;
import dijkstra.graph.Node;

public class FileParser {
	
	private ArrayList<ArrayList<Node>> table2d = new ArrayList<ArrayList<Node>>();
	private Graph graph = null;
	
    public ArrayList<ArrayList<Node>> parseIn2dTable(String path) throws IOException {
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));;
    	String line;
    	int cLine = 0;
    	int id = 1;
    	while ((line = bufferedReader.readLine()) != null) {
    		ArrayList<Node> tmp = new ArrayList<Node>();
    		for (int i=0; i<line.length(); i++) {
    			String type = getType(line.charAt(i));
    			tmp.add(new Node(id++, type, i, cLine));
    		}
    		table2d.add(tmp);
    		cLine++;
    	}
    	if (bufferedReader != null) bufferedReader.close();
    	return table2d;
    }
    
    private String getType(char s) {
    	switch (s) {
	        case '*': return Node.WALL;
	        case 'G': return Node.GRASS;
	        case 'D': return Node.DOOR;
	        case 'A': return Node.CHEESE;
	        default : return Node.GROUND;
    	}
    }
	
    private Node getNode(int row, int col) {
    	if ((row>0) && (col>0) && (row<table2d.size()) && (col<table2d.get(row).size())) {
    		if (table2d.get(row).get(col).type.equals(Node.WALL)) {
    			return null;
    		} else {
    			return table2d.get(row).get(col);
    		}
    	} else {
    		return null;
    	}
	}
    
    /**
     * Valeur bizarre pour pouvoir aller sur les noeuds autour d'un autre noeud
     */
    private final int[][] arounds = {
        	// row, col, weight
			  //{-1, -1, 2}, // Nord-Ouest
			{-1,  0, 1}, // Nord
			  //{-1,  1, 2}, // Nord-Est
			{ 0, -1, 1}, // Ouest
			{ 0,  1, 1}, // Est
			  //{ 1, -1, 2}, // Sud-Ouest
			{ 1,  0, 1}, // Sud
			  //{ 1,  1, 2}  // Sud-Est
    	};
    
    /**
     * On récupère le graphe et s'il n'existe pas, on le génère
     * @return Graph
     */
    public Graph getGraph() {
    	if (graph == null) {
    		graph = generateGraph();
    	}
    	return graph;
    }
    
    /**
     * On génère le graphe à partir du tableau 2d en analysant pour chaque noeud les noeuds autour
     * @return Graph
     */
    private Graph generateGraph() {
    	Graph graph = new Graph(table2d.get(0).size(), table2d.size());
    	
    	for (int row=0; row<table2d.size(); row++) {
    		for (int col=0; col<table2d.get(row).size(); col++) {
    			if (! table2d.get(row).get(col).type.equals(Node.WALL)) {
    				Node current = getNode(row, col);
    				
    				for (int i=0; i<arounds.length; i++) {
    					Node aroundNode = getNode(row + arounds[i][0], col + arounds[i][1]);
    					if (aroundNode != null) {
    						int weight = arounds[i][2];
    						if (aroundNode.type.equals(Node.GRASS) ) weight = 3;
    						current.addEdge(aroundNode, weight);
    					}	
    				}
    				graph.registerNode(current);
    			}
    		}
    	}
    	return graph;
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
     * @throws IOException 
     */
    /*@TODO : Il serait bien d'ajouter un handler d'erreurs, pour que l'utilisateur sache exactement pourquoi le fichier n'est pas parsable
    * @TODO : et éventuellement une vérification des caractères de chaque ligne, parmis ceux autorisés (exceptions personnalisées -> best)*/
    public boolean  isParseable(String file) throws IOException{
        //file opening
        String line;
        StringBuilder content = new StringBuilder();
        int lineCount = 0;
        Vector<Integer> lineLengths = new Vector<Integer>();

        InputStream ips=new FileInputStream(file);
        InputStreamReader ipsr=new InputStreamReader(ips);
        @SuppressWarnings("resource")
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

    /**
     * This method checks if the field is well closed by '*'. Controls on lines length must have been done.
     * @param fileContent : the content of the file, just put into a StringBuffer (without '\n' characters)
     * @param lineLength : the length of each line of the file (so must be the same for each)
     * @param lineCount : the number of lines of the file
     * @return
     */
    // @TODO : la mise en tableau n'est pas la méthode la plus optimisée, en jouant sur les index, nous pouvons parvenir au même résultat sans utiliser de tableau (-> si temps suffisant, optimiser)
    private boolean hasEnclosure(StringBuilder fileContent, int lineLength, int lineCount){
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