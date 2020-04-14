package com.frankzhu.animatorstackswipecardviewlibrary.adapter;

import android.view.View;

import androidx.annotation.NonNull;

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
public abstract class AbstractScaleBaseStackItemViewHolder<T> extends AbstractBaseStackItemViewHolder<T> {
  public AbstractScaleBaseStackItemViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  /**
   * 需要同步缩放的View
   *
   * @return 同步缩放的View
   */
  public abstract View getScaleImageView();

  /**
   * 获取最小缩放值
   *
   * @return 最大缩放值
   */
  public abstract float getMaxScaleValue();

  /**
   * 同步播放子View的缩放动画
   *
   * @param value 变化值
   */
  public void startChildScaleAnimator(float value) {
    if (getScaleImageView() != null) {
      getScaleImageView().setScaleX(value);
      getScaleImageView().setScaleY(value);
    }
  }
}
