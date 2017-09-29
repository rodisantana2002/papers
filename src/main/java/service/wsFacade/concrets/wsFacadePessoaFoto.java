/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

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
import model.entity.Pessoa;
import model.entity.PessoaFoto;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author rodolfosantana
 */
@Path("foto")
public class wsFacadePessoaFoto extends wsFacadeGeneric<PessoaFoto>{
    @Context   
    SecurityContext securityContext;
    private UriInfo context;    
    
    public wsFacadePessoaFoto(){
        super(new PessoaFoto());
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(PessoaFoto entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(PessoaFoto entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        PessoaFoto pessoaFoto = new PessoaFoto();        
        pessoaFoto.setId(id);
        return super.delete(pessoaFoto);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public PessoaFoto findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("obterbyautor/{id}")    
    @Produces({"application/json"})
    public PessoaFoto findByAutor(@PathParam("id") Integer id) {          
        Predicate<PessoaFoto> predDAutor = p -> p.getPessoa().getId().equals(id);        
        PessoaFoto pessoaFoto = new PessoaFoto();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoaFoto.setPessoa(pessoa);
        
        if (super.findByFilter(pessoaFoto, predDAutor).isEmpty()){
            return new PessoaFoto();
        }
        return super.findByFilter(pessoaFoto, predDAutor).get(0);  
    }     
}
