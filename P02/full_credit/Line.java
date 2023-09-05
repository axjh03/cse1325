public class Line{

    // Fields
    private double x1, y1, x2, y2;
    private Color color;

    // Constructor
    public Line(double x1, double y1, double x2, double y2, Color color){ 
        this.x1 = x1; 
        this.y1 = y1; 
        this.x2 = x2; 
        this.y2 = y2;
        this.color = color;
        }

    // Method length()
    public double length(){ 
            return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

    // Method toString()
    public String toString(){ 
        // Format : color (x1, y1) - (x2,y2)
        // return color+" ("+x1+","+y1+") - ("+x2+","+y2+")";
        return String.format("%9s (%.3f,%.3f)-(%.3f,%.3f)", color, x1, y1, x2, y2);
    }
}