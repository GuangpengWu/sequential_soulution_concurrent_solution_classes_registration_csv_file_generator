package sequentialSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * SequentialVleFileContentTest
 */
public class SequentialVleFileContentTest {

  private SequentialVleFileContent sequentialVleFileContent;

  /**
   * initialize the sequentialVleFileContent
   * @throws Exception when invalid file
   */
  @Before
  public void setUp() throws Exception {
    HashMap<String,String> hashMap=new HashMap<>();
    hashMap.put("code_module","value1");
    hashMap.put("code_presentation","value2");
    hashMap.put("date","value3");
    hashMap.put("sum_click","value4");
    sequentialVleFileContent=new SequentialVleFileContent(hashMap);
  }

  /**
   * test ToString
   */
  @Test
  public void testToString() {
    Assert.assertEquals("SequentialFileContent = {date: value3,\n" +
        "sum_click: value4,\n" +
        "code_module: value1,\n" +
        "code_presentation: value2",sequentialVleFileContent.toString());
  }

  /**
   * test getModuleSession
   */
  @Test
  public void getModuleSession() {
    Assert.assertEquals("value1_value2",sequentialVleFileContent.getModuleSession());
  }

  /**
   * test getDate
   */
  @Test
  public void getDate() {
    Assert.assertEquals("value3",sequentialVleFileContent.getDate());
  }

  /**
   * test getClick
   */
  @Test
  public void getClick() {
    Assert.assertEquals("value4",sequentialVleFileContent.getClick());
  }
}
