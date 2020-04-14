package com.frankzhu.animatorstackswipecardviewlibrary;

import android.content.Context;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-04-14 20:04
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-04-14      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class DensityUtil {
  public static int dp2px(Context context, float dpValue) {
    return (int)(dpValue * getDensity(context) + 0.5F);
  }

  private static float getDensity(Context context) {
    return context.getResources().getDisplayMetrics().density;
  }
}
