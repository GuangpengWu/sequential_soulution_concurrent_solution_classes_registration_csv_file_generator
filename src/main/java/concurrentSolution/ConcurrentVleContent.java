package concurrentSolution;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConcurrentVleContent {

    private final Map<String, String> content;

    /**
     * @param content with module_session as key; date and click as value
     */
    public ConcurrentVleContent(HashMap<String, String> content){
        this.content = content;
    }

    @Override
    public String toString() {
        return "ConcurrentVleContent{" +
                "content=" + content +
                '}';
    }

    /**
     * @return content
     */
    public Map<String, String> getContent() {
        return content;
    }

    /**
     * @return module_session
     */
    public String getModuleSession(){
        return content.get("code_module") + "_" + content.get("code_presentation");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConcurrentVleContent that = (ConcurrentVleContent) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
