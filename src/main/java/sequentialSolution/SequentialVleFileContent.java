package sequentialSolution;

import java.util.HashMap;

/**
 * Line Content, with csv title as mapping key.
 */
public class SequentialVleFileContent extends SequentialContent{

  public SequentialVleFileContent(HashMap<String, String> content){
    super(content);
  }

  @Override
  public String toString() {
    return "SequentialFileContent = " + super.toString();
  }

  public String getModuleSession(){
    return getContent().get("code_module") + "_" + getContent().get("code_presentation");
  }

  public String getDate(){
    return getContent().get("date");
  }

  public String getClick(){
    return getContent().get("sum_click");
  }
}
