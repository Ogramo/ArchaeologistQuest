package objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Quest {
    private String title;
    private String exposition;
    private Map<String, Decision> decisions;

}
