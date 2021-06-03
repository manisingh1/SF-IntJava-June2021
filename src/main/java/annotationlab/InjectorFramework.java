package annotationlab;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class InjectorFramework {
  public static void main(String[] args) throws Throwable {
//    System.setSecurityManager(new SecurityManager());
//    System.setSecurityManager(new SecurityManager());
//    FileReader fr = new FileReader("data.txt");
    String theClass = "annotationlab.InjectMe";
    Class<?> clazz = Class.forName(theClass);
    Constructor<?> cons = clazz.getConstructor();
    Object obj = cons.newInstance();
    System.out.println(obj);

    Field[] fields = clazz.getDeclaredFields();
    for (Field f : fields) {
      System.out.println("> " + f);
      Inject theAnnotation = f.getDeclaredAnnotation(Inject.class);
      if (theAnnotation != null) {
        String value = theAnnotation.value();
        System.out.println("**** Inject found, value is " + value);
        value = "Whoot, hello " + value;
        f.setAccessible(true);
        f.set(obj, value);
      }
    }
    System.out.println(obj);
  }
}
