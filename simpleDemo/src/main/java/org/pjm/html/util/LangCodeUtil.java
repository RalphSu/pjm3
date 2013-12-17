package org.pjm.html.util;

import java.util.Locale;

/**
 * Utility methods for manipulating Locales and language codes.
 */
public class LangCodeUtil
{
	private static Locale gDefaultLocale;

	/** the default language for this JVM */
	private static final String gDefaultLanguage;

	/** a Locale with no language or country */
	public static final Locale EMPTY_LOCALE;

	static
	{
		gDefaultLanguage = Locale.getDefault().getLanguage();

		EMPTY_LOCALE = new Locale("", "");
	}


	/**
	 * Converts a String to a locale. The string is typically
	 * in the "en_US" form. Note that you'll always get something
	 * back. In the case of a null param, you get back the default
	 * locale.
	 */
	public static Locale makeLocale(String val)
	{
		if (val == null)
		{
			return Locale.getDefault();
		}

		int i = val.indexOf("_");
		if (i == -1)
		{
			return new Locale(val);
		}

		String lang = val.substring(0, i);
		if (val.length() <= i + 1)
		{
			return new Locale(lang);
		}

		String suffix = val.substring(i + 1);
		i = val.indexOf("_");
		if (i == -1)
		{
			return new Locale(lang, suffix);
		}

		if (suffix.length() <= i + 1)
		{
			return new Locale(lang, suffix.substring(0, i));
		}

		String country = suffix.substring(0, i);
		String variant = suffix.substring(i + 1);

		return new Locale(lang, country, variant);
	}

	

	/**
	 * Returns the default Locale for this environment, if one is configured.
	 * We normally configure all of our servers as en_US, even if they are
	 * hosting an environment for a different locale, so
	 * {@link Locale#getDefault()} is not always useful.
	 * Note that this will return null if the Locale has not been overridden
	 * in this environment
	 *
	 * @return this environment's default Locale, or null if one has not been
	 * configured
	 */
	public static Locale getConfiguredLocale()
	{
		return gDefaultLocale;
	}

	/**
	 * Returns the default Locale for this environment, or the system default
	 * locale if an env-specific locale has not been configured.
	 * We normally configure all of our servers as en_US, even if they are
	 * hosting an environment for a different locale, so
	 * {@link Locale#getDefault()} is not always useful.
	 *
	 * @return this environment's default Locale; will never be null
	 */
	public static Locale getDefaultLocale()
	{
		return (gDefaultLocale != null) ? gDefaultLocale : Locale.getDefault();
	}

	

	/**
	 * "Generalizes" a Locale, following the algorithm specified in
	 * {@link java.util.ResourceBundle#getBundle(String, Locale, ClassLoader)}.
	 *
	 * @param locale Locale to generalize
	 * @return more generic Locale, or null if no further generalization can
	 * be performed
	 */
	public static Locale generalizeLocale(Locale locale)
	{
		return generalizeLocale(locale, true);
	}

	/**
	 * "Generalizes" a Locale, using an algorithm similar to the one specified in
	 * {@link java.util.ResourceBundle#getBundle(String, Locale, ClassLoader)}.
	 * However, you have the option not fall back to the default locale by
	 * setting useDefault to <code>false</code>.
	 *
	 * @param locale Locale to generalize
	 * @param useDefault true if the most general non-default locale should
	 * generalize to the default locale; false if that locale should generalize
	 * to null
	 * @return more generic Locale, or null if no further generalization can
	 * be performed
	 */
	public static Locale generalizeLocale(Locale locale, boolean useDefault)
	{
		String lang = locale.getLanguage();
		String country = locale.getCountry();
		String variant = locale.getVariant();

		// If there was a variant, generalize by removing the variant
		if (variant.length() > 0)
		{
			return new Locale(lang, country);
		}

		// No variant. If there was a country, generalize to just the language.
		if (country.length() > 0)
		{
			return new Locale(lang, "");
		}

		// No country specified. Is there a language?
		if (lang.length() > 0)
		{
			// If the language was the default language (e.g. "en"), then
			// generalize to a Locale with no language.
			if (lang.equals(gDefaultLanguage))
			{
				return EMPTY_LOCALE;
			}

			if (useDefault)
			{
				// A non-default language was specified (e.g. "fr"). Fall back
				// to the default Locale (e.g. "en_US").
				return Locale.getDefault();
			}
		}

		// No variant, country, or language, We're at the top of the chain.
		return null;
	}
	
	
	
}
