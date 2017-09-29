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
import model.entity.Notificacao;

/**
 *
 * @author Rodolfo
 */
public class ctrlNotificacao implements Icontroll<Notificacao> {
    private Ibusiness ibusiness;
    private Ivalidator<Notificacao> ivalidator;
    private List<String> msgs, regras;
    
    public ctrlNotificacao(){
        ibusiness = new businessFactory<Notificacao>(new Notificacao()).getBusiness();
        ivalidator = new validatorFactory<Notificacao>(new Notificacao()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }

    @Override
    public List<String> salvar(Notificacao entity) {
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_REG_NOTIFICACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(Notificacao entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_DEL_NOTIFICACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public Notificacao obter(Integer id) {
        Notificacao notificacao = new Notificacao();
        notificacao.setId(id);
        return (Notificacao) ibusiness.consultar(notificacao);
    }

    @Override
    public List<Notificacao> obterTodos() {
        List<Notificacao> notificacaoes = ibusiness.listarAll(new Notificacao());
        Collections.reverse(notificacaoes);
        return notificacaoes;
    }

    @Override
    public List<Notificacao> obterByFilter(Notificacao entity, Predicate<Notificacao> predicate) {
        List<Notificacao> notificacoes = ibusiness.listarByFilter(entity, predicate); 
        Collections.reverse(notificacoes);
        return notificacoes;
    }    
    
    private List<String> validarDelete(Notificacao entity){
        if(entity.getId()!=null){
            regras.add("validarRegistroNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(Notificacao entity){
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
