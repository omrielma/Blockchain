import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Iterables;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@JsonSerialize
public class Blockchain {

    @JsonProperty
    private final List<Block> blockchain;

    public Blockchain() {
        blockchain = new LinkedList<>();
        blockchain.add(new Block( 1, LocalDateTime.now(), 1L, "0", "First Block"));
    }

    public Block mineBlock(String data) {
        Long nonceToCheck = 1L;
        Block blockToCheck = new Block(blockchain.size() + 1,
                LocalDateTime.now(),
                nonceToCheck,
                Iterables.getLast(blockchain).hash(),
                data);
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
