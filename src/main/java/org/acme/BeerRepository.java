package org.acme;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeerRepository implements PanacheRepository<Beer> {
    
    public Uni<Beer> findByName(String name){
        return find("name", name).firstResult();
    }

}
