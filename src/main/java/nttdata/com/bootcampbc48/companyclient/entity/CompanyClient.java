package nttdata.com.bootcampbc48.companyclient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CompanyClient {

    @Id
    private String id;
    private String ruc;
    private String companyName;
    private String legalResidence;
    private String profile;
    private short registrationStatus;
    private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}
