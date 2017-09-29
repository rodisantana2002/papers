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
import java.util.function.Predicate;
import model.entity.Notificacao;

/**
 *
 * @author Rodolfo
 */
public class validNotificacao extends validGeneric<Notificacao> {
    public validNotificacao(){
        super();
    }
    
    public void validarCamposObrigatorios(Notificacao entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getDtCriacao()==null || entity.getDtCriacao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Data de Criação" + ").");            
        }
        if (entity.getHoraCriacao()==null || entity.getHoraCriacao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Hora de Criação" + ").");            
        }
        
        if (entity.getStatus()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Sattus Notificacação" + ").");            
        }
        
        if (entity.getPessoa()==null || entity.getPessoa().getId()==null || entity.getPessoa().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Criado Por" + ").");                        
        }        
    }
    
    public void validarRegistroCadastrado(Notificacao entity){
        Ibusiness<Notificacao> ibusiness = new businessFactory<Notificacao>(entity).getBusiness();
        Predicate<Notificacao> predID = p -> p.getId().equals(entity.getId());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
    }
    
    public void validarRegistroNaoCadastrado(Notificacao entity){
        Ibusiness<Notificacao> ibusiness = new businessFactory<Notificacao>(entity).getBusiness();
        Predicate<Notificacao> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }        
}
