package a.monitor.scan.core.common.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
@author:jacky
@Date:2020/9/8 圆形文本视图
 **/
class CircleTextView : AppCompatTextView {

    private val mBgPaint = Paint()

    private var pfd = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {//empty
      }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mBgPaint.color = Color.WHITE
        mBgPaint.isAntiAlias = true
    }

    constructor(context: Context) : super(context) {
        mBgPaint.color = Color.WHITE
        mBgPaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = measuredWidth
        val measuredHeight = measuredHeight
        val max = Math.max(measuredWidth, measuredHeight)
        setMeasuredDimension(max, max)
    }

    override fun setBackgroundColor(color: Int) {
        mBgPaint.color = color
    }

    /**
     * 设置通知个数显示
     * @param text
     */
    fun setNotifiText(text: Int) {
        setText(text.toString() + "")
    }

    override fun draw(canvas: Canvas) {
        canvas.drawFilter = pfd
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            (Math.max(width, height) / 2).toFloat(),
            mBgPaint
        )
        super.draw(canvas)
    }
}