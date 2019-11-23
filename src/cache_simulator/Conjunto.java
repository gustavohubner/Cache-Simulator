package cache_simulator;

import java.util.Random;
import java.util.LinkedList;

public class Conjunto {

    private final Via vias[];
    private final char subst;
    private final LinkedList<Integer> lista;

    public Conjunto(int assoc, char subst) {
        this.vias = new Via[assoc];
        this.subst = subst;
        for (int i = 0; i < assoc; i++) {
            this.vias[i] = new Via();
        }
        this.lista = new LinkedList<>();
    }

    public int inserir(int tag) {
        // @retorno:
        //0 = hit
        //1 = miss compulsorio;
        //2 = miss capacidade/conflito
        //for (Via via : vias) {
        for (int i = 0; i < vias.length; i++) {
            if (vias[i].isVal()) {
                if (vias[i].getTag() == tag) {
                    if (subst == 'L') { //se LRU, o hit muda a próxima substituição
                        lista.removeFirstOccurrence(i);
                        lista.add(i);
                    }
                    return 0; // VALIDO E MESMA TAG = hit
                }
            } else {
                vias[i].setTag(tag);
                vias[i].setVal(true);
                lista.add(i);
                return 1; // NAO VALIDO = compulsorio
            }
        }//SAIU DO LOOP -> USA POL. DE SUBST.
        return substituir(tag);
    }

    private int substituir(int tag) {
        if (subst == 'R') {
            int nVia;
            Random r = new Random();
            nVia = r.nextInt(vias.length);
            vias[nVia].setTag(tag);
            return 2;
        }
        if (subst == 'F' || subst == 'L') {
            int nVia = lista.getFirst();
            lista.add(nVia);
            lista.removeFirst();
            
            vias[nVia].setTag(tag);
            
            return 2;
        }
        return -1;//erro de tag de politica
    }
}
