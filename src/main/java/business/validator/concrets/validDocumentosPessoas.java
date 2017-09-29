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
import model.entity.Documento;
import model.entity.DocumentosPessoas;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class validDocumentosPessoas extends validGeneric<DocumentosPessoas>{
    
    public validDocumentosPessoas(){
        super();
    }
    
       
    public void validarCamposObrigatorios(DocumentosPessoas entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }

        if (entity.getDocumento()==null || entity.getDocumento().getId()==null || entity.getDocumento().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Documento" + ").");                        
        }
        
        if (entity.getPessoa()==null || entity.getPessoa().getId()==null || entity.getPessoa().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Coautor" + ").");                        
        }        
    }

    public void validarAutorCoautorIguais(DocumentosPessoas entity){
        Ibusiness<Documento> ibusiness = new businessFactory<Documento>(entity.getDocumento()).getBusiness();
        Predicate<Documento> predCoautor = p -> ((p.getId().equals(entity.getDocumento().getId()) && 
                                                 (p.getPessoa().getId().equals(entity.getPessoa().getId()))));
        
        if (ibusiness.listarByFilter(entity.getDocumento(), predCoautor).size()>0){
            getLstMsg().add(excMessages.STR_REG_AUTOR_IGUAL_COAUTOR);
        }  
    }
    
    public void validarAutorNaoCadastrado(DocumentosPessoas entity){
        Ibusiness<Pessoa> ibusiness = new businessFactory<Pessoa>(entity.getPessoa()).getBusiness();
        Predicate<Pessoa> predAutor = p -> (p.getId().equals(entity.getPessoa().getId()));
        
        if (ibusiness.listarByFilter(entity.getPessoa(), predAutor).size()==0){
            getLstMsg().add(excMessages.STR_REG_AUTOR_NAO_EXISTE);
        }  
    }

    public void validarDocumentoNaoCadastrado(DocumentosPessoas entity){
        Ibusiness<Documento> ibusiness = new businessFactory<Documento>(entity.getDocumento()).getBusiness();
        Predicate<Documento> predDocumento = p -> ((p.getId().equals(entity.getDocumento().getId())));
        
        if (ibusiness.listarByFilter(entity.getDocumento(), predDocumento).size()==0){
            getLstMsg().add(excMessages.STR_REG_DOCUMENTO_NAO_EXISTE);
        }  
    }
        
    public void validarRegistroCadastrado(DocumentosPessoas entity){
        Ibusiness<DocumentosPessoas> ibusiness = new businessFactory<DocumentosPessoas>(entity).getBusiness();
        Predicate<DocumentosPessoas> predID = p -> p.getId().equals(entity.getId());
        Predicate<DocumentosPessoas> predCoautor = p -> ((p.getDocumento().getId().equals(entity.getDocumento().getId()) && 
                                                         (p.getPessoa().getId().equals(entity.getPessoa().getId()))));
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predCoautor).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
    }
    
    public void validarRegistroNaoCadastrado(DocumentosPessoas entity){
        Ibusiness<DocumentosPessoas> ibusiness = new businessFactory<DocumentosPessoas>(entity).getBusiness();
        Predicate<DocumentosPessoas> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }      
}
