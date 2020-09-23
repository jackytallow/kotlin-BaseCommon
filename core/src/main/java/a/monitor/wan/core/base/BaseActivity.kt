package a.monitor.wan.core.base

import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions

/**
@author:jacky
@Date:2020/9/23
 **/
abstract class BaseActivity : AppCompatActivity() {


    /**
     * 布局文件id
     */
    @LayoutRes
    protected abstract fun attachLayoutRes(): Int


    /**
     * 初始化数据
     */
    open fun initData() {}

    //是否使用EventBus
    open fun useEventBus():Boolean = false;

    /**
     * 获取权限处理类
     */
    protected val rxPermissions: RxPermissions by lazy {
        RxPermissions(this)
    }


}