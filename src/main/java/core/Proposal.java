package core;

/**
 * 提案
 */
public class Proposal implements Comparable<Proposal> {

    /**
     * 提案序号
     */
    public long b;
    /**
     * 提案内容
     */
    public Object v;


    public Proposal() {
    }

    public Proposal(long b, Object v) {
        this.b = b;
        this.v = v;
    }

    @Override
    public int compareTo(Proposal o) {
        return Long.compare(b, o.b);
    }


    @Override
    public String toString() {
        return String.valueOf(b) + ':' + v;
    }
}
