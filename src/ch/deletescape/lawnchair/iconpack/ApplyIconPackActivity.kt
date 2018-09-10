/*
 *     This file is part of Lawnchair Launcher.
 *
 *     Lawnchair Launcher is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Lawnchair Launcher is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Lawnchair Launcher.  If not, see <https://www.gnu.org/licenses/>.
 */

package ch.deletescape.lawnchair.iconpack
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import ch.deletescape.lawnchair.reloadIcons
import ch.deletescape.lawnchair.theme.ThemeManager
import ch.deletescape.lawnchair.theme.ThemeOverride
import com.android.launcher3.*

class ApplyIconPackActivity : Activity() {
    private val prefs by lazy { Utilities.getLawnchairPrefs(this) }
    private val themeSet: ThemeOverride.ThemeSet get() = ThemeOverride.SettingsTransparent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.getInstance(this).addOverride(ThemeOverride(themeSet, this))

        prefs.iconPack = intent.getStringExtra("packageName")
        reloadIcons(this)
        val packName = IconPackManager.getInstance(this).currentPack.displayName
        val message = String.format(getString(R.string.icon_pack_applied_toast), packName)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
        Utilities.goToHome(this)
    }
}