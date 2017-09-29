/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoDocumento;

import controlls.controll.concrets.ctrlTipoDocumento;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import model.entity.TipoDocumento;
import service.wsFacade.concrets.wsFacadeTipoDocumento;

/**
 *
 * @author Rodolfo
 */
public class controll_tipodocumento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TipoDocumento documento = new TipoDocumento();
        documento.setId(1);
        
//        ctrTipoDocumento tipoDocumento = new ctrTipoDocumento();
//        ArrayList<TipoDocumento> lst = (ArrayList<TipoDocumento>) tipoDocumento.obterTodos();
//        clsPSR.prt(String.valueOf(lst.isEmpty()));
            wsFacadeTipoDocumento facadeTipoDocumento = new wsFacadeTipoDocumento();
            //ArrayList<TipoDocumento> lst = (ArrayList<TipoDocumento>) facadeTipoDocumento.findByID(1);            
            documento = facadeTipoDocumento.findByID(1);            

    }
    
}
