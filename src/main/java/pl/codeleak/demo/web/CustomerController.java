package pl.codeleak.demo.web;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.codeleak.demo.core.Customer;
import pl.codeleak.demo.core.CustomerRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Profile("web")
@Component
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {

    @Inject
    private CustomerRepository customerRepository;

    @Context
    private UriInfo uriInfo;

    @GET
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Customer findOne(@PathParam("id") Long id) {
        return customerRepository.findOne(id);
    }

    @POST
    public Response save(@Valid Customer customer) {

        customer = customerRepository.save(customer);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path("{id}")
                .resolveTemplate("id", customer.getId())
                .build();

        return Response.created(location).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        customerRepository.delete(id);
        return Response.accepted().build();
    }
}
