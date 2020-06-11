package basic.verysimple;

import core.Proposal;
import util.U;

/**
 * @author cy
 * @date 2020/6/9 2:47 下午
 */
public class PaxosClient {

    public static void main(String[] args) {

        PaxosServer paxosServer = new PaxosServer();
        paxosServer.doSubmit();
        Proposal proposal = new Proposal();
        proposal.b = U.getNext();
        proposal.v = "我喜欢" + proposal.b;
        paxosServer.submit(proposal);
    }

}
