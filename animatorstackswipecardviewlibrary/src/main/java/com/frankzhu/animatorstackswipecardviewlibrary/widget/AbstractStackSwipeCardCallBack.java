package com.frankzhu.animatorstackswipecardviewlibrary.widget;

import android.graphics.Canvas;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-16 11:38
 * Description: 基础堆栈卡片效果滑动处理回调
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class AbstractStackSwipeCardCallBack extends ItemTouchHelper.SimpleCallback {
  private final static String TAG = AbstractStackSwipeCardCallBack.class.getSimpleName();
  private ChildrenHolderAnimatorListener mChildrenHolderAnimatorListener;

  public AbstractStackSwipeCardCallBack(int dragDirs, int swipeDirs) {
    super(dragDirs, swipeDirs);
  }

  public void setChildrenHolderAnimatorListener(ChildrenHolderAnimatorListener childrenHolderAnimatorListener) {
    mChildrenHolderAnimatorListener = childrenHolderAnimatorListener;
  }

  public ChildrenHolderAnimatorListener getChildrenHolderAnimatorListener() {
    return mChildrenHolderAnimatorListener;
  }

  @Override
  public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                        @NonNull RecyclerView.ViewHolder target) {
    return false;
  }

  @Override
  public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    //当已经滑动删除了的时候会被回掉--删除数据，循环的效果
    Log.d(TAG, "onSwiped --> direction = " + direction);
    onLoopItems(viewHolder);
  }

  /**
   * 处理数据循环效果
   *
   * @param viewHolder holder
   */
  abstract void onLoopItems(@NonNull RecyclerView.ViewHolder viewHolder);

  /**
   * 移动时的动画效果处理
   *
   * @param position 当前第几个View
   * @param holder   当前View对象
   * @param offset   当前偏移量
   */
  abstract void animatorChildrenView(int position, RecyclerView.ViewHolder holder, double offset);

  @Override
  public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                          int actionState, boolean isCurrentlyActive) {
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    drawChildrenAnimator(recyclerView, viewHolder, dX);
  }

  /**
   * 绘制子View相关动画
   *
   * @param recyclerView RecyclerView
   * @param viewHolder   ViewHolder
   * @param dX           X轴偏移距离
   */
  protected void drawChildrenAnimator(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX) {
    double maxDistance = recyclerView.getWidth() * getSwipeThreshold(viewHolder);
    double distance = Math.abs(dX);
    //动画执行的百分比
    double fraction = distance / maxDistance;
    if (fraction > 1) {
      fraction = 1;
    }

    int itemCount = recyclerView.getChildCount();

    animatorChildrenViews(recyclerView, fraction, itemCount);
  }

  /**
   * 绘制所以子View的动画
   *
   * @param recyclerView RecyclerView
   * @param fraction     ViewHolder
   * @param itemCount    X轴偏移距离
   */
  private void animatorChildrenViews(@NonNull RecyclerView recyclerView, double fraction, int itemCount) {
    for (int i = 0; i < itemCount; i++) {
      RecyclerView.ViewHolder holder = recyclerView.findViewHolderForLayoutPosition(i);
      if (holder != null) {
        animatorChildrenView(i, holder, fraction);
      }
    }
  }
}
