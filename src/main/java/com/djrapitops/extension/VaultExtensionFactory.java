/*
    Copyright(c) 2019 Risto Lahtela (Rsl1122)

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Factory for DataExtension.
 *
 * @author Rsl1122
 */
public class VaultExtensionFactory {

    private boolean isAvailable() {
        try {
            Class.forName("net.milkbowl.vault.Vault");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public Collection<DataExtension> createExtensions() {
        if (!isAvailable()) return Collections.emptyList();

        Collection<DataExtension> extensions = new ArrayList<>();
        getExtension(EconomyExtension::new).ifPresent(extensions::add);
        getExtension(PermissionsExtension::new).ifPresent(extensions::add);
        return extensions;
    }

    private Optional<DataExtension> getExtension(Supplier<DataExtension> constructor) {
        try {
            return Optional.of(constructor.get());
        } catch (NoClassDefFoundError | NoSuchFieldError | NoSuchMethodError | Exception ecoServiceUnavailable) {
            /* Economy service is unavailable. */
        }
        return Optional.empty();
    }
}