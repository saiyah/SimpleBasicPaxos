package core;

import util.U;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Proposer {


    public List<Acceptor> acceptors = new ArrayList<>();

    public long mab;

    public Object v;

    /**
     * 投票
     */
    public void vote(Proposal proposal) {
        // 计算多数派
        int quorum = Math.floorDiv(acceptors.size(), 2) + 1;
        // 重复计算maxVote 观察是否会变
        int c = 20;
        while (c > 0) {
            // 投票给接受者
            List<Proposal> proposals = new ArrayList<>();
            for (Acceptor acceptor : acceptors) {
                Promise promise = acceptor.onPrepare(proposal.b);
                if (promise != null && promise.ack) {
                    proposals.add(new Proposal(promise.ab, promise.av));
                    if (mab < promise.ab && promise.av != null) {
                        mab = promise.ab;
                        v = promise.av;
                    }
                }
            }
            // 接受提案小于多数派
            if (proposals.size() < quorum) {
                proposal = nextProposal(proposal, proposals);
                continue;
            }
            int acceptCount = 0;
            // 接受提案大于多数派，让接受
            for (Acceptor acceptor : acceptors) {
                if (acceptor.onAccept(proposal)) {
                    acceptCount++;
                }
            }
            if (acceptCount < quorum) {
                proposal = nextProposal(proposal, proposals);
                continue;
            }
            System.out.println("提案[" + proposal + "]" + "投票" + "成功-----------");
            c--;
        }
    }

    private static Proposal nextProposal(Proposal proposal, List<Proposal> proposals) {
        if (proposals.isEmpty()) {
            proposal.b = U.getNext();
            return proposal;
        }
        Collections.sort(proposals);
        Proposal maxVote = proposals.get(proposals.size() - 1);
        Object content = maxVote.v;
        if (content != null) {
            return new Proposal(U.getNext(), content);
        } else {
            long next = U.getNext();
            return new Proposal(next, "我喜欢" + next);
        }
    }


}
