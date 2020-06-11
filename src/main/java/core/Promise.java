package core;

/**
 * 承诺
 */

public class Promise {

    public boolean ack;

    /**
     * 最大接受值得投票编号
     */
    public long ab;

    public Object av;

    public Promise(boolean ack, long ab, Object av) {
        this.ack = ack;
        this.ab = ab;
        this.av = av;
    }

    public Promise() {
    }
}