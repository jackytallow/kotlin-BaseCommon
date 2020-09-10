package a.monitor.scan.ktbaseliabrary.mvp

import android.os.Bundle
import a.monitor.scan.ktbaseliabrary.base.BaseActivity
import a.monitor.scan.ktbaseliabrary.event.MessageEvent
import a.monitor.scan.ktbaseliabrary.global.GlobalConfig
import com.tbright.ktbaselibrary.utils.ReflectUtils

abstract class BaseMvpActivity<P : IPresenter> : BaseActivity(), BaseView {

    open var mPresenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = ReflectUtils.getObject(this, 0)
        mPresenter?.onAttachView(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mPresenter?.onDestroy()
        super.onDestroy()
    }

    override fun onMessageEvent(messageEvent: MessageEvent<*>) {
        super.onMessageEvent(messageEvent)
        GlobalConfig.showUIProxy?.parseResponseFailMessage(messageEvent)
    }

    override fun showLoading() {
        GlobalConfig.showUIProxy?.showLoading()
    }

    override fun hideLoading() {
        GlobalConfig.showUIProxy?.hideLoading()
    }

    override fun showError(errorMessage: String) {
        GlobalConfig.showUIProxy?.showError(errorMessage)
    }
}