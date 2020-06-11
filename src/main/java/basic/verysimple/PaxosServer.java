package basic.verysimple;

import core.Acceptor;
import core.Proposal;
import core.Proposer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author cy
 * @date 2020/6/9 2:47 下午
 */
public class PaxosServer {

    private static final BlockingQueue<Proposal> serverMsgQueue = new LinkedBlockingDeque<>();

    static Proposer proposer;

    static {
        proposer = new Proposer();
        List<Acceptor> acceptors = new ArrayList<>();
        acceptors.add(new Acceptor());
        acceptors.add(new Acceptor());
        acceptors.add(new Acceptor());
        acceptors.add(new Acceptor());
        acceptors.add(new Acceptor());
        proposer.acceptors = acceptors;

    }

    public void submit(Proposal proposal) {
        serverMsgQueue.add(proposal);
    }

    public void doSubmit() {
        new Thread(() -> {
            while (true) {
                try {
                    Proposal proposal = serverMsgQueue.take();
                    proposer.vote(proposal);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
