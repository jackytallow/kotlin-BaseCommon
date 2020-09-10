package a.monitor.scan.core.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
@author:jacky
@Date:2020/9/8
 **/
abstract class BaseActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initListener()
        initData()
    }

    /*
    获取布局
   */
    protected abstract fun getLayoutId(): Int

    //初始化视图,子类实现
    protected open fun initView() {}

    //初始化监听事件,子类实现
    protected open fun initListener() {}


    //初始化data事件，子类实现
    protected open fun initData() {}

    //弹出toast，传入当前信息
    protected open fun showToast(msg: String) {
        runOnUiThread {
            toast(msg)
        }
    }

    //弹出toast需要当前的资源
    protected open fun showToast(stringRes: Int) {
        runOnUiThread {
            toast(getString(stringRes))
        }
    }

    //跳转到另外的activity并且finish掉
    inline fun <reified T : BaseActivity> startActivityAndFinish(vararg params: Pair<String, Any?>) {
        startActivity<T>(*params)
        finish()
    }

    //跳转到另外的activity
    inline fun <reified T : BaseActivity> startForActivity(vararg params: Pair<String, Any?>) {
        startActivity<T>(*params)
    }


}