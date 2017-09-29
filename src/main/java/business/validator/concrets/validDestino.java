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
import model.entity.Destino;
import model.entity.FilaSubmissao;

/**
 *
 * @author Rodolfo
 */
public class validDestino extends validGeneric<Destino>{
    
    public validDestino(){
        super();
    }
    
    public void validarCamposObrigatorios(Destino entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getDescricao()==null || entity.getDescricao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Descrição" + ").");            
        }
        if (entity.getClassificacao()==null || entity.getClassificacao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Classificação" + ").");                        
        }
    }
    public void validarDestinoCadastrado(Destino entity){
        Ibusiness<Destino> ibusiness = new businessFactory<Destino>(entity).getBusiness();
        Predicate<Destino> predID = p -> p.getId().equals(entity.getId());
        Predicate<Destino> predDescricao = p -> p.getDescricao().equalsIgnoreCase(entity.getDescricao());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predDescricao).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
    }
    public void validarDestinoNaoCadastrado(Destino entity){
        Ibusiness<Destino> ibusiness = new businessFactory<Destino>(entity).getBusiness();
        Predicate<Destino> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }     
    
    public void validarDestinoVinculado(Destino entity){
        Ibusiness<FilaSubmissao> ibusiness = new businessFactory<FilaSubmissao>(new FilaSubmissao()).getBusiness();        
        Predicate<FilaSubmissao> predDestino = p -> p.getDestino().getId().equals(entity.getId());
        FilaSubmissao filaSubmissao = new FilaSubmissao();
        filaSubmissao.setDestino(entity);
        
        if (!ibusiness.listarByFilter(filaSubmissao, predDestino).isEmpty()){
            getLstMsg().add(excMessages.STR_DEL_VINCULO_DESTINO);
        }          
    }     
}
