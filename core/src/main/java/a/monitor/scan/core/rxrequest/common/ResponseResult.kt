package a.monitor.scan.core.rxrequest.common

import a.monitor.scan.core.rxrequest.common.OneResponse
import java.io.Serializable

/**
 * @description 请求返回结果数据 符合 {"code":"0000","msg":"","data":{},"datas":[]} 的基类
 */
class ResponseResult : OneResponse(), Serializable

