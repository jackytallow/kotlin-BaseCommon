package a.monitor.scan.ktbaseliabrary.global

import android.app.Application
import android.graphics.Color
import a.monitor.scan.ktbaseliabrary.proxy.HttpConfigProxy
import a.monitor.scan.ktbaseliabrary.proxy.ShowUIProxy
import com.blankj.utilcode.BuildConfig
import com.tbright.ktbaselibrary.utils.AppUtils

object GlobalConfig {
    //配置Http相关的类
    var httpConfigProxy: HttpConfigProxy? = null

    //配置UI显示提示弹框
    var showUIProxy: ShowUIProxy? = null

    //是否是debug包
    var isDebug = BuildConfig.DEBUG

    //app的statusBar颜色,默认透明的
    var statusBarColor = Color.TRANSPARENT
    fun init(application: Application,
             httpConfigProxy: HttpConfigProxy,
             showUIProxy: ShowUIProxy,
             statusBarColor : Int = Color.TRANSPARENT,
             callback: Application.ActivityLifecycleCallbacks? = null) {
        GlobalConfig.httpConfigProxy = httpConfigProxy
        GlobalConfig.showUIProxy = showUIProxy
        AppUtils.init(application, callback)
        GlobalConfig.httpConfigProxy?.initRetrofit()
        GlobalConfig.statusBarColor = statusBarColor
    }
}