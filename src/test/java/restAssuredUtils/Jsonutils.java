package restAssuredUtils;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Jsonutils {
    private final Configuration configuration;

    public Jsonutils() {
        this.configuration = Configuration.builder().options(new Option[]{Option.ALWAYS_RETURN_LIST}).build();
    }

    public String getValueForKey(String json, String key) {
        return (String) this.flatMapToString(json).get(key);
    }

    public Map flatMapToString(String json) {
        return (Map)JsonFlattener.flattenAsMap(json).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,(entry) -> {
            return entry.getValue() != null ? entry.getValue().toString() : "";
        }));
    }

    public int numberOfoccuranceOfNode(String json, String jsonPath) {
        return ((List)JsonPath.using(this.configuration).parse(json).read(jsonPath, new Predicate[0])).size();
    }





}
