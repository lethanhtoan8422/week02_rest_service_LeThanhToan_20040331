package vn.edu.iuh.fit.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.Order;
import vn.edu.iuh.fit.services.OrderService;
import java.util.List;

@Path("/orders")
public class OrderResource {
    private final OrderService orderService;

    public OrderResource() {
        orderService = new OrderService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order) {
        boolean isSuccess = orderService.createOrder(order);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("orderId") long orderId, Order order) {
        order.setOrderId(orderId);
        boolean isSuccess = orderService.updateOrder(order);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{orderId}")
    public Response deleteOrder(@PathParam("orderId") long orderId) {
        boolean isSuccess = orderService.deleteOrder(orderId);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("orderId") long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return Response.status(Response.Status.OK).entity(order).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return Response.status(Response.Status.OK).entity(orders).build();
    }
}