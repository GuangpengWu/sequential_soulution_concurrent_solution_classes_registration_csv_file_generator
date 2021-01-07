package sequentialSolution;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SequentialCourseLoaderTest {

  private SequentialCourseLoader sequentialCourseLoader1;
  private SequentialCourseLoader sequentialCourseLoader2;

  @Before
  public void setUp() throws Exception {
    String pathName="test_data";
    String filename="/courses.csv";
    this.sequentialCourseLoader1 = new SequentialCourseLoader(pathName, filename);
    this.sequentialCourseLoader2 = new SequentialCourseLoader(pathName, filename);
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(sequentialCourseLoader1, sequentialCourseLoader2);
  }

  @Test
  public void testHashCode() {
    Assert.assertNotEquals(sequentialCourseLoader1.hashCode(), sequentialCourseLoader2.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("SequentialLoader{filename='/courses.csv', file=test_data/courses.csv, titles=[code_module, code_presentation, module_presentation_length], Container=SequentialCourseContainer = Container={ SequentialCourseContent= {code_module: AAA,\n"
        + "code_presentation: 2013J,\n"
        + "module_presentation_length: 268, SequentialCourseContent= {code_module: AAA,\n"
        + "code_presentation: 2014J,\n"
        + "module_presentation_length: 269, SequentialCourseContent= {code_module: BBB,\n"
        + "code_presentation: 2013J,\n"
        + "module_presentation_length: 268, SequentialCourseContent= {code_module: BBB,\n"
        + "code_presentation: 2014J,\n"
        + "module_presentation_length: 262, SequentialCourseContent= {code_module: BBB,\n"
        + "code_presentation: 2013B,\n"
        + "module_presentation_length: 240, SequentialCourseContent= {code_module: BBB,\n"
        + "code_presentation: 2014B,\n"
        + "module_presentation_length: 234, SequentialCourseContent= {code_module: CCC,\n"
        + "code_presentation: 2014J,\n"
        + "module_presentation_length: 269, SequentialCourseContent= {code_module: CCC,\n"
        + "code_presentation: 2014B,\n"
        + "module_presentation_length: 241, SequentialCourseContent= {code_module: DDD,\n"
        + "code_presentation: 2013J,\n"
        + "module_presentation_length: 261, SequentialCourseContent= {code_module: DDD,\n"
        + "code_presentation: 2014J,\n"
        + "module_presentation_length: 262, SequentialCourseContent= {code_module: DDD,\n"
        + "code_presentation: 2013B,\n"
        + "module_presentation_length: 240, SequentialCourseContent= {code_module: DDD,\n"
        + "code_presentation: 2014B,\n"
        + "module_presentation_length: 241, SequentialCourseContent= {code_module: EEE,\n"
        + "code_presentation: 2013J,\n"
        + "module_presentation_length: 268, SequentialCourseContent= {code_module: EEE,\n"
        + "code_presentation: 2014J,\n"
        + "module_presentation_length: 269, SequentialCourseContent= {code_module: EEE,\n"
        + "code_presentation: 2014B,\n"
        + "module_presentation_length: 241, SequentialCourseContent= {code_module: FFF,\n"
        + "code_presentation: 2013J,\n"
        + "module_presentation_length: 268, SequentialCourseContent= {code_module: FFF,\n"
        + "code_presentation: 2014J,\n"
        + "module_presentation_length: 269, SequentialCourseContent= {code_module: FFF,\n"
        + "code_presentation: 2013B,\n"
        + "module_presentation_length: 240, SequentialCourseContent= {code_module: FFF,\n"
        + "code_presentation: 2014B,\n"
        + "module_presentation_length: 241, SequentialCourseContent= {code_module: GGG,\n"
        + "code_presentation: 2013J,\n"
        + "module_presentation_length: 261, SequentialCourseContent= {code_module: GGG,\n"
        + "code_presentation: 2014J,\n"
        + "module_presentation_length: 269, SequentialCourseContent= {code_module: GGG,\n"
        + "code_presentation: 2014B,\n"
        + "module_presentation_length: 241}}", sequentialCourseLoader1.toString());
  }

}
