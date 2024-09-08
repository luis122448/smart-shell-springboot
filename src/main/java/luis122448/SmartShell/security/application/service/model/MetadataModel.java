package luis122448.SmartShell.security.application.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.SmartShell.application.domain.persistence.entity.*;
import luis122448.SmartShell.security.application.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetadataModel {
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
}
