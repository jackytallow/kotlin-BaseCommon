package a.monitor.scan.ktbaseliabrary.mvp

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BaseModel : IModel {


    //协程创建
    protected open var mainScope = MainScope()

    override fun onDestroy() {
        mainScope.cancel()
    }

}