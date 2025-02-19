package service;

import digit.repository.WaterConnectionRepository;
import digit.web.models.WaterConnection;
import digit.web.models.WaterConnectionRequest;
import digit.web.models.WaterConnectionSearchCriteria;
import enrichment.WaterConnectionEnrichment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import validators.WaterConnectionValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class WaterConnectionService {

    @Autowired
    private WaterConnectionValidator waterConnectionValidator;

    @Autowired
    private WaterConnectionEnrichment waterConnectionEnrichment;

    @Autowired
    private WaterConnectionRepository waterConnectionRepository;


    /**
     * Creates a new water connection application.
     */
    public void createWaterConnection(WaterConnectionRequest request) {
        // Validate the incoming request
        waterConnectionValidator.validateWaterConnectionRequest(request);

        // Enrich the request with system-generated values
        waterConnectionEnrichment.enrichWaterConnection(request);


        // Return the created application
        return ;
    }

    /**
     * Searches for water connection applications based on criteria.
     */
    public List<WaterConnection> searchWaterConnection(WaterConnectionSearchCriteria searchCriteria) {
        // Fetch applications from the repository
        List<WaterConnection> applications = waterConnectionRepository.getConnections(searchCriteria);

        // If no applications are found, return an empty list
        if (CollectionUtils.isEmpty(applications)) {
            return new ArrayList<>();
        }

        return applications;
    }

    /**
     * Updates an existing water connection application.
     */
    public WaterConnection updateWaterConnection(WaterConnectionRequest request) {
        // Validate if the application exists
        WaterConnection existingApplication =
                waterConnectionValidator.validateWaterConnectionRequest(request);

        waterConnectionEnrichment.enrichWaterConnectionUponUpdate(request);
        return request.getWaterConnection();
    }
}
