/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import java.util.List;
import java.util.function.Predicate;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import model.entity.Destino;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("destino")
public class wsFacadeDestino extends wsFacadeGeneric<Destino>{

    @Context   
    SecurityContext securityContext;
    private UriInfo context;    

    public wsFacadeDestino() {
        super(new Destino());
    }
    
    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    public String create(Destino entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(Destino entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        Destino entity = new Destino();        
        entity.setId(id);
        return super.delete(entity);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public Destino findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<Destino> findAll() {
        return super.findAll();  
    }     
}
