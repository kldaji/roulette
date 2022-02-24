package com.kldaji.roulette.ui.roulette

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.kldaji.roulette.R
import com.kldaji.roulette.data.Partition

class RouletteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val partitionPaint = Paint()
    private val rouletteViewRectF = RectF()
    private val partitions = mutableListOf<Partition>()
    private var rouletteViewWidth = 0f
    private var rouletteViewHeight = 0f

    init {
        context.withStyledAttributes(attrs, R.styleable.RouletteView, defStyleAttr, defStyleRes) {
            rouletteViewWidth = getDimension(R.styleable.RouletteView_rouletteViewWidth, 0f)
            rouletteViewHeight = getDimension(R.styleable.RouletteView_rouletteViewHeight, 0f)

            initiatePartitionPaint()
        }
        initiatePartitions()
    }

    private fun initiatePartitionPaint() {
        partitionPaint.apply {
            style = Paint.Style.FILL
        }
    }

    private fun initiatePartitions() {
        partitions.add(Partition("빨간색", ContextCompat.getColor(context, R.color.red), 0.2f))
        partitions.add(Partition("노란색", ContextCompat.getColor(context, R.color.yellow), 0.2f))
        partitions.add(Partition("초록색", ContextCompat.getColor(context, R.color.green), 0.2f))
        partitions.add(Partition("파란색", ContextCompat.getColor(context, R.color.blue), 0.2f))
        partitions.add(Partition("보라색", ContextCompat.getColor(context, R.color.purple), 0.2f))

        var startAngle = 270f
        partitions.forEach { partition ->
            val sweepAngle = 360 * partition.ratio
            partition.setAngle(startAngle, startAngle + sweepAngle)
            startAngle += sweepAngle
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        setRouletteViewRectF()
    }

    private fun setRouletteViewRectF() {
        val rouletteViewLeft = width / 2f - rouletteViewWidth / 2f
        val rouletteViewTop = height / 2f - rouletteViewHeight / 2f
        rouletteViewRectF.set(
            rouletteViewLeft,
            rouletteViewTop,
            rouletteViewWidth + rouletteViewLeft,
            rouletteViewHeight + rouletteViewTop
        )
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        drawPartitions(canvas)
    }

    private fun drawPartitions(canvas: Canvas) {
        partitions.forEach { partition ->
            setPartitionPaintColor(partition.color)
            canvas.drawArc(rouletteViewRectF,
                partition.startAngle,
                partition.endAngle - partition.startAngle,
                true,
                partitionPaint)
        }
    }

    private fun setPartitionPaintColor(_color: Int) {
        partitionPaint.color = _color
    }

    fun startRouletteViewAnimation(rotateDegree: Float, duration: Long) {
        val rotateAnimation = createRotateAnimation(rotateDegree, duration)
        startAnimation(rotateAnimation)
    }

    private fun createRotateAnimation(rotateDegree: Float, duration: Long): RotateAnimation {
        return RotateAnimation(0f,
            rotateDegree,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            this.duration = duration
            fillAfter = true
            interpolator = DecelerateInterpolator(1.5f)
        }
    }
}
