package org.maxkey.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.mybatis.jpa.persistence.JpaBaseDomain;
import org.maxkey.constants.ConstantsStatus;

@Table(name = "ROLE_PERMISSIONS")
public class RolePermissions  extends JpaBaseDomain implements Serializable {
    private static final long serialVersionUID = -8783585691243853899L;
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
    String id;
    @Column
    String appId;
    @Column
    String roleId;
    @Column
    String resourceId;
    
    int status = ConstantsStatus.ACTIVE;

    public RolePermissions() {
    }

    public RolePermissions(String appId, String roleId) {
        this.appId = appId;
        this.roleId = roleId;
    }
    
    /**
     * .
     * @param appId String
     * @param roleId String
     * @param resourceId String
     */
    public RolePermissions(String appId, String roleId, String resourceId) {
        this.id = this.generateId();
        this.appId = appId;
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public String  getUniqueId() {
        return  appId + "_" + roleId + "_" + resourceId;
    }
    

}
