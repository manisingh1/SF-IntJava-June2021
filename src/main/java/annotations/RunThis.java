package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Target({ElementType.ANNOTATION_TYPE})
@interface Strange {
  String value();
}

//@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@Strange
public @interface RunThis {
  String name() default "unnamed";
  int value();
//  Strange s();
  /*primitives, Class, String, Annotation, arrays of the preceding (i.e. single dimensional)*/
//  Class<?> when();
}
