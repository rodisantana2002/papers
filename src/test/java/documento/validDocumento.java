/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documento;

import business.core.Ivalidator;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import model.entity.Documento;
import model.entity.TipoDocumento;

/**
 *
 * @author Rodolfo
 */
public class validDocumento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Documento doc = new Documento();
        doc.setId(1);
        doc.setTitulo("asd");
        doc.setPalavrasChave("44");
        doc.setTipoDocumento(new TipoDocumento());
       // doc.setPessoa(new Pessoa());
                
        
        Ivalidator v = new validatorFactory<Documento>(doc).getValidator();
        clsPSR.prt(v.validarRegras(doc, "validarCamposObrigatorios").toString());
        System.exit(0);    
    }
    
}
