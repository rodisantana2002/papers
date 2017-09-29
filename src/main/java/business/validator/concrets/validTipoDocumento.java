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
import model.entity.TipoDocumento;

/**
 *
 * @author Rodolfo
 */
public class validTipoDocumento extends validGeneric<TipoDocumento>{
    
    public validTipoDocumento(){
        super();
    }
    
    public void validarCamposObrigatorios(TipoDocumento entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getDescricao()==null || entity.getDescricao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Descrição" + ").");            
        }
    }
    public void validarTipoDocumentoCadastrado(TipoDocumento entity){
        Ibusiness<TipoDocumento> ibusiness = new businessFactory<TipoDocumento>(entity).getBusiness();
        Predicate<TipoDocumento> predID = p -> p.getId().equals(entity.getId());
        Predicate<TipoDocumento> predDescricao = p -> p.getDescricao().equalsIgnoreCase(entity.getDescricao());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predDescricao).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
    }
    public void validarTipoDocumentoNaoCadastrado(TipoDocumento entity){
        Ibusiness<TipoDocumento> ibusiness = new businessFactory<TipoDocumento>(entity).getBusiness();
        Predicate<TipoDocumento> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }       
}
