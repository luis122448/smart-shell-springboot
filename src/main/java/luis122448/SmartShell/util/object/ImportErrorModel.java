package luis122448.SmartShell.util.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportErrorModel {

    private Integer line;
    private String value;
    private String message;

}
