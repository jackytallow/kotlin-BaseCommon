package a.monitor.scan.core.rxrequest.common

import java.io.Serializable

/**
 * @description 请求返回结果数据基类
 */
open class OneResponse(val code: String = "0000", val msg: String = ""): Serializable {
    companion object {
        val REQUEST_OK = "0000"  //请求成功的code码
        val REQUEST_ERROR = "-1" //请求失败的code码
    }
}
