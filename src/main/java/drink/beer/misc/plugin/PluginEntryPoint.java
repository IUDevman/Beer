/*
 * This file is licensed under the GNU Lesser General Public License v2.1
 * Please review the license at https://github.com/IUDevman/Beer/blob/main/LICENSE
 */

package drink.beer.misc.plugin;

import java.lang.annotation.*;

/**
 * @author IUDevman
 * @since 10-25-2021
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PluginEntryPoint {

}
