package helpers.types;

/**
 * @author Rodolfo
 */
public class clsConstants {
    //Constantes de tipos de dados
    public static final int D_BOOLEAN    = 1;
    public static final int D_CHAR       = 2;
    public static final int D_BYTE       = 3;
    public static final int D_SHORT      = 4;
    public static final int D_INT        = 5;
    public static final int D_LONG       = 6;
    public static final int D_FLOAT      = 7;
    public static final int D_DOUBLE     = 8;
    public static final int D_STRING     = 9;
    public static final int D_DATETIME   = 10;
    public static final int D_ARRAYLIST  = 11;
    public static final int D_OBJECT     = 12;
    public static final int D_HORA       = 13;

    //Constantes de tipos de operações com dados
    public static final int TO_INCLUIR   = 1;
    public static final int TO_ALTERAR   = 2;
    public static final int TO_CONSULTAR = 3;
    public static final int TO_EXCLUIR   = 4;
    public static final int TO_PESQUISAR = 5;

    //Constantes de tipos de mensagens
    public static final int MSG_INFORMACAO   = 1;
    public static final int MSG_EXCLAMACAO   = 2;
    public static final int MSG_INTERROGACAO = 3;
    public static final int MSG_ERRO         = 4;

    //Constantes Booleanas
    public static final int BLN_SIM      = 1;
    public static final int BLN_NAO      = 0;

    //constantes para o numero de regs a serem carregados pelas listas genericas
    public static final int ROWS_DEFAULT        = 0;  //Todos
    public static final int ROWS_MINIMO         = 50;
    public static final int ROWS_MAXIMO         = 300;

    //constantes para tratamento de datas
    public static final int DT_ULT_DIA_MES[]       = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static final int DT_MES_FEV             = 2;
    public static final int DT_ULT_DIA_MES_FEV_BIX = 29;
    public static final int DT_ANO_MINIMO          = 1900;        

    //Tipo de Sinais para identificar Horario
    public static final String SINAL_POSITIVO = "+";
    public static final String SINAL_NEGATIVO = "-"; 
            
    public static final String METODO_ID = "getId";
}
