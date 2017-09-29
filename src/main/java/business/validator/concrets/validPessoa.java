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
import model.entity.DocumentosPessoas;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class validPessoa extends validGeneric<Pessoa>{
    
    public validPessoa(){
        super();
    }
    
    public void validarCamposObrigatorios(Pessoa entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getPrimeiroNome()==null || entity.getPrimeiroNome().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Primeiro nome" + ").");            
        }
        if (entity.getSegundoNome()==null || entity.getSegundoNome().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Segundo nome" + ").");                        
        }
        if (entity.getEmail()==null || entity.getEmail().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Email" + ").");                        
        }
    }
    public void validarPessoaCadastrada(Pessoa entity){
        Ibusiness<Pessoa> ibusiness = new businessFactory<Pessoa>(entity).getBusiness();
        Predicate<Pessoa> predID = p -> p.getId().equals(entity.getId());
        Predicate<Pessoa> predEMail = p -> p.getEmail().equalsIgnoreCase(entity.getEmail());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predEMail).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " (Email ja foi vinculado a outro Autor).");
        }  
    }
    public void validarPessoaNaoCadastrada(Pessoa entity){
        Ibusiness<Pessoa> ibusiness = new businessFactory<Pessoa>(entity).getBusiness();
        Predicate<Pessoa> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }    
    
    public void validarDocumentoVinculado(Pessoa entity){       
        ///documento pessoas
        Ibusiness<DocumentosPessoas> ibusinessPessoas = new businessFactory<DocumentosPessoas>(new DocumentosPessoas()).getBusiness();                
        Predicate<DocumentosPessoas> predDocPessoas = p -> p.getPessoa().getId().equals(entity.getId());
        DocumentosPessoas documentosPessoas = new DocumentosPessoas();
        documentosPessoas.setPessoa(entity);
        
        if (!ibusinessPessoas.listarByFilter(documentosPessoas, predDocPessoas).isEmpty()){
            getLstMsg().add(excMessages.STR_DEL_VINCULO_PARTICIPANTE_DOCUMENTO);
        }          
    }     
}
