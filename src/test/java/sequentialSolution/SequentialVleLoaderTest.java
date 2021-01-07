package sequentialSolution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * SequentialLoaderTest
 */
public class SequentialVleLoaderTest {

  private SequentialVleLoader sequentialVleFileLoader1;
  private SequentialVleLoader sequentialVleFileLoader2;

  /**
   * initialize the sequentialVleLoader
   * @throws Exception when invalid file
   */
  @Before
  public void setUp() throws Exception {
    String pathName="test_data";
    String filename="/studentVle.csv";
    this.sequentialVleFileLoader1 = new SequentialVleLoader(pathName, filename);
    this.sequentialVleFileLoader2 = new SequentialVleLoader(pathName, filename);
  }

  /**
   * test Equals
   */
  @Test
  public void testEquals() {
    Assert.assertEquals(sequentialVleFileLoader1, sequentialVleFileLoader2);
  }

  /**
   * test HashCode
   */
  @Test
  public void testHashCode() {
    Assert.assertNotEquals(sequentialVleFileLoader1.hashCode(), sequentialVleFileLoader2.hashCode());
  }

  /**
   * test ToString
   */
  @Test
  public void testToString() {
    Assert.assertEquals("SequentialVleLoader{SequentialLoader{filename='/studentVle.csv', file=test_data/studentVle.csv, titles=[code_module, code_presentation, id_student, id_site, date, sum_click], Container=SequentialFileContainer = Container={ SequentialFileContent = {date: -10,\n"
        + "sum_click: 4,\n"
        + "code_module: AAA,\n"
        + "id_site: 546652,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 28400, SequentialFileContent = {date: -10,\n"
        + "sum_click: 1,\n"
        + "code_module: AAA,\n"
        + "id_site: 546652,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 28400, SequentialFileContent = {date: -10,\n"
        + "sum_click: 1,\n"
        + "code_module: AAA,\n"
        + "id_site: 546652,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 28400, SequentialFileContent = {date: -10,\n"
        + "sum_click: 11,\n"
        + "code_module: AAA,\n"
        + "id_site: 546614,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 28400, SequentialFileContent = {date: -10,\n"
        + "sum_click: 1,\n"
        + "code_module: AAA,\n"
        + "id_site: 546714,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 28400, SequentialFileContent = {date: 8,\n"
        + "sum_click: 13,\n"
        + "code_module: AAA,\n"
        + "id_site: 546614,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 1758449, SequentialFileContent = {date: 8,\n"
        + "sum_click: 3,\n"
        + "code_module: AAA,\n"
        + "id_site: 546667,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 1758449, SequentialFileContent = {date: 8,\n"
        + "sum_click: 1,\n"
        + "code_module: AAA,\n"
        + "id_site: 546654,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 1758449, SequentialFileContent = {date: 8,\n"
        + "sum_click: 1,\n"
        + "code_module: AAA,\n"
        + "id_site: 546879,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 1758449, SequentialFileContent = {date: 8,\n"
        + "sum_click: 1,\n"
        + "code_module: AAA,\n"
        + "id_site: 546681,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 1758449, SequentialFileContent = {date: 8,\n"
        + "sum_click: 7,\n"
        + "code_module: AAA,\n"
        + "id_site: 546659,\n"
        + "code_presentation: 2013J,\n"
        + "id_student: 1758449}}endLine='BBB\",\"2013B\",\"2541120\",\"542891\",\"51\",\"2'}", sequentialVleFileLoader1.toString());
  }
}
