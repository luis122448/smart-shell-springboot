package luis122448.SmartShell.security.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static luis122448.SmartShell.security.application.entity.constant.DatabaseConstant.SCHEMA_SMART_SHELL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CompanyInfoEntitySecurity")
@Table(schema = SCHEMA_SMART_SHELL, name = "TBL_COMPANY_INFO")
public class CompanyInfoEntity {
    @Id
    private Integer idcompany;
    private String company;
    private String appellation;
    private String addres;
    private String poscod;
}
