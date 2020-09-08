package a.monitor.scan.core.common.adapter


import a.monitor.scan.core.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.recyclerview.widget.RecyclerView
import a.monitor.scan.core.common.extend.loadImage
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast


/**
 * @description 所有RecyclerView 的Adapter 的基类
 */
abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<VH>(), View.OnClickListener,
    View.OnLongClickListener {
    private val mDatas: MutableList<T> = ArrayList<T>()
    private lateinit var context: Context
    private var clickListener: OnItemClickListener<T>? = null
    private var longClickListener: OnItemLongClickListener<T>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        val convertView =
            LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)
        convertView.setOnClickListener(this)
        convertView.setOnLongClickListener(this)
        return VH(convertView)
    }

    /**
     * 设置Item 的点击事件
     */
    fun setOnItemClick(listener: OnItemClickListener<T>) {
        clickListener = listener
    }

    /**
     * 设置Item 的点击事件
     */
    fun setOnItemClick(onClick: (view: View, position: Int, data: T) -> Unit) {
        clickListener = object : OnItemClickListener<T> {
            override fun onItemClick(view: View, position: Int, data: T) {
                onClick(view, position, data)
            }
        }
    }

    /**
     * 设置Item 的长按事件
     */
    fun setOnItemLongClick(listener: OnItemLongClickListener<T>) {
        longClickListener = listener
    }

    /**
     * 设置Item 的长按事件
     */
    fun setOnItemLongClick(onLongClick: (view: View, position: Int, data: T) -> Boolean) {
        longClickListener = object : OnItemLongClickListener<T> {
            override fun onItemLongClick(view: View, position: Int, data: T): Boolean {
                return onLongClick(view, position, data)
            }
        }
    }

    override fun onClick(p0: View?) {
        val position: Int = p0?.getTag(R.id.itemViewPosition) as Int
        val data: T = mDatas.get(position)
        clickListener?.onItemClick(p0, position, data)
    }

    override fun onLongClick(p0: View?): Boolean {
        val position: Int = p0?.getTag(R.id.itemViewPosition) as Int
        val data: T = mDatas.get(position)
        return longClickListener?.onItemLongClick(p0, position, data) ?: true
    }

    /**
     * 设置数据,并且会清空原数据列表
     * @param datas 新的数据列表
     */
    open fun setDatas(datas: List<T>) {
        mDatas.clear()
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    /**
     * 添加新的数据列表到原数据列表末尾
     * @param datas 新的数据列表
     */
    open fun addDatas(datas: List<T>) {
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    /**
     * 添加新的数据列表到原数据列表的指定位置
     * @param position 需要添加的指定位置
     * @param datas 新的数据列表
     */
    open fun addDatas(position: Int, datas: List<T>) {
        val pos = if (position > mDatas.size) {
            mDatas.size
        } else position
        mDatas.addAll(pos, datas)
        notifyDataSetChanged()
    }

    /**
     * 更新数据列表指定位置上的数据
     * @param position 需要更新数据的位置
     * @param data 更新后的数据
     */
    open fun updateData(position: Int, data: T) {
        if (position < mDatas.size) {
            mDatas.removeAt(position)
            mDatas.add(position, data)
            notifyItemChanged(position)
        }
    }

    /**
     * 添加新的数据到原数据列表末尾
     * @param datas 新的数据
     */
    open fun addData(data: T) {
        mDatas.add(data)
        notifyItemInserted(mDatas.size - 1)
    }

    /**
     * 添加新的数据到原数据列表的指定位置
     * @param position 需要添加的指定位置
     * @param data 新的数据
     */
    open fun addData(position: Int, data: T) {
        val pos = if (position > mDatas.size) {
            mDatas.size
        } else position
        mDatas.add(pos, data)
        notifyItemInserted(pos)
    }

    /**
     * 移除指定位置上的数据
     * @param position 需要添加的指定位置
     */
    open fun removeDataAt(position: Int) {
        if (position < mDatas.size) {
            mDatas.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    protected open fun showToast(msg: String) {
        context.runOnUiThread {
            context.toast(msg)
        }
    }

    /**
     * 移除指定的数据
     * @param data 需要移除的数据实体
     */
    open fun removeData(data: T) {
        if (mDatas.contains(data)) {
            val position = mDatas.indexOf(data)
            mDatas.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * 根据View的类型获取对应的Item布局的ID
     */
    @LayoutRes
    abstract fun getLayoutId(viewType: Int): Int


    /**
     * 绑定ViewHolder 时执行的方法，在此方法里处理对Item的view的操作
     */
    abstract fun onBindVH(holder: VH, position: Int, datas: T)

    /**
     * 返回数据的数量
     */
    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setTag(R.id.itemViewPosition, position)
        onBindVH(holder, position, mDatas.get(position))
    }


}

/**
 *Item 的点击事件
 */
interface OnItemClickListener<T> {
    fun onItemClick(view: View, position: Int, data: T)
}

/**
 *Item 的长按事件
 */
interface OnItemLongClickListener<T> {
    fun onItemLongClick(view: View, position: Int, data: T): Boolean
}

/**
 * 所有的Adapter 使用的ViewHolder
 */
class VH(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


    /**
     * 根据View的id获取对应的View
     */
    inline fun <reified E : View> getView(@IdRes viewId: Int): E {
        return itemView.find<E>(viewId)
    }

    /**
     * 对TextView及其子类设置文本内容
     * @param viewId 对应的View 的id
     * @param value 需要设置的文本内容
     */
    inline fun setText(@IdRes viewId: Int, value: String) {
        val view: View = this.getView(viewId)
        if (view is TextView) {
            val tv: TextView = view
            tv.text = value
        }
    }

    /**
     * 对TextView及其子类设置文本内容
     * @param viewId 对应的View 的id
     * @param value 需要设置的文本内容
     */
    inline fun setText(@IdRes viewId: Int, value: Spannable) {
        val view: View = this.getView(viewId)
        if (view is TextView) {
            val tv: TextView = view
            tv.text = value
        }
    }

    /**
     * 对TextView及其子类设置文本内容
     * @param viewId 对应的View 的id
     * @param stringRes 需要设置的文本资源的id
     */
    inline fun setText(@IdRes viewId: Int, @StringRes stringRes: Int) {
        val view: View = this.getView(viewId)
        if (view is TextView) {
            val tv: TextView = view
            tv.setText(stringRes)
        }
    }

    /**
     * 对ImageView及其子类设置图片
     * @param viewId 对应的View 的id
     * @param resId 需要设置的图片资源的id
     */
    inline fun setImageResource(@IdRes viewId: Int, @DrawableRes resId: Int) {
        val view: View = getView(viewId)
        if (view is ImageView) {
            val iv: ImageView = view
            iv.imageResource = resId
        }
    }

    /**
     * 对ImageView及其子类设置图片
     * @param viewId 对应的View 的id
     * @param imgUrl 需要设置的网络图片的地址
     */
    inline fun setImageUrl(@IdRes viewId: Int, imgUrl: String) {
        val view: View = getView(viewId)
        if (view is ImageView) {
            val iv: ImageView = view
            iv.loadImage(imgUrl)
        }
    }

    /**
     * 对ImageView及其子类设置图片
     * @param viewId 对应的View 的id
     * @param imgUrl 需要设置的网络图片的地址
     */
    inline fun setImageUrl(@IdRes viewId: Int, imgUrl: String, width: Int, height: Int) {
        val view: View = getView(viewId)
        if (view is ImageView) {
            val iv: ImageView = view
            iv.loadImage(imgUrl,width,height)
        }
    }

    /**
     * 对View及其子类设置背景图片
     * @param viewId 对应的View 的id
     * @param resId 需要设置的背景图片的资源地址
     */
    inline fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes resId: Int) {
        val view: View = getView(viewId)
        view.setBackgroundResource(resId)
    }

    /**
     * 对View及其子类设置背景颜色
     * @param viewId 对应的View 的id
     * @param color 需要设置的背景颜色
     */
    inline fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) {
        val view: View = getView(viewId)
        view.setBackgroundColor(color)
    }


    /**
     * 对View及其子类设置点击事件
     * @param viewId 对应的View 的id
     * @param color 需要设置的背景颜色
     */
    inline fun setOnClick(@IdRes viewId: Int, crossinline onClick: (View) -> Unit) {
        val view: View = getView(viewId)
        view.setOnClickListener {
            onClick.invoke(it)
        }
    }


    fun findViewById(@IdRes id: Int): View? {
        return itemView.findViewById(id)
    }


    fun setTextSize(@IdRes id: Int, size: Int) {
        val view = itemView.findViewById<View>(id)
        if (view != null && view is TextView) {
            view.textSize = size.toFloat()
        }
    }

    fun setTextColor(@IdRes id: Int, @ColorInt color: Int) {
        val view = itemView.findViewById<View>(id)
        if (view != null && view is TextView) {
            view.setTextColor(color)
        }
    }

    fun setTextColor(@IdRes id: Int, color: ColorStateList?) {
        val view = itemView.findViewById<View>(id)
        if (view != null && view is TextView) {
            view.setTextColor(color)
        }
    }

    fun setImageRes(@IdRes id: Int, @DrawableRes resId: Int) {
        val view = itemView.findViewById<View>(id)
        if (view != null && view is ImageView) {
            view.setImageResource(resId)
        }
    }

    fun setImageDrawable(@IdRes id: Int, drawable: Drawable?) {
        if (drawable == null) {
            return
        }
        val view = itemView.findViewById<View>(id)
        if (view != null && view is ImageView) {
            view.setImageDrawable(drawable)
        }
    }


    fun setImageBitmap(@IdRes id: Int, bitmap: Bitmap?) {
        if (bitmap == null) {
            return
        }
        val view = itemView.findViewById<View>(id)
        if (view != null && view is ImageView) {
            view.setImageBitmap(bitmap)
        }
    }

    fun setImageColorFilter(@IdRes id: Int, colorFilter: ColorFilter?) {
        val view = itemView.findViewById<View>(id)
        if (view != null && view is ImageView) {
            view.colorFilter = colorFilter
        }
    }


    fun setChecked(@IdRes id: Int, checked: Boolean) {
        val view = itemView.findViewById<View>(id)
        if (view != null && view is CompoundButton) {
            view.isChecked = checked
        }
    }

    fun setSelected(@IdRes id: Int, selected: Boolean) {
        val view = itemView.findViewById<View>(id)
        if (view != null && view is CompoundButton) {
            view.isSelected = selected
        }
    }

    fun setVisibility(@IdRes id: Int, visibility: Int) {
        val view = itemView.findViewById<View>(id)
        if (view != null) {
            view.visibility = visibility
        }
    }

    fun setEnable(@IdRes id: Int, enable: Boolean) {
        val view = itemView.findViewById<View>(id)
        if (view != null) {
            view.isEnabled = enable
        }
    }

    fun setClickable(@IdRes id: Int, clickable: Boolean) {
        val view = itemView.findViewById<View>(id)
        if (view != null) {
            view.isClickable = clickable
        }
    }

}