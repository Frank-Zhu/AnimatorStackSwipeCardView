package com.frankzhu.animatorstackswipecardviewlibrary.widget;


import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-16 20:41
 * Description: {@link AbstractStackSwipeCardCallBack} 相关滑动动画时同步执行子View动画回调
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public interface ChildrenHolderAnimatorListener {
  /**
   * 同步滑动时执行holder相关的动画回调
   *
   * @param position 当前第几个View
   * @param holder   当前需要动画的holder
   * @param scale    缩放的比例
   */
  void startHolderAnimator(int position, RecyclerView.ViewHolder holder, float scale);


  /**
   * 选中View被移除回调
   *
   * @param dataPosition     实际真实的数据Position 永远是第0个
   * @param removePosition   当前移除的Position
   * @param selectedPosition 当前选中的Position
   */
  void onHolderRemovedAndSelected(int dataPosition, int removePosition, int selectedPosition);

}
