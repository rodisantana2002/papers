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
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import model.entity.Documento;

/**
 *
 * @author Rodolfo
 */
public class ctrlDocumento implements Icontroll<Documento> {
    private Ibusiness ibusiness;
    private Ivalidator<Documento> ivalidator;
    private List<String> msgs, regras;    

    public ctrlDocumento(){
        ibusiness = new businessFactory<Documento>(new Documento()).getBusiness();
        ivalidator = new validatorFactory<Documento>(new Documento()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();
    }

    public List<String> salvar(Documento entity) { 
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_REG_DOCUMENTO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(Documento entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_DEL_DOCUMENTO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }   
    
    @Override
    public Documento obter(Integer id) {
        Documento p = new Documento();
        p.setId(id);
        return (Documento) ibusiness.consultar(p);
    }

    @Override
    public List<Documento> obterTodos() {
        List listDocumentos = ibusiness.listarAll(new Documento());
        Collections.sort(listDocumentos);
        return listDocumentos;
    }

    @Override
    public List<Documento> obterByFilter(Documento entity, Predicate<Documento> predicate) {
        List listDocumentos = ibusiness.listarByFilter(entity, predicate);
        Collections.sort(listDocumentos);        
        return listDocumentos;
    }    
        
    private List<String> validarDelete(Documento entity){
        if(entity.getId()!=null){
            regras.add("validarRegistroNaoCadastrado");
            regras.add("validarDocumentoVinculado");            
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(Documento entity){
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
