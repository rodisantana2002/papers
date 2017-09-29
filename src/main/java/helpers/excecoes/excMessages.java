package helpers.excecoes;
/**
 *
 * @author Rodolfo
 */
public class excMessages {
    //FORMATCAO 
    public static final String STR_DATA_REGISTRO_MAIOR_DATA_ATUAL   = "A Data de Registro não pode ser superior a Data Atual.";
    public static final String STR_DATA_PERIODO   = "A Data de Início deve ser anterior a Data Fim.";
    public static final String STR_DATA_INVALIDA  = "A data informada no campo não é válida.";    
    public static final String STR_HORA_PERIODO_INVALIDO = "A Hora de Entrada deve ser anterior a Hora de Saida.";
    
    //CRUDS
    public static final String STR_REG_JA_EXISTE = "Os dados informados já estão cadastrados.";    
    public static final String STR_REG_NAO_EXISTE = "Os dados informados não foram Localizados ou não estão cadastrados no sistema.";    
    public static final String STR_REG_EM_ANDAMENTO = "O documento já esta em tramite na fila de publicações.";    
    public static final String STR_DADOS_OBRIGATORIOS = "Os campos obrigatórios devem ser informados.";    
    public static final String STR_DATA_SUMISSAO_MENOR_DATA_PUBLICACAO  = "A Data Submissão deve ser anterior a Data Publicação.";
    public static final String STR_REG_AUTOR_IGUAL_COAUTOR = "Não é permitido o registro do Autor na relação de Participantes.";
    public static final String STR_REG_AUTOR_NAO_EXISTE = "O Autor informado não esta cadastrado no sistema.";
    public static final String STR_REG_DOCUMENTO_NAO_EXISTE = "O Artigo informado não esta cadastrado no sistema.";
       
    public static final String STR_OPERACAO_SUCESSO = "A operação foi realizada com sucesso";
    public static final String STR_OPERACAO_INSUCESSO = " A operação não pode ser realizada";
    
    //registro sucesso entity
    public static final String STR_REG_AUTOR_SUCESSO = "Autor registrado com sucesso";
    public static final String STR_REG_PARTICIPANTE_SUCESSO = "Participante registrado com sucesso";
    public static final String STR_REG_USUARIO_SUCESSO = "Usuário registrado com sucesso";
    public static final String STR_REG_DESTINO_SUCESSO = "Repositório registrado com sucesso";    
    public static final String STR_REG_DOCUMENTO_SUCESSO = "Artigo registrado com sucesso";
    public static final String STR_REG_SUBMISSAO_SUCESSO = "Submissão registrada com sucesso";
    public static final String STR_REG_TIPO_DOCUMENTO_SUCESSO = "Tipo Artigo registrado com sucesso";    
    public static final String STR_REG_USUARIO_ALTERAR_SENHA_SUCESSO = "Senha alterada com sucesso";    
    public static final String STR_REG_NOTIFICACAO_SUCESSO = "Notificação registrada com sucesso";
    
        
    //removido sucesso entity
    public static final String STR_DEL_AUTOR_SUCESSO = "Autor removido do sistema com sucesso";
    public static final String STR_DEL_PARTICIPANTE_SUCESSO = "Participante removido do sistema com sucesso";
    public static final String STR_DEL_USUARIO_SUCESSO = "Usuário removido do sistema com sucesso";
    public static final String STR_DEL_DESTINO_SUCESSO = "Repositório removido do sistema com sucesso";    
    public static final String STR_DEL_DOCUMENTO_SUCESSO = "Artigo removido do sistema com sucesso";
    public static final String STR_DEL_SUBMISSAO_SUCESSO = "Submissão removida do sistema com sucesso";
    public static final String STR_DEL_TIPO_DOCUMENTO_SUCESSO = "Tipo Artigo removido do sistema com sucesso";    
    public static final String STR_DEL_NOTIFICACAO_SUCESSO = "Notificação removida do sistema com sucesso";
    
    public static final String STR_DEL_VINCULO_DESTINO = "O Repositório não pode ser excluído pois já foi vinculado a uma Publicação. ";    
    public static final String STR_DEL_VINCULO_DOCUMENTO_FILA = "O Artigo não pode ser excluído pois já foi vinculado a uma Publicação. ";    
    public static final String STR_DEL_VINCULO_DOCUMENTO_PARTICIPANTE = "O Artigo não pode ser excluído pois já foi vinculado a um outro Participante. ";    
    public static final String STR_DEL_VINCULO_DOCUMENTO_FAVORITO = "O Artigo não pode ser excluído pois já foi adicionado na lista de Favoritos. ";    
    
    public static final String STR_DEL_VINCULO_PARTICIPANTE_DOCUMENTO = "O Autor não pode ser excluído pois já foi adicionado como participante de um Artigo. ";    
    
    //SERVICES
    public static final Integer STATUS_OK = 200;
    public static final Integer STATUS_CREATE = 201;
    public static final Integer STATUS_NO_CONTENT = 204;
    public static final Integer STATUS_BAD_REQUEST = 400;
    public static final Integer STATUS_NOT_FOUND = 404;
    public static final Integer STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final Integer STATUS_NOT_IMPLEMENTED = 501;
    public static final Integer STATUS_SERVICE_UNAVAILABLE = 503;
                 
    //UTILITÁRIOS
    public static final String STR_EMAIL_SUCESSO = "O email foi enviado com sucesso";    
    public static final String STR_EMAIL_INSUCESSO = "O email não pode ser enviado";        
    public static final String STR_EMAIL_NAO_CADASTRADO = "O email informado não esta registrado no sistema.";    
}
