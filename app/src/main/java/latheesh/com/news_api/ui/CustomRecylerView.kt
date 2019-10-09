package latheesh.com.news_api.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import latheesh.com.news_api.R
import latheesh.com.news_api.Utils.gone
import latheesh.com.news_api.Utils.visible
import latheesh.com.news_api.model.DataStatus

class CustomRecylerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    /**
     * Empty layout
     */
    private var mEmptyView: View? = null

    /**
     * Progress view
     */
    private var mProgressView: View? = null

    /**
     * Column width for grid layout
     */
    private var columnWidth: Int = 0

    init {
        gone()
        if (attrs != null) {
            val attrsArray = intArrayOf(android.R.attr.columnWidth)
            val array = context.obtainStyledAttributes(
                attrs, attrsArray)
            columnWidth = array.getDimensionPixelSize(0, -1)
            array.recycle()
        }
    }

    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        visible()
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(mAdapterObserver)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(mAdapterObserver)
        refreshState()
    }

    private fun refreshState() {
        adapter?.let {
            val noItems = 0 == it.itemCount
            if (noItems) {
                mProgressView?.gone()
                mEmptyView?.visible()
                gone()
            } else {
                mProgressView?.gone()
                mEmptyView?.gone()
                visible()
            }
        }
    }

    fun setEmptyView(emptyView: View) {
        this.mEmptyView = emptyView
        mEmptyView?.gone()
    }

    fun setProgressView(progressView: View) {
        this.mProgressView = progressView
        mProgressView?.visible()
    }

    fun setEmptyMessage(@StringRes mEmptyMessageResId: Int) {
        val emptyText = mEmptyView?.findViewById<TextView>(R.id.blanktext)
        emptyText?.setText(mEmptyMessageResId)
    }


    fun showState(status: DataStatus) {
        when (status) {
            DataStatus.SUCCESS, DataStatus.ERROR -> {
                mProgressView?.gone()
                mEmptyView?.visible()
            }
            DataStatus.LOADING -> {
                mEmptyView?.gone()
                mProgressView?.visible()
            }
        }
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        if (layoutManager is GridLayoutManager) {
            val manager = layoutManager as GridLayoutManager
            if (columnWidth > 0) {
                val spanCount = Math.max(1, measuredWidth / columnWidth)
                manager.spanCount = spanCount
            }
        }
    }


    private val mAdapterObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() = refreshState()
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) = refreshState()
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) = refreshState()
    }

}