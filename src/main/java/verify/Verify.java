package verify;

public record Verify(int x, String y) {
  public static void main(String[] args) {
    Object v = new Verify(10, "Hello");
    System.out.println(v instanceof Verify t && t.x == 10);
  }
}
