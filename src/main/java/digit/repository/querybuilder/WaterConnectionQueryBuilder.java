package digit.repository.querybuilder;

import digit.web.models.WaterConnectionSearchCriteria;
import org.springframework.stereotype.Component;
import java.util.StringJoiner;
@Component
public class WaterConnectionQueryBuilder {

    private static final String BASE_WC_QUERY = "SELECT * FROM water_connection wc";

    public String getWaterConnectionSearchQuery(WaterConnectionSearchCriteria criteria) {
        StringBuilder query = new StringBuilder(BASE_WC_QUERY);

        if (criteria.getTenantId() != null) {
            query.append(" AND tenant_id = '").append(criteria.getTenantId()).append("'");
        }
        if (criteria.getConnectionNo() != null) {
            query.append(" AND connection_no = '").append(criteria.getConnectionNo()).append("'");
        }
        if (criteria.getStatus() != null) {
            query.append(" AND status = '").append(criteria.getStatus()).append("'");
        }

        return query.toString();
    }

    private void addClauseIfRequired(StringJoiner conditions, String clause, String value) {
        if (value != null && !value.trim().isEmpty()) {
            conditions.add(clause);
        }
    }

    private String createQuery(StringBuilder queryBuilder, StringJoiner conditions) {
        if (conditions.length() > 0) {
            queryBuilder.append(" WHERE ").append(conditions.toString());
        }
        return queryBuilder.toString();
    }

    public static void main(String[] args) {
        WaterConnectionQueryBuilder queryBuilder = new WaterConnectionQueryBuilder();
    }
}