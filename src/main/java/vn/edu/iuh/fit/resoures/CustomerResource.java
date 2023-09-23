package vn.edu.iuh.fit.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.services.CustomerService;
import java.util.List;

@Path("/customers")
public class CustomerResource {
    private final CustomerService customerService;

    public CustomerResource() {
        customerService = new CustomerService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer customer) {
        boolean isSuccess = customerService.createCustomer(customer);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{customerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("customerId") long customerId, Customer customer) {
        customer.setCustId(customerId);
        boolean isSuccess = customerService.updateCustomer(customer);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{customerId}")
    public Response deleteCustomer(@PathParam("customerId") long customerId) {
        boolean isSuccess = customerService.deleteCustomer(customerId);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("customerId") long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return Response.status(Response.Status.OK).entity(customer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return Response.status(Response.Status.OK).entity(customers).build();
    }
}