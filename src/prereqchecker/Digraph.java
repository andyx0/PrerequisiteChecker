package prereqchecker;

import java.util.*;

/**
 * The {@code Digraph} class represents a directed graph of string vertices.
 * An edge from vertex v to vertex w indicates that w is a prerequisite of v.
 */

public class Digraph {

    private Map<String, List<String>> map = new HashMap<>();

    /**
     * Creates the adjacency list for the graph.
     * 
     * @param input the filename of the AdjListInputFile
     */
    public void createAdjList(String input) {
        StdIn.setFile(input);
        int numCourses = StdIn.readInt();
        StdIn.readLine();
        for (int i = 0; i < numCourses; i++) {
            addVertex(StdIn.readLine());
        }

        int numPrereqs = StdIn.readInt();
        StdIn.readLine();
        for (int i = 0; i < numPrereqs; i++) {
            String vertex = StdIn.readString();
            addEdge(vertex, StdIn.readString());
        }
    }

    /**
     * Determines if possiblePrereq is a valid prereq for course.
     * 
     * @param course         the course to have a prereq added
     * @param possiblePrereq the course to be added as a prereq
     * @return whether all courses are still possible to take if
     *         {@code possiblePrereq} were a prereq for {@code course}
     */
    public boolean isValidPrereq(String course, String possiblePrereq) {
        DepthFirstSearch dfs = new DepthFirstSearch(this, possiblePrereq);
        return !dfs.marked(course);
    }

    /**
     * Calculates eligible courses.
     * 
     * @param completedCourses the courses already taken
     * @return the courses eligible to take that have not been taken yet
     */
    public HashSet<String> eligible(HashSet<String> completedCourses) {
        HashSet<String> eligibleCourses = new HashSet<String>();
        for (String v : map.keySet()) {
            if (!completedCourses.contains(v) && completedCourses.containsAll(adj(v))) {
                eligibleCourses.add(v);
            }
        }
        return eligibleCourses;
    }

    /**
     * Calculates the courses to take in order to be eligible to take the target
     * course.
     * 
     * @param completedCourses the courses already taken
     * @param targetCourse     the target course to take
     * @return the courses required to take before taking the target course
     */
    public HashSet<String> needToTake(HashSet<String> completedCourses, String targetCourse) {
        HashSet<String> needToTake = new HashSet<String>();
        DepthFirstSearch targetPrereqs = new DepthFirstSearch(this, targetCourse);
        for (String v : targetPrereqs.getMarked()) {
            if (!completedCourses.contains(v) && !targetCourse.equals(v)) {
                needToTake.add(v);
            }
        }
        return needToTake;
    }

    /**
     * Plans a schedule to become eligible to take the target course in the fewest
     * possible number of semesters.
     * 
     * @param completedCourses the courses already taken
     * @param targetCourse     the target course to take
     * @return an ArrayList of string HashSets, where each HashSet represents a
     *         semester and the strings in each HashSet are the courses for that
     *         semester
     */
    public ArrayList<HashSet<String>> schedulePlan(HashSet<String> completedCourses, String targetCourse) {
        ArrayList<HashSet<String>> plan = new ArrayList<HashSet<String>>();
        HashSet<String> eligible;
        HashSet<String> needToTake = needToTake(completedCourses, targetCourse);
        while (!needToTake.isEmpty()) {
            eligible = eligible(completedCourses);
            HashSet<String> sem = new HashSet<String>();
            for (String course : eligible) {
                if (needToTake.contains(course)) {
                    sem.add(course);
                    needToTake.remove(course);
                }
            }
            completedCourses.addAll(sem);
            plan.add(sem);
        }
        return plan;
    }

    // Returns a HashSet of all completed courses
    public HashSet<String> completedCourses() {
        HashSet<String> completedCourses = new HashSet<String>();
        int numTaken = StdIn.readInt();
        StdIn.readLine();
        for (int i = 0; i < numTaken; i++) {
            String course = StdIn.readLine();
            completedCourses.add(course);
            DepthFirstSearch dfs = new DepthFirstSearch(this, course);
            completedCourses.addAll(dfs.getMarked());
        }
        return completedCourses;
    }

    public void addVertex(String s) {
        map.put(s, new LinkedList<String>());
    }

    /**
     * Adds the directed edge source → destination to this digraph.
     *
     * @param source      the tail vertex
     * @param destination the head vertex
     */
    public void addEdge(String source, String destination) {
        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(destination);
    }

    /**
     * Returns the vertices adjacent to vertex {@code source} in this digraph.
     *
     * @param source the vertex
     * @return the vertices adjacent to vertex {@code source}
     */
    public List<String> adj(String source) {
        return map.get(source);
    }

    // Returns the adjacency list of each vertex as a string.
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (String v : map.keySet()) {
            builder.append(v.toString() + " ");
            for (String w : map.get(v)) {
                builder.append(w.toString() + " ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}