package luis122448.SmartShell.application.domain.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
public class DocumentGenericBasicDTO {
    private Long numint;
    private String serie;
    private Long numdoc;
}
