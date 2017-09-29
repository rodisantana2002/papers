/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
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
import model.entity.FilaSubmissao;
import model.entity.HistoricoFilaSubmissao;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("historico")
public class wsFacadeHistoricoFilaSubmissao extends wsFacadeGeneric<HistoricoFilaSubmissao>{
    @Context   
    SecurityContext securityContext;
    private UriInfo context;        private Date dtDataHoraAtual;
    private DateFormat dateFormat;
      
    
    public wsFacadeHistoricoFilaSubmissao() {
        super(new HistoricoFilaSubmissao());
        dtDataHoraAtual = new Date();        
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    
    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(HistoricoFilaSubmissao entity) {
        entity.setDtAtualizacao(dateFormat.format(dtDataHoraAtual).substring(0, 10));
        entity.setHoraAtualizacao(dateFormat.format(dtDataHoraAtual).substring(11, 19));     
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    public String update(HistoricoFilaSubmissao entity) {        
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        HistoricoFilaSubmissao historicoFilaSubmissao = new HistoricoFilaSubmissao();
        historicoFilaSubmissao.setId(id);
        return super.delete(historicoFilaSubmissao);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public HistoricoFilaSubmissao findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }
    
    @GET
    @Path("list/")    
    @Produces({"application/json"})
    public List<HistoricoFilaSubmissao> findAll() {          
        return super.findAll();  
    } 

    @GET
    @Path("submissao/{id}/list/")
    @Produces({"application/json"})
    public List<HistoricoFilaSubmissao> findAllByFilaSubmissao(@PathParam("id") Integer id) {
        Predicate<HistoricoFilaSubmissao> predFila = p -> (p.getFilaSubmissao().getId().equals(id));        
        
        HistoricoFilaSubmissao historicoFilaSubmissao = new HistoricoFilaSubmissao();
        FilaSubmissao filaSubmissao = new FilaSubmissao();
        filaSubmissao.setId(id);
        historicoFilaSubmissao.setFilaSubmissao(filaSubmissao);
        
        List<HistoricoFilaSubmissao> historicoFilaSubmissaos = super.findByFilter(historicoFilaSubmissao, predFila);
        Collections.reverse(historicoFilaSubmissaos);
        return historicoFilaSubmissaos;             
    }     
}
