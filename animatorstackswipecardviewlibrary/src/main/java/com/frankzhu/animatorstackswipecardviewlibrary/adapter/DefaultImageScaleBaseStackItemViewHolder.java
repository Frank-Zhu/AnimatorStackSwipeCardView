package com.frankzhu.animatorstackswipecardviewlibrary.adapter;

import android.view.View;
import android.widget.ImageView;

import com.frankzhu.animatorstackswipecardviewlibrary.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

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
public class DefaultImageScaleBaseStackItemViewHolder<T> extends AbstractScaleBaseStackItemViewHolder<T> {
  private CardView mCardView;
  private ImageView mImageView;

  public DefaultImageScaleBaseStackItemViewHolder(@NonNull View itemView) {
    super(itemView);
    mCardView = itemView.findViewById(R.id.card_view);
    mImageView = itemView.findViewById(R.id.iv_picture);
  }

  @Override
  public void bindViewData(T data) {
//    getScaleImageView().setScaleX(1 + getMaxScaleValue());
//    getScaleImageView().setScaleY(1 + getMaxScaleValue());
  }

  /**
   * 需要同步缩放的View
   *
   * @return 同步缩放的View
   */
  public View getScaleImageView() {
    return mImageView;
  }

  @Override
  public float getMaxScaleValue() {
    return 0.1F;
  }
}
