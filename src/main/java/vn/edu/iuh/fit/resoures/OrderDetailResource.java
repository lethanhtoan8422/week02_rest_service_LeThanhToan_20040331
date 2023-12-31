package vn.edu.iuh.fit.resoures;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.OrderDetail;
import vn.edu.iuh.fit.services.OrderDetailService;
import java.util.List;

@Path("/orderdetails")
public class OrderDetailResource {
    private final OrderDetailService orderDetailService;

    public OrderDetailResource() {
        orderDetailService = new OrderDetailService();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrderDetail(OrderDetail orderDetail) {
        boolean isSuccess = orderDetailService.createOrderDetail(orderDetail);
        if (isSuccess) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{orderDetailId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrderDetail(@PathParam("orderDetailId") long orderDetailId, OrderDetail orderDetail) {
        orderDetail.setOrderDetailId(orderDetailId);
        boolean isSuccess = orderDetailService.updateOrderDetail(orderDetail);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{orderDetailId}")
    public Response deleteOrderDetail(@PathParam("orderDetailId") long orderDetailId) {
        boolean isSuccess = orderDetailService.deleteOrderDetail(orderDetailId);
        if (isSuccess) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{orderDetailId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderDetailById(@PathParam("orderDetailId") long orderDetailId) {
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderDetailId);
        if (orderDetail != null) {
            return Response.status(Response.Status.OK).entity(orderDetail).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        return Response.status(Response.Status.OK).entity(orderDetails).build();
    }
}
