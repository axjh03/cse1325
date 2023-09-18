package library;
import java.time.Duration;

public class Video extends Publication {

    private Duration runtime;

    public Video(String title, String author, int copyright, minutes runtime){
        super(title, author, copyright);
        this.runtime = runtime;
    }

    //toString
    @Override
    public String toString(){
        return "Video: " + super.toString() + " " + runtime;
    }
    
}