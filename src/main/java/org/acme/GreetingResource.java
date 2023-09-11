package org.acme;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.Status;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @NonBlocking
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        return Uni.createFrom().item("Hello from RESTEasy Reactive")
            .log()
            ;
    }

    @ServerExceptionMapper(IllegalArgumentException.class)
    public RestResponse error(){
        return RestResponse.status(Status.INTERNAL_SERVER_ERROR, "Something went wrong");
    }

}
