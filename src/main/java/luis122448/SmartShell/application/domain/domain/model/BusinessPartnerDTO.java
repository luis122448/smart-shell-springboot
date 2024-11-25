package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BusinessPartnerDTO {
    private String codbuspar;
    private Short typbuspar;
    private Short typidedoc;
    private String nroidedoc;
    private String codext;
    private String busnam;
    private String apepat;
    private String apemat;
    private String nombre;
    private LocalDate registdate;
    private String addres;
    private String poscod;
    private String codtel;
    private String telefo;
    private String email;
    private Short typpaycon;
    private BigDecimal limcre;
    private Short codlistprice;
    private byte[] image;
    private String observ;
    private String commen;
    private String status;
}
