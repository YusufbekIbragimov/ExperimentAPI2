package uz.yusufbek_ibragimov.experimentapi.core.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponse
import uz.yusufbek_ibragimov.experimentapi.core.model.news_model.NewsResponseItem
import uz.yusufbek_ibragimov.experimentapi.core.utils.GlideApp
import uz.yusufbek_ibragimov.experimentapi.databinding.ListItemBinding

/**
 * Bismillah
 * Ibragimov Yusufbek
 * 07.07.2021
 * ibragimovy390@gmail.com
 * */

class NewsAdapter(var context: Context, var onLongClickListener: OnLongClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var dataList=ArrayList<NewsResponseItem>()

    fun setData(newsResponse: NewsResponse) {
        this.dataList.clear()
        dataList.addAll(newsResponse)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(var bind: ListItemBinding) : RecyclerView.ViewHolder(bind.root) {

        fun bindData(newsResponseItem: NewsResponseItem, position: Int) {

            GlideApp.with(context).load(newsResponseItem.newsImg).into(bind.imageId)
            bind.titleId.text=newsResponseItem.title
            bind.description.text=newsResponseItem.description
            bind.createdId.text=newsResponseItem.created_at

            bind.root.setOnLongClickListener {
                if (onLongClickListener != null) {
                    onLongClickListener.onLongClick(newsResponseItem)
                }
                false
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val bind = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(bind)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.bindData(dataList[position], position)

    override fun getItemCount(): Int {

        Log.d("mytag", "getItemCount: ${dataList?.size!!}")
        return dataList.size

    }

    interface OnLongClickListener {
        fun onLongClick(newsResponseItem: NewsResponseItem)
    }

}