package reflection.Switch;
import LoggerLib.Logger;
import java.lang.reflect.*;

public class Switch {
 static int i;

  public static void main() throws ClassNotFoundException {
        String string = "com.xlnt.java.awt.";
        switch(i) {
                case 0:
                        string = string+"Frame";
                        break;
                case 1:
                        string = string+"Window";
                        break;
                default:
                        string = string+"Container";
                        break;
        }
        Class.forName(string);
			Logger.reportString(string,"reflection.Switch.Switch19");
  }
}
