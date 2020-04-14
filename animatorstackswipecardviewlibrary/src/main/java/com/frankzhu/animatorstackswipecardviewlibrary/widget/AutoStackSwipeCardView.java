package com.frankzhu.animatorstackswipecardviewlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.frankzhu.animatorstackswipecardviewlibrary.StackSwipeCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-17 11:54
 * Description: 自动播放的卡片式View
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-17      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class AutoStackSwipeCardView<T, P extends RecyclerView.Adapter> extends StackSwipeCardView<T, P> implements IAutoSwipeView {
  private IAutoSwipeView mIAutoSwipeView;

  public AutoStackSwipeCardView(@NonNull Context context) {
    super(context);
  }

  public AutoStackSwipeCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public AutoStackSwipeCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setIAutoSwipeView(IAutoSwipeView IAutoSwipeView) {
    mIAutoSwipeView = IAutoSwipeView;
  }

  @Override
  public void startAutoSlider() {
    if (mIAutoSwipeView != null) {
      mIAutoSwipeView.startAutoSlider();
    }
  }

  @Override
  public void pauseAutoSlider() {
    if (mIAutoSwipeView != null) {
      mIAutoSwipeView.pauseAutoSlider();
    }
  }

  @Override
  public void releaseSlider() {
    if (mIAutoSwipeView != null) {
      mIAutoSwipeView.releaseSlider();
    }
  }

  @Override
  public void resumeAutoSlider() {
    if (mIAutoSwipeView != null) {
      mIAutoSwipeView.resumeAutoSlider();
    }
  }

  @Override
  public void setDefaultSwitchDuration(long duration) {
    if (mIAutoSwipeView != null) {
      mIAutoSwipeView.setDefaultSwitchDuration(duration);
    }
  }

  @Override
  public long getCurrentResourceSwitchTimer(int position) {
    if (mIAutoSwipeView != null) {
      return mIAutoSwipeView.getCurrentResourceSwitchTimer(position);
    }
    return 0;
  }

  @Override
  public void setAutoSwitchResource(boolean isAutoSwitch) {
    if (mIAutoSwipeView != null) {
      mIAutoSwipeView.setAutoSwitchResource(isAutoSwitch);
    }
  }

  @Override
  public boolean isAutoSwitchResource() {
    return mIAutoSwipeView == null || mIAutoSwipeView.isAutoSwitchResource();
  }
}
