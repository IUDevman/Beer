/*
 * This file is licensed under the GNU Lesser General Public License v2.1
 * Please review the license at https://github.com/IUDevman/Beer/blob/main/LICENSE
 */

package drink.beer;

import drink.beer.misc.event.EventHandler;
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

    public EventHandler EVENT_HANDLER;

    @Override
    public void onInitializeClient() {
        long startTime = System.currentTimeMillis();
        this.LOGGER.info("Initializing " + this.MOD_NAME + " " + this.MOD_VERSION + "!");

        loadClient();

        long finishedTime = System.currentTimeMillis() - startTime;
        this.LOGGER.info("Finished initializing " + this.MOD_NAME + " " + this.MOD_VERSION + " (" + finishedTime + "ms)!");
    }

    private void loadClient() {
        this.EVENT_HANDLER = new EventHandler();
    }
}
