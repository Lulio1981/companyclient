package nttdata.com.bootcampbc48.companyclient.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nttdata.com.bootcampbc48.companyclient.dto.CreateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.DeleteCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.dto.UpdateCompanyClientDto;
import nttdata.com.bootcampbc48.companyclient.entity.CompanyClient;
import nttdata.com.bootcampbc48.companyclient.service.impl.CompanyClientServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("client/company")
@Tag(name = "Company Clients Information", description = "Manage company clients")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class CompanyClientController {

    public final CompanyClientServiceImpl service;

    @GetMapping("/{rucNumber}")
    public Single<ResponseEntity<CompanyClient>> findByRuc(@PathVariable String rucNumber) {
        return service.findByRuc(rucNumber).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping("/find/{id}")
    public Single<ResponseEntity<CompanyClient>> findById(@PathVariable String id) {

        return service.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<CompanyClient>> findAll() {
        Flowable<CompanyClient> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<CompanyClient>> create(@RequestBody CreateCompanyClientDto createCompanyClientDto) {
        return service.create(createCompanyClientDto).map(p -> ResponseEntity
                .created(URI.create("/client/personal/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<CompanyClient>> update(@RequestBody UpdateCompanyClientDto updateCompanyClientDto) {
        return service.update(updateCompanyClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<CompanyClient>> delete(@RequestBody DeleteCompanyClientDto deleteCompanyClientDto) {
        return service.delete(deleteCompanyClientDto)
                .map(p -> ResponseEntity.created(URI.create("/client/personal/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}