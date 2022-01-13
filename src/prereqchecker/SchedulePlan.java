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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * SchedulePlanInputFile is read with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * SchedulePlanOutputFile has the format:
 * 1. One line containing an int c, the number of semesters required to take the
 * course
 * 2. c lines, each with up to 3 space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Execute: java SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }
        Digraph g = new Digraph();
        g.createAdjList(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        String targetCourse = StdIn.readLine();
        HashSet<String> completedCourses = g.completedCourses();
        ArrayList<HashSet<String>> schedulePlan = g.schedulePlan(completedCourses, targetCourse);
        StdOut.println(schedulePlan.size());
        for (HashSet<String> sem : schedulePlan) {
            for (String v : sem) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
