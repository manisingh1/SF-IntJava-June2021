package annotations;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

//@RunThis(name="Fred")
public class UnitUnderTest {
//  @RunThis(name="Fred")
  String x = "Odd";

  @RunThis(100)
  public void itShouldWork() {
    System.out.println("itShouldWork is running");
  }
//  @RunThis(name="Jim", value=10, s=@Strange("Weird"))
  @RunThis(name="Jim", value=10)
  public void itShouldDoGoodStuff() {
    System.out.println("itShouldDoGoodStuff is running");
  }
  public void notATest() {
//    int goto;
    System.out.println("notATest is running");
    var x = 99;
    int var = 100;
//    Map<String, List<Map.Entry<ZonedDateTime, List<String>>>> m;
    var m = getAMap();
  }

  public Map<String, List<Map.Entry<ZonedDateTime, List<String>>>>
  getAMap() {return null;}
}
