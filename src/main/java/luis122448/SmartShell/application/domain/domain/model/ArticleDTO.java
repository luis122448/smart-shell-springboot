package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArticleDTO {
    private String codart;
    private Integer typinv;
    private String abrevi;
    private String descri;
    private String codext;
    private String codbar;
    private String codean;
    private LocalDate registdate;
    private String cstock;
    private String codprv;
    private String codman;
    private String coduni;
    private String stocknegative;
    private String editdescri;
    private String printcomment;
    private byte[] image;
    private String observ;
    private String commen;
    private String status;
}
