package a.monitor.scan.baseprogram.net.api

import a.monitor.scan.baseprogram.bean.Gank
import a.monitor.scan.baseprogram.net.response.BaseGankResponse
import a.monitor.scan.ktbaseliabrary.extension.create
import com.tbright.ktbaselibrary.net.interceptor.MULTI_URL_HEADER
import com.tbright.ktbaseproject.demo.customconfig.GANK_URL
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GankServices {

    companion object {
        val instance: GankServices by lazy {
            create(GankServices::class.java)
        }
    }

    @GET("api/v2/categories/Girl")
    @Headers(MULTI_URL_HEADER + GANK_URL) //多域名header设置
    fun getGanHuo(): Deferred<BaseGankResponse<List<Gank>>>

    @POST
    @Headers(MULTI_URL_HEADER + GANK_URL)
    fun postGank(): Deferred<BaseGankResponse<List<Gank>>>
}