package sequentialSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * SequentialCourseContainerTest
 */
public class SequentialCourseContainerTest {

  private SequentialContainer sequentialCourseContainer1;

  private SequentialCourseContent sequentialCourseContent1;

  /**
   * initialize the sequentialCourseContainer
   */
  @Before
  public void setUp() {
    HashMap<String,String> hashMap=new HashMap<>();
    hashMap.put("key1","value1");
    hashMap.put("key2","value2");
    sequentialCourseContent1 = new SequentialCourseContent(hashMap);

    sequentialCourseContainer1 = new SequentialCourseContainer();

    sequentialCourseContainer1.addContent(sequentialCourseContent1);
  }

  /**
   * test ToString
   */
  @Test
  public void testToString() {
    Assert.assertEquals("SequentialCourseContainer = Container={ SequentialCourseContent= {key1: value1,\n"
        + "key2: value2}",sequentialCourseContainer1.toString());
  }
}
