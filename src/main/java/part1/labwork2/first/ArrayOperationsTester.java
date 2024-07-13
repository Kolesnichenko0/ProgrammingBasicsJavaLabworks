package part1.labwork2.first;

/**
 * The {@code ArrayOperationsTester} class is used to test
 * the functionality of the {@link IndividualArrayOperations} class.
 */
class ArrayOperationsTester {
    /** Test array to be used for testing the array operations */
    static final int[][] testArray = new int[][]{
            {24, 8, 6, 8},
            {22, 14, 10, 28},
            {4, 6, 12, 20},
            {20, 8, 8, 24},
            {26, 30, 12, 12}
    };

    /**
     * It calls static methods of the {@link IndividualArrayOperations} class
     * showing traditional and functional approaches.
     * {@code args} are not used.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Using a traditional approach:");
        IndividualArrayOperations.useTraditionalApproach();
        System.out.println("\nUsing a functional approach:");
        IndividualArrayOperations.useFunctionalApproach();
    }
}
