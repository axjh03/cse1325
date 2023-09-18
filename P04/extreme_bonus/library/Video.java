package library;

import java.time.Duration;

public class Video extends Publication {

    private int runtime;

    // Inner class InvalidRuntimeException nested in Video
    public static class InvalidRuntimeException extends ArithmeticException {
        public InvalidRuntimeException(String title, int runtime) {
            super(title + " has an invalid runtime of " + runtime + " minutes.");
        }
    }

    public Video(String title, String author, int copyright, int runtime) {
        super(title, author, copyright);

        if (runtime <= 0) {
            throw new InvalidRuntimeException(title, runtime);
        }

        this.runtime = runtime;
    }

    @Override
    public String toString() {
        return super.toStringBuilder("Video", ", runtime " + runtime);
    }
}
