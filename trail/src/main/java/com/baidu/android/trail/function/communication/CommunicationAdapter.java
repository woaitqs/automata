package com.baidu.android.trail.function.communication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.android.trail.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CommunicationAdapter extends RecyclerView.Adapter<CommunicationAdapter.ItemViewHolder> {

  private List<CommunicationData> data;
  private final Context context;

  public CommunicationAdapter(Context context) {
    this.context = context;
  }

  public void setData(List<CommunicationData> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context)
        .inflate(R.layout.item_communicaton, parent, false);
    return new ItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {

    CommunicationData itemData = data.get(position);

    holder.detailView.setText(itemData.getDetail());
    holder.nickView.setText(itemData.getNickName());

    RequestOptions options =
        new RequestOptions().centerCrop().placeholder(R.color.image_default);
    if (!TextUtils.isEmpty(itemData.getAvatarUrl())) {
      Glide
          .with(holder.itemView)
          .load(itemData.getAvatarUrl())
          .apply(options)
          .into(holder.avatarView);
    }
    if (!TextUtils.isEmpty(itemData.getCoverUrl())) {
      holder.coverView.setVisibility(View.VISIBLE);
      Glide
          .with(holder.coverView)
          .load(itemData.getCoverUrl())
          .apply(options)
          .into(holder.coverView);
    } else {
      holder.coverView.setVisibility(View.GONE);
    }
  }

  @Override
  public int getItemCount() {
    return data == null ? 0 : data.size();
  }

  public static class ItemViewHolder extends RecyclerView.ViewHolder {

    private TextView nickView;
    private ImageView avatarView;
    private TextView detailView;
    private ImageView coverView;

    public ItemViewHolder(View itemView) {
      super(itemView);
      nickView = itemView.findViewById(R.id.item_nick);
      avatarView = itemView.findViewById(R.id.item_avatar);
      detailView = itemView.findViewById(R.id.item_detail);
      coverView = itemView.findViewById(R.id.item_cover);
    }
  }

}
