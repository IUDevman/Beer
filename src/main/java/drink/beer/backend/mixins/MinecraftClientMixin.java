/*
 * This file is licensed under the GNU Lesser General Public License v2.1
 * Please review the license at https://github.com/IUDevman/Beer/blob/main/LICENSE
 */

package drink.beer.backend.mixins;

import drink.beer.Client;
import drink.beer.backend.MixinPriority;
import drink.beer.misc.imp.Global;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author IUDevman
 * @since 10-24-2021
 */

@Mixin(value = MinecraftClient.class, priority = MixinPriority.VALUE)
public final class MinecraftClientMixin implements Global {

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(Client.INSTANCE.MOD_NAME + " " + Client.INSTANCE.MOD_VERSION + " (" + cir.getReturnValue() + ")");
    }
}
