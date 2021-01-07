package concurrentSolution;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentVleContentTest {

  private ConcurrentVleContent contentTest;
  private ConcurrentVleContent contentTest2;

  @Before
  public void setUp(){
    HashMap<String, String> map = new HashMap<>();
    map.put("KeySet","ValueSet");
    map.put("code_module","aaa");
    map.put("code_presentation","bbb");
    contentTest = new ConcurrentVleContent(map);
  }

  @Test
  public void testTestToString() {
    Assert.assertEquals("ConcurrentVleContent{content={KeySet=ValueSet, code_module=aaa, code_presentation=bbb}}", this.contentTest.toString());
  }

  @Test
  public void testGetContent() {
    HashMap<String, String> map2 = new HashMap<>();
    map2.put("KeySet","ValueSet");
    map2.put("code_module","aaa");
    map2.put("code_presentation","bbb");
    Assert.assertEquals(map2, this.contentTest.getContent());
  }

  @Test
  public void testGetModuleSession() {
    Assert.assertEquals("aaa_bbb",this.contentTest.getModuleSession());
  }

  @Test
  public void testTestEquals() {
    HashMap<String, String> map2 = new HashMap<>();
    map2.put("KeySet","ValueSet");
    map2.put("code_module","aaa");
    map2.put("code_presentation","bbb");
    contentTest2 = new ConcurrentVleContent(map2);
    Assert.assertEquals(contentTest, contentTest2);
  }

  @Test
  public void testTestHashCode() {
    HashMap<String, String> map2 = new HashMap<>();
    map2.put("KeySet","ValueSet");
    map2.put("code_module","aaa");
    map2.put("code_presentation","bbb");
    contentTest2 = new ConcurrentVleContent(map2);

    Assert.assertEquals(contentTest.hashCode(),contentTest2.hashCode());
  }
}
