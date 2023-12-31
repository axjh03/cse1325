import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class performs word search on a collection of puzzles using multi-threading.
 */
public class WordSearch {
    private static final String usage = "usage: java WordSearch [-h] [-v] [#threads] [#puzzles] [puzzleFile]...";

    /**
     * Constructs a WordSearch object and initializes it with command line arguments.
     *
     * @param args A list of command line arguments.
     */
    public WordSearch(List<String> args) {

        // Validate the command line arguments
        if (args.size() > 0 && args.get(0).equals("-h")) {
            System.out.println(usage);
            System.exit(0);
        }
        if (args.size() > 0 && args.get(0).equals("-v")) {
            verbose = true;
            args.remove(0);
        } else {
            verbose = false;
        }
        int threads = 0;
        try {
            threads = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch (Exception e) {
            System.err.println("Error: Invalid number of threads\n" + usage);
            System.exit(-1);
        }
        NUM_THREADS = threads;
        int numPuzzles = 0;
        try {
            numPuzzles = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch (Exception e) {
            System.err.println("Error: Invalid number of puzzles\n" + usage);
            System.exit(-1);
        }

        // Load the puzzles!
        for (String puzzleName : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(puzzleName))) {
                puzzles.add(new Puzzle(puzzleName, br));
            } catch (IOException e) {
                System.err.println("Unable to load puzzle " + puzzleName);
            }
        }

        // Verify all puzzles loaded successfully
        if (puzzles.size() != args.size())
            System.exit(-3);

        // Delete or duplicate puzzles to get the right number
        if (numPuzzles < puzzles.size()) puzzles.subList(numPuzzles, puzzles.size()).clear();
        else if (numPuzzles > puzzles.size()) {
            for (int i = puzzles.size(); i < numPuzzles; ++i)
                puzzles.add(puzzles.get(i % puzzles.size()));
        }
        NUM_PUZZLES = puzzles.size();

        // -------- All Puzzles Loaded --------
    }

    // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    // Modify THIS method to divide up the puzzles among your threads!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    /**
     * Solves word search puzzles using multi-threading.
     */
    public void solve() {
        System.err.println("\n" + NUM_PUZZLES + " puzzles with " + NUM_THREADS + " threads"); // Show the # puzzles and threads

        // Create a SortedSet to store solutions
        SortedSet<Solution> sortedSolutions = new TreeSet<>();

        // Create and start threads
        List<Thread> threads = new ArrayList<>();
        for (int threadID = 0; threadID < NUM_THREADS; ++threadID) {
            final int finalThreadID = threadID;
            final int firstPuzzle = finalThreadID * (NUM_PUZZLES / NUM_THREADS);
            final int lastPuzzlePlusOne = (finalThreadID == NUM_THREADS - 1) ? NUM_PUZZLES : (finalThreadID + 1) * (NUM_PUZZLES / NUM_THREADS);

            Thread thread = new Thread(() -> solve(finalThreadID, firstPuzzle, lastPuzzlePlusOne));
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Add all solutions to the sortedSolutions set
        sortedSolutions.addAll(solutions);

        // Print the sorted solutions
        for (Solution s : sortedSolutions) {
            System.out.println(s);
        }
    }

    /**
     * Solves word search puzzles for a specific range of puzzles.
     *
     * @param threadID          The ID of the thread performing the puzzle solving.
     * @param firstPuzzle       The index of the first puzzle to be solved.
     * @param lastPuzzlePlusOne The index of the last puzzle (exclusive) to be solved.
     */
    public void solve(int threadID, int firstPuzzle, int lastPuzzlePlusOne) {
        System.err.println("Thread " + threadID + ": " + firstPuzzle + "-" + (lastPuzzlePlusOne - 1));
        for (int i = firstPuzzle; i < lastPuzzlePlusOne; ++i) {
            Puzzle p = puzzles.get(i);
            Solver solver = new Solver(p);
            for (String word : p.getWords()) {
                try {
                    Solution s = solver.solve(word);
                    if (s == null) System.err.println("#### Failed to solve " + p.name() + " for '" + word + "'");
                    else solutions.add(s);
                } catch (Exception e) {
                    System.err.println("#### Exception solving " + p.name()
                            + " for " + word + ": " + e.getMessage());
                }
            }
        }

        // -------- All Puzzles Solved --------
    }

    /**
     * The main entry point for the WordSearch application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        WordSearch ws = new WordSearch(new LinkedList<>(Arrays.asList(args)));
        ws.solve();
    }

    public final int NUM_THREADS;
    public final int NUM_PUZZLES;
    public final boolean verbose;

    private List<Puzzle> puzzles = new ArrayList<>();
    private SortedSet<Solution> solutions = new TreeSet<>();
}
