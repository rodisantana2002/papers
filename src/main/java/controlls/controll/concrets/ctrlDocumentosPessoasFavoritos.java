/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls.controll.concrets;

import business.core.Ibusiness;
import business.core.Ivalidator;
import business.factory.businessFactory;
import business.factory.validatorFactory;
import controlls.core.Icontroll;
import helpers.excecoes.excMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import model.entity.DocumentosPessoasFavoritos;

/**
 *
 * @author Rodolfo
 */
public class ctrlDocumentosPessoasFavoritos implements Icontroll<DocumentosPessoasFavoritos>{
    private Ibusiness ibusiness;
    private Ivalidator<DocumentosPessoasFavoritos> ivalidator;
    private List<String> msgs, regras; 
    
    public ctrlDocumentosPessoasFavoritos(){
        ibusiness = new businessFactory<DocumentosPessoasFavoritos>(new DocumentosPessoasFavoritos()).getBusiness();
        ivalidator = new validatorFactory<DocumentosPessoasFavoritos>(new DocumentosPessoasFavoritos()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }

    public List<String> salvar(DocumentosPessoasFavoritos entity) { 
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(DocumentosPessoasFavoritos entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }   
    
    @Override
    public DocumentosPessoasFavoritos obter(Integer id) {
        DocumentosPessoasFavoritos p = new DocumentosPessoasFavoritos();
        p.setId(id);
        return (DocumentosPessoasFavoritos) ibusiness.consultar(p);
    }

    @Override
    public List<DocumentosPessoasFavoritos> obterTodos() {
        return ibusiness.listarAll(new DocumentosPessoasFavoritos());
    }

    @Override
    public List<DocumentosPessoasFavoritos> obterByFilter(DocumentosPessoasFavoritos entity, Predicate<DocumentosPessoasFavoritos> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }    
        
    private List<String> validarDelete(DocumentosPessoasFavoritos entity){
        if(entity.getId()!=null){
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(DocumentosPessoasFavoritos entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarRegistroCadastrado");
        }       
        else{
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }     
}
