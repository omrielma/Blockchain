import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/blockchain")
@Produces(MediaType.APPLICATION_JSON)
public class BlockchainAPI {

    private final Blockchain blockchain;
    private final MemPool memPool;

    @Inject
    public BlockchainAPI(Blockchain blockchain, MemPool memPool) {
        this.blockchain = blockchain;
        this.memPool = memPool;
    }

    @GET
    @Path("/get_chain")
    public Blockchain getChain() {
        return blockchain;
    }


    @GET
    @Path("/mine_block")
    public Block mineBlock() {
        return blockchain.mineBlock();
    }

    @GET
    @Path("is_valid")
    public boolean isChainValid() {
        return blockchain.getBlockchain().stream()
                .reduce(true, ((aBoolean, block) -> aBoolean && previousHashEqual(block)), Boolean::logicalAnd);
    }

    @POST
    @Path("add_transaction")
    public String addTransaction(Transaction transaction) {
        memPool.addTransaction(transaction);
        return String.format("Transaction from %s to %s of %s ElmaCoin has been added to the MemPool for processing",
                transaction.getSender(),
                transaction.getReceiver(),
                transaction.getAmount());
    }

    private boolean previousHashEqual(Block block) {
        return block.getBlockNumber() == 1 || block.getPreviousHash().equals(blockchain.getBlock(block.getBlockNumber() - 2).hash());
    }

}
