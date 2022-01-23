import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<BasicConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run("server", "introduction-config.yml");

    }

    @Override
    public void run(BasicConfiguration configuration, Environment environment) throws Exception {
        Blockchain blockchain = new Blockchain();
        BlockchainAPI blockchainAPI = new BlockchainAPI(blockchain);

        environment.jersey().register(blockchainAPI);
    }

    @Override
    public void initialize(Bootstrap<BasicConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

}
