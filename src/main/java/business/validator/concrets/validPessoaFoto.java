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
import model.entity.PessoaFoto;

/**
 *
 * @author rodolfosantana
 */
public class validPessoaFoto extends validGeneric<PessoaFoto>{
    
    public validPessoaFoto(){
        super();
    }
    
    public void validarCamposObrigatorios(PessoaFoto entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
    }
    
    public void validarPessoaFotoCadastrada(PessoaFoto entity){
        Ibusiness<PessoaFoto> ibusiness = new businessFactory<PessoaFoto>(entity).getBusiness();
        Predicate<PessoaFoto> predID = p -> p.getId().equals(entity.getId());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
    }
    
    public void validarPessoaFotoNaoCadastrada(PessoaFoto entity){
        Ibusiness<PessoaFoto> ibusiness = new businessFactory<PessoaFoto>(entity).getBusiness();
        Predicate<PessoaFoto> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }        
}
