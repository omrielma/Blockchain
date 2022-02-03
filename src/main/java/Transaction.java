import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Transaction {

    private final String sender;
    private final String receiver;
    private final int amount;

    @JsonCreator
    public Transaction(
            @JsonProperty(value = "sender") String sender,
            @JsonProperty(value = "receiver") String receiver,
            @JsonProperty(value = "amount") int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (amount != that.amount) return false;
        if (!Objects.equals(sender, that.sender)) return false;
        return Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        int result = sender != null ? sender.hashCode() : 0;
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                '}';
    }
}
