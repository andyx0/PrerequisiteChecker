package prereqchecker;

import java.util.*;

/**
 * AdjListInputFile name is passed through the command line as args[0]
 * AdjListInputFile is read with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * EligibleInputFile name is passed through the command line as args[1]
 * EligibleInputFile is read with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * EligibleOutputFile name is passed through the command line as args[2]
 * EligibleOutputFile has the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Execute: Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
        Digraph g = new Digraph();
        g.createAdjList(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        HashSet<String> completedCourses = g.completedCourses();
        for (String v : g.eligible(completedCourses)) {
            StdOut.println(v);
        }
    }
}
