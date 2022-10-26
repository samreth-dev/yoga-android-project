package com.example.yoga

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_popup_settings.view.*
import java.util.*
import kotlin.math.max


class PopupSettingsFragment : DialogFragment() {

    lateinit var languageStrings: List<String>
    lateinit var themeStrings: List<String>
    lateinit var themeTags: List<String>
    lateinit var styles: List<Int>
    lateinit var locales: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_popup_settings, container, false)

        languageStrings = listOf(resources.getString(R.string.english), resources.getString(R.string.turkish))
        locales = listOf("en", "tr")

        themeStrings = listOf(resources.getString(R.string.light_theme), resources.getString(R.string.dark_theme))
        themeTags = listOf("Yoga", "YogaDark")
        styles = listOf(R.style.Theme_Yoga, R.style.Theme_Yoga_Dark)

        val languageAdapter = ArrayAdapter(requireContext(), R.layout.popup_settings_list_item, languageStrings)
        (view.settingsLanguagePick.editText as? AutoCompleteTextView)?.apply {
            setAdapter(languageAdapter)
            setText(languageStrings[max(locales.indexOf(getCurrentLocaleCode()), 0)], false)
        }
        val themeAdapter = ArrayAdapter(requireContext(), R.layout.popup_settings_list_item, themeStrings)
        (view.settingsThemePick.editText as? AutoCompleteTextView)?.apply {
            setAdapter(themeAdapter)
            setText(themeStrings[max(themeTags.indexOf(getCurrentThemeTag()), 0)], false)
        }

        view.popupSettingsSaveButton.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
        params.height = ConstraintLayout.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveChanges()
    }

    fun getSelectedThemeId(): Int {
        val themeText = view?.settingsThemePick?.editText?.text.toString()
        return styles[themeStrings.indexOf(themeText)]
    }
    fun getCurrentThemeTag(): String {
        val outValue = TypedValue();
        requireActivity().theme.resolveAttribute(R.attr.themeTag, outValue, true);
        return outValue.string.toString();
    }

    fun getSelectedThemeTag(): String {
        val theme = view?.settingsThemePick?.editText?.text.toString()
        return themeTags[themeStrings.indexOf(theme)]
    }

    fun getCurrentLocaleCode(): String {
        return resources.configuration.locale.toLanguageTag().substring(0, 2)
    }

    fun getSelectedLanguageToLocaleCode(): String {
        return locales[languageStrings.indexOf(view?.settingsLanguagePick?.editText?.text.toString())]
    }

    fun saveChanges(){
        val localeCode = getSelectedLanguageToLocaleCode()
        var somethingChanged = false
        if(getCurrentLocaleCode() != getSelectedLanguageToLocaleCode()) {
            val locale = Locale(localeCode)
            val config = resources.configuration
            Locale.setDefault(locale)
            config.locale = locale
            // Update new locale settings
            resources.updateConfiguration(config, resources.displayMetrics)
            somethingChanged = true
        }
        val theme = styles[themeStrings.indexOf(view?.settingsThemePick?.editText?.text.toString())]
        if(getCurrentThemeTag() != getSelectedThemeTag()){
            requireActivity().setTheme(theme)
            requireActivity().getPreferences(MODE_PRIVATE).edit().putInt("theme", getSelectedThemeId()).apply()
            somethingChanged = true
        }
        if(somethingChanged) {
            recreate(requireActivity())
        }
    }

    companion object {
        const val TAG = "PopupSettingsDialog"
    }
}