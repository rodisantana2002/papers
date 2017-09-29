/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filaSubmissao;

import model.entity.Destino;
import model.entity.Documento;
import model.entity.FilaSubmissao;
import model.entity.HistoricoFilaSubmissao;
import model.entity.Pessoa;
import model.enumeration.Situacao;
import repository.core.interfaces.Irepository;
import repository.factory.repositoryFactory;

/**
 *
 * @author Rodolfo
 */
public class repoFilaSubmissao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        
        Destino destino = new Destino();
        destino.setId(6);
        
        Documento doc = new Documento();
        doc.setId(44);
                
        FilaSubmissao filaSubmissao = new FilaSubmissao();
        filaSubmissao.setId(null);
        filaSubmissao.setDocumento(doc);
        filaSubmissao.setDestino(destino);
        filaSubmissao.setCriadoPor(pessoa);
        filaSubmissao.setSituacao(Situacao.INICIADO);
        filaSubmissao.setDtLimiteSubmissao("21/08/2017");
        filaSubmissao.setDtUltAtualizacao("21/08/2017");
        filaSubmissao.setHoraUltAtualizacao("");
        filaSubmissao.setIdioma("Portugues/Ingles");
        filaSubmissao.setVersao("1");

        HistoricoFilaSubmissao historicoFilaSubmissao = new HistoricoFilaSubmissao();
        historicoFilaSubmissao.setFilaSubmissao(filaSubmissao);
        historicoFilaSubmissao.setComentario("oi");
        historicoFilaSubmissao.setCriadoPor(pessoa);
        historicoFilaSubmissao.setDtAtualizacao("21/08/2017");
        historicoFilaSubmissao.setHoraAtualizacao("22:10");
        historicoFilaSubmissao.setSituacao(Situacao.INICIADO);
        historicoFilaSubmissao.setVersao("1");
                
       // filaSubmissao.addHistoricoFilaSubmissao(historicoFilaSubmissao);
                               
        Irepository d = new repositoryFactory<FilaSubmissao>(filaSubmissao).getRepository();
        FilaSubmissao filaSubmissao1 = (FilaSubmissao) d.salvar(filaSubmissao);
        
        System.exit(0);
    }
    
}
