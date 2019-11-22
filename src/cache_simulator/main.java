package cache_simulator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
        int stats[], cont;
        char subst;
        String nome_arquivo;
        Cache cache;

        nSets = Integer.parseInt(args[0]);
        bSize = Integer.parseInt(args[1]);
        assoc = Integer.parseInt(args[2]);
        subst = args[3].charAt(0);
        flag_saida = Integer.parseInt(args[4]);
        nome_arquivo = args[5];

        stats = new int[4];
        cont = 0;

        cache = new Cache(nSets, bSize, assoc, subst);

        try {
            DataInputStream bin = new DataInputStream(new FileInputStream("bin_10000.bin"));
            while (bin.available() > 0) {
                end = bin.readInt();
                resposta = cache.carregar(end);
                
                if (resposta == 2) { //testa se o miss é conflito ou cap
                    if (cache.isFull()) {
                        resposta = 2; //miss de capacidade
                    }
                    else {
                        resposta = 3; //miss de conflito
                    }
                }
                
                stats[resposta]++;
                cont++;
            }
            bin.close();
            int totalMiss = stats[1] + stats[2] + stats[3];
            System.err.println(cont + " " + (double) stats[0] / cont + " "
                    + (double) totalMiss / cont + " " 
                    + (double) stats[1] / totalMiss + " " 
                    + (double) stats[2] / totalMiss + " " 
                    + (double) stats[3] / totalMiss);
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
