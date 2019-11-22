package cache_simulator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    /**
     * @param args the command line arguments cache_simulator<nsets> <bsize>
     * <assoc> <substituição> <flag_saida> arquivo_de_entrada
     *
     * nsets: número de conjuntos na cache;
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
        int nSets, bSize, assoc, flag_saida;
        int end, resposta, end_conj, indice, tag;
        char subst;
        String nome_arquivo;
        Cache cache;

        nSets = Integer.parseInt(args[0]);
        bSize = Integer.parseInt(args[1]);
        assoc = Integer.parseInt(args[2]);
        subst = args[3].charAt(0);
        flag_saida = Integer.parseInt(args[4]);
        nome_arquivo = args[5];

        cache = new Cache(nSets, bSize, assoc, subst);

        try {
            DataInputStream bin = new DataInputStream(new FileInputStream(nome_arquivo));
            while (bin.available() > 0) {
                end = bin.readInt();
                resposta = cache.carregar(end);
                
            }
            bin.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public static int log2(int num) {
        if (num == 0) {
            return 0;
        }
        return 31 - Integer.numberOfLeadingZeros(num);
    }

}
