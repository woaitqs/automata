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
        .detail("的确，一对一单打哈登真的不惧怕任何人，对杜兰特来说你不知道杜兰特会用那种方法单打你，而哈登你知道他会用三分或者突破，但是你就是挡不住")
        .build());
    data.add(CommunicationData.newBuilder()
        .avatarUrl("http://q1.qlogo.cn/g?b=qq&k=EEAzOP1icgdPNVO9Ciag8OYQ&s=40&t=1526227200")
        .nickName("二哈")
        .detail("杜兰特可以前三节铁，但是最后一节投篮可以连成串，比赛最后关头谁都无法，保罗有点稳，但是第一次打西决表现怎样还是未知数，库里汤普森格林曾经被保罗嘲笑过，我相信他们会好好给保罗上上课的")
        .build());
    data.add(CommunicationData.newBuilder()
        .avatarUrl("http://q4.qlogo.cn/g?b=qq&k=KwG0837kpMwgUggCzVbS0A&s=40&t=1526227200")
        .nickName("狗子是你吗？")
        .coverUrl("http://inews.gtimg.com/newsapp_bt/0/3564104952/641")
        .detail("兄弟我就说 保罗羡慕巴莫特和塔克来是干啥的？就是专门防杜兰特的 阿里扎 巴莫特 塔克 三个轮流防杜兰特 累也累死了")
        .build());
    data.add(CommunicationData.newBuilder()
        .avatarUrl("http://q3.qlogo.cn/g?b=qq&k=sNnuunGqfiamoEAKb0IX1Gw&s=40&t=1526227200")
        .nickName("西瓜")
        .detail("你好傻，杜兰特永远无法撼动詹皇的地位的，这点你要清楚，二，勇士要赢也不会赢在库里这点的，说白了保罗哈登单打单防都是可以压住你家小库的，不懂别逼逼")
        .build());
    data.add(CommunicationData.newBuilder()
        .avatarUrl("http://q4.qlogo.cn/g?b=qq&k=Xm3bJK1eKxZRVo3PAzNcEg&s=40&t=1526227200")
        .nickName("我是小坏蛋")
        .coverUrl("http://inews.gtimg.com/newsapp_bt/0/3564961938/641")
        .detail("你需要弄明白，对手是要如何去防守你，然后你要用自己最好的武器")
        .build());
    return data;
  }
}
