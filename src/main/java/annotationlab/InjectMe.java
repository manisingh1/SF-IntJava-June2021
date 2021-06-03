package annotationlab;

public class InjectMe {
  private String s = "Boring";
  @Inject("Albert")
  private String a;
  @Inject("Alice")
  private String a2;

  @Override
  public String toString() {
    return "InjectMe{" +
        "s='" + s + '\'' +
        ", a='" + a + '\'' +
        ", a2='" + a2 + '\'' +
        '}';
  }
}
