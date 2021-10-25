/*
 * This file is licensed under the GNU Lesser General Public License v2.1
 * Please review the license at https://github.com/IUDevman/Beer/blob/main/LICENSE
 */

package drink.beer.misc.imp;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;

/**
 * @author IUDevman
 * @since 10-24-2021
 */

public interface Global {

    default MinecraftClient getMinecraft() {
        return MinecraftClient.getInstance();
    }

    default ClientPlayerEntity getPlayer() {
        return getMinecraft().player;
    }

    default ClientWorld getWorld() {
        return getMinecraft().world;
    }

    default ClientPlayNetworkHandler getNetwork() {
        return getPlayer().networkHandler;
    }

    default PlayerInventory getInventory() {
        return getPlayer().getInventory();
    }

    default ChatHud getChatHud() {
        return getMinecraft().inGameHud.getChatHud();
    }

    default boolean isNull() {
        return getPlayer() == null || getWorld() == null;
    }
}
