package com.frankzhu.animatorstackswipecardviewlibrary.layout;

import android.view.View;
import android.view.ViewGroup;

import com.frankzhu.animatorstackswipecardviewlibrary.bean.StackCardConfig;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-04-14 17:08
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-04-14      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class StackSwipeCardLayoutManager extends AbstractStackSwipeCardLayoutManager {
  private final StackCardConfig mStackCardConfig;

  public StackSwipeCardLayoutManager(StackCardConfig config) {
    mStackCardConfig = config;
  }

  @Override
  public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
  }

  public StackCardConfig getStackCardConfig() {
    return mStackCardConfig;
  }

  @Override
  public int getMaxShowItem() {
    return mStackCardConfig.getMaxShowItem();
  }

  @Override
  public void offsetChildrenView(int position, View view) {
    viewAlpha(position, view);
    viewTranslation(position, view);
    viewScale(position, view);
  }

  /**
   * 设置透明度
   *
   * @param position 当前View下标
   * @param view     当前View
   */
  private void viewAlpha(int position, View view) {
    float alpha = (position == getMaxShowItem() - 1) ? 0 : (1 - getStackCardConfig().getAlpha() * position);
    view.setAlpha(alpha);
  }

  /**
   * 设置缩放大小
   *
   * @param position 当前View下标
   * @param view     当前View
   */
  private void viewScale(int position, View view) {
    view.setScaleX(1 - getStackCardConfig().getScaleLevel() * position);
    if (position == getMaxShowItem() - 1) {
      view.setScaleY(1 - getStackCardConfig().getScaleLevel() * (position - 1));
    } else {
      view.setScaleY(1 - getStackCardConfig().getScaleLevel() * position);
    }
  }

  /**
   * 设置位移偏移量
   *
   * @param position 当前View下标
   * @param view     当前View
   */
  private void viewTranslation(int position, View view) {
//    int translation = getStackCardConfig().getTranslationLevel() * position;
//    if (getStackCardConfig().isBottomMode() || getStackCardConfig().isTopMode()) {
//      view.setTranslationY(getStackCardConfig().isBottomMode() ? translation : -translation);
//    } else {
//      view.setTranslationX(getStackCardConfig().isRightMode() ? translation : -translation);
//    }
    if (position == getMaxShowItem() - 1) {
      int translation = getStackCardConfig().getTranslationLevel() * (position - 1);
      if (getStackCardConfig().isBottomMode() || getStackCardConfig().isTopMode()) {
        view.setTranslationY(getStackCardConfig().isBottomMode() ? translation : -translation);
      } else {
        view.setTranslationX(getStackCardConfig().isRightMode() ? translation : -translation);
      }
      view.setScaleY(1 - getStackCardConfig().getScaleLevel() * (position - 1));
    } else {
      int translation = getStackCardConfig().getTranslationLevel() * position;
      if (getStackCardConfig().isBottomMode() || getStackCardConfig().isTopMode()) {
        view.setTranslationY(getStackCardConfig().isBottomMode() ? translation : -translation);
      } else {
        view.setTranslationX(getStackCardConfig().isRightMode() ? translation : -translation);
      }
      view.setScaleY(1 - getStackCardConfig().getScaleLevel() * position);
    }
  }
}
