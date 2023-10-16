import java.util.*;
import java.io.*;

public class WordSearch {
    private static final String usage = "usage: java WordSearch [-h] [-v] [#threads] [#puzzles] [puzzleFile]...";

    public WordSearch(List<String> args) {
        // Validate and process the command line arguments.
        if (args.size() > 0 && args.get(0).equals("-h")) {
            // Print usage message and exit.
            System.out.println(usage);
            System.exit(0);
        }
        if (args.size() > 0 && args.get(0).equals("-v")) {
            // Enable verbose mode.
            verbose = true;
            args.remove(0);
        } else {
            verbose = false;
        }

        int threads = 0;
        try {
            // Parse the number of threads.
            threads = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch (Exception e) {
            System.err.println("Error: Invalid number of threads\n" + usage);
            System.exit(-1);
        }
        NUM_THREADS = threads;

        int numPuzzles = 0;
        try {
            // Parse the number of puzzles.
            numPuzzles = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch (Exception e) {
            System.err.println("Error: Invalid number of puzzles\n" + usage);
            System.exit(-1);
        }

        // Load the puzzles from puzzle files.
        for (String puzzleName : args) {
            try (BufferedReader br = new BufferedReader(new FileReader(puzzleName))) {
                puzzles.add(new Puzzle(puzzleName, br));
            } catch (IOException e) {
                System.err.println("Unable to load puzzle " + puzzleName);
            }
        }

        // Verify that all puzzles loaded successfully.
        if (puzzles.size() != args.size())
            System.exit(-3);

        // Adjust the number of puzzles by deleting or duplicating them.
        if (numPuzzles < puzzles.size())
            puzzles.subList(numPuzzles, puzzles.size()).clear();
        else if (numPuzzles > puzzles.size()) {
            for (int i = puzzles.size(); i < numPuzzles; ++i)
                puzzles.add(puzzles.get(i % puzzles.size()));
        }
        NUM_PUZZLES = puzzles.size();
    }

    public void solve() {
        System.err.println(NUM_PUZZLES + " puzzles with " + NUM_THREADS + " threads");
    
        for (int threadID = 0; threadID < NUM_THREADS; ++threadID) {
            int puzzlesPerThread = NUM_PUZZLES / NUM_THREADS;
            int firstPuzzle = threadID * puzzlesPerThread;
            int lastPuzzlePlusOne = (threadID == NUM_THREADS - 1) ? NUM_PUZZLES : (threadID + 1) * puzzlesPerThread;
    
            final int finalThreadID = threadID;  // Declare threadID as final
    
            // Create a new thread to solve a subset of puzzles.
            Thread thread = new Thread(() -> solve(finalThreadID, firstPuzzle, lastPuzzlePlusOne));
            thread.start();
        }
    
        // Wait for all threads to finish.
        for (int threadID = 0; threadID < NUM_THREADS; ++threadID) {
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

    public void solve(int threadID, int firstPuzzle, int lastPuzzlePlusOne) {
        // Thread-specific solving logic.
        System.err.println("Thread " + threadID + ": " + firstPuzzle + "-" + (lastPuzzlePlusOne - 1));

        for (int i = firstPuzzle; i < lastPuzzlePlusOne; ++i) {
            Puzzle p = puzzles.get(i);
            Solver solver = new Solver(p);
            
            for (String word : p.getWords()) {
                try {
                    Solution s = solver.solve(word);

                    if (s == null) {
                        System.err.println("#### Failed to solve " + p.name() + " for '" + word + "'");
                    } else {
                        // Add the solution to the collection (solutions).
                        synchronized (solutions) {
                            solutions.add(s);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("#### Exception solving " + p.name() + " for " + word + ": " + e.getMessage());
                }
            }
        }

        // All puzzles for this thread have been solved.
    }

    public void printSolutions() {
        // Sort the solutions using a TreeSet.
        SortedSet<Solution> sortedSolutions = new TreeSet<>(solutions);

        // Print the sorted solutions.
        for (Solution s : sortedSolutions) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        // Create a WordSearch object and start solving puzzles.
        WordSearch ws = new WordSearch(new LinkedList<>(Arrays.asList(args)));
        ws.solve();

        // Print solutions if verbose mode is enabled.
        if (ws.verbose)
            ws.printSolutions();
    }

    public final int NUM_THREADS;
    public final int NUM_PUZZLES;
    public final boolean verbose;

    private List<Puzzle> puzzles = new ArrayList<>();
    private List<Solution> solutions = new ArrayList<>();

}
