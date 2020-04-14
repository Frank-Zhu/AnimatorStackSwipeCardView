package com.frankzhu.animatorstackswipecardviewlibrary.bean;


/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-16 12:16
 * Description: 堆叠卡片基础配置信息
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class StackCardConfig {
  public final static int OFFSET_MODE_BOTTOM = 0;
  public final static int OFFSET_MODE_TOP = OFFSET_MODE_BOTTOM + 1;
  public final static int OFFSET_MODE_LEFT = OFFSET_MODE_TOP + 1;
  public final static int OFFSET_MODE_RIGHT = OFFSET_MODE_LEFT + 1;

  public final static long DEFAULT_SWITCH_DURATION = 500L;

  /**
   * 默认最大显示数
   */
  private final static int DEFAULT_MAX_SHOW_ITEM = 4;

  /**
   * 默认偏移量 20PX
   */
  private final static int DEFAULT_TRANSLATION_LEVEL = 20;

  /**
   * 默认缩放比 0.1f
   */
  private final static float DEFAULT_SCALE_LEVEL = 0.1F;

  /**
   * 默认缩放比 0.1f
   */
  private final static int DEFAULT_CHILDREN_SCALE_LEVEL = 3;

  /**
   * 最大显示卡片数量 默认值为3 显示时会添加1作为缓存View
   */
  private int maxShowItem = DEFAULT_MAX_SHOW_ITEM;

  /**
   * 每个卡片之间的位移偏移量 默认值为20px
   */
  private int translationLevel = DEFAULT_TRANSLATION_LEVEL;

  /**
   * 每个卡片之间的缩放比 默认值为0.1f
   */
  private float scaleLevel = DEFAULT_SCALE_LEVEL;

  /**
   * 偏移模式 默认右偏移
   */
  private int offsetMode = OFFSET_MODE_BOTTOM;

  /**
   * 子View相对父View的缩放比倍数
   */
  private int childrenScaleLevel = DEFAULT_CHILDREN_SCALE_LEVEL;

  /**
   * 是否底部模式
   *
   * @return true
   */
  public boolean isBottomMode() {
    return offsetMode == OFFSET_MODE_BOTTOM;
  }

  /**
   * 是否右侧模式
   *
   * @return true
   */
  public boolean isRightMode() {
    return offsetMode == OFFSET_MODE_RIGHT;
  }


  /**
   * 是否左侧模式
   *
   * @return true
   */
  public boolean isLeftMode() {
    return offsetMode == OFFSET_MODE_LEFT;
  }

  /**
   * 是否顶部模式
   *
   * @return true
   */
  public boolean isTopMode() {
    return offsetMode == OFFSET_MODE_TOP;
  }

  /**
   * 是否设置透明值
   */
  private float alpha = 0;

  /**
   * 切换时长
   */
  private long switchDuration = DEFAULT_SWITCH_DURATION;

  /**
   * 切换的距离
   * 默认1080/2
   */
  private float switchWidth = 540;

  /**
   * 开始切换的透明度
   */
  private float switchStartAlpha = 1f;

  /**
   * 结束切换的透明度
   */
  private float switchEndAlpha = 0f;

  public float getSwitchStartAlpha() {
    return switchStartAlpha;
  }

  public void setSwitchStartAlpha(float switchStartAlpha) {
    this.switchStartAlpha = switchStartAlpha;
  }

  public float getSwitchEndAlpha() {
    return switchEndAlpha;
  }

  public void setSwitchEndAlpha(float switchEndAlpha) {
    this.switchEndAlpha = switchEndAlpha;
  }

  public float getSwitchWidth() {
    return switchWidth;
  }

  public void setSwitchWidth(float switchWidth) {
    this.switchWidth = switchWidth;
  }

  public float getAlpha() {
    return alpha;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
  }

  public int getMaxShowItem() {
    return maxShowItem;
  }

  public long getSwitchDuration() {
    return switchDuration;
  }

  public void setSwitchDuration(long switchDuration) {
    this.switchDuration = switchDuration;
  }

  public void setMaxShowItem(int maxShowItem) {
    this.maxShowItem = maxShowItem + 1;
  }

  public int getTranslationLevel() {
    return translationLevel;
  }

  public void setTranslationLevel(int translationLevel) {
    this.translationLevel = translationLevel;
  }

  public float getScaleLevel() {
    return scaleLevel;
  }

  public void setScaleLevel(float scaleLevel) {
    this.scaleLevel = scaleLevel;
  }

  public int getOffsetMode() {
    return offsetMode;
  }

  public void setOffsetMode(int offsetMode) {
    this.offsetMode = offsetMode;
  }

  public int getChildrenScaleLevel() {
    return childrenScaleLevel;
  }

  public void setChildrenScaleLevel(int childrenScaleLevel) {
    this.childrenScaleLevel = childrenScaleLevel;
  }
}
