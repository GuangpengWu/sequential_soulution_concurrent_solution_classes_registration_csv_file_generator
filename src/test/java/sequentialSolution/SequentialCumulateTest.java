package sequentialSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * SequentialCumulateTest
 */
public class SequentialCumulateTest {

  private SequentialCumulate sequentialCumulate;
  private SequentialCumulate sequentialCumulate2;

  /**
   * initialize the sequentialCumulate
   */
  @Before
  public void setUp() {

    String moduleSession2="BBB";

    sequentialCumulate2=new SequentialCumulate(moduleSession2);

    String moduleSession="AAA";

    sequentialCumulate=new SequentialCumulate(moduleSession);
  }

  /**
   * test calculate
   */
  @Test
  public void calculate() {
    String date="2017 Mar";
    String click="1";
    sequentialCumulate.calculate(date,click);

    Assert.assertEquals(1, sequentialCumulate.getCumulate().get(date),0);

    sequentialCumulate.calculate(date,click);

    Assert.assertEquals(2, sequentialCumulate.getCumulate().get(date),0);
  }

  /**
   * test Equals
   */
  @Test
  public void testEquals() {
    Assert.assertFalse(sequentialCumulate.equals(null));
    Assert.assertTrue(sequentialCumulate.equals(sequentialCumulate));
    Assert.assertFalse(sequentialCumulate.equals(0));
    Assert.assertFalse(sequentialCumulate.equals(sequentialCumulate2));

    String date="2017 Mar";
    String click="1";
    sequentialCumulate.calculate(date,click);

    sequentialCumulate2=new SequentialCumulate("AAA");

    Assert.assertFalse(sequentialCumulate.equals(sequentialCumulate2));

    sequentialCumulate2.calculate(date,click);

    sequentialCumulate2=new SequentialCumulate("AAAA");

    Assert.assertFalse(sequentialCumulate.equals(sequentialCumulate2));
  }

  /**
   * test HashCode
   */
  @Test
  public void testHashCode() {
    Assert.assertEquals(65506,sequentialCumulate.hashCode());
  }

  /**
   * test ToString
   */
  @Test
  public void testToString() {
    Assert.assertEquals("SequentialCumulate{cumulate={}, moduleSession='AAA'}",sequentialCumulate.toString());
    Assert.assertEquals("SequentialCumulate{cumulate={}, moduleSession='BBB'}",sequentialCumulate2.toString());
  }

  /**
   * test getCumulate
   */
  @Test
  public void getCumulate() {
    String date="2017 Mar";
    String click="1";
    sequentialCumulate.calculate(date,click);

    Assert.assertEquals(1, sequentialCumulate.getCumulate().get(date),0);
  }

  /**
   * test getModuleSession
   */
  @Test
  public void getModuleSession() {
    Assert.assertEquals("AAA",sequentialCumulate.getModuleSession());
  }
}
