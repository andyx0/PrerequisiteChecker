package prereqchecker;

/**
 * AdjListInputFile name is passed through the command line as args[0]
 * AdjListInputFile is read with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * ValidPreReqInputFile is read with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * ValidPreReqOutputFile has the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq {
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Execute: java ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        Digraph g = new Digraph();
        g.createAdjList(args[0]);
        StdIn.setFile(args[1]);
        String course1 = StdIn.readLine();
        String course2 = StdIn.readLine();
        StdOut.setFile(args[2]);
        if (g.isValidPrereq(course1, course2)) {
            StdOut.print("YES");
        } else {
            StdOut.print("NO");
        }
    }
}
