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
        int end, resposta;
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
            DataInputStream bin = new DataInputStream(new FileInputStream(nome_arquivo));
            while (bin.available() > 0) {
                end = bin.readInt();
                resposta = cache.carregar(end);

                if (resposta == 2) { //testa se o miss é conflito ou cap
                    if (!cache.isFull()) {
                        resposta = 3; //miss de conflito
                    }
                }

                stats[resposta]++;
                cont++;
            }
            bin.close();
            int totalMiss = stats[1] + stats[2] + stats[3];
            if (flag_saida == 1) {
                System.out.println(cont + " "
                        + String.format("%.2f", (double) stats[0] / cont) + " "
                        + String.format("%.2f", (double) totalMiss / cont) + " "
                        + String.format("%.2f", (double) stats[1] / totalMiss) + " "
                        + String.format("%.2f", (double) stats[2] / totalMiss) + " "
                        + String.format("%.2f", (double) stats[3] / totalMiss));
            } else if (flag_saida == 0) {
                System.out.println("Nome do Arquivo:\t\t" + nome_arquivo + "\nTotal de acessos:\t\t" + cont
                        + "\nTotal de Hit:\t\t\t" + stats[0]
                        + "\nTaxa de hit:\t\t\t" + String.format("%.2f", ((double) stats[0] / cont) * 100)
                        + "%\nTotal de Miss:\t\t\t" + totalMiss
                        + "\nTaxa de miss:\t\t\t" + String.format("%.2f", ((double) totalMiss / cont) * 100)
                        + "%\nTotal de Miss Compulsório:\t" + stats[1]
                        + "\nTaxa de miss Compulsório:\t" + String.format("%.2f", ((double) stats[1] / totalMiss) * 100)
                        + "%\nTotal de Miss de Capacidade:\t" + stats[2]
                        + "\nTaxa de miss de capacidade:\t" + String.format("%.2f", ((double) stats[2] / totalMiss) * 100)
                        + "%\nTotal de Miss de Conflito:\t" + stats[3]
                        + "\nTaxa de miss de conflito:\t" + String.format("%.2f", ((double) stats[3] / totalMiss) * 100) + "%");
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
