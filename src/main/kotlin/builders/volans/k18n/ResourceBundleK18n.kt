package builders.volans.k18n

import java.util.*

class ResourceBundleK18n(
    override val locale: Locale,
    override val basename: String,
    override val resourceBundle: ResourceBundle = ResourceBundle.getBundle(basename, locale)
) : K18n