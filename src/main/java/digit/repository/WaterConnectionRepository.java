package digit.repository;
import digit.repository.querybuilder.WaterConnectionQueryBuilder;
import digit.repository.rowmapper.WaterConnectionRowMapper;
import digit.web.models.WaterConnection;
import digit.web.models.WaterConnectionSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WaterConnectionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WaterConnectionQueryBuilder queryBuilder;

    @Autowired
    private WaterConnectionRowMapper rowMapper;

    public List<WaterConnection> getConnections(WaterConnectionSearchCriteria criteria) {
        // Generate the SQL query dynamically based on search criteria
        String query = queryBuilder.getWaterConnectionSearchQuery(criteria);

        // Execute the query using JdbcTemplate and map results
        return jdbcTemplate.query(query, rowMapper);
    }
}
