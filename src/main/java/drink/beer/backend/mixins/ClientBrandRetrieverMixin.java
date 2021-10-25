/*
 * This file is licensed under the GNU Lesser General Public License v2.1
 * Please review the license at https://github.com/IUDevman/Beer/blob/main/LICENSE
 */

package drink.beer.backend.mixins;

import drink.beer.Client;
import drink.beer.backend.MixinPriority;
import drink.beer.misc.imp.Global;
import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author IUDevman
 * @since 10-24-2021
 */

@Mixin(value = ClientBrandRetriever.class, priority = MixinPriority.VALUE)
public final class ClientBrandRetrieverMixin implements Global {

    @Inject(method = "getClientModName", at = @At("HEAD"), cancellable = true, remap = false)
    private static void getClientModName(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(Client.INSTANCE.MOD_NAME + "_(Cheats-Enabled!)");
    }
}
