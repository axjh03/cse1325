package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.Duration;

/**
 * Represents a video publication.
 */
public class Video extends Publication {

  private Duration runtime;

  /**
   * Creates a new video publication with the provided title, author, copyright year, and runtime.
   *
   * @param title         The title of the video
   * @param author        The author of the video
   * @param copyrightYear The copyright year of the video
   * @param runtime       The runtime duration of the video
   */
  public Video(String title, String author, int copyrightYear, Duration runtime) {
    super(title, author, copyrightYear);
    this.runtime = runtime;
  }

  /**
   * Gets the runtime duration of this video.
   *
   * @return The runtime duration
   */
  public Duration getRuntime() {
    return runtime;
  }

  /**
   * Sets the runtime duration of this video.
   *
   * @param runtime The runtime duration to set
   */
  public void setRuntime(Duration runtime) {
    this.runtime = runtime;
  }

  /**
   * Checks if this video is currently checked out.
   *
   * @return true if checked out, false if checked in
   */
  @Override
  public boolean isCheckedOut() {
    return super.isCheckedOut();
  }

  /**
   * Checks out this video to the provided patron.
   *
   * @param patron The patron checking out this video
   */
  @Override
  public void checkOut(Patron patron) {
    super.checkOut(patron);
  }

  /**
   * Checks in this video.
   */
  @Override
  public void checkIn() {
    super.checkIn();
  }

  /**
   * Gets the string representation of this video.
   *
   * @return The string representation
   */
  @Override
  public String toString() {
    return super.toString() + " Runtime: " + runtime.toMinutes() + " minutes";
  }

  /**
   * Loads the state of this video from the provided reader.
   *
   * @param reader The reader to load data from
   */
  public Video(BufferedReader reader) throws IOException {
    super(reader);
    long minutes = Long.parseLong(reader.readLine());
    runtime = Duration.ofMinutes(minutes);
  }

  /**
   * Saves the state of this video to the provided writer.
   *
   * @param writer The writer to save data to
   */
  @Override
  public void save(BufferedWriter writer) throws IOException {
    super.save(writer);
    writer.write("" + runtime.toMinutes() + "\n");
  }
}
