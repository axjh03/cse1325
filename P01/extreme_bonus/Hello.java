import java.lang.System;

public class Hello {

  public static void main(String[] args) {

    String user = System.getProperty("user.name");
    
    System.out.println("hello, " + user + "!");

  }

}
