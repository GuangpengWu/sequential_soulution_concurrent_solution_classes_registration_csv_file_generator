package sequentialSolution;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * SequentialVleFileContainer contains a big map named cumulates.
 *
 * It's key is module_session, and the value is a nested map named cumulate.
 */
public class SequentialVleFileContainer extends SequentialContainer{
  private Map<String, SequentialCumulate> cumulates;

  public SequentialVleFileContainer(){
    super();
    this.cumulates = new HashMap<>();
  }

  /**
   * Cumulates will be updated by appending a new cumulate if module_session is already exists.
   * Will be add a new module_session map if the module_session doesn't exist before.
   */
  public void calculate(){
    for (SequentialContent c:getContainer()) {
      SequentialVleFileContent vle = (SequentialVleFileContent) c;
      SequentialCumulate cumulate;
      // if current moduleSession already exist, update cumulate
      if (this.cumulates.containsKey(vle.getModuleSession())){
        cumulate = this.cumulates.get(vle.getModuleSession());
      } else {
        cumulate = new SequentialCumulate(vle.getModuleSession());
      }
      cumulate.calculate(vle.getDate(), vle.getClick());
      this.cumulates.put(vle.getModuleSession(), cumulate);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SequentialVleFileContainer container = (SequentialVleFileContainer) o;
    return Objects.equals(cumulates, container.cumulates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), cumulates);
  }

  @Override
  public String toString() {
    return "SequentialFileContainer = " + super.toString();
  }

  /**
   * @return cumulates
   */
  public Map<String, SequentialCumulate> getCumulates() {
    return cumulates;
  }
}
