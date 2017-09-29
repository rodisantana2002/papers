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
import model.entity.Documento;
import model.entity.Pessoa;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("documento")
public class wsFacadeDocumento extends wsFacadeGeneric<Documento>{
    @Context   
    SecurityContext securityContext;
    private UriInfo context;    
    
    public wsFacadeDocumento() {
        super(new Documento());
    }
    
    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(Documento entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    public String update(Documento entity) {        
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        Documento documento = new Documento();                
        documento.setId(id);
        return super.delete(documento);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public Documento findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("list/")    
    @Produces({"application/json"})
    public List<Documento> findAll() {          
        return super.findAll();  
    } 
    
    @GET
    @Path("obterbyautor/{id}")    
    @Produces({"application/json"})
    public List<Documento> findAllByAutor(@PathParam("id") Integer id) {          
        Predicate<Documento> predDocumentosAutor = p -> p.getPessoa().getId().equals(id);        
        Documento documento = new Documento();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        documento.setPessoa(pessoa);
                
        return super.findByFilter(documento, predDocumentosAutor);  
    }     
}
