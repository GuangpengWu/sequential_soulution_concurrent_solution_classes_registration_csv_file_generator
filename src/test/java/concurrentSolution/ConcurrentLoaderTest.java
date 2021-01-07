package concurrentSolution;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConcurrentLoaderTest {

  private static final String REAL_PATH="/studentVle.csv";
  private static final String SAMPLE_PATH="/sampleStudentVle.csv";
  private ConcurrentBuffer<ConcurrentVleContent> buffer1;
  private ConcurrentBuffer<ConcurrentVleContent> buffer2;
  private ConcurrentLoader loader1;
  private ConcurrentLoader loader2;
  private ConcurrentLoader loader3;

  @Before
  public void setUp() throws IOException, InvalidFileException {
    this.buffer1 = new ConcurrentBuffer<>();
    this.loader1 = new ConcurrentLoader(buffer1, REAL_PATH, "test_data");

    this.buffer2 = new ConcurrentBuffer<>();
    this.loader2 = new ConcurrentLoader(buffer2, SAMPLE_PATH, "test_data");

    this.loader3 = new ConcurrentLoader(buffer1, REAL_PATH, "test_data");

  }

  @Test
  public void equalsTest(){
    Assert.assertNotEquals(loader1, loader2);
    Assert.assertEquals(loader1, loader3);
  }

  @Test
  public void hashTest(){
    Assert.assertNotEquals(this.loader2.hashCode(), this.loader1.hashCode());
  }

  @Test
  public void toStringTest(){
    System.out.println(this.loader1.toString());
    Assert.assertEquals("ConcurrentLoader{" +
        "buffer=" + "ConcurrentBuffer{" +
        "BUFFER_SIZE=" + "500" +
        ", itemCount=" + "0" +
        ", contents=" + "[]" +
        '}' +
        ", titles=" + "[code_module, code_presentation, id_student, id_site, date, sum_click]" +
        ", file=" + "test_data/studentVle.csv" +
        ", filename='" + "/studentVle.csv" + '\'' +
       '}', this.loader1.toString());
  }

}
