package annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyJUnit {
  public static void main(String[] args) throws Throwable {
    String classToTest = "annotations.UnitUnderTest";
    Class<?> clazz = Class.forName(classToTest);
    Constructor<?> cons = clazz.getConstructor(/* arg TYPE sequence goes here*/); // zero arg constructor :)
    Object obj = cons.newInstance();

    Method [] methods = clazz.getMethods();
    for (Method m : methods) {
      System.out.println("> " + m);
      RunThis theAnnotation = m.getAnnotation(RunThis.class);
      if (theAnnotation != null) {
        System.out.println("**** Found RunThis, name = " + theAnnotation.name());
        m.invoke(obj);
      }
    }
  }
}
