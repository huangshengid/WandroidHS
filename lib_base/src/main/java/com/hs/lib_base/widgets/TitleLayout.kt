package com.hs.lib_base.widgets

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.hs.lib_base.R
import com.hs.lib_base.databinding.LayoutTitleBinding
import com.hs.lib_base.utils.ScreenUtil

/**
 * @Author      : huangsheng
 * @Email       : 942698056@qq.com
 * @Date        : on 2023-09-07 22:21.
 * @Description :描述
 */
class TitleLayout(context:Context,attrs: AttributeSet): ConstraintLayout(context,attrs) {

    private var mBinding: LayoutTitleBinding

    //执行的顺序 主构造》伴生对象》init代码块按照顺序执行》此构造
    init {
        // 自定义TitleLayout的相关属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout)
        val titleBackGroundColor = typedArray.getColor(R.styleable.TitleLayout_titleBackgroundColor,
        ContextCompat.getColor(context,R.color.purple_500))
        val backIconRes = typedArray.getResourceId(R.styleable.TitleLayout_backIconRes,R.drawable.ic_back)
        val isShowBack = typedArray.getBoolean(R.styleable.TitleLayout_isShowBack, true)
        val titleTextColor = typedArray.getColor(
            R.styleable.TitleLayout_titleTextColor,
            ContextCompat.getColor(context, R.color._ffffff)
        )
        val titleTextSize = typedArray.getDimensionPixelSize(
            R.styleable.TitleLayout_titleTextSize,
            ScreenUtil.sp2px(context, 20f)
        )
        val titleText = typedArray.getString(R.styleable.TitleLayout_titleText)
        typedArray.recycle()

        // 自定义TitleLayout的布局
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_title,
            this,
            true
        )

        //设置TitleLayout的背景色
        mBinding.clTitleBar.setBackgroundColor(titleBackGroundColor)

        //TitleBar的返回键
        mBinding.ivBack.apply {
            visibility = if (isShowBack) View.VISIBLE else View.GONE
        }

        //TitleBar的文字标题
        mBinding.tvTitleText.apply {
            setTextColor(titleTextColor)
            setTextSize(TypedValue.COMPLEX_UNIT_PX,titleTextSize.toFloat())
            text = titleText
            isSelected = true
        }


        fun setBackIcon(resId : Int ) : TitleLayout{
            mBinding.ivBack.setImageResource(resId)
            return this
        }




    }


}