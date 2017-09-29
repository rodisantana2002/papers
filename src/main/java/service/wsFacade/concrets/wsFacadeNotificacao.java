/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import model.entity.Notificacao;
import model.entity.Pessoa;
import model.enumeration.Status;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("notificacao")
public class wsFacadeNotificacao extends wsFacadeGeneric<Notificacao> {
    @Context   
    SecurityContext securityContext;
    
    private UriInfo context;    
    private Date dtDataHoraAtual;
    private DateFormat dateFormat;
    
    
    public wsFacadeNotificacao() {
        super(new Notificacao());
        dtDataHoraAtual = new Date();        
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");                     
    }
    
    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(Notificacao entity) {
        entity.setDtCriacao(dateFormat.format(dtDataHoraAtual).substring(0, 10));
        entity.setHoraCriacao(dateFormat.format(dtDataHoraAtual).substring(11, 19));               
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    public String update(Notificacao entity) {        
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {return "não impmenetadado";}

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public Notificacao findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("list/")    
    @Produces({"application/json"})
    public List<Notificacao> findAll() {          
        return super.findAll();  
    } 
    
    @GET
    @Path("obterbyautor/{id}")    
    @Produces({"application/json"})
    public List<Notificacao> findAllByAutor(@PathParam("id") Integer id) {          
        Predicate<Notificacao> predDNotificacoesAutor = p -> p.getPessoa().getId().equals(id);        
        Notificacao notificacao = new Notificacao();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        notificacao.setPessoa(pessoa);
                
        return super.findByFilter(notificacao, predDNotificacoesAutor);  
    }       
    
    @GET
    @Path("obterpendentesbyautor/{id}")    
    @Produces({"application/json"})
    public List<Notificacao> findPendentesByAutor(@PathParam("id") Integer id) {          
        Predicate<Notificacao> predDNotificacoesAutor = p -> p.getPessoa().getId().equals(id) && p.getStatus().equals(Status.PENDENTE);
        Notificacao notificacao = new Notificacao();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        notificacao.setPessoa(pessoa);
                
        return super.findByFilter(notificacao, predDNotificacoesAutor);  
    }         
}
