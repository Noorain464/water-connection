package validators;

import digit.web.models.WaterConnectionRequest;
import digit.web.models.WaterConnection;
import org.egov.tracer.model.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class WaterConnectionValidator {

    public WaterConnection validateWaterConnectionRequest(WaterConnectionRequest waterConnectionRequest) {
        if (waterConnectionRequest == null || waterConnectionRequest.getWaterConnection() == null) {
            throw new CustomException("WC_REQ_ERR", "WaterConnectionRequest or WaterConnection cannot be null");
        }

        WaterConnection waterConnection = waterConnectionRequest.getWaterConnection();
        if (ObjectUtils.isEmpty(waterConnection.getTenantId())) {
            throw new CustomException("WC_TENANT_ERR", "tenantId is mandatory for creating water connection applications");
        }
        return waterConnection;
    }
}
