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
import model.entity.DocumentosPessoas;
import model.entity.Pessoa;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("docspessoas")
public class wsFacadeDocumentosPessoas extends wsFacadeGeneric<DocumentosPessoas>{
    @Context   
    SecurityContext securityContext;
    private UriInfo context;    

    public wsFacadeDocumentosPessoas(){
        super(new DocumentosPessoas());
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(DocumentosPessoas entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    public String update(DocumentosPessoas entity) {        
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        DocumentosPessoas documentosPessoas = new DocumentosPessoas();                
        documentosPessoas.setId(id);
        return super.delete(documentosPessoas);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public DocumentosPessoas findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("list/")    
    @Produces({"application/json"})
    public List<DocumentosPessoas> findAll() {          
        return super.findAll();  
    }         
    
    @GET
    @Path("autor/{id}/list/")
    @Produces({"application/json"})
    public List<DocumentosPessoas> findAllDocsByAutor(@PathParam("id") Integer id) {
        Predicate<DocumentosPessoas> predDocs = p -> (p.getPessoa().getId().equals(id));        
        
        DocumentosPessoas documentosPessoas = new DocumentosPessoas();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        documentosPessoas.setPessoa(pessoa);
        
        return super.findByFilter(documentosPessoas, predDocs);             
    }       
    
    @GET
    @Path("documento/{id}/list/")
    @Produces({"application/json"})
    public List<DocumentosPessoas> findAllAutorsByDoc(@PathParam("id") Integer id) {
        Predicate<DocumentosPessoas> predAutors = p -> (p.getDocumento().getId().equals(id));        
        
        DocumentosPessoas documentosPessoas = new DocumentosPessoas();
        Documento documento = new Documento();
        documento.setId(id);
        documentosPessoas.setDocumento(documento);
        
        return super.findByFilter(documentosPessoas, predAutors);             
    }       
    
}
