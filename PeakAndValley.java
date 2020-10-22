/* Name: Nicholas Nguyen
 * Date: 10/20/2020
 * Class: CS 3310.02
 */

public class PeakAndValley {

	private static void sortPV(int[] array) {
		// For loop - iterate by 2
		for (int i = 1; i < array.length; i += 2) {
			if (array[i - 1] < array[i]) {
				// Check if array index - 1 is larger than current index, then swap
				swap(array, i - 1, i);
			}
			// Check if index + 1 is less than current index, then swap
			if (i + 1 < array.length && array[i + 1] < array[i]) {
				swap(array, i + 1, i);
			}
		}
		// Print array
		printArray(array);
	}

	// Swap index of values
	private static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	private static void printArray(int arr[]) {
		int rowCount = 0; // Iterator to keep track of when to create new row
		int n = arr.length;
		for (int i = 0; i < n; ++i) {
			// If the current element is a multiple of 10, create a new row
			if (rowCount >= 10) {
				System.out.println();
				rowCount = 0; // Reset the rowCount iterator
			}
			System.out.printf("%3d" + " ", arr[i]); // Print out element in the array with 3 digit wide format
			rowCount++; // Iterate rowCount
		}
		System.out.println(); // Print out empty line
	} // end printArray

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 5, 3, 1, 2, 3 };
		sortPV(array);
	}
}

// Time Complexity: O(n)
// Space Complexity: O(1)
