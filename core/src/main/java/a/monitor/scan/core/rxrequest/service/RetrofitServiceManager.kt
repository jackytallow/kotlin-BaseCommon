package a.monitor.scan.core.rxrequest.service

import a.monitor.scan.core.rxrequest.common.HttpCommonInterceptor
import a.monitor.scan.core.rxrequest.common.RetryInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


/**
 * @description 生成接口实例的管理类
 */
abstract class RetrofitServiceManager() {
    protected val mRetrofit: Retrofit

    init {
        val interceptorBuild = HttpCommonInterceptor.Builder()
        val okHttpClientBuild = OkHttpClient.Builder()
        okHttpClientBuild.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptorBuild.build())//拦截器添加公共请求参数
            .addInterceptor(RetryInterceptor(2))//重试三次的拦截

        //初始化Retrofit
        mRetrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientBuild.build())
            .build()

    }
    abstract fun getBaseUrl(): String


    /**
     * 生成对应接口的实例
     *
     * @param service
     * @param <T>
     * @return
    </T> */
    fun <T: BaseService> create(service: Class<T>): T {
        return mRetrofit.create(service)

    }

    companion object {
        private const val CONNECTION_TIMEOUT = 5
        private const val READ_TIMEOUT = 20
        private const val WRITE_TIMEOUT = 10
    }
}
