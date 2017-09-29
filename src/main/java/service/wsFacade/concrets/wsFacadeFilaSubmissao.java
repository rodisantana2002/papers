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
import model.entity.Documento;
import model.entity.FilaSubmissao;
import model.enumeration.Situacao;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("submissoes")
public class wsFacadeFilaSubmissao extends wsFacadeGeneric<FilaSubmissao> {

    @Context
    SecurityContext securityContext;
    private UriInfo context;
    private Date dtDataHoraAtual;
    private DateFormat dateFormat;

    public wsFacadeFilaSubmissao() {
        super(new FilaSubmissao());
        dtDataHoraAtual = new Date();        
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");             
        
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(FilaSubmissao entity) {
        //campos atualizados pelo servidor
        entity.setDtUltAtualizacao(dateFormat.format(dtDataHoraAtual).substring(0, 10));
        entity.setHoraUltAtualizacao(dateFormat.format(dtDataHoraAtual).substring(11, 19));        
        return super.create(entity);
    }
       
    @POST
    @Consumes({"application/json"})
    public String update(FilaSubmissao entity) {
        entity.setDtUltAtualizacao(dateFormat.format(dtDataHoraAtual).substring(0, 10));
        entity.setHoraUltAtualizacao(dateFormat.format(dtDataHoraAtual).substring(11, 19));        
        return super.update(entity);
    }    
        
    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        FilaSubmissao filaSubmissao = new FilaSubmissao();
        filaSubmissao.setId(id);
        return super.delete(filaSubmissao);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public FilaSubmissao findByID(@PathParam("id") Integer id) {
        return super.findByID(id);
    }

    @GET
    @Path("list/")
    @Produces({"application/json"})
    public List<FilaSubmissao> findAll() {
        return super.findAll();
    }

    @GET
    @Path("documento/{id}/list/")
    @Produces({"application/json"})
    public List<FilaSubmissao> findAllByDocumento(@PathParam("id") Integer id) {
        Predicate<FilaSubmissao> predFila = p -> ((p.getDocumento().getId().equals(id)) && (!p.getSituacao().equals(Situacao.REJEITADO)) && (!p.getSituacao().equals(Situacao.CANCELADO)) && (!p.getSituacao().equals(Situacao.PUBLICADO)));
        FilaSubmissao filaSubmissao = new FilaSubmissao();
        Documento documento = new Documento();
        documento.setId(id);        
        filaSubmissao.setDocumento(documento);
        List<FilaSubmissao> lstFilaSubmissaos = super.findByFilter(filaSubmissao, predFila);
        Collections.reverse(lstFilaSubmissaos);
        return lstFilaSubmissaos;
    }
    
    @GET
    @Path("documento/{id}/arquivadas")
    @Produces({"application/json"})
    public List<FilaSubmissao> findAllByDocumentoByArquivadas(@PathParam("id") Integer id) {
        Predicate<FilaSubmissao> predFila = p -> ((p.getDocumento().getId().equals(id)) && ((p.getSituacao().equals(Situacao.REJEITADO)) || (p.getSituacao().equals(Situacao.CANCELADO)) || (p.getSituacao().equals(Situacao.PUBLICADO))));
        FilaSubmissao filaSubmissao = new FilaSubmissao();
        Documento documento = new Documento();
        documento.setId(id);        
        filaSubmissao.setDocumento(documento);
        List<FilaSubmissao> lstFilaSubmissaos = super.findByFilter(filaSubmissao, predFila);
        Collections.reverse(lstFilaSubmissaos);
        return lstFilaSubmissaos;    
    }

}
