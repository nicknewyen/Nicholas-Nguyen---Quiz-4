/* Name: Nicholas Nguyen
 * Date: 10/20/2020
 * Class: CS 3310.02
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BuildOrder {

	// Search through all unmarked nodes accessible from projects
	public static void visit(int projects, int[][] processes, Set<Integer> tempM, Set<Integer> permM,
			List<Integer> dependencies) {
		// Throw an error if we find a cycle
		if (tempM.contains(projects))
			throw new RuntimeException("Graph is not acyclic");

		// If we haven't visited the node, recursively search from there
		if (!permM.contains(projects)) {
			tempM.add(projects);

			// Perform recursive search from children
			for (int i : processes[projects]) {
				visit(i, processes, tempM, permM, dependencies);
			}

			// Add permanent mark, remove temporary mark, and add to results list
			permM.add(projects);
			tempM.remove(projects);
			dependencies.add(projects);
		}
	}

	// Helper method for tests. Checks if lists have equal values
	private static boolean compareResults(List<Integer> a, int[] b) {
		if (a.size() != b.length)
			return false;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) != b[i])
				return false;
		}
		return true;
	}

	// Perform topological sort
	// Input is a list of dependencies where the index is the projects number
	// and the value is the numbers the processes it depends on
	public static List<Integer> buildOrder(int[][] processes) {
		Set<Integer> tempM = new HashSet<Integer>();
		Set<Integer> permM = new HashSet<Integer>();
		List<Integer> dependencies = new LinkedList<Integer>();

		// Recursively search from any unmarked node
		for (int i = 0; i < processes.length; i++) {
			if (!permM.contains(i)) {
				visit(i, processes, tempM, permM, dependencies);
			}
		}
		return dependencies;
	}

	// Sample test cases
	public static void main(String[] args) {
		assert compareResults(buildOrder(new int[][] { {}, { 0 }, { 1 }, { 2 }, { 3 } }), new int[] { 0, 1, 2, 3, 4 })
				: "Simple sorted order";
		assert compareResults(buildOrder(new int[][] { {}, { 0 }, { 0 }, { 1, 2 }, { 1, 2, 3 } }),
				new int[] { 0, 1, 2, 3, 4 }) : "Complex sorted order";
		assert compareResults(buildOrder(new int[][] { { 3 }, { 0 }, { 4 }, {}, { 1 } }), new int[] { 3, 0, 1, 4, 2 })
				: "Simple unsorted order";
		assert compareResults(buildOrder(new int[][] { { 3 }, { 0, 3 }, { 0, 1, 3 }, {}, { 1, 2, 3 } }),
				new int[] { 3, 0, 1, 2, 4 }) : "Complex unsorted order";
		try {
			buildOrder(new int[][] { { 1 }, { 0 } });
			assert false : "Throw error on cycle";
		} catch (Exception e) {
		}
		System.out.println("No errors - Successful Test Casese");
	}
}

/*
 * Time Complexity: O(p + d), where p is projects and d is dependencies
 * The node are built at runtime and are immediately built
 * 
 * Add nodes with no edges
 * Remove all edges from the roots
 * Find nodes that have no incoming edges
 * If nodes are remaining, and all nodes have dependencies then return error
*/
