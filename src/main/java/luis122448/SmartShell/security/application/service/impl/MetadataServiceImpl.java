package luis122448.SmartShell.security.application.service.impl;

import luis122448.SmartShell.application.domain.domain.service.generic.WarehouseService;
import luis122448.SmartShell.application.domain.domain.service.service.*;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.security.application.service.model.MetadataModel;
import luis122448.SmartShell.security.application.service.service.MetadataService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetadataServiceImpl implements MetadataService {
    @Override
    public Optional<MetadataModel> initMetadata() throws GenericAuthServiceException {
        return Optional.empty();
    }

//    private final TypeCommercialDocumentService typeCommercialDocumentService;
//    private final SellerService sellerService;
//    private final SerieCommercialDocumentService serieCommercialDocumentService;
//    private final ReasonCommercialDocumentService reasonCommercialDocumentService;
//    private final SituationCommercialDocumentService situationCommercialDocumentService;
//    private final TypeInventoryService typeInventoryService;
//    private final WarehouseService warehouseService;
//    private final ListPriceService listPriceService;
//    private final TypeBusinessPartnerService typeBusinessPartnerService;
//    private final UserService userService;
//
//    public MetadataServiceImpl(TypeCommercialDocumentService typeCommercialDocumentService, SellerService sellerService, SerieCommercialDocumentService serieCommercialDocumentService, ReasonCommercialDocumentService reasonCommercialDocumentService, SituationCommercialDocumentService situationCommercialDocumentService, TypeInventoryService typeInventoryService, WarehouseService warehouseService, ListPriceService listPriceService, TypeBusinessPartnerService typeBusinessPartnerService, UserService userService) {
//        this.typeCommercialDocumentService = typeCommercialDocumentService;
//        this.sellerService = sellerService;
//        this.serieCommercialDocumentService = serieCommercialDocumentService;
//        this.reasonCommercialDocumentService = reasonCommercialDocumentService;
//        this.situationCommercialDocumentService = situationCommercialDocumentService;
//        this.typeInventoryService = typeInventoryService;
//        this.warehouseService = warehouseService;
//        this.listPriceService = listPriceService;
//        this.typeBusinessPartnerService = typeBusinessPartnerService;
//        this.userService = userService;
//    }
//
//    @Override
//    public Optional<MetadataModel> initMetadata() throws GenericAuthServiceException {
//       try {
//          MetadataModel metadataModel = new MetadataModel();
//          metadataModel.setTypeCommercialDocument(this.typeCommercialDocumentService.findAll().getList().orElseThrow());
//          metadataModel.setSeller(this.sellerService.findAll().getList().orElseThrow());
//          metadataModel.setSerieCommercialDocument(this.serieCommercialDocumentService.findAll().getList().orElseThrow());
//          metadataModel.setReasonCommercialDocument(this.reasonCommercialDocumentService.findAll().getList().orElseThrow());
//          metadataModel.setSituationCommercialDocument(this.situationCommercialDocumentService.findAll().getList().orElseThrow());
//          metadataModel.setTypeInventory(this.typeInventoryService.findAll("Y").getList().orElseThrow());
//          metadataModel.setWarehouse(warehouseService.findAll("Y").getList().orElseThrow());
//          metadataModel.setListPrice(listPriceService.findAll().getList().orElseThrow());
//          metadataModel.setTypeBusinessPartner(this.typeBusinessPartnerService.findAll().getList().orElseThrow());
//          metadataModel.setUser(this.userService.findByProfile().getObject());
//          return Optional.of(metadataModel);
//       } catch (Exception e) {
//           throw new GenericAuthServiceException("ERROR INIT METADATA");
//       }
//    }

}
