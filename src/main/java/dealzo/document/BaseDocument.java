package dealzo.document;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class BaseDocument implements Persistable<Serializable> {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("created_time")
    @CreatedDate
    private Date createdTime;

    @Field("updated_time")
    @LastModifiedDate
    private Date updatedTime;

    @Field("created_by")
    @CreatedBy
    private String createdBy;

    @Field("updated_by")
    @LastModifiedBy
    private String updatedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean isNew() {
        return StringUtils.isEmpty(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseDocument [id=");
        builder.append(id);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", updatedTime=");
        builder.append(updatedTime);
        builder.append(", createdBy=");
        builder.append(createdBy);
        builder.append(", updatedBy=");
        builder.append(updatedBy);
        builder.append("]");
        return builder.toString();
    }

}