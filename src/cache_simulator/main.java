package cache_simulator;

public class main {

    /**
     * @param args the command line arguments nsets: número de conjuntos na
     * cache;
     *
     * bsize: tamanho do bloco em bytes;
     *
     * assoc: grau de associatividade;
     *
     * substituição: política de substituição, que pode ser Random (R), FIFO (F)
     * ou L (LRU);
     *
     * flag_saida: flag que ativa o modo padrão de saída de dados;
     *
     * arquivo_de_entrada: arquivo com os endereços para acesso à cache.
     */
    public static void main(String[] args) {
        int nsets, bsize, assoc, flag_saida;
        char subst, flag;

        for (String arg : args) {
            System.out.print(arg + " ");
        }
    }

}
