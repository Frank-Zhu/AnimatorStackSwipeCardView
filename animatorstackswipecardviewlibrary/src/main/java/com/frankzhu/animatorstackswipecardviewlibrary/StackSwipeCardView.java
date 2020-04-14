package com.frankzhu.animatorstackswipecardviewlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.frankzhu.animatorstackswipecardviewlibrary.bean.StackCardConfig;
import com.frankzhu.animatorstackswipecardviewlibrary.layout.StackSwipeCardLayoutManager;
import com.frankzhu.animatorstackswipecardviewlibrary.widget.ChildrenHolderAnimatorListener;
import com.frankzhu.animatorstackswipecardviewlibrary.widget.ISwipeView;
import com.frankzhu.animatorstackswipecardviewlibrary.widget.StackSwipeCardCallBack;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-04-14 17:03
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-04-14      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class StackSwipeCardView<T, P extends RecyclerView.Adapter> extends FrameLayout implements ChildrenHolderAnimatorListener, ISwipeView {
  private final static String TAG = StackSwipeCardView.class.getSimpleName();
  private RecyclerView mRecyclerView;
  private StackSwipeCardCallBack<T> mCallback;
  private List<OnCardViewSelectedListener> mOnCardViewSelectedListeners;

  public StackSwipeCardView(@NonNull Context context) {
    this(context, null);
  }

  public StackSwipeCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public StackSwipeCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context, attrs);
  }

  private void initView(Context context, AttributeSet attrs) {
    View rootView = LayoutInflater.from(context).inflate(R.layout.widget_animator_stack_swipe_card_view, this, true);
    mRecyclerView = rootView.findViewById(R.id.recycler_view);
  }

  /**
   * 初始化相关参数
   *
   * @param adapter 列表适配器
   * @param config  动效配置相关参数
   */
  public void initViewConfig(P adapter, StackCardConfig config) {
    StackSwipeCardLayoutManager stackSwipeCardLayoutManager = new StackSwipeCardLayoutManager(config);
    mRecyclerView.setLayoutManager(stackSwipeCardLayoutManager);
    mRecyclerView.setAdapter(adapter);
    mCallback = new StackSwipeCardCallBack<>(config, adapter, mRecyclerView);
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
    itemTouchHelper.attachToRecyclerView(mRecyclerView);
    mCallback.setChildrenHolderAnimatorListener(this);
  }

  /**
   * 绑定View相关数据
   *
   * @param data 数据源
   */
  public void bindViewData(List<T> data) {
    Log.d(TAG, "bindViewData --> size = " + (data == null ? 0 : data.size()));
    if (data != null && data.size() > 0) {
      mCallback.setResources(data);
    } else {
      clearAll();
    }
  }

  @Override
  public void startHolderAnimator(int position, RecyclerView.ViewHolder holder, float scale) {

  }

  @Override
  public void onHolderRemovedAndSelected(int dataPosition, int removePosition, int selectedPosition) {
    Log.d(TAG, "onHolderRemovedAndSelected --> removePosition = " + removePosition + " selectedPosition = " + selectedPosition);
    if (mOnCardViewSelectedListeners != null && mOnCardViewSelectedListeners.size() > 0) {
      for (OnCardViewSelectedListener onCardViewSelectedListener : mOnCardViewSelectedListeners) {
        onCardViewSelectedListener.onCardViewRemovedAndSelected(dataPosition, removePosition, selectedPosition);
      }
    }
  }

  @Override
  public void moveToNext() {
    if (mCallback != null) {
      mCallback.swipeNextCard();
    }
  }

  @Override
  public int getCurrentPosition() {
    return mCallback == null ? 0 : mCallback.getCurrentPosition();
  }

  @Override
  public void clearAll() {
    if (mCallback != null) {
      mCallback.setResources(null);
    }
  }

  @Override
  public void addCardViewSelectedListener(OnCardViewSelectedListener listener) {
    if (mOnCardViewSelectedListeners == null) {
      mOnCardViewSelectedListeners = new ArrayList<>();
    }
    mOnCardViewSelectedListeners.add(listener);
  }

  @Override
  public void removeCardViewSelectedListener(OnCardViewSelectedListener listener) {
    if (mOnCardViewSelectedListeners != null) {
      mOnCardViewSelectedListeners.remove(listener);
    }
  }

  /**
   * 卡片选择相关监听器
   */
  public interface OnCardViewSelectedListener {
    /**
     * 选中View被移除回调
     *
     * @param dataPosition     实际真实的数据Position 永远是第0个
     * @param removePosition   当前移除的Position
     * @param selectedPosition 当前选中的Position
     */
    void onCardViewRemovedAndSelected(int dataPosition, int removePosition, int selectedPosition);
  }
}
