package luis122448.SmartShell.application.domain.persistence.entity.auditing;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingEntity {

    private String status;
    @CreatedBy
    private String createby;
    @LastModifiedBy
    private String updateby;
    @CreatedDate
    private LocalDateTime createat;
    @LastModifiedDate
    private LocalDateTime updateat;
}
