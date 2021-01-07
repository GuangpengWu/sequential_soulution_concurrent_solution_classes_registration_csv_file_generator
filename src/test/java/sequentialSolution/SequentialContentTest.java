package sequentialSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
/**
 * SequentialContentTest
 */
public class SequentialContentTest {

  private SequentialVleFileContent sequentialVleFileContent;

  /**
   * initialize the sequentialVleFileContent
   */
  @Before
  public void setUp() {
    HashMap<String,String> hashMap=new HashMap<>();
    hashMap.put("code_module","value1");
    hashMap.put("code_presentation","value2");
    hashMap.put("date","value3");
    hashMap.put("sum_click","value4");
    sequentialVleFileContent=new SequentialVleFileContent(hashMap);
  }

  /**
   * test Equals
   */
  @Test
  public void testEquals() {
    HashMap<String,String> hashMap=new HashMap<>();
    hashMap.put("code_module","value1");
    hashMap.put("code_presentation","value2");
    hashMap.put("date","value3");
    hashMap.put("sum_click","value4");
    SequentialVleFileContent sequentialVleFileContent2=new SequentialVleFileContent(hashMap);
    Assert.assertTrue(sequentialVleFileContent.equals(sequentialVleFileContent));
    Assert.assertTrue(sequentialVleFileContent.equals(sequentialVleFileContent2));
    Assert.assertFalse(sequentialVleFileContent.equals(null));
    Assert.assertFalse(sequentialVleFileContent.equals(1));

    HashMap<String,String> hashMap3=new HashMap<>();
    hashMap3.put("code_module2","value1");
    SequentialVleFileContent sequentialVleFileContent3=new SequentialVleFileContent(hashMap3);
    Assert.assertFalse(sequentialVleFileContent.equals(sequentialVleFileContent3));

    HashMap<String,String> hashMap4=new HashMap<>();
    hashMap4.put("code_module","value10");
    hashMap4.put("code_presentation","value20");
    hashMap4.put("date","value3");
    hashMap4.put("sum_click","value4");
    SequentialVleFileContent sequentialVleFileContent4=new SequentialVleFileContent(hashMap4);
    Assert.assertFalse(sequentialVleFileContent.equals(sequentialVleFileContent4));
  }

  /**
   * test HashCode
   */
  @Test
  public void testHashCode() {
    Assert.assertEquals(-1903597875,sequentialVleFileContent.hashCode());
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
   * test getContent
   */
  @Test
  public void getContent() {
    HashMap<String,String> hashMap=new HashMap<>();
    hashMap.put("code_module","value1");
    hashMap.put("code_presentation","value2");
    hashMap.put("date","value3");
    hashMap.put("sum_click","value4");
    Assert.assertEquals(hashMap,sequentialVleFileContent.getContent());
  }
}
