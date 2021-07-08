package uz.yusufbek_ibragimov.experimentapi.core.model.news_model

data class NewsResponseItem(
    val created_at: String,
    val description: String,
    val id: Int,
    val newsImg: String,
    val title: String,
    val updated_at: String
)