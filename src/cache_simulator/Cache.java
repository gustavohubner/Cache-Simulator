package cache_simulator;

public class Cache {

    int nSets, bSize, assoc;
    char subst;
    Conjunto conj[];

    public Cache(int nSets, int bSize, int assoc, char subst) {
        this.nSets = nSets;
        this.bSize = bSize;
        this.assoc = assoc;
        this.subst = subst;

        this.conj = new Conjunto[nSets];
        for (int i = 0; i < nSets; i++) {
            this.conj[i] = new Conjunto(assoc, subst);
        }
    }

    public int carregar(int end) {
        // @retorno:
        //0 = hit
        //1 = miss compulsorio;
        //2 = miss capacidade;
        //3 = miss conflito;
        return 0;
    }
}
