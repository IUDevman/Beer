package drink.beer;

import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author IUDevman
 * @since 10-24-2021
 */

public final class Client implements ClientModInitializer {

    public static Client INSTANCE;

    public Client() {
        INSTANCE = this;
    }

    public final String MOD_NAME = "Beer";
    public final String MOD_VERSION = "0.1.0-SNAPSHOT";

    public final Logger LOGGER = LogManager.getLogger(this.MOD_NAME);

    @Override
    public void onInitializeClient() {
        long startTime = System.currentTimeMillis();
        this.LOGGER.info("Initializing " + this.MOD_NAME + " " + this.MOD_VERSION + "!");

        loadClient();

        long finishedTime = System.currentTimeMillis() - startTime;
        this.LOGGER.info("Finished initializing " + this.MOD_NAME + " " + this.MOD_VERSION + " (" + finishedTime + "ms)!");
    }

    private void loadClient() {

    }
}
