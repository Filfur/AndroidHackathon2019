package com.noteamname.androidhackathon.toiletRoll.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class RollView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var progress: Float = 0f
        set(value) {
            field = value
            requestLayout()
        }

    val angles = (0..10).map {
        Pair(getRandomAngle(), getRandomAngle(120f, 300f))
    }

    val sleeveWidth: Float = 100f
    val sleeveHeight: Float = 200f

    val arcPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 2f
        strokeCap = Paint.Cap.ROUND
        color = Color.BLACK
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = (suggestedMinimumWidth + paddingStart + paddingEnd)
        val desiredHeight = (suggestedMinimumHeight + paddingTop + paddingBottom)
        val width = sleeveWidth + (measureDimension(desiredWidth, widthMeasureSpec) - sleeveWidth) * (1 - progress)
        val height = sleeveHeight + (measureDimension(desiredHeight, heightMeasureSpec) - sleeveHeight) * (1 - progress)


        setMeasuredDimension(width.toInt(), height.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        val lineCounts = ((1f - progress) * 10).toInt()
        val lineWidth = (measuredWidth - sleeveWidth) / 2 / lineCounts
        val lineHeight = (measuredHeight - sleeveHeight) / 2 / lineCounts
        (0..lineCounts).forEachIndexed { index, i ->
            val left = index * lineWidth
            val top = index * lineHeight
            val right = measuredWidth - left
            val bottom = measuredHeight - top
            val startAngle = ((1 - progress) * 10f - lineCounts) * 360f

            canvas.drawArc(
                left,
                top,
                right,
                bottom,
                angles[index].first + startAngle,
                if (index == 0) 360f else angles[index].second,
                false,
                arcPaint
            )
        }
        val left = (measuredWidth - sleeveWidth) / 2
        val top = (measuredHeight - sleeveHeight) / 2
        val right = measuredWidth - left
        val bottom = measuredHeight - top
        canvas.drawArc(left, top, right, bottom, 0f, 360f, false, arcPaint)
    }

    fun getRandomAngle(from: Float = 0f, to: Float = 360f): Float {
        return Random.nextDouble(from.toDouble(), to.toDouble()).toFloat()
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {

        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        val result = when (specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> min(desiredSize, specSize)
            MeasureSpec.UNSPECIFIED -> desiredSize
            else -> desiredSize
        }
        return result
    }
}