package nttdata.com.bootcampbc48.companyclient.repository;

import nttdata.com.bootcampbc48.companyclient.entity.CompanyClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CompanyClientRepository extends ReactiveMongoRepository<CompanyClient, String> {

    public Mono<CompanyClient> findById(String id);

    public Mono<CompanyClient> findByRuc(String ruc);

}