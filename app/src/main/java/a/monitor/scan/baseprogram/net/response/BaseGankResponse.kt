package a.monitor.scan.baseprogram.net.response

import com.tbright.ktbaselibrary.base.BaseResponse

class BaseGankResponse<T> : BaseResponse<T>() {

    var data: T? = null

    var msg: String? = null

    var status: Int = 100

    override fun isResponseSuccess(): Boolean {
        return status == 100
    }

    override fun getResponseData(): T? {
        return data
    }

    override fun getResponseMessage(): String? {
       if(status == 100) {
           "请求成功"
       } else if(status == 200) {
           "请求失败"
       } else msg

        return if (status == 100) "请求成功！！！"
        else msg
    }

    override fun getResponseStatus(): Any? {
        return status
    }
}