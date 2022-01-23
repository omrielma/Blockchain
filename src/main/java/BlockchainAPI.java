
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/blockchain")
@Produces(MediaType.APPLICATION_JSON)
public class BlockchainAPI {

    private final Blockchain blockchain;

    public BlockchainAPI(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @GET
    @Path("/get_chain")
    public Blockchain getChain() {
        return blockchain;
    }


    @GET
    @Path("/mine_block")
    public Block mineBlock() {
        return blockchain.mineBlock(String.format("Block number %s", blockchain.getBlockchainSize() + 1));
    }

    @GET
    @Path("is_valid")
    public boolean isChainValid() {
        return blockchain.getBlockchain().stream()
                .reduce(true, ((aBoolean, block) -> aBoolean && previousHashEqual(block)), Boolean::logicalAnd);
    }

    private boolean previousHashEqual(Block block) {
        return block.getBlockNumber() == 1 || block.getPreviousHash().equals(blockchain.getBlock(block.getBlockNumber() - 2).hash());
    }

}
