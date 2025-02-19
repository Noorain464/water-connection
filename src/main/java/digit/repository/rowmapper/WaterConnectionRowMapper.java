package digit.repository.rowmapper;

import digit.web.models.AuditDetails;
import digit.web.models.WaterConnection;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class WaterConnectionRowMapper implements ResultSetExtractor<List<WaterConnection>> {

    @Override
    public List<WaterConnection> extractData(ResultSet rs) throws SQLException {
        List<WaterConnection> waterConnections = new ArrayList<>();

        while (rs.next()) {
            WaterConnection connection = new WaterConnection();
            connection.setId(rs.getString("id"));
            connection.setTenantId(rs.getString("tenant_id"));
            connection.setConnectionNo(rs.getString("connection_no"));
            connection.setStatus(WaterConnection.StatusEnum.valueOf(rs.getString("status")));

            // Mapping AuditDetails
            AuditDetails auditDetails = new AuditDetails();
            auditDetails.setCreatedBy(rs.getString("created_by"));
            auditDetails.setCreatedTime(rs.getLong("created_time"));
            auditDetails.setLastModifiedBy(rs.getString("last_modified_by"));
            auditDetails.setLastModifiedTime(rs.getLong("last_modified_time"));
            connection.setAuditDetails(auditDetails);

            waterConnections.add(connection);
        }

        return waterConnections;
    }
}
