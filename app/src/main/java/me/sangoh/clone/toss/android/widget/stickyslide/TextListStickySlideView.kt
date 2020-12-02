package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.sangeoh.clone.toss.android.R

/**
 * StickySlideView에 RecyclerView를 적용한 것이다.
 */
class TextListStickySlideView constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerStickySlideView(context, attrs, defStyleAttr) {

    var data: ArrayList<String>
        get() = textListAdapter.data
        set(value) {
            textListAdapter.data = value
        }

    private val linearLayoutManager = LinearLayoutManager(context)
    private val textListAdapter = TextListAdapter(this)

    var onClickListener: ITextListStickySlideItemClickListener? = null

    init {
        this.recyclerView.layoutManager = linearLayoutManager
        this.recyclerView.adapter = textListAdapter
    }

    class TextListAdapter internal constructor(
        val parentObject: TextListStickySlideView,
        data: ArrayList<String> = ArrayList()
    ) : RecyclerView.Adapter<TextListAdapter.ViewHolder>() {

        internal var data: ArrayList<String> = data
            set(value) {
                field.clear()
                field.addAll(value)
                notifyDataSetChanged()
            }

        // 아이템 뷰를 저장하는 뷰홀더 클래스.
        class ViewHolder internal constructor(
            itemView: View,
        ) : RecyclerView.ViewHolder(itemView) {
            // 뷰 객체에 대한 참조. (hold strong reference)
            val textview: TextView = itemView.findViewById(R.id.tv_text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater =
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View =
                inflater.inflate(R.layout.custom_view_sticky_list_text_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textview.text = data[position]
            holder.textview.setOnClickListener {
                parentObject.close()
                parentObject.onClickListener?.onClick(data[position])
            }
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

}
