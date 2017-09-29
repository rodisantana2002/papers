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
import model.entity.DocumentosPessoasFavoritos;

/**
 *
 * @author Rodolfo
 */
public class validDocumentosPessoasFavoritos extends validGeneric<DocumentosPessoasFavoritos>{
    
    public validDocumentosPessoasFavoritos(){
        super();
    }
    
       
    public void validarCamposObrigatorios(DocumentosPessoasFavoritos entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }

        if (entity.getDocumento()==null || entity.getDocumento().getId()==null || entity.getDocumento().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Documento" + ").");                        
        }
        
        if (entity.getPessoa()==null || entity.getPessoa().getId()==null || entity.getPessoa().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Autor" + ").");                        
        }        
    }

    public void validarRegistroCadastrado(DocumentosPessoasFavoritos entity){
        Ibusiness<DocumentosPessoasFavoritos> ibusiness = new businessFactory<DocumentosPessoasFavoritos>(entity).getBusiness();
        Predicate<DocumentosPessoasFavoritos> predID = p -> p.getId().equals(entity.getId());
        Predicate<DocumentosPessoasFavoritos> predCoautor = p -> ((p.getDocumento().getId().equals(entity.getDocumento().getId()) && 
                                                        (p.getPessoa().getId().equals(entity.getPessoa().getId()))));
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predCoautor).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
    }
    
    public void validarRegistroNaoCadastrado(DocumentosPessoasFavoritos entity){
        Ibusiness<DocumentosPessoasFavoritos> ibusiness = new businessFactory<DocumentosPessoasFavoritos>(entity).getBusiness();
        Predicate<DocumentosPessoasFavoritos> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }      
}
