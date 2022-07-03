package com.iven.musicplayergo.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.text.Spanned
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.text.parseAsHtml
import androidx.core.text.toSpanned
import androidx.core.widget.ImageViewCompat
import com.google.android.material.appbar.MaterialToolbar
import com.iven.musicplayergo.GoConstants
import com.iven.musicplayergo.GoPreferences
import com.iven.musicplayergo.R
import com.iven.musicplayergo.extensions.setIconTint
import com.iven.musicplayergo.player.MediaPlayerHolder
import com.iven.musicplayergo.ui.MainActivity


object Theming {

    @JvmStatic
    fun applyChanges(activity: Activity, restoreSettings: Boolean) {
        val intent = Intent(activity, MainActivity::class.java)
        val bundle = bundleOf(GoConstants.RESTORE_SETTINGS_FRAGMENT to restoreSettings)
        intent.putExtras(bundle)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        with(activity) {
            finishAfterTransition()
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    @JvmStatic
    fun getDefaultNightMode(context: Context) = when (GoPreferences.getPrefsInstance().theme) {
        context.getString(R.string.theme_pref_light) -> AppCompatDelegate.MODE_NIGHT_NO
        context.getString(R.string.theme_pref_dark) -> AppCompatDelegate.MODE_NIGHT_YES
        else -> if (Versioning.isQ()) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        } else {
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        }
    }

    @JvmStatic
    fun isThemeNight(resources: Resources) : Boolean {
        val uiMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return uiMode == Configuration.UI_MODE_NIGHT_YES
    }

    fun getSortIconForSongs(sort: Int): Int {
        return when (sort) {
            GoConstants.ASCENDING_SORTING -> R.drawable.ic_sort_alphabetical_descending
            GoConstants.DESCENDING_SORTING -> R.drawable.ic_sort_alphabetical_ascending
            GoConstants.TRACK_SORTING -> R.drawable.ic_sort_numeric_descending
            else -> R.drawable.ic_sort_numeric_ascending
        }
    }

    fun getSortIconForSongsDisplayName(sort: Int) : Int {
        return when (sort) {
            GoConstants.ASCENDING_SORTING -> R.drawable.ic_sort_alphabetical_descending
            else -> R.drawable.ic_sort_alphabetical_ascending
        }
    }

    @JvmStatic
    fun isDeviceLand(resources: Resources) =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Fixed array of pairs (first: accent, second: theme)
    @JvmStatic
    val accents = arrayOf(
        Pair(first = R.color.red, second = R.style.BaseTheme_Red),
        Pair(first = R.color.pink, second = R.style.BaseTheme_Pink),
        Pair(first = R.color.purple, second = R.style.BaseTheme_Purple),
        Pair(first = R.color.deep_purple, second = R.style.BaseTheme_DeepPurple),
        Pair(first = R.color.indigo, second = R.style.BaseTheme_Indigo),
        Pair(first = R.color.blue, second = R.style.BaseTheme_Blue),
        Pair(first = R.color.light_blue, second = R.style.BaseTheme_LightBlue),
        Pair(first = R.color.cyan, second = R.style.BaseTheme_Cyan),
        Pair(first = R.color.teal, second = R.style.BaseTheme_Teal),
        Pair(first = R.color.green, second = R.style.BaseTheme_Green),
        Pair(first = R.color.light_green, second = R.style.BaseTheme_LightGreen),
        Pair(first = R.color.lime, second = R.style.BaseTheme_Lime),
        Pair(first = R.color.yellow, second = R.style.BaseTheme_Yellow),
        Pair(first = R.color.amber, second = R.style.BaseTheme_Amber),
        Pair(first = R.color.orange, second = R.style.BaseTheme_Orange),
        Pair(first = R.color.deep_orange, second = R.style.BaseTheme_DeepOrange),
        Pair(first = R.color.brown, second = R.style.BaseTheme_Brown),
        Pair(first = R.color.grey, second = R.style.BaseTheme_Grey),
        Pair(first = R.color.blue_grey, second = R.style.BaseTheme_BlueGrey)
    )

    // Fixed array of pairs (first: accent, second: theme)
    @JvmStatic
    private val accentsBlack = arrayOf(
        Pair(first = R.color.red, second = R.style.BaseTheme_Black_Red),
        Pair(first = R.color.pink, second = R.style.BaseTheme_Black_Pink),
        Pair(first = R.color.purple, second = R.style.BaseTheme_Black_Purple),
        Pair(first = R.color.deep_purple, second = R.style.BaseTheme_Black_DeepPurple),
        Pair(first = R.color.indigo, second = R.style.BaseTheme_Black_Indigo),
        Pair(first = R.color.blue, second = R.style.BaseTheme_Black_Blue),
        Pair(first = R.color.light_blue, second = R.style.BaseTheme_Black_LightBlue),
        Pair(first = R.color.cyan, second = R.style.BaseTheme_Black_Cyan),
        Pair(first = R.color.teal, second = R.style.BaseTheme_Black_Teal),
        Pair(first = R.color.green, second = R.style.BaseTheme_Black_Green),
        Pair(first = R.color.light_green, second = R.style.BaseTheme_Black_LightGreen),
        Pair(first = R.color.lime, second = R.style.BaseTheme_Black_Lime),
        Pair(first = R.color.yellow, second = R.style.BaseTheme_Black_Yellow),
        Pair(first = R.color.amber, second = R.style.BaseTheme_Black_Amber),
        Pair(first = R.color.orange, second = R.style.BaseTheme_Black_Orange),
        Pair(first = R.color.deep_orange, second = R.style.BaseTheme_Black_DeepOrange),
        Pair(first = R.color.brown, second = R.style.BaseTheme_Black_Brown),
        Pair(first = R.color.grey, second = R.style.BaseTheme_Black_Grey),
        Pair(first = R.color.blue_grey, second = R.style.BaseTheme_Black_BlueGrey)
    )

    // Fixed array of pairs (first: accent, second: accent name)
    @JvmStatic
    val accentsNames = arrayOf(
        Pair(first = R.color.red, second = R.string.red),
        Pair(first = R.color.pink, second = R.string.pink),
        Pair(first = R.color.purple, second = R.string.purple),
        Pair(first = R.color.deep_purple, second = R.string.deep_purple),
        Pair(first = R.color.indigo, second = R.string.indigo),
        Pair(first = R.color.blue, second = R.string.blue),
        Pair(first = R.color.light_blue, second = R.string.light_blue),
        Pair(first = R.color.cyan, second = R.string.cyan),
        Pair(first = R.color.teal, second = R.string.teal),
        Pair(first = R.color.green, second = R.string.green),
        Pair(first = R.color.light_green, second = R.string.light_green),
        Pair(first = R.color.lime, second = R.string.lime),
        Pair(first = R.color.yellow, second = R.string.yellow),
        Pair(first = R.color.amber, second = R.string.amber),
        Pair(first = R.color.orange, second = R.string.orange),
        Pair(first = R.color.deep_orange, second = R.string.deep_orange),
        Pair(first = R.color.brown, second = R.string.brown),
        Pair(first = R.color.grey, second = R.string.grey),
        Pair(first = R.color.blue_grey, second = R.string.blue_grey)
    )

    @JvmStatic
    fun getAccentName(context: Context, accent: Int): Spanned {
        val accents = accentsNames.toMap()
        return try {
            context.getString(
                R.string.accent_and_hex,
                context.getString(accents[accent]!!),
                context.getString(accent).uppercase()
            ).parseAsHtml()
        } catch (e: Exception) {
            e.printStackTrace()
            context.getString(R.string.error_eq).toSpanned()
        }
    }

    // Search theme from accents array of Pair, returns a Pair(theme, position)
    @JvmStatic
    fun getAccentedTheme(resources: Resources) = try {
        val selAccent = GoPreferences.getPrefsInstance().accent
        val stylesMap = if (GoPreferences.getPrefsInstance().isBlackTheme && isThemeNight(resources)) {
            accentsBlack
        } else {
            accents
        }.toMap()
        stylesMap[selAccent]?.let { style ->
            val position = stylesMap.keys.indexOf(selAccent)
            return@let Pair(first = style, second = position)
        }
    } catch (e: Exception) {
        Pair(first = R.style.BaseTheme_DeepPurple, second = 3)
    }

    @JvmStatic
    fun updateIconTint(imageView: ImageView, tint: Int) {
        ImageViewCompat.setImageTintList(
            imageView, ColorStateList.valueOf(tint)
        )
    }

    @ColorInt
    @JvmStatic
    fun resolveThemeAccent(context: Context): Int {
        var accent = GoPreferences.getPrefsInstance().accent

        // Fallback to default color when the pref is f@#$ed (when resources change)
        if (!accents.map { accentId -> accentId.first }.contains(accent)) {
            accent = R.color.deep_purple
            GoPreferences.getPrefsInstance().accent = accent
        }
        return ContextCompat.getColor(context, accent)
    }

    @ColorInt
    @JvmStatic
    fun resolveColorAttr(context: Context, @AttrRes colorAttr: Int): Int {
        val resolvedAttr: TypedValue =
            resolveThemeAttr(
                    context,
                    colorAttr
            )
        // resourceId is used if it's a ColorStateList, and data if it's a color reference or a hex color
        val colorRes =
            if (resolvedAttr.resourceId != 0) {
                resolvedAttr.resourceId
            } else {
                resolvedAttr.data
            }
        return ContextCompat.getColor(context, colorRes)
    }

    @JvmStatic
    private fun resolveThemeAttr(context: Context, @AttrRes attrRes: Int) =
        TypedValue().apply { context.theme.resolveAttribute(attrRes, this, true) }

    @JvmStatic
    fun getAlbumCoverAlpha(context: Context): Int {
        return when {
            isThemeNight(context.resources) && GoPreferences.getPrefsInstance().isBlackTheme -> 25
            isThemeNight(context.resources) -> 15
            else -> 20
        }
    }

    @JvmStatic
    fun getTabIcon(tab: String) = when (tab) {
        GoConstants.ARTISTS_TAB -> R.drawable.ic_artist
        GoConstants.ALBUM_TAB -> R.drawable.ic_library_music
        GoConstants.SONGS_TAB -> R.drawable.ic_music_note
        GoConstants.FOLDERS_TAB -> R.drawable.ic_folder_music
        else -> R.drawable.ic_settings
    }

    @JvmStatic
    fun getPreciseVolumeIcon(volume: Int) = when (volume) {
        in 1..33 -> R.drawable.ic_volume_mute
        in 34..67 -> R.drawable.ic_volume_down
        in 68..100 -> R.drawable.ic_volume_up
        else -> R.drawable.ic_volume_off
    }

    @JvmStatic
    fun getRepeatIcon(mediaPlayerHolder: MediaPlayerHolder) = when {
        mediaPlayerHolder.isRepeat1X -> R.drawable.ic_repeat_one
        mediaPlayerHolder.isLooping -> R.drawable.ic_repeat
        mediaPlayerHolder.isPauseOnEnd -> R.drawable.ic_pause
        else -> R.drawable.ic_repeat_one_notif_disabled
    }

    @JvmStatic
    fun tintSleepTimerMenuItem(tb: MaterialToolbar, isEnabled: Boolean) {
        tb.menu.findItem(R.id.sleeptimer).setIconTint(if (isEnabled) {
            resolveThemeAccent(tb.context)
        } else {
            ContextCompat.getColor(tb.context, R.color.widgetsColor)
        })
    }
}
