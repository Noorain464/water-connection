package enrichment;

import digit.web.models.WaterConnectionRequest;
import digit.web.models.WaterConnection;
import digit.web.models.AuditDetails;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class WaterConnectionEnrichment {

    public void enrichWaterConnection(WaterConnectionRequest waterConnectionRequest) {
        long currentTime = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString();

        WaterConnection waterConnection = waterConnectionRequest.getWaterConnection();
        AuditDetails auditDetails = AuditDetails.builder()
                .createdBy(waterConnectionRequest.getRequestInfo().getUserInfo().getUuid())
                .createdTime(currentTime)
                .lastModifiedBy(waterConnectionRequest.getRequestInfo().getUserInfo().getUuid())
                .lastModifiedTime(currentTime)
                .build();

        waterConnection.setAuditDetails(auditDetails);
        waterConnection.setId(uuid);
    }

    public void enrichWaterConnectionUponUpdate(WaterConnectionRequest waterConnectionRequest) {
        WaterConnection waterConnection = waterConnectionRequest.getWaterConnection();

        if (waterConnection.getAuditDetails() != null) {
            waterConnection.getAuditDetails().setLastModifiedTime(System.currentTimeMillis());
            waterConnection.getAuditDetails().setLastModifiedBy(waterConnectionRequest.getRequestInfo().getUserInfo().getUuid());
        }
    }
}
