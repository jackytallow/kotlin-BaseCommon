package a.monitor.scan.core.common.utils

import android.content.res.Resources

/**
@author:jacky
@Date:2020/9/8
 **/
object DensityUtil {
    val density = Resources.getSystem().getDisplayMetrics().density

    /**
     * dip转换为px
     */
    fun dip2px(dp: Int): Int {
        return (dp * density).toInt()
    }


    /**
     * px转换为dip
     */
    fun px2dip(px: Float): Int {
        return (px / density).toInt()
    }

    /**
     * 获取屏幕宽度
     */
    fun appWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun appHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
    }


}