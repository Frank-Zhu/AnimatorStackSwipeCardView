package com.frankzhu.animatorstackswipecardviewlibrary.widget;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.frankzhu.animatorstackswipecardviewlibrary.StackSwipeCardView;

import androidx.annotation.NonNull;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-17 12:16
 * Description: 自动轮播View定义帮助类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-17      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BaseAutoSwipeViewHelper implements IAutoSwipeView, StackSwipeCardView.OnCardViewSelectedListener {
  private final static String TAG = BaseAutoSwipeViewHelper.class.getSimpleName();
  private final static int MSG_AUTO_SWIPE = 1;
  /**
   * 切换的View
   */
  private final ISwipeView mISwipeView;

  /**
   * 素材默认切换时间
   */
  private long mDefaultDuration = 5000L;//单位毫秒

  /**
   * 当前素材切换剩余时间
   */
  protected long mDurationRemain;

  private boolean isAutoSwitchResource = true;

  public BaseAutoSwipeViewHelper(ISwipeView swipeView) {
    mISwipeView = swipeView;
    mISwipeView.addCardViewSelectedListener(this);
  }

  @Override
  public void startAutoSlider() {
    startSwitchResourceTimer(getCurrentResourceSwitchTimer(0));
  }

  @Override
  public void pauseAutoSlider() {
    stopSwitchResourceTimer();
  }

  @Override
  public void releaseSlider() {
    stopSwitchResourceTimer();
    if (getSwipeView() != null) {
      getSwipeView().removeCardViewSelectedListener(this);
    }
  }

  @Override
  public void resumeAutoSlider() {
    startSwitchResourceTimer(getCurrentResourceSwitchTimer(mISwipeView.getCurrentPosition()));
  }

  @Override
  public void setDefaultSwitchDuration(long duration) {
    mDefaultDuration = duration;
  }

  @Override
  public long getCurrentResourceSwitchTimer(int position) {
    return mDefaultDuration;
  }

  @Override
  public void setAutoSwitchResource(boolean isAutoSwitch) {
    isAutoSwitchResource = isAutoSwitch;
  }

  @Override
  public boolean isAutoSwitchResource() {
    return isAutoSwitchResource;
  }

  public ISwipeView getSwipeView() {
    return mISwipeView;
  }

  /**
   * 启动切换Timer
   *
   * @param time 切换的时间 单位毫秒
   */
  protected void startSwitchResourceTimer(long time) {
    Log.d(TAG, "startSwitchResourceTimer --> time = " + time);
    stopSwitchResourceTimer();
    //切换时间大于0才启动定时器
    if (time > 0 && isAutoSwitchResource()) {
      mHandler.sendEmptyMessageDelayed(MSG_AUTO_SWIPE, time / 1000L);
    }
  }

  /**
   * 停止素材切换定时器
   */
  private void stopSwitchResourceTimer() {
    Log.d(TAG, "stopSwitchResourceTimer --> 停止素材切换定时器");
    if (mHandler != null) {
      mHandler.removeMessages(MSG_AUTO_SWIPE);
    }
  }

  @SuppressLint("HandlerLeak")
  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(@NonNull Message msg) {
      if (msg.what == MSG_AUTO_SWIPE) {
        if (getSwipeView() != null) {
          getSwipeView().moveToNext();
        }
      }
    }
  };

  @Override
  public void onCardViewRemovedAndSelected(int dataPosition, int removePosition, int selectedPosition) {
    startSwitchResourceTimer(getCurrentResourceSwitchTimer(dataPosition));
  }
}
