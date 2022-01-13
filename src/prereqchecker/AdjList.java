package prereqchecker;

/**
 * AdjListInputFile name is passed through the command line as args[0]
 * AdjListInputFile is read with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * AdjListOutputFile name is passed through the command line as args[1]
 * AdjListOutputFile has the format:
 * 1. c lines, each starting with a different course ID, then
 * listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {
        if (args.length < 2) {
            StdOut.println("Execute: java AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
        Digraph g = new Digraph();
        g.createAdjList(args[0]);
        StdOut.setFile(args[1]);
        StdOut.print(g.toString());
    }
}
