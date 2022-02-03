import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

public class ApplicationGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    @Named("memPool")
    public MemPool provideMemPool() {
        return new MemPool();
    }

    @Provides
    @Singleton
    public Blockchain provideBlockchain(MemPool memPool) {
        return new Blockchain(memPool);
    }
}
