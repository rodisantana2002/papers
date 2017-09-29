/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.validator.concrets;

import business.core.Ibusiness;
import business.factory.businessFactory;
import business.validator.abstracts.validGeneric;
import helpers.excecoes.excMessages;
import helpers.formatacao.clsTrataDatas;
import java.util.function.Predicate;
import model.entity.FilaSubmissao;
import model.entity.HistoricoFilaSubmissao;

/**
 *
 * @author Rodolfo
 */
public class validHistoricoFilaSubmissao extends validGeneric<HistoricoFilaSubmissao>{    
    private final clsTrataDatas trataDatas;
    
    public validHistoricoFilaSubmissao(){
        super();
        trataDatas = new clsTrataDatas();
    }
    
    public void validarCamposObrigatorios(HistoricoFilaSubmissao entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getSituacao()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Situação" + ").");            
        }
                
        if (entity.getVersao()==null || entity.getVersao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Versão" + ").");                        
        }
        
        if (entity.getDtAtualizacao()==null || entity.getDtAtualizacao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Data Atualização" + ").");                        
        }
        
        if (entity.getHoraAtualizacao()==null || entity.getHoraAtualizacao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Hora Atualização" + ").");                        
        }

        if (entity.getComentario()==null || entity.getComentario().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Versão" + ").");                        
        }
        
        if (entity.getCriadoPor()==null || entity.getCriadoPor().getId()==null || entity.getCriadoPor().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Criado Por" + ").");                        
        }        

        if (entity.getFilaSubmissao()==null || entity.getFilaSubmissao().getId()==null || entity.getFilaSubmissao().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Fila de Submissão" + ").");                        
        }        
    }

    public void validarDatas(HistoricoFilaSubmissao entity) {
        if (entity.getDtAtualizacao()!=null && !trataDatas.isDate(entity.getDtAtualizacao())) {
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data Atualização" + ").");
        }        
    }    

    public void validarRegistroCadastrado(HistoricoFilaSubmissao entity){
        Ibusiness<HistoricoFilaSubmissao> ibusiness = new businessFactory<HistoricoFilaSubmissao>(entity).getBusiness();
        Predicate<HistoricoFilaSubmissao> predID = p -> p.getId().equals(entity.getId());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        } 
    }

    public void validarRegistroNaoCadastrado(HistoricoFilaSubmissao entity){
        Ibusiness<HistoricoFilaSubmissao> ibusiness = new businessFactory<HistoricoFilaSubmissao>(entity).getBusiness();
        Predicate<HistoricoFilaSubmissao> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }    

    public void validarFilaSubmissoaNaoCadastrada(HistoricoFilaSubmissao entity){
        Ibusiness<FilaSubmissao> ibusiness = new businessFactory<FilaSubmissao>(entity.getFilaSubmissao()).getBusiness();
        Predicate<FilaSubmissao> predFila = p -> ((p.getId().equals(entity.getFilaSubmissao().getId())));
        
        if (ibusiness.listarByFilter(entity.getFilaSubmissao(), predFila).size()==0){
            getLstMsg().add(excMessages.STR_REG_DOCUMENTO_NAO_EXISTE);
        }  
    }  
}
