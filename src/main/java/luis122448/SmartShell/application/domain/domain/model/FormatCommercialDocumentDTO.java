package luis122448.SmartShell.application.domain.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormatCommercialDocumentDTO {
    private Integer typcomdoc;
    private Integer typformat;
    private String abrevi;
    private String descri;
    private String format;
    private String url;
    private byte[] image;
    private byte[] pdf;
    private String observ;
    private String commen;
    private String defaul;
    private String status;
}
