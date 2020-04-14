package com.frankzhu.animatorstackswipecardviewlibrary;

import android.content.Context;
import android.util.AttributeSet;

import com.frankzhu.animatorstackswipecardviewlibrary.adapter.AbstractBaseStackSwipeAdapter;
import com.frankzhu.animatorstackswipecardviewlibrary.adapter.AbstractScaleBaseStackItemViewHolder;
import com.frankzhu.animatorstackswipecardviewlibrary.bean.StackCardConfig;
import com.frankzhu.animatorstackswipecardviewlibrary.widget.AutoStackSwipeCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-19 17:04
 * Description: 子View同步缩放的切换View
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-19      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ChildScaleStackSwipeCardView<T> extends AutoStackSwipeCardView<T, AbstractBaseStackSwipeAdapter> {
  public ChildScaleStackSwipeCardView(@NonNull Context context) {
    this(context, null);
  }

  public ChildScaleStackSwipeCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public ChildScaleStackSwipeCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void startHolderAnimator(int position, RecyclerView.ViewHolder holder, float scale) {
    if (holder instanceof AbstractScaleBaseStackItemViewHolder && position == 1) {
      ((AbstractScaleBaseStackItemViewHolder) holder).startChildScaleAnimator(scale);
    }
  }

  @Override
  public void initViewConfig(AbstractBaseStackSwipeAdapter adapter, StackCardConfig config) {
    super.initViewConfig(adapter, config);
  }
}
