package luis122448.SmartShell.util.object;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ColumnResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(
	    name = "ResultMessage",
	    classes = @ConstructorResult(
	        targetClass = ResultMessage.class,
	        columns = {
	            @ColumnResult(name = "rescode", type = Short.class),
	            @ColumnResult(name = "resmens", type = String.class)
	        }
	    )
)

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ResultMessage {
	@Id
    private Short rescode;
    private String resmens;

}