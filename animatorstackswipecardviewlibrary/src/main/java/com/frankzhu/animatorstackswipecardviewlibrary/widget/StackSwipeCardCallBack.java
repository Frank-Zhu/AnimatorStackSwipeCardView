package com.frankzhu.animatorstackswipecardviewlibrary.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.frankzhu.animatorstackswipecardviewlibrary.bean.StackCardConfig;

import java.util.List;

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
public class StackSwipeCardCallBack<T> extends AbstractStackSwipeCardCallBack {
  private final static String TAG = StackSwipeCardCallBack.class.getSimpleName();
  private List<T> mResources;
  private RecyclerView.Adapter mAdapter;
  private final StackCardConfig mStackCardConfig;
  private int mCurrentPosition = 0;
  private RecyclerView mRecyclerView;

  public StackSwipeCardCallBack(StackCardConfig config, RecyclerView.Adapter adapter, RecyclerView recyclerView) {
    super(0, config.isLeftMode() ? ItemTouchHelper.RIGHT : (config.isRightMode() ? ItemTouchHelper.LEFT :
        (ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)));
    mStackCardConfig = config;
    mAdapter = adapter;
    mRecyclerView = recyclerView;
  }

  public StackCardConfig getStackCardConfig() {
    return mStackCardConfig;
  }

  public RecyclerView.Adapter getAdapter() {
    return mAdapter;
  }

  public void setResources(List<T> resources) {
    mResources = resources;
    mCurrentPosition = 0;
  }

  public int getCurrentPosition() {
    return mCurrentPosition;
  }

  /**
   * 处理数据循环效果
   *
   * @param viewHolder holder
   */
  @Override
  public void onLoopItems(@NonNull RecyclerView.ViewHolder viewHolder) {
    int position = viewHolder.getLayoutPosition();
    Log.d(TAG, "onLoopItems --> position = " + position);
    if (mResources != null && position < mResources.size()) {
      T remove = mResources.remove(position);
      mResources.add(remove);
      getAdapter().notifyDataSetChanged();
      if (getChildrenHolderAnimatorListener() != null) {
        getChildrenHolderAnimatorListener().onHolderRemovedAndSelected(0, mCurrentPosition, (mCurrentPosition + 1) % mResources.size());
        mCurrentPosition = (mCurrentPosition + 1) % mResources.size();
      }
    }
  }

  @Override
  void animatorChildrenView(int position, RecyclerView.ViewHolder holder, double offset) {
    View view = holder.itemView;
    float scale = (float) (getStackCardConfig().getScaleLevel() * position - (getStackCardConfig().getScaleLevel() * offset));
    float translation = (float) (getStackCardConfig().getTranslationLevel() * position - (getStackCardConfig().getTranslationLevel() * offset));
    view.setScaleX(1 - scale);
    view.setScaleY(1 - scale);
    if (getStackCardConfig().isBootomMode() || getStackCardConfig().isTopMode()) {
      view.setTranslationY(getStackCardConfig().isBootomMode() ? translation : -translation);
    } else {
      if (position > 0) {
        view.setTranslationX(getStackCardConfig().isRightMode() ? translation : -translation);
      }
    }
    //同步执行子View相关效果
    if (getChildrenHolderAnimatorListener() != null) {
      getChildrenHolderAnimatorListener().startHolderAnimator(position, holder, 1 + getStackCardConfig().getChildrenScaleLevel() * scale);
    }
  }

  /**
   * 切换到下一个卡片
   */
  public void swipeNextCard() {
    float width = getStackCardConfig().getSwitchWidth();
    ObjectAnimator animator = ObjectAnimator.ofFloat(this, "swipeLeftCard", 0, getStackCardConfig().isRightMode() ? -width : width);
    ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(this, "alpha", getStackCardConfig().getSwitchStartAlpha(),
        getStackCardConfig().getSwitchEndAlpha());
    ObjectAnimator lastAnimatorAlpha = ObjectAnimator.ofFloat(this, "lastAlpha", 0f, 0.4f);
    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.setInterpolator(new DecelerateInterpolator());
    animatorSet.setDuration(getStackCardConfig().getSwitchDuration());
    animatorSet.playTogether(animator, animatorAlpha, lastAnimatorAlpha);
    animatorSet.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {

      }

      @Override
      public void onAnimationEnd(Animator animation) {
        RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForLayoutPosition(0);
        if (holder != null) {
          onLoopItems(holder);
          holder.itemView.setTranslationX(0);
          holder.itemView.setAlpha(1);
        }
      }

      @Override
      public void onAnimationCancel(Animator animation) {

      }

      @Override
      public void onAnimationRepeat(Animator animation) {

      }
    });
    animatorSet.start();
  }

  /**
   * 用户动效更改自动切换
   *
   * @param swipeLeftCard 动效参数
   */
  public void setSwipeLeftCard(float swipeLeftCard) {
    RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForLayoutPosition(0);
    if (holder != null) {
      drawChildrenAnimator(mRecyclerView, holder, swipeLeftCard);
      holder.itemView.setTranslationX(swipeLeftCard);
    }
  }

  public void setAlpha(float alpha) {
    RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForLayoutPosition(0);
    if (holder != null) {
      holder.itemView.setAlpha(alpha);
    }
  }

  public void setLastAlpha(float lastAlpha) {
    Log.d("setLastAlpha", "setLastAlpha:" + lastAlpha);
    RecyclerView.ViewHolder lastHolder = mRecyclerView.findViewHolderForLayoutPosition(getStackCardConfig().getMaxShowItem() - 1);
    if (lastHolder != null) {
      lastHolder.itemView.setAlpha(lastAlpha);
    }
  }

}
