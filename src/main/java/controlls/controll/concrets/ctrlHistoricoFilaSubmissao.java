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
import model.entity.HistoricoFilaSubmissao;

/**
 *
 * @author Rodolfo
 */
public class ctrlHistoricoFilaSubmissao implements Icontroll<HistoricoFilaSubmissao>{
    private Ibusiness ibusiness;
    private Ivalidator<HistoricoFilaSubmissao> ivalidator;
    private List<String> msgs, regras;   
    
    public ctrlHistoricoFilaSubmissao(){
        ibusiness = new businessFactory<HistoricoFilaSubmissao>(new HistoricoFilaSubmissao()).getBusiness();
        ivalidator = new validatorFactory<HistoricoFilaSubmissao>(new HistoricoFilaSubmissao()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }
    
    @Override
    public List<String> salvar(HistoricoFilaSubmissao entity) {
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
    public List<String> deletar(HistoricoFilaSubmissao entity) {
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
    public HistoricoFilaSubmissao obter(Integer id) {
        HistoricoFilaSubmissao p = new HistoricoFilaSubmissao();
        p.setId(id);
        return (HistoricoFilaSubmissao) ibusiness.consultar(p);
    }

    @Override
    public List<HistoricoFilaSubmissao> obterTodos() {
        return ibusiness.listarAll(new HistoricoFilaSubmissao());
    }

    @Override
    public List<HistoricoFilaSubmissao> obterByFilter(HistoricoFilaSubmissao entity, Predicate<HistoricoFilaSubmissao> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }    
         
    private List<String> validarDelete(HistoricoFilaSubmissao entity){
        if(entity.getId()!=null){
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(HistoricoFilaSubmissao entity){
        regras.add("validarCamposObrigatorios");    
        regras.add("validarDatas");    
        if(entity.getId()==null){
            regras.add("validarSubmissaoNaoCadastrada");            
            regras.add("validarRegistroCadastrado");
        }       
        else{
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }  
}
