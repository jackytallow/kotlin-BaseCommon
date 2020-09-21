package a.monitor.scan.baseprogram.activity

import a.monitor.scan.ktbaseliabrary.mvp.BaseModel
import a.monitor.scan.ktbaseliabrary.mvp.BasePresenter
import a.monitor.scan.ktbaseliabrary.mvp.BaseView

interface MainContract {

    abstract class MainPresenter : BasePresenter<BaseModel, MainView>(){

        abstract fun changeBaseUrl ()
    }

    interface MainView : BaseView {
        fun showResult(result : String)
    }
}