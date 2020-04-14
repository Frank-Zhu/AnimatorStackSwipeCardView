package com.frankzhu.animatorstackswipecardviewlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-19 17:10
 * Description: 卡片切换的基础适配器
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-19      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class AbstractBaseStackSwipeAdapter<T> extends RecyclerView.Adapter<AbstractBaseStackItemViewHolder<T>> {
  private List<T> mDataList = new ArrayList<>();
  private final LayoutInflater mLayoutInflater;

  public AbstractBaseStackSwipeAdapter(Context context) {
    mLayoutInflater = LayoutInflater.from(context);
  }

  public AbstractBaseStackSwipeAdapter(Context context, List<T> list) {
    mLayoutInflater = LayoutInflater.from(context);
    mDataList.addAll(list);
  }

  public LayoutInflater getLayoutInflater() {
    return mLayoutInflater;
  }

  public List<T> getDataList() {
    return mDataList;
  }

  public T getItemData(int position) {
    return (position >= 0 && position < mDataList.size()) ? mDataList.get(position) : null;
  }

  @Override
  public void onBindViewHolder(@NonNull AbstractBaseStackItemViewHolder<T> baseStackItemViewHolder, int position) {
    baseStackItemViewHolder.bindViewData(getItemData(position));
  }

  @Override
  public int getItemCount() {
    return mDataList == null ? 0 : mDataList.size();
  }

  /**
   * 添加一条记录
   *
   * @param data     需要加入的数据结构
   * @param position 插入位置
   */
  public void addItem(T data, int position) {
    if (position >= 0 && position <= mDataList.size()) {
      mDataList.add(position, data);
      notifyItemInserted(position);
    }
  }

  /**
   * 添加一条记录
   *
   * @param data 需要加入的数据结构
   */
  public void addItem(T data) {
    addItem(data, mDataList.size());
  }

  /**
   * 移除所有记录
   */
  public void clearItems() {
    int size = mDataList.size();
    if (size > 0) {
      mDataList.clear();
      notifyItemRangeRemoved(0, size);
    }
  }

  /**
   * 批量添加记录
   *
   * @param data     需要加入的数据结构
   * @param position 插入位置
   */
  public void addItems(List<T> data, int position) {
    if (position >= 0 && position <= mDataList.size() && data != null && data.size() > 0) {
      mDataList.addAll(position, data);
      notifyItemRangeChanged(position, data.size());
    }
  }

  /**
   * 批量添加记录
   *
   * @param data 需要加入的数据结构
   */
  public void addItems(List<T> data) {
    addItems(data, mDataList.size());
  }

}
