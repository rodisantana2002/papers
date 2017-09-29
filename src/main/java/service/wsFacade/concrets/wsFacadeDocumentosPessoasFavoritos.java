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
import model.entity.DocumentosPessoasFavoritos;
import model.entity.Pessoa;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("favoritos")
public class wsFacadeDocumentosPessoasFavoritos extends wsFacadeGeneric<DocumentosPessoasFavoritos>{
    @Context   
    SecurityContext securityContext;
    private UriInfo context;    

    public wsFacadeDocumentosPessoasFavoritos(){
        super(new DocumentosPessoasFavoritos());
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(DocumentosPessoasFavoritos entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    public String update(DocumentosPessoasFavoritos entity) {        
        return super.update(entity);
    }

    @DELETE
    @Path("autor/{autoid}/documento/{docid}")
    public String delete(@PathParam("autoid") Integer autoid, @PathParam("docid") Integer docid) {
        DocumentosPessoasFavoritos documentosPessoasFavoritos = new DocumentosPessoasFavoritos();                        
        Documento documento = new Documento();
        documento.setId(docid);
        Pessoa pessoa = new Pessoa();
        pessoa.setId(autoid);
        documentosPessoasFavoritos.setDocumento(documento);
        documentosPessoasFavoritos.setPessoa(pessoa);
        Predicate<DocumentosPessoasFavoritos> predData = p -> (p.getPessoa().getId().equals(autoid) && p.getDocumento().getId().equals(docid));      
        
        documentosPessoasFavoritos = super.findByFilter(documentosPessoasFavoritos, predData).get(0);
        
        return super.delete(documentosPessoasFavoritos);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public DocumentosPessoasFavoritos findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("list/")    
    @Produces({"application/json"})
    public List<DocumentosPessoasFavoritos> findAll() {          
        return super.findAll();  
    }         
        
    @GET
    @Path("autor/{id}/list/")
    @Produces({"application/json"})
    public List<DocumentosPessoasFavoritos> findAllFavoritosByAutor(@PathParam("id") Integer id) {
        Predicate<DocumentosPessoasFavoritos> predData = p -> (p.getPessoa().getId().equals(id));        
        
        DocumentosPessoasFavoritos documentosPessoasFavoritos = new DocumentosPessoasFavoritos();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        documentosPessoasFavoritos.setPessoa(pessoa);
        
        return super.findByFilter(documentosPessoasFavoritos, predData);             
    }           

    @Override
    public String delete(Integer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
