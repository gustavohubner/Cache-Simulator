package cache_simulator;

public class Cache {

    private final int nSets, bSize, assoc, nEsp;
    private final char subst;
    private Conjunto conj[];
    private int contOcupados;
    
    public Cache(int nSets, int bSize, int assoc, char subst) {
        this.nSets = nSets;
        this.bSize = bSize;
        this.assoc = assoc;
        this.subst = subst;
        
        this.conj = new Conjunto[nSets];
        for (int i = 0; i < nSets; i++) {
            this.conj[i] = new Conjunto(assoc, subst);
        }
        
        this.nEsp = nSets * assoc;
        this.contOcupados = 0;
    }

    public int carregar(int end) {
        // @retorno:
        //0 = hit
        //1 = miss compulsorio;
        //2 = miss capacidade/conflito

        int end_conj = end / bSize;
        int indice = end_conj % nSets;
        int tag = end_conj / nSets;
        int retorno;

//        System.out.println("Endereco: " + end + "\tindice: "
//                + indice + "\tend. conjunto: " + end_conj
//                + "\ttag: " + tag);
        
        retorno = conj[indice].inserir(tag);
        
        if (retorno == 1) {
           contOcupados++; 
        }
        
        return retorno;
    }
    
    public boolean isFull() {
        if (contOcupados < nEsp) {
            return false;
        } else {
            return true;
        }
    }
}
