package cache_simulator;

public class Conjunto {

    Via vias[];
    int acessos;
    char subst;

    public Conjunto(int assoc, char subst) {
        this.vias = new Via[assoc];
        this.subst = subst;
        for (int i = 0; i < assoc; i++) {
            this.vias[i] = new Via();
        }
    }

    public int inserir(int end) {
        // @retorno:
        //0 = hit
        //1 = miss compulsorio;
        //2 = miss capacidade;
        //3 = miss conflito;
        return 0;
    }

}
