package builders.volans.k18n

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.atomic.AtomicReference

class K18nManager {
    companion object {
        internal val currentLanguage = AtomicReference<K18n>()

        var language: K18n?
            set(value) {
                currentLanguage.set(value)
            }
            get() = currentLanguage.get() ?: languages.firstOrNull()

        private val languages = ConcurrentLinkedDeque<K18n>()
        private val registeredLanguages = ConcurrentHashMap<Locale, K18n>()

        @Synchronized
        private fun registerLanguage(locale: Locale, k18n: K18n) {
            if (!registeredLanguages.containsKey(locale)) {
                languages.push(k18n)
            }
            registeredLanguages[locale] = k18n
        }

        fun initI18N(
            locale: Locale,
            basename: String,
            default: () -> K18n = { ResourceBundleK18n(locale, basename) }
        ): K18n {
            return default().also {
                registerLanguage(locale, it)
            }
        }
    }
}