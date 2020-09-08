package a.monitor.scan.core.common.extend

import a.monitor.scan.core.common.utils.ImageUtils
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.request.RequestListener

/**
 * @name ImageViewExtend
 * @description ImageView 加载网络图片的扩展方法
 */
inline fun ImageView.loadImage(imgUrl: String): ImageView {
    ImageUtils.loadImageUrl(this.context, imgUrl, this)
    return this
}

inline fun ImageView.loadImage(imgUrl: String, width: Int, height: Int): ImageView {
    ImageUtils.loadImageUrl(this.context, imgUrl, this,width, height)
    return this
}

inline fun ImageView.loadBitmap(imgUrl: String): ImageView {
    ImageUtils.loadImageBitmap(this.context, imgUrl, this)
    return this
}

inline fun ImageView.loadGif(imgUrl: String): ImageView {
    ImageUtils.loadImageGif(this.context, imgUrl, this)
    return this
}

inline fun ImageView.loadImgCallBack(imgUrl: String, requestListener: RequestListener<Bitmap>): ImageView {
    ImageUtils.loadImageCallBack(this.context, imgUrl, this, requestListener)
    return this
}
