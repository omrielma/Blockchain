import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.Set;

@JsonSerialize
public class Block {

    @JsonProperty
    private final int blockNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "uuuu-MM-dd'T'HH:mm:ss")
    private final LocalDateTime timeStamp;
    @JsonProperty
    private Long nonce;
    @JsonProperty
    private final String previousHash;
    @JsonProperty
    private final Set<Transaction> transactions;

    public Block(int blockNumber,
                 LocalDateTime timeStamp,
                 Long nonce,
                 String previousHash,
                 Set<Transaction> transactions) {
        this.blockNumber = blockNumber;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.transactions = transactions;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Long getNonce() {
        return nonce;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockNumber=" + blockNumber +
                ", timeStamp=" + timeStamp +
                ", nonce=" + nonce +
                ", previousHash='" + previousHash + '\'' +
                ", transactions='" + transactions + '\'' +
                '}';
    }

    @JsonGetter
    public String hash() {
        return DigestUtils.sha256Hex(this.toString());
    }

    public int addTransaction(String sender, String receiver, int amount) {
        transactions.add(new Transaction(sender, receiver, amount));
        return blockNumber;
    }
}
