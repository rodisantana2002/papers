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
import model.entity.FilaSubmissao;

/**
 *
 * @author Rodolfo
 */
public class ctrlFilaSubmissao implements Icontroll<FilaSubmissao>{
    private Ibusiness ibusiness;
    private Ivalidator<FilaSubmissao> ivalidator;
    private List<String> msgs, regras;   
    
    public ctrlFilaSubmissao(){
        ibusiness = new businessFactory<FilaSubmissao>(new FilaSubmissao()).getBusiness();        
        ivalidator = new validatorFactory<FilaSubmissao>(new FilaSubmissao()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }
    
    @Override
    public List<String> salvar(FilaSubmissao entity) {
        msgs = validar(entity);        
        if (msgs.isEmpty()){                        
            FilaSubmissao filaSubmissao = (FilaSubmissao) ibusiness.salvar(entity);
            if (filaSubmissao!=null){
                msgs.add(excMessages.STR_REG_SUBMISSAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(FilaSubmissao entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_DEL_SUBMISSAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }   
    
    @Override
    public FilaSubmissao obter(Integer id) {
        FilaSubmissao p = new FilaSubmissao();
        p.setId(id);
        return (FilaSubmissao) ibusiness.consultar(p);
    }

    @Override
    public List<FilaSubmissao> obterTodos() {
        return ibusiness.listarAll(new FilaSubmissao());
    }

    @Override
    public List<FilaSubmissao> obterByFilter(FilaSubmissao entity, Predicate<FilaSubmissao> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }    
         
    private List<String> validarDelete(FilaSubmissao entity){
        if(entity.getId()!=null){
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(FilaSubmissao entity){
        regras.add("validarCamposObrigatorios");    
        regras.add("validarDatas");    
        if(entity.getId()==null){
            regras.add("validarDocumentoNaoCadastrado");            
            regras.add("validarRegistroCadastrado");
        }       
        else{
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }       
}
