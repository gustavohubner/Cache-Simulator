package cache_simulator;

public class Via {

    private int tag;
    private boolean val;

    public Via() {
        this.tag = 0;
        this.val = false;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public boolean isVal() {
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }
}
