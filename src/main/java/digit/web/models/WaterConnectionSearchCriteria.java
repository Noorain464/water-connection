package digit.web.models;

public class WaterConnectionSearchCriteria {
    private String tenantId;
    private String connectionNo;
    private String status;

    // Constructors
    public WaterConnectionSearchCriteria() {}

    public WaterConnectionSearchCriteria(String tenantId, String connectionNo, String status) {
        this.tenantId = tenantId;
        this.connectionNo = connectionNo;
        this.status = status;
    }

    // Getters and Setters
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getConnectionNo() {
        return connectionNo;
    }

    public void setConnectionNo(String connectionNo) {
        this.connectionNo = connectionNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

