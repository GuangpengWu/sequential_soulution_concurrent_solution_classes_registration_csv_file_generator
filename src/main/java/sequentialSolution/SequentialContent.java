package sequentialSolution;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Line Content, with csv title as mapping key.
 */
public abstract class SequentialContent {
  private final Map<String, String> content;

  /**
   * @param content sequential content
   */
  public SequentialContent(HashMap<String, String> content){
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SequentialContent that = (SequentialContent) o;
    return Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content);
  }

  @Override
  public String toString(){
    StringBuilder result = new StringBuilder("{");
    for (String s: content.keySet()) {
      String value = content.get(s);
      result.append(s).append(": ").append(value).append(",\n");
    }
    return result.substring(0, result.length()-2);
  }

  /**
   * @return content
   */
  public Map<String, String> getContent() {
    return content;
  }
}
