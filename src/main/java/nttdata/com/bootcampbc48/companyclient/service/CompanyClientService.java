package nttdata.com.bootcampbc48.companyclient.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import nttdata.com.bootcampbc48.companyclient.dto.CreateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.DeleteCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.UpdateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.entity.CompanyClient;

public interface CompanyClientService {

    public Single<CompanyClient> create(CreateCompanyClientDto createCompanyClientDto);

    public Flowable<CompanyClient> findAll();

    public Single<CompanyClient> findById(String id);

    public Single<CompanyClient> findByRuc(String ruc);

    public Single<CompanyClient> update(UpdateCompanyClientDto updateCompanyClientDto);

    public Single<CompanyClient> delete(DeleteCompanyClientDto deleteCompanyClientDto);


}