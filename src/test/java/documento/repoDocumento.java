/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documento;

import helpers.mensagens.clsPSR;
import model.entity.Documento;
import model.entity.Pessoa;
import model.entity.TipoDocumento;
import repository.core.interfaces.Irepository;
import repository.factory.repositoryFactory;

/**
 *
 * @author Rodolfo
 */
public class repoDocumento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(2);
        
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setId(1);
        
        Documento doc = new Documento();
        doc.setId(null);
        doc.setTitulo("Meu primeiro artigo cientifico");
        doc.setResumo("bçaajsdajshdakhjsdajsdhasdjhajsdhaksdjhaksd");
        doc.setPalavrasChave("palavra1, palavra2, palavra3 ...etc");
        doc.setTipoDocumento(tipoDocumento);
       // doc.setPessoa(pessoa);  
        
        Irepository d = new repositoryFactory<Documento>(doc).getRepository();
        //d.salvar(doc);
        
        Documento dd = new Documento();
        dd = (Documento) d.obterById(dd, 1);
        clsPSR.prt(dd.getTitulo());
        
        //System.exit(0);
    }
    
}
