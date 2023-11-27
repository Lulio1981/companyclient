package nttdata.com.bootcampbc48.companyclient.util.mapper;

import nttdata.com.bootcampbc48.companyclient.dto.CreateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.DeleteCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.UpdateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.entity.CompanyClient;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class CompanyClientModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static CompanyClientModelMapper instance;

    private CompanyClientModelMapper() {
    }

    public static CompanyClientModelMapper singleInstance() {
        if (instance == null) {
            instance = new CompanyClientModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public CompanyClient reverseMapCreateWithDate(CreateCompanyClientDto createDto) {
        CompanyClient o = mapper.map(createDto, CompanyClient.class);

        o.setInsertionDate(new Date());
        o.setRegistrationStatus((short) 1);

        return o;
    }


    public CompanyClient reverseMapUpdate(CompanyClient companyClient, UpdateCompanyClientDto updateDto) {

        companyClient.setProfile(updateDto.getProfile());
        companyClient.setLegalResidence(updateDto.getLegalResidence());

        return companyClient;
    }

    public CompanyClient reverseMapDelete(CompanyClient companyClient, DeleteCompanyClientDto deleteDto) {

        companyClient.setRegistrationStatus((short) 0);

        return companyClient;
    }

}
