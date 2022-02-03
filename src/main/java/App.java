import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import javax.inject.Inject;

public class App extends Application<BasicConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run("server", "introduction-config.yml");
    }

    private GuiceBundle guiceBundle;

    @Override
    public void run(BasicConfiguration configuration, Environment environment) {
        Injector injector = Guice.createInjector(new ApplicationGuiceModule());
        Blockchain blockchain = injector.getInstance(Blockchain.class);
        MemPool memPool = injector.getInstance(MemPool.class);
        BlockchainAPI blockchainAPI = new BlockchainAPI(blockchain, memPool);
        environment.jersey().register(blockchainAPI);
    }

    @Override
    public void initialize(Bootstrap<BasicConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.builder()
                .modules(new ApplicationGuiceModule())
                .build();
        bootstrap.addBundle(guiceBundle);
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

}
