package org.acme;
 
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/beers/v2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BeerResouceV2 {

    @Inject
    BeerService beerService;

    

    @GET
    public Uni<Beer> delay(){
        return Uni.createFrom().completionStage(beerService.getFromDraft());
    }
    
}
