package cache_simulator;

import java.util.Random;

public class Conjunto {

    private Via vias[];
    private int acessos;
    private final char subst;

    public Conjunto(int assoc, char subst) {
        this.vias = new Via[assoc];
        this.subst = subst;
        for (int i = 0; i < assoc; i++) {
            this.vias[i] = new Via();
        }
    }

    public int inserir(int tag) {
        // @retorno:
        //0 = hit
        //1 = miss compulsorio;
        //2 = miss capacidade/conflito
        for (Via via : vias) {
            if (via.isVal()) {
                if (via.getTag() == tag) {
                    return 0; // VALIDO E MESMA TAG = hit
                }
            } else {
                via.setTag(tag);
                via.setVal(true);
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
            Via via = vias[nVia];
            via.setTag(tag);
            return 2;
        }
        if (subst == 'F') {
            //do something
        }
        if (subst == 'L') {
            //do something
        }
        return -1;//erro de tag de politica
    }
}
