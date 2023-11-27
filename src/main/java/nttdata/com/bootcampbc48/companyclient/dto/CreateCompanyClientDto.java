package nttdata.com.bootcampbc48.companyclient.dto;

import lombok.Data;

@Data
public class CreateCompanyClientDto {

    private String ruc;
    private String companyName;
    private String legalResidence;
    private String profile;
    private String fk_insertionUser;
    private String insertionTerminal;

}