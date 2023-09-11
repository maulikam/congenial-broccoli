package org.acme;
 

import java.net.URI;
import java.util.List;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("beers/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BeerResouceV1 {

    @GET 
    public Uni<List<Beer>> all() {
        return Beer.listAll(Sort.by("name"));
    }    

    @GET
    @Path("/{id}")
    public Uni<Beer> getById(@PathParam("id") Long beerId){
        return Beer.findById(beerId);
    }

    @POST
    public Uni<Response> create(Beer beer) {
        return Panache.<Beer>withTransaction(beer::persist)
            .onItem()
            .transform(inserted->URI.create("/beers/v1" + inserted.id))
            .onItem()
            .transform(uri -> Response.seeOther(uri).build());
    }

    @ServerExceptionMapper
    public Response persistenceExpception(PersistenceException exception) {
        Log.error(exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}
