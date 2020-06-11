package core;

/**
 * 接受者
 */
public class Acceptor {

    public long ab;

    public Object av;

    public long pb;

    public synchronized Promise onPrepare(long b) {
        if (netWorkError()) {
            return null;
        }
        if (pb < b) {
            Promise promise = new Promise(true, ab, av);
            pb = b;
            return promise;
        } else {
            return new Promise(false, 0, null);
        }
    }

    public synchronized boolean onAccept(Proposal proposal) {
        if (netWorkError()) {
            return false;
        }
        if (proposal.b == pb) {
            ab = proposal.b;
            av = proposal.v;
            return true;
        }
        return false;
    }

    /**
     * 模拟网络异常
     */
    public boolean netWorkError() {
        return Math.random() - 0.5 > 0;
    }
}
