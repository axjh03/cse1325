package library;
import java.time.Year;

import java.time.Duration;

/**
 * Represents a video publication in the library. 
 */
public class Video extends Publication {

    private Duration runtime;

    // Nested exception class

    /**
     * Custom exception class for invalid runtime.
     */
    public static class InvalidRuntimeException extends ArithmeticException {

        /**
         * Constructs an instance of InvalidRuntimeException with no detail message.
         */ 
        public InvalidRuntimeException() {
            super();
        }

        /**
         * Constructs an instance of InvalidRuntimeException with the specified detail message.
         *  
         * @param message The detail message
         */
        public InvalidRuntimeException(String message) {
            super(message); 
        }

        /**
         * Constructs an instance of InvalidRuntimeException with a specific error
         * message.
         *
         * @param title   The title of the video.
         * @param runtime The runtime of the video in minutes.
         */
        public InvalidRuntimeException(String title, int runtime) {
            super(title + " has an invalid runtime of " + runtime + " minutes.");
        }
        
    }

    /**
     * Creates a new Video publication.
     *  
     * @param title     The title of the video
     * @param runtime   The runtime of the video in minutes
     * @param author    The author of the video
     * @param copyright The Copyright year of the video
     */
    public Video(String title, String author, int copyright, int runtime) {
        super(title, author, copyright);
        int currentYear = Year.now().getValue();

        if (runtime <= 0) {
            throw new InvalidRuntimeException(title, runtime);
        }

        if (copyright > currentYear || copyright<1900 ) {
            String message = "The year of the Video is "+copyright+" which is more that current year which is "+currentYear+" and hence it's invalid";
            throw new InvalidRuntimeException(message);
        }        

        this.runtime = Duration.ofMinutes(runtime);
    }

    /**
     * Generates a string representation of the video.
     *
     * @return The generated string
     */ 
    @Override
    public String toString() {
        return super.toStringBuilder("Video", ", runtime " + runtime.toMinutes() + " minutes");
    }
}