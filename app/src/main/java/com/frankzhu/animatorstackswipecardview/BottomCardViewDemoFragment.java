package com.frankzhu.animatorstackswipecardview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frankzhu.animatorstackswipecardviewlibrary.ChildScaleStackSwipeCardView;
import com.frankzhu.animatorstackswipecardviewlibrary.DensityUtil;
import com.frankzhu.animatorstackswipecardviewlibrary.bean.StackCardConfig;
import com.frankzhu.animatorstackswipecardviewlibrary.widget.BaseAutoSwipeViewHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2020-01-16 12:30
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020-01-16      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class BottomCardViewDemoFragment extends Fragment {
  ChildScaleStackSwipeCardView<Integer> mAutoStackSwipeCardView;

  private List<Integer> resources = new ArrayList<>();
  private int mMode = StackCardConfig.OFFSET_MODE_RIGHT;
  private StackCardConfig mStackCardConfig;

  public static BottomCardViewDemoFragment newInstance() {
    return new BottomCardViewDemoFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.activity_bottom_card_view_demo, container, false);
    mAutoStackSwipeCardView = rootView.findViewById(R.id.auto_stack_swipe_card_view);
    return rootView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpViewsComponent();
  }

  private void setUpViewsComponent() {
    initData();
    setData();
  }

  private void setData() {
    mStackCardConfig = new StackCardConfig();
    mStackCardConfig.setOffsetMode(mMode);
    mStackCardConfig.setTranslationLevel(DensityUtil.dp2px(getActivity(), 30));
    mStackCardConfig.setAlpha(0f);
    BottomCardAdapter adapter = new BottomCardAdapter(getActivity(), resources);
    mAutoStackSwipeCardView.initViewConfig(adapter, mStackCardConfig);
    mAutoStackSwipeCardView.setIAutoSwipeView(new BaseAutoSwipeViewHelper(mAutoStackSwipeCardView));
    mAutoStackSwipeCardView.bindViewData(adapter.getDataList());
  }

  private void initData() {
    resources.add(R.mipmap.demo21);
    resources.add(R.mipmap.demo22);
    resources.add(R.mipmap.demo23);
    resources.add(R.mipmap.demo21);
    resources.add(R.mipmap.demo22);
    resources.add(R.mipmap.demo23);
  }

  @Override
  public void onPause() {
    super.onPause();
    if (mAutoStackSwipeCardView != null) {
      mAutoStackSwipeCardView.pauseAutoSlider();
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (mAutoStackSwipeCardView != null) {
      mAutoStackSwipeCardView.resumeAutoSlider();
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (mAutoStackSwipeCardView != null) {
      mAutoStackSwipeCardView.releaseSlider();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

}
