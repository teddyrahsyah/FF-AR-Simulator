package com.develo.ff_arsimulator.ui.arguide

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.develo.ff_arsimulator.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroCustomLayoutFragment

class ArGuideActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.guide_layout_1)
        )
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.guide_layout_2)
        )
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.guide_layout_3)
        )
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.guide_layout_4)
        )
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.guide_layout_5)
        )
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.guide_layout_6)
        )

        setImmersiveMode()

        setSkipText(R.string.app_intro_skip_button)
        setDoneText(R.string.app_intro_done_button)
        setColorSkipButton(getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant))
        setNextArrowColor(getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant))
        setColorDoneText(getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant))
        setIndicatorColor(
            selectedIndicatorColor = getColorFromAttr(com.google.android.material.R.attr.colorSecondaryVariant),
            unselectedIndicatorColor = getColorFromAttr(com.google.android.material.R.attr.colorSecondary)
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }

    @ColorInt
    private fun Context.getColorFromAttr(
        @AttrRes attrColor: Int,
        typedValue: TypedValue = TypedValue(),
        resolveRefs: Boolean = true
    ): Int {
        theme.resolveAttribute(attrColor, typedValue, resolveRefs)
        return typedValue.data
    }
}