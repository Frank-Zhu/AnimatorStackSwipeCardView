package com.frankzhu.animatorstackswipecardview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.frankzhu.animatorstackswipecardviewlibrary.adapter.DefaultImageBaseStackSwipeAdapter;
import com.frankzhu.animatorstackswipecardviewlibrary.adapter.DefaultImageScaleBaseStackItemViewHolder;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-16 12:33
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BottomCardAdapter extends DefaultImageBaseStackSwipeAdapter<Integer> {

  public BottomCardAdapter(Context context, List<Integer> list) {
    super(context, list);
  }

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new ItemViewHolder(getLayoutInflater().inflate(R.layout.item_animator_default_picture_view_holer_view,
        viewGroup, false));
  }

  public class ItemViewHolder extends DefaultImageScaleBaseStackItemViewHolder<Integer> {
    public ItemViewHolder(View itemView) {
      super(itemView);
    }

    public void bindViewData(Integer resId) {
      super.bindViewData(resId);
      ((ImageView) getScaleImageView()).setImageResource(resId);
    }
  }
}
