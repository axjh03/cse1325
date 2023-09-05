
public enum Color{    
    TANGERINE(0xF28500), 
    MAGENTA(0xFF00FF), 
    CYAN(0x00FFFF), 
    VERMILION(0xFF5349);

    private int rgb;

    private Color(int rgb){
        this.rgb = rgb;
    }

     
    @Override
    public String toString(){
    
      //String hexRGB = "(" + "0x" + Integer.toHexString(rgb).toUpperCase() + ")";
      
      String HexString = String.format("(0x%06X)", rgb);
      String ColorizedString = Colorize.colorizer(name(), HexString);
      return ColorizedString;
    }
}