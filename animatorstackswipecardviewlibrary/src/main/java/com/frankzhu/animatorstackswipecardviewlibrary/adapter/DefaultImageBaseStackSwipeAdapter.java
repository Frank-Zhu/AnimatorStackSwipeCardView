package com.frankzhu.animatorstackswipecardviewlibrary.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.frankzhu.animatorstackswipecardviewlibrary.R;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-19 17:10
 * Description: 默认图片卡片切换的基础适配器
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-19      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DefaultImageBaseStackSwipeAdapter<T> extends AbstractBaseStackSwipeAdapter<T> {

  public DefaultImageBaseStackSwipeAdapter(Context context) {
    super(context);
  }

  public DefaultImageBaseStackSwipeAdapter(Context context, List<T> list) {
    super(context, list);
  }

  @NonNull
  @Override
  public AbstractBaseStackItemViewHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new DefaultImageScaleBaseStackItemViewHolder<>(getLayoutInflater().inflate(R.layout.item_animator_default_picture_view_holer_view,
        viewGroup, false));
  }
}
