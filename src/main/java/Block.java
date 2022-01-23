import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;

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
    private final String data;

    public Block(int blockNumber,
                 LocalDateTime timeStamp,
                 Long nonce,
                 String previousHash,
                 String data) {
        this.blockNumber = blockNumber;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.data = data;
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

    public String getData() {
        return data;
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
                ", data='" + data + '\'' +
                '}';
    }

    @JsonGetter
    public String hash() {
        return DigestUtils.sha256Hex(this.toString());
    }
}
