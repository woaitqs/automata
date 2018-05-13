package com.baidu.android.trail.function.communication;

public class CommunicationData {

  private String avatarUrl;
  private String nickName;
  private String coverUrl;
  private String detail;

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public String getNickName() {
    return nickName;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public String getDetail() {
    return detail;
  }

  private CommunicationData(Builder builder) {
    avatarUrl = builder.avatarUrl;
    nickName = builder.nickName;
    coverUrl = builder.coverUrl;
    detail = builder.detail;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private String avatarUrl;
    private String nickName;
    private String coverUrl;
    private String detail;

    private Builder() {
    }

    public Builder avatarUrl(String val) {
      avatarUrl = val;
      return this;
    }

    public Builder nickName(String val) {
      nickName = val;
      return this;
    }

    public Builder coverUrl(String val) {
      coverUrl = val;
      return this;
    }

    public Builder detail(String val) {
      detail = val;
      return this;
    }

    public CommunicationData build() {
      return new CommunicationData(this);
    }
  }
}
