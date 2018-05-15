package com.baidu.android.trail.function.communication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.android.trail.R;

import java.util.ArrayList;
import java.util.List;

public class CommunicationFragment extends Fragment {

  private RecyclerView recyclerView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return new RecyclerView(getContext());
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView = (RecyclerView) view;
    CommunicationAdapter adapter = new CommunicationAdapter(getContext());
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
    divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));
    recyclerView.addItemDecoration(divider);
    recyclerView.setAdapter(adapter);
    adapter.setData(generate());
  }

  public List<CommunicationData> generate() {
    List<CommunicationData> data = new ArrayList<>();
    data.add(CommunicationData.newBuilder()
        .avatarUrl("http://q1.qlogo.cn/g?b=qq&k=mrv4PHjqDtibNuJyGlEaB8Q&s=40&t=1526227200")
        .nickName("liujiang")
        .detail("推荐一些C语言学习比较好用的书？")
        .build());
    data.add(CommunicationData.newBuilder()
        .avatarUrl("http://q1.qlogo.cn/g?b=qq&k=EEAzOP1icgdPNVO9Ciag8OYQ&s=40&t=1526227200")
        .nickName("二哈")
        .detail("C 语言的指针部分应该是最难了的吧！")
        .build());
    data.add(CommunicationData.newBuilder()
        .avatarUrl("http://q4.qlogo.cn/g?b=qq&k=KwG0837kpMwgUggCzVbS0A&s=40&t=1526227200")
        .nickName("狗子是你")
        .coverUrl("https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=25409051fefaaf5190ee89eded3dff8b/aec379310a55b319009fba0240a98226cffc1766.jpg")
        .detail("求大神指导")
        .build());

    return data;
  }
}
