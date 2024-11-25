package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDTO {
    private Integer codbranch;
    private String abrevi;
    private String descri;
    private String codext;
    private String addres;
    private String poscod;
    private String phone;
    private String email;
    private Integer codwarehouse;
    private byte[] glossdocument;
    private byte[] glossticket;
    private String observ;
    private String status;
}
