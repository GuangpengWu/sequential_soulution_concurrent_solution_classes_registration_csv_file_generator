package sequentialSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * SequentialVleFileContainerTest
 */
public class SequentialVleFileContainerTest {

  private SequentialContainer sequentialVleFileContainer1;
  private SequentialContainer sequentialVleFileContainer2;

  private SequentialVleFileContent sequentialVleFileContent1;

  /**
   * initialize the sequentialVleFileContent
   */
  @Before
  public void setUp() {
    HashMap<String,String> hashMap=new HashMap<>();
    hashMap.put("code_module","value1");
    hashMap.put("code_presentation","value2");
    hashMap.put("date","value3");
    hashMap.put("sum_click","4");
    sequentialVleFileContent1=new SequentialVleFileContent(hashMap);

    sequentialVleFileContainer1=new SequentialVleFileContainer();
    sequentialVleFileContainer2=new SequentialVleFileContainer();
  }

  /**
   * test addContent
   */
  @Test
  public void addContent() {
    sequentialVleFileContainer1.addContent(sequentialVleFileContent1);
    Assert.assertEquals("SequentialFileContainer = Container={ SequentialFileContent = {date: value3,\n"
        + "sum_click: 4,\n"
        + "code_module: value1,\n"
        + "code_presentation: value2}",this.sequentialVleFileContainer1.toString());
  }

  /**
   * test Equals
   */
  @Test
  public void testEquals() {
    sequentialVleFileContainer1.addContent(sequentialVleFileContent1);
    sequentialVleFileContainer2.addContent(sequentialVleFileContent1);
    Assert.assertEquals(sequentialVleFileContainer1, sequentialVleFileContainer2);
  }

  /**
   * test HashCode
   */
  @Test
  public void testHashCode() {
    sequentialVleFileContainer1.addContent(sequentialVleFileContent1);
    sequentialVleFileContainer2.addContent(sequentialVleFileContent1);
    Assert.assertEquals(sequentialVleFileContainer1.hashCode(),sequentialVleFileContainer2.hashCode());
  }

  /**
   * test ToString
   */
  @Test
  public void testToString() {
    sequentialVleFileContainer1.addContent(sequentialVleFileContent1);
    Assert.assertEquals("SequentialFileContainer = Container={ SequentialFileContent = {date: value3,\n"
        + "sum_click: 4,\n"
        + "code_module: value1,\n"
        + "code_presentation: value2}",sequentialVleFileContainer1.toString());
  }

  /**
   * test getContainer
   */
  @Test
  public void getContainerTest() {
    sequentialVleFileContainer1.addContent(sequentialVleFileContent1);
    Assert.assertEquals("[SequentialFileContent = {date: value3,\n"
        + "sum_click: 4,\n"
        + "code_module: value1,\n"
        + "code_presentation: value2]", sequentialVleFileContainer1.getContainer().toString());
  }

  /**
   * test flash
   */
  @Test
  public void flashTest() {
    sequentialVleFileContainer1.addContent(sequentialVleFileContent1);
    sequentialVleFileContainer1.flash();
    Assert.assertEquals(0,sequentialVleFileContainer1.getContainer().size());
  }
}
