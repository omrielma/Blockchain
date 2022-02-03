import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Iterables;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Singleton
public class Blockchain {

    @JsonProperty
    private final List<Block> blockchain;
    private final MemPool memPool;

    @Inject
    public Blockchain(@Named("memPool") MemPool memPool) {
        this.memPool = memPool;
        blockchain = new LinkedList<>();
        blockchain.add(new Block( 1, LocalDateTime.now(), 1L, "0", Set.of()));
    }

    public Block mineBlock() {
        Long nonceToCheck = 1L;
        Block blockToCheck = new Block(blockchain.size() + 1,
                LocalDateTime.now(),
                nonceToCheck,
                Iterables.getLast(blockchain).hash(),
                memPool.getTransactionsToAdd());
        proofOfWork(nonceToCheck, blockToCheck);
        blockchain.add(blockToCheck);
        return blockToCheck;
    }

    private void proofOfWork(Long nonceToCheck, Block blockToCheck) {
        while (!DigestUtils.sha256Hex(blockToCheck.toString()).startsWith("0000")) {
            nonceToCheck++;
            blockToCheck.setNonce(nonceToCheck);
        }
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public int getBlockchainSize() {
        return blockchain.size();
    }

    public Block getBlock(int index) {
        return blockchain.get(index);
    }

}
