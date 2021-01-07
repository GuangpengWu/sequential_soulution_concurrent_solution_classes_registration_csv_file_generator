package sequentialSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SequentialContainer contains SequentialContent.
 */
public abstract class SequentialContainer {
  private List<SequentialContent> container;

  public SequentialContainer(){
    this.container = new ArrayList<>();
  }

  /**
   * @param content sequential content added
   */
  public void addContent(SequentialContent content){
    this.container.add(content);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SequentialContainer that = (SequentialContainer) o;
    return Objects.equals(container, that.container);
  }

  @Override
  public int hashCode() {
    return Objects.hash(container);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("Container={ ");
    for (SequentialContent c: container) {
      result.append(c).append(", ");
    }
    return result.substring(0, result.length()-2) + "}";
  }

  /**
   * @return container of this sequential content
   */
  public List<SequentialContent> getContainer() {
    return container;
  }

  /**
   * Container clear
   */
  public void flash(){
    this.container.clear();
  }
}
