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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * NeedToTakeInputFile is read with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * NeedToTakeOutputFile has the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }
        Digraph g = new Digraph();
        g.createAdjList(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        String targetCourse = StdIn.readLine();
        HashSet<String> completedCourses = g.completedCourses();
        for (String v : g.needToTake(completedCourses, targetCourse)) {
            StdOut.println(v);
        }
    }
}
