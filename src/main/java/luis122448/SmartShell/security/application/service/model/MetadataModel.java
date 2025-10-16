package luis122448.SmartShell.security.application.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class MetadataModel {
    private List<CurrencyEntity> currency;
    private List<BranchEntity> branch;
    private List<TypeCommercialDocumentEntity> typeCommercialDocument;
    private List<SellerEntity> seller;
    private List<SerieCommercialDocumentEntity> serieCommercialDocument;
    private List<ReasonCommercialDocumentEntity> reasonCommercialDocument;
    private List<SituationCommercialDocumentEntity> situationCommercialDocument;
    private List<TypeInventoryEntity> typeInventory;
    private List<WarehouseEntity> warehouse;
    private List<ListPriceEntity> listPrice;
    private List<TypeBusinessPartnerEntity> typeBusinessPartner;
    private Optional<UserEntity> user;
    private Optional<CompanyInfoEntity> company;
}
