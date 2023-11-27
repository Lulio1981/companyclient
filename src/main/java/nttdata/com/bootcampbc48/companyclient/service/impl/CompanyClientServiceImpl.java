package nttdata.com.bootcampbc48.companyclient.service.impl;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.companyclient.adapter.Adapter;
import nttdata.com.bootcampbc48.companyclient.dto.CreateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.DeleteCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.UpdateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.entity.CompanyClient;
import nttdata.com.bootcampbc48.companyclient.repository.CompanyClientRepository;
import nttdata.com.bootcampbc48.companyclient.service.CompanyClientService;
import nttdata.com.bootcampbc48.companyclient.util.handler.exceptions.BadRequestException;
import nttdata.com.bootcampbc48.companyclient.util.mapper.CompanyClientModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class CompanyClientServiceImpl implements CompanyClientService {

    static CompanyClientModelMapper modelMapper = CompanyClientModelMapper.singleInstance();
    public final CompanyClientRepository repository;

    @Override
    public Flowable<CompanyClient> findAll() {
        return Adapter.fluxConverter(repository.findAll())
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal clients dont exist.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )));
    }

    @Override
    public Single<CompanyClient> findById(String id) {
        return Adapter.monoConverter(repository.findById(id))
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with id number " + id + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(CompanyClient.class).toSingle();
    }

    @Override
    public Single<CompanyClient> findByRuc(String ruc) {
        return Adapter.monoConverter(repository.findByRuc(ruc))

                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with document number " + ruc + " does not exists.",
                        getClass(),
                        "getByDocumentNumber.switchIfEmpty"
                )))
                .cast(CompanyClient.class).toSingle();

    }

    @Override
    public Single<CompanyClient> create(CreateCompanyClientDto createCompanyClientDto) {

        return Adapter.monoConverter(repository.findByRuc(createCompanyClientDto.getRuc()))
                .map(p -> {
                    throw new BadRequestException(
                            "DocumentNumber",
                            "[save] The document number " + createCompanyClientDto.getRuc() + " is already in use.",
                            "An error occurred while trying to create an item.",
                            getClass(),
                            "save"
                    );
                })
                .switchIfEmpty(Maybe.defer(() ->
                        Adapter.monoConverter(repository.save(modelMapper.reverseMapCreateWithDate(createCompanyClientDto)))
                ))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                )))
                .cast(CompanyClient.class).toSingle();
    }


    @Override
    public Single<CompanyClient> update(UpdateCompanyClientDto updateCompanyClientDto) {

        return Adapter.monoConverter(repository.findByRuc(updateCompanyClientDto.getRuc()))
                .switchIfEmpty(Maybe.error(new Exception("An item with the document number " + updateCompanyClientDto.getRuc() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> Adapter.monoConverter(repository.save(modelMapper.reverseMapUpdate(p, updateCompanyClientDto))))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).toSingle();
    }

    @Override
    public Single<CompanyClient> delete(DeleteCompanyClientDto deleteCompanyClientDto) {

        return Adapter.monoConverter(repository.findByRuc(deleteCompanyClientDto.getRuc())
                        .switchIfEmpty(Mono.error(new Exception("An item with the document number " + deleteCompanyClientDto.getRuc() + " was not found. >> switchIfEmpty")))
                        .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteCompanyClientDto))))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                ))).toSingle();
    }
}