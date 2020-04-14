package com.frankzhu.animatorstackswipecardviewlibrary.widget;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-17 11:55
 * Description: 自动播放的View接口定义
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-17      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface IAutoSwipeView {
  /**
   * 开始自动切换
   */
  void startAutoSlider();

  /**
   * 暂停自动切换
   */
  void pauseAutoSlider();

  /**
   * 释放自动切换相关资源
   */
  void releaseSlider();

  /**
   * 恢复自动切换
   */
  void resumeAutoSlider();

  /**
   * 设置默认切换时长
   *
   * @param duration 切换时长
   */
  void setDefaultSwitchDuration(long duration);

  /**
   * 获取当前素材切换时间 单位毫秒
   *
   * @return long 切换时间 单位毫秒
   */
  long getCurrentResourceSwitchTimer(int position);

  /**
   * 设置是否自动切换View
   *
   * @param isAutoSwitch true:自动定时切换 false:不自动切换
   */
  void setAutoSwitchResource(boolean isAutoSwitch);

  /**
   * 是否自动切换View
   *
   * @return true:自动切换
   */
  boolean isAutoSwitchResource();
}
