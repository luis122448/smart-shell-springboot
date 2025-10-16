package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SellerDTO {
    private String codsel;
    private String abrevi;
    private String descri;
    private String apepat;
    private String apemat;
    private String nombre;
    private LocalDate registdate;
    private String poscod;
    private String addres;
    private String telefo;
    private String email;
    private String fax;
    private byte[] image;
    private String observ;
    private String commen;
    private String status;
}
