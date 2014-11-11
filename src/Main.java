import dijkstra.parser.FileParser;

public class Main {

    public static void main(String[] args) {
        String testfile = "test.txt";

        System.out.println(FileParser.isParseable(testfile));
    }
}
