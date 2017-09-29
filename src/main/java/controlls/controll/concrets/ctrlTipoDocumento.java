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
import model.entity.TipoDocumento;

/**
 *
 * @author Rodolfo
 */
public class ctrlTipoDocumento implements Icontroll<TipoDocumento>{
    private Ibusiness ibusiness;
    private Ivalidator<TipoDocumento> ivalidator;
    private List<String> msgs, regras;    

    public ctrlTipoDocumento(){
        ibusiness = new businessFactory<TipoDocumento>(new TipoDocumento()).getBusiness();
        ivalidator = new validatorFactory<TipoDocumento>(new TipoDocumento()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();           
    }
    
    @Override
    public List<String> salvar(TipoDocumento entity) {
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
    public List<String> deletar(TipoDocumento entity) {
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
    public TipoDocumento obter(Integer id) {
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setId(id);
        return (TipoDocumento) ibusiness.consultar(tipoDocumento);

    }

    @Override
    public List<TipoDocumento> obterTodos() {
        return ibusiness.listarAll(new TipoDocumento());
    }

    @Override
    public List<TipoDocumento> obterByFilter(TipoDocumento entity, Predicate<TipoDocumento> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }
    
    private List<String> validarDelete(TipoDocumento entity){
        if(entity.getId()!=null){
            regras.add("validarTipoDocumentoNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(TipoDocumento entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarTipoDocumentoCadastrado");
        }       
        else{
            regras.add("validarTipoDocumentoNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }      
}
