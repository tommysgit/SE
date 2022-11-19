package SoftwareEngineering.server.Domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;
    @LastModifiedBy
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
