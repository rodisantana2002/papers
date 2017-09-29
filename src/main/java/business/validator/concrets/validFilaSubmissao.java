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
import model.entity.Documento;
import model.entity.FilaSubmissao;
import model.enumeration.Situacao;

/**
 *
 * @author Rodolfo
 */
public class validFilaSubmissao extends validGeneric<FilaSubmissao>{
    private final clsTrataDatas trataDatas;
    
    public validFilaSubmissao(){
        super();
        trataDatas = new clsTrataDatas();
    }
    
    public void validarCamposObrigatorios(FilaSubmissao entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getSituacao()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Situação" + ").");            
        }
        
        if (entity.getDocumento()==null || entity.getDocumento().getId()==null || entity.getDocumento().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Documento" + ").");                        
        }
        
        if (entity.getVersao()==null || entity.getVersao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Versão" + ").");                        
        }
        
        if (entity.getDtLimiteSubmissao()==null || entity.getDtLimiteSubmissao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Data Limite Submissão" + ").");                        
        }
        
        if (entity.getDestino()==null || entity.getDestino().getId()==null || entity.getDestino().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Destino" + ").");                        
        }        
                
        if (entity.getCriadoPor()==null || entity.getCriadoPor().getId()==null || entity.getCriadoPor().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Criado Por" + ").");                        
        }        
    }
    
    public void validarDatas(FilaSubmissao entity) {
        if (entity.getDtLimiteSubmissao()!=null && !trataDatas.isDate(entity.getDtLimiteSubmissao())) {
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data Limite Submissão" + ").");
        }
        
        if (entity.getDtPublicacao()!=null && !trataDatas.isDate(entity.getDtPublicacao())) {
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data Publicação" + ").");
        }
        
        if (entity.getDtUltAtualizacao()!=null && !trataDatas.isDate(entity.getDtUltAtualizacao())) {
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data Última Atualização" + ").");
        }
        
        if (entity.getDtLimiteSubmissao()!=null && entity.getDtPublicacao()!=null){
            if (trataDatas.isDateMaior(trataDatas.parseDataBraIso(entity.getDtLimiteSubmissao()), trataDatas.parseDataBraIso(entity.getDtPublicacao()))){
                getLstMsg().add(excMessages.STR_DATA_SUMISSAO_MENOR_DATA_PUBLICACAO);             
            }                 
        }
    }    

    public void validarRegistroCadastrado(FilaSubmissao entity){
        Ibusiness<FilaSubmissao> ibusiness = new businessFactory<FilaSubmissao>(entity).getBusiness();
        Predicate<FilaSubmissao> predID = p -> p.getId().equals(entity.getId());
        Predicate<FilaSubmissao> predDocExistFila = p -> p.getDocumento().getId().equals(entity.getDocumento().getId()) && (
                                                         p.getSituacao().equals(Situacao.INICIADO) || 
                                                         p.getSituacao().equals(Situacao.EM_AVALIACAO_ORIENTADOR) ||
                                                         p.getSituacao().equals(Situacao.AGUARDANDO_AJUSTES) ||
                                                         p.getSituacao().equals(Situacao.APROVADA_PUBLICACAO) ||
                                                         p.getSituacao().equals(Situacao.LIBERADO_ORIENTADOR) ||
                                                         p.getSituacao().equals(Situacao.SUBMETIDO_PUBLICACAO));
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        } 
        
        if (ibusiness.listarByFilter(entity, predDocExistFila).size()>0){
            getLstMsg().add(excMessages.STR_REG_EM_ANDAMENTO);
        }  
    }
    
    public void validarRegistroNaoCadastrado(FilaSubmissao entity){
        Ibusiness<FilaSubmissao> ibusiness = new businessFactory<FilaSubmissao>(entity).getBusiness();
        Predicate<FilaSubmissao> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }    
    
    public void validarDocumentoNaoCadastrado(FilaSubmissao entity){
        Ibusiness<Documento> ibusiness = new businessFactory<Documento>(entity.getDocumento()).getBusiness();
        Predicate<Documento> predDocumento = p -> ((p.getId().equals(entity.getDocumento().getId())));
        
        if (ibusiness.listarByFilter(entity.getDocumento(), predDocumento).size()==0){
            getLstMsg().add(excMessages.STR_REG_DOCUMENTO_NAO_EXISTE);
        }  
    }          
}
