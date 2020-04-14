package com.frankzhu.animatorstackswipecardviewlibrary.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-19 17:10
 * Description: 同步缩放的ViewHolder
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-19      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class AbstractBaseStackItemViewHolder<T> extends RecyclerView.ViewHolder {
  public AbstractBaseStackItemViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  public abstract void bindViewData(T data);
}
