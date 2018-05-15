package com.baidu.android.trail.fragment;

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
import com.baidu.android.trail.TrailApp;
import com.baidu.android.trail.WorkThreadPool;
import com.baidu.android.trail.bean.Subject;
import com.baidu.android.trail.db.SubjectEntity;
import com.baidu.android.trail.function.CollectionAdapter;

import java.util.ArrayList;
import java.util.List;

public class CollectionFragment extends Fragment {

  public static CollectionFragment newInstance(int type) {

    Bundle bundle = new Bundle();
    bundle.putInt("type", type);
    CollectionFragment fragment = new CollectionFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return LayoutInflater.from(getContext())
        .inflate(R.layout.fragment_collection, container, false);
  }

  @Override
  public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final RecyclerView recyclerView = view.findViewById(R.id.collection);
    final View tipsView = view.findViewById(R.id.tips);
    final int type = getArguments().getInt("type");

    WorkThreadPool.execute(new Runnable() {
      @Override
      public void run() {
        final SubjectEntity[] entities = type == 1 ? TrailApp.getSubjectDB().subjectDAO().loadFavSubjects()
            : TrailApp.getSubjectDB().subjectDAO().loadErrorSubjects();
        final boolean hasData = entities != null && entities.length > 0;
        view.post(new Runnable() {
          @Override
          public void run() {
            tipsView.setVisibility(hasData ? View.GONE : View.VISIBLE);
            if (hasData) {
              CollectionAdapter adapter = new CollectionAdapter();
              recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
              DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
              divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));
              recyclerView.addItemDecoration(divider);
              recyclerView.setAdapter(adapter);
              List<Subject> questions = new ArrayList<>();
              for (SubjectEntity entity : entities) {
                questions.add(fromEntity(entity));
              }
              adapter.setQuestions(questions);
            }
          }
        });

      }
    });
  }

  private Subject fromEntity(SubjectEntity entity) {
    Subject subject = new Subject();
    subject.setQuestion(entity.question);
    subject.setPicture(entity.picture);
    subject.setAnswer(entity.answer);
    subject.setOptionA(entity.optionA);
    subject.setOptionB(entity.optionB);
    subject.setOptionC(entity.optionC);
    subject.setOptionD(entity.optionD);
    return subject;
  }

}
