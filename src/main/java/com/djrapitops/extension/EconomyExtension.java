/*
    Copyright(c) 2019 Risto Lahtela (AuroraLS3)

    The MIT License(MIT)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files(the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions :
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
package com.djrapitops.extension;

import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.NotReadyException;
import com.djrapitops.plan.extension.annotation.DoubleProvider;
import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

/**
 * DataExtension for Vault.
 *
 * @author AuroraLS3
 */
@PluginInfo(name = "Economy (Vault)", iconName = "money-bill-wave", iconFamily = Family.SOLID, color = Color.GREEN)
public class EconomyExtension implements DataExtension {

    private Economy eco;

    public EconomyExtension() {
        RegisteredServiceProvider<Economy> registration = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (registration == null) throw new NotReadyException();
        eco = registration.getProvider();
    }

    @DoubleProvider(
            text = "Balance",
            description = "How much money the player has",
            iconName = "money-bill-wave",
            iconColor = Color.GREEN,
            showInPlayerTable = true
    )
    public double balance(UUID playerUUID) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        return eco.hasAccount(player) ? eco.getBalance(player) : 0.0;
    }
}