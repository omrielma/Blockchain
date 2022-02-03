import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class MemPool {

    private final Set<Transaction> transactions;

    public MemPool() {
        transactions = new HashSet<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Set<Transaction> getTransactionsToAdd() {
        final Set<Transaction> randomTransactions = ImmutableSet.copyOf(Iterables.limit(transactions, 3));
        transactions.removeAll(randomTransactions);
        return randomTransactions;
    }
}
