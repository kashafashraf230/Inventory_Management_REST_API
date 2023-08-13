package com.example.inventorymanagementsystem.resources;

import com.example.inventorymanagementsystem.model.Inventory;
import com.example.inventorymanagementsystem.services.InventoryCrud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {

    private static final Logger logger = LoggerFactory.getLogger(InventoryResource.class);
    public static int count = 0;
    InventoryCrud crud = new InventoryCrud();


    @GET
    @Path("/list")
    public Response getInventoryList(@QueryParam("location") int locationID, @QueryParam("category")int categoryID) {

        try {
            logger.info(getLogMessage("getInventoryList", "Request received", "GET"));
            if(locationID > 0 && categoryID > 0){
                logger.info(getLogMessage("getInventoryList", "Response Sent", "GET"));
                return Response.status(Response.Status.OK)
                        .entity(crud.getListByCategoryAndLocationId(locationID, categoryID))
                        .build();
            }
            if(locationID > 0){
                logger.info(getLogMessage("getInventoryList", "Response Sent", "GET"));
                return Response.status(Response.Status.OK).entity(crud.getListByLocationId(locationID)).build();
            }
            logger.info(getLogMessage("getInventoryList", "Response Sent", "GET"));
            return Response.status(Response.Status.OK).entity(crud.getInventory()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.warn(getLogMessage("getInventoryList", "Can't Process Request", "GET"));
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // GET a specific inventory by ID
    @GET
    @Path("/{id}")
    public Response getInventoryById(@PathParam("id") int id) {
        try {
            logger.info(getLogMessage("getInventoryById", "Request Received", "GET"));
            logger.info(getLogMessage("getInventoryById", "Response Sent", "GET"));
            return Response.status(Response.Status.OK).entity(crud.getInventoryById(id)).build();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        logger.warn(getLogMessage("getInventoryById", "Can't Process Request", "GET"));
        return Response.status(Response.Status.BAD_REQUEST)
                .build();
    }

    // Create a new customer
    @POST
    @Path("/add")
    public Response createInventory(Inventory inventory) {

        try {
           logger.info(getLogMessage("createInventory", "Request received", "POST"));
            inventory.setId((count + 1));   // Generate a unique ID and add the customer to the list

            if(crud.createInventory(inventory)){
                logger.info(getLogMessage("createInventory", "Response Sent", "POST"));
                return Response.status(Response.Status.CREATED).entity(inventory).build();
            }
            else{
                logger.warn(getLogMessage("createInventory", "Can't Create Inventory", "POST"));
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
       logger.error(getLogMessage("createInventory", "Can't Create Inventory", "POST"));
        return Response.status(Response.Status.BAD_REQUEST)
                .build();
    }

    @GET
    @Path("/listByCategory")
    public Response fetchByCategory(@QueryParam("category") int categoryID){

        try {
            logger.info(getLogMessage("fetchByCategory", "Response Sent", "GET"));
            return Response.status(Response.Status.OK).entity(crud.getListByCategoryId(categoryID)).build();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        logger.error(getLogMessage("fetchByCategory", "Can't Fetch List by this Category", "GET"));
        return Response.status(Response.Status.BAD_REQUEST)
                .build();

    }

    @PUT
    @Path("/{id}")

    public Response updateInventory(@PathParam("id") int id, Inventory inventory){

        try {
            logger.info(getLogMessage("updateInventory", "Request Received", "PUT"));
            inventory.setId(id);
            if(crud.updateInventory(id, inventory)){
                logger.info(getLogMessage("updateInventory", "Response Sent", "PUT"));
                return Response.status(Response.Status.OK).entity(inventory).build();}
            else{
                logger.info(getLogMessage("updateInventory", "No inventory exists with id" + id, "PUT"));
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        logger.error(getLogMessage("updateInventory", "Can't Process Request", "PUT"));
        return Response.status(Response.Status.BAD_REQUEST)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteInventory(@PathParam("id") int id){

        try{
            logger.info(getLogMessage("deleteInventory", "Request Received", "DELETE"));
            if (crud.deleteInventory(id)){
                logger.info(getLogMessage("deleteInventory", "Response Sent", "DELETE"));
                return Response.status(Response.Status.OK).entity("Deleted").build();
            }
            logger.info(getLogMessage("deleteInventory", "No inventory exists with id" + id, "DELETE"));
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.error(getLogMessage("deleteInventory", "Can't process Request", "DELETE"));
        return Response.status(Response.Status.BAD_REQUEST)
                .build();
    }

    private String getLogMessage(String methodName, String message, String reqMethod) {
        return String.format("%s_%s_%s_%s",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                reqMethod,
                methodName,
                message);
    }

}