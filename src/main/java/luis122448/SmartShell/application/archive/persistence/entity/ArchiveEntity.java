package luis122448.SmartShell.application.archive.persistence.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "Archive")
public class ArchiveEntity {
    @Id
    private String id;
    private String title;
    private String format;
    private String extension;
    private Integer size;
    private Binary archive;
    private Long companyId;
    private String status;
    private String createBy;
    private String updateBy;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
