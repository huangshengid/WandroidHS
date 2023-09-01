package com.hs.lib_base.data.bean

/**
 * 分页实体
 *
 * @author LTP  2022/3/22
 */
//TODO json 转成data class的工具生成  https://blog.csdn.net/vitaviva/article/details/104396226
data class PageResponse<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)