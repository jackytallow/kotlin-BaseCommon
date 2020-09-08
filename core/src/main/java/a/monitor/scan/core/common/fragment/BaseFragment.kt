package a.monitor.scan.core.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**
@author:jacky
@Date:2020/9/8 所有fragment的基类
 **/
abstract class BaseFragment : Fragment(), AnkoLogger {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutId(), null)
        initView(view)
        initListener(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }


    /**
     * 获取布局ID,子类必须实现
     */
    protected abstract fun getLayoutId(): Int


    /**
     * 初始化 View 的相关操作，若有需要可在子类实现
     */
    protected open fun initView(contentView: View) {}

    /**
     * 初始化 Listener 事件的相关操作，若有需要可在子类实现
     */
    protected open fun initListener(contentView: View) {}


    /**
     * 初始化 Data 数据的相关操作，若有需要可在子类实现
     */
    protected open fun initData() {}


    /**
     * 在主线程弹出Toast 提示
     * @param msg 需要弹出的提示信息
     */
    protected open fun showToast(msg: String) {
        activity!!.runOnUiThread {
            activity!!.toast(msg)
        }
    }

    /**
     * 在主线程弹出Toast 提示
     * @param stringRes 需要弹出的提示信息的string资源ID
     */
    protected open fun showToast(stringRes: Int) {
        activity!!.runOnUiThread {
            activity!!.toast(getString(stringRes))
        }
    }
}