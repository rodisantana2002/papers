/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import java.util.List;
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
import model.entity.TipoDocumento;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("tipodocumento")
public class wsFacadeTipoDocumento extends wsFacadeGeneric<TipoDocumento>{
    
    @Context   
    SecurityContext securityContext;
    private UriInfo context;    

    public wsFacadeTipoDocumento() {
        super(new TipoDocumento());
    }
    
    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    public String create(TipoDocumento entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(TipoDocumento entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        TipoDocumento entity = new TipoDocumento();        
        entity.setId(id);
        return super.delete(entity);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public TipoDocumento findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<TipoDocumento> findAll() {
        return super.findAll();  
    }     
}
