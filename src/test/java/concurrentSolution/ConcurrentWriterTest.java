package concurrentSolution;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentWriterTest {

  private ConcurrentBuffer<ConcurrentCumulate> calculatorBufferTest;
  private String outputPathTest;

  private ConcurrentWriter writer1;
  private ConcurrentWriter writer2;

  @Before
  public void setUp() throws Exception {
    this.outputPathTest = "./output";
    this.writer1 = new ConcurrentWriter(calculatorBufferTest, outputPathTest);
  }

  @Test
  public void testTestEquals() {
    this.writer2 = new ConcurrentWriter(calculatorBufferTest, outputPathTest);
    Assert.assertEquals(writer1, writer2);
  }

  @Test
  public void testTestHashCode() {
    this.writer2 = new ConcurrentWriter(calculatorBufferTest, outputPathTest);
    Assert.assertEquals(writer1.hashCode(), writer2.hashCode());
  }

  @Test
  public void testTestToString() {
    Assert.assertEquals("ConcurrentWriter{calculatorBuffer=null, outputPath='./output', threshold=-1, activity=null}", writer1.toString());
  }
}
