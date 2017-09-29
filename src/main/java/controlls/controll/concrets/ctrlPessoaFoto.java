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
import model.entity.PessoaFoto;

/**
 *
 * @author rodolfosantana
 */
public class ctrlPessoaFoto implements Icontroll<PessoaFoto>{
    private Ibusiness ibusiness;
    private Ivalidator<PessoaFoto> ivalidator;
    private List<String> msgs, regras;
    
    public ctrlPessoaFoto(){
        ibusiness = new businessFactory<PessoaFoto>(new PessoaFoto()).getBusiness();
        ivalidator = new validatorFactory<PessoaFoto>(new PessoaFoto()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();
    }
   
    public List<String> salvar(PessoaFoto entity) { 
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_REG_AUTOR_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(PessoaFoto entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_DEL_AUTOR_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }   
    
    @Override
    public PessoaFoto obter(Integer id) {
        PessoaFoto p = new PessoaFoto();
        p.setId(id);
        return (PessoaFoto) ibusiness.consultar(p);
    }

    @Override
    public List<PessoaFoto> obterTodos() {
        return null; 
    }

    @Override
    public List<PessoaFoto> obterByFilter(PessoaFoto entity, Predicate<PessoaFoto> predicate) {
        return  ibusiness.listarByFilter(entity, predicate);
    }    
        
    private List<String> validarDelete(PessoaFoto entity){
        if(entity.getId()!=null){
            regras.add("validarPessoaFotoNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(PessoaFoto entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarPessoaFotoCadastrada");
        }       
        else{
            regras.add("validarPessoaFotoNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }        
}
