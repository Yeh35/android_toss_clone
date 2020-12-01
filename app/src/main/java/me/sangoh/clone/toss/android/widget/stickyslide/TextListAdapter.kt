package me.sangoh.clone.toss.android.widget.stickyslide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.sangeoh.clone.toss.android.R


class TextListAdapter(
    private val data: List<String>
) : RecyclerView.Adapter<TextListAdapter.ViewHolder>() {

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 뷰 객체에 대한 참조. (hold strong reference)
        val textview: TextView = itemView.findViewById(R.id.tv_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.custom_view_sticky_list_text_item , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }


}