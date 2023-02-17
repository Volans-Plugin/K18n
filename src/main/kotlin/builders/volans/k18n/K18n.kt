package builders.volans.k18n

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import java.text.MessageFormat
import java.util.*

interface K18n {
    val locale: Locale

    val resourceBundle: ResourceBundle

    val basename: String

    fun i18nArgs(key: String, vararg args: Any): Component {
        return if (resourceBundle.containsKey(key)) {
            MiniMessage.miniMessage().deserialize(MessageFormat.format(resourceBundle.getString(key), *args))
        } else {
            MiniMessage.miniMessage().deserialize(MessageFormat.format(key, *args))
        }
    }

    fun i18n(key: String): Component {
        return if (resourceBundle.containsKey(key)) {
            MiniMessage.miniMessage().deserialize(resourceBundle.getString(key))
        } else {
            MiniMessage.miniMessage().deserialize(key)
        }
    }

    operator fun String.invoke(vararg args: Any): Component {
        return i18nArgs(this, *args)
    }

    operator fun String.invoke(): Component {
        return i18n(this)
    }
}