package sequentialSolution;

import java.util.HashMap;

public class SequentialCourseContent extends SequentialContent{

  public SequentialCourseContent(HashMap<String, String> content){
    super(content);
  }

  @Override
  public String toString() {
    return "SequentialCourseContent= "+ super.toString();
  }
}
