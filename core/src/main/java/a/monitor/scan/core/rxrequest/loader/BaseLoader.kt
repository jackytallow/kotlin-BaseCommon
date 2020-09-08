package cn.onestravel.library.rxrequest.loader


import a.monitor.scan.core.rxrequest.service.BaseService
import a.monitor.scan.core.rxrequest.service.RetrofitServiceManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @description 封装公共loader进行线程切换
 */
abstract class BaseLoader<S : BaseService> {
    protected val mServiceManager: RetrofitServiceManager by lazy { createServiceManager() }
    protected val mService: S by lazy { createService() }

    /**
     * 创建 ServiceManager 实例
     */
    abstract fun createServiceManager(): RetrofitServiceManager

    /**
     * 创建 Service 实例
     */
    abstract fun createService(): S

    /**
     * 设置Observable的工作线程
     * @param observable
     * @param <T>
     * @return
    </T> */
    fun <T> observe(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
