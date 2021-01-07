package concurrentSolution;

import java.util.HashMap;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentCumulateTest {

  private String moduleSession;
  private ConcurrentCumulate cumulateTest;
  private ConcurrentCumulate cumulateTest2;

  @Before
  public void setUp() {
    this.moduleSession = "aaa";
    cumulateTest = new ConcurrentCumulate(moduleSession);
    cumulateTest2 = new ConcurrentCumulate(moduleSession);
  }

  @Test
  public void testCalculate() {
    cumulateTest.calculate("day1","10");
    Assert.assertEquals("{day1=10}", cumulateTest.getCumulate().toString());
  }

  @Test
  public void testTestEquals() {
    Assert.assertEquals(cumulateTest2, cumulateTest);
  }

  @Test
  public void testTestHashCode() {
    Assert.assertEquals(cumulateTest2.hashCode(), cumulateTest.hashCode());
  }

  @Test
  public void testTestToString() {
    Assert.assertEquals("ConcurrentCumulate{moduleSession=aaa, cumulate='{}'}",cumulateTest.toString());
  }

  @Test
  public void testGetCumulate() {
    Assert.assertEquals("{}",cumulateTest.getCumulate().toString());
  }

  @Test
  public void testGetModuleSession() {
    Assert.assertEquals("aaa", cumulateTest.getModuleSession());
  }
}
