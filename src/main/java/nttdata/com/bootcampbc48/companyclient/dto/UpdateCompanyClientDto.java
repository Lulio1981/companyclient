package nttdata.com.bootcampbc48.companyclient.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UpdateCompanyClientDto {

    @Id
    private String id;
    private String ruc;
    private String legalResidence;
    private String profile;

}