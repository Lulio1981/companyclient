package nttdata.com.bootcampbc48.companyclient.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class CreateCompanyClientAccountDto {

    @Id
    private String accountId;
    private String accountType;
    private String accountUrl;
    private String accountIsoCurrencyCode;
    private String accountFk_insertionUser;
    private String accountInsertionTerminal;

}