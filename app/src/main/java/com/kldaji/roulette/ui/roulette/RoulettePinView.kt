package com.kldaji.roulette.ui.roulette

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import androidx.core.content.withStyledAttributes
import com.kldaji.roulette.R

class RoulettePinView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = R.style.RoulettePinView,
) : View(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private var rouletteViewRadius = 0f
    private var rouletteToolBarHeight = 0
    private val roulettePinViewRect = Rect()
    var roulettePinBitmap: Bitmap? = null

    init {
        context.withStyledAttributes(attrs,
            R.styleable.RoulettePinView,
            defStyleAttr,
            defStyleRes) {
            rouletteViewRadius = getDimension(R.styleable.RoulettePinView_rouletteViewRadius, 0f)
        }
        setToolBarHeight()
    }

    private fun setToolBarHeight() {
        val typedValue = TypedValue()
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            rouletteToolBarHeight =
                TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        setPinViewRect()
    }

    private fun setPinViewRect() {
        roulettePinBitmap?.let {
            val pinViewWidth = rootView.width / 2 - it.width / 2
            val pinViewHeight =
                rootView.height / 2 - rouletteToolBarHeight - rouletteViewRadius + it.height

            roulettePinViewRect.set(
                pinViewWidth,
                pinViewHeight.toInt(),
                pinViewWidth + it.width,
                (pinViewHeight + it.height).toInt()
            )
        }

    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        roulettePinBitmap?.let {
            canvas.drawBitmap(it, null, roulettePinViewRect, null)
        }
    }
}
