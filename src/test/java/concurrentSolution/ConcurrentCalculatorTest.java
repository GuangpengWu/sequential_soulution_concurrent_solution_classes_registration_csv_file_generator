package concurrentSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentCalculatorTest {

  private ConcurrentBuffer<ConcurrentCumulate> calculatorBufferTest;
  private ConcurrentBuffer<ConcurrentVleContent> recordBufferTest;
  private ConcurrentCalculator calculatorTest;
  private ConcurrentCalculator calculatorTest2;

  @Before
  public void setUp() {
    this.calculatorBufferTest = new ConcurrentBuffer<>();
    this.recordBufferTest = new ConcurrentBuffer<>();
    calculatorTest = new ConcurrentCalculator(calculatorBufferTest, recordBufferTest);
    calculatorTest2 = new ConcurrentCalculator(calculatorBufferTest, recordBufferTest);
  }


  @Test
  public void testTestEquals() {
    Assert.assertEquals(calculatorTest, calculatorTest2);
  }

  @Test
  public void testTestHashCode() {
    Assert.assertEquals(calculatorTest.hashCode(), calculatorTest2.hashCode());
  }

  @Test
  public void testTestToString() {
    Assert.assertEquals("ConcurrentCalculator{calculatorBuffer=ConcurrentBuffer{BUFFER_SIZE=500, itemCount=0, contents=[]}, recordBuffer=ConcurrentBuffer{BUFFER_SIZE=500, itemCount=0, contents=[]}, moduleSessions=[]}", calculatorTest.toString());
  }
}
