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
import model.entity.DocumentosPessoas;

/**
 *
 * @author Rodolfo
 */
public class ctrlDocumentosPessoas implements Icontroll<DocumentosPessoas>{
    private Ibusiness ibusiness;
    private Ivalidator<DocumentosPessoas> ivalidator;
    private List<String> msgs, regras; 
    
    public ctrlDocumentosPessoas(){
        ibusiness = new businessFactory<DocumentosPessoas>(new DocumentosPessoas()).getBusiness();
        ivalidator = new validatorFactory<DocumentosPessoas>(new DocumentosPessoas()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }

    public List<String> salvar(DocumentosPessoas entity) { 
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_REG_PARTICIPANTE_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(DocumentosPessoas entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_DEL_PARTICIPANTE_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }   
    
    @Override
    public DocumentosPessoas obter(Integer id) {
        DocumentosPessoas p = new DocumentosPessoas();
        p.setId(id);
        return (DocumentosPessoas) ibusiness.consultar(p);
    }

    @Override
    public List<DocumentosPessoas> obterTodos() {
        return ibusiness.listarAll(new DocumentosPessoas());
    }

    @Override
    public List<DocumentosPessoas> obterByFilter(DocumentosPessoas entity, Predicate<DocumentosPessoas> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }    
        
    private List<String> validarDelete(DocumentosPessoas entity){
        if(entity.getId()!=null){
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(DocumentosPessoas entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarDocumentoNaoCadastrado");
            regras.add("validarAutorNaoCadastrado");            
            regras.add("validarRegistroCadastrado");
            regras.add("validarAutorCoautorIguais");
        }       
        else{
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }     
}
