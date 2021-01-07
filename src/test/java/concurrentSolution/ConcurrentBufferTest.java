package concurrentSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentBufferTest {

  private String contents;
  private ConcurrentBuffer<String> testBuffer;
  private ConcurrentBuffer<String> testBuffer2;

  @Before
  public void setUp(){
    this.contents = "a";
    this.testBuffer = new ConcurrentBuffer<>();
    this.testBuffer2 = new ConcurrentBuffer<>();
    testBuffer.putIntoBuffer(this.contents);
    testBuffer2.putIntoBuffer(this.contents);
  }

  @Test
  public void putInputBufferTest(){
    testBuffer.putIntoBuffer("b");
    Assert.assertEquals(2, this.testBuffer.getItemCount());
  }


  @Test
  public void getFromBufferTest() {
    String itemTest = testBuffer.getFromBuffer();
    Assert.assertEquals("a",itemTest);
  }

  @Test
  public void ContainsItemTest(){
    Assert.assertEquals(true, this.testBuffer.contains("a"));
  }

  @Test
  public void getBufferSizeTest(){
    Assert.assertEquals(500, this.testBuffer.getBUFFER_SIZE());
  }

  @Test
  public void getItemCountTest(){
    Assert.assertEquals(1,this.testBuffer.getItemCount());
  }

  @Test
  public void getContentsTest(){
    Assert.assertEquals("[a]", this.testBuffer.getContents().toString());
  }

  @Test
  public void equalsTest(){
    Assert.assertEquals(testBuffer,testBuffer2);
  }

  @Test
  public void testTestHashCode() {
    Assert.assertEquals(testBuffer.hashCode(),testBuffer2.hashCode());
  }

  @Test
  public void testTestToString() {
    Assert.assertEquals("ConcurrentBuffer{" +
        "BUFFER_SIZE=" + 500 +
        ", itemCount=" + 1 +
        ", contents=" + "[a]" +
        '}', testBuffer.toString());
  }
}
