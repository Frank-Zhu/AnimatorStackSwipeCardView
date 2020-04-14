package com.frankzhu.animatorstackswipecardviewlibrary.layout;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-04-14 17:03
 * Description: 基础卡片式布局管理类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-04-14      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class AbstractStackSwipeCardLayoutManager extends RecyclerView.LayoutManager {

  public AbstractStackSwipeCardLayoutManager() {

  }

  /**
   * 最大显示页面数量
   *
   * @return int
   */
  public abstract int getMaxShowItem();

  /**
   * 子View相关动画实现
   *
   * @param position 第几个View
   * @param view     当前View
   */
  public abstract void offsetChildrenView(int position, View view);

  @Override
  public RecyclerView.LayoutParams generateDefaultLayoutParams() {
    return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
  }

  @Override
  public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    super.onLayoutChildren(recycler, state);
    //1.先移除所有的View
    detachAndScrapAttachedViews(recycler);

    //2.计算需要展示的View数量
    int itemCount = getItemCount();
    if (itemCount > getMaxShowItem()) {
      itemCount = getMaxShowItem();
    }

    //3.添加需要展示的数量View到页面中，注意这里需要倒序添加，才能保证前面的View是显示在上面的
    layoutChildrenView(recycler, itemCount);
  }

  /**
   * 添加子View
   *
   * @param recycler  Recycler
   * @param itemCount 需要添加的总数
   */
  private void layoutChildrenView(RecyclerView.Recycler recycler, int itemCount) {
    for (int i = itemCount - 1; i >= 0; i--) {
      View view = recycler.getViewForPosition(i);
      layoutAndAddChildrenView(view);
      offsetChildrenView(i, view);
    }
  }

  /**
   * 布局和添加相关的子View到页面中
   *
   * @param view 子View
   */
  private void layoutAndAddChildrenView(View view) {
    addView(view);
    layoutChildren(view);
  }

  /**
   * 摆放子View
   *
   * @param view 子View
   */
  private void layoutChildren(View view) {
    measureChildWithMargins(view, 0, 0);
    int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
    int heightSpace = getWidth() - getDecoratedMeasuredHeight(view);
    layoutDecorated(view, widthSpace / 2, heightSpace / 2, widthSpace / 2 + getDecoratedMeasuredWidth(view),
        heightSpace / 2 + getDecoratedMeasuredHeight(view));
  }
}
