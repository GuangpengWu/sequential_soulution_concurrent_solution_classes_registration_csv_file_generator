package sequentialSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * SequentialCourseContentTest
 */
public class SequentialCourseContentTest {

  private SequentialCourseContent sequentialCourseContent;


  /**
   * initialize the sequentialCourseContent
   */
  @Before
  public void setUp() {
    HashMap<String,String> hashMap=new HashMap<>();
    hashMap.put("key1","value1");
    hashMap.put("key2","value2");
    sequentialCourseContent=new SequentialCourseContent(hashMap);
  }

  /**
   * test ToString
   */
  @Test
  public void testToString() {
    Assert.assertEquals("SequentialCourseContent= {key1: value1,\n" +
        "key2: value2",sequentialCourseContent.toString());
  }
}
