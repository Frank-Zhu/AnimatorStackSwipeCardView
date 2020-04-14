package com.frankzhu.animatorstackswipecardviewlibrary.widget;

import com.frankzhu.animatorstackswipecardviewlibrary.StackSwipeCardView;

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
public interface ISwipeView {
  /**
   * 移动到下一页
   */
  void moveToNext();

  /**
   * 获取当前播放页面的position
   *
   * @return int 当前页面position
   */
  int getCurrentPosition();

  /**
   * 清除所有数据
   */
  void clearAll();

  /**
   * 添加卡片选中监听器
   *
   * @param listener 监听函数
   */
  void addCardViewSelectedListener(StackSwipeCardView.OnCardViewSelectedListener listener);

  /**
   * 移除卡片选中监听器
   *
   * @param listener 监听函数
   */
  void removeCardViewSelectedListener(StackSwipeCardView.OnCardViewSelectedListener listener);
}
