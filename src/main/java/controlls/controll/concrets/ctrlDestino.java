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
import model.entity.Destino;

/**
 *
 * @author Rodolfo
 */
public class ctrlDestino implements Icontroll<Destino>{
    private Ibusiness ibusiness;
    private Ivalidator<Destino> ivalidator;
    private List<String> msgs, regras;
    
    public ctrlDestino(){
        ibusiness = new businessFactory<Destino>(new Destino()).getBusiness();
        ivalidator = new validatorFactory<Destino>(new Destino()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }

    @Override
    public List<String> salvar(Destino entity) {
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_REG_DESTINO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(Destino entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_DEL_DESTINO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public Destino obter(Integer id) {
        Destino destino = new Destino();
        destino.setId(id);
        return (Destino) ibusiness.consultar(destino);
    }

    @Override
    public List<Destino> obterTodos() {
        List<Destino> destinos = ibusiness.listarAll(new Destino());
        Collections.sort(destinos);
        return destinos;
    }

    @Override
    public List<Destino> obterByFilter(Destino entity, Predicate<Destino> predicate) {
        List<Destino> destinos = ibusiness.listarByFilter(entity, predicate); 
        Collections.sort(destinos);
        return destinos;
    }    
    
    private List<String> validarDelete(Destino entity){
        if(entity.getId()!=null){
            regras.add("validarDestinoNaoCadastrado");
            regras.add("validarDestinoVinculado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(Destino entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarDestinoCadastrado");
        }       
        else{
            regras.add("validarDestinoNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }      
}
