package com.realator.dennism501.moodlemobileproject.RecyclerAdaptors;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realator.dennism501.moodlemobileproject.POJO.Course;
import com.realator.dennism501.moodlemobileproject.R;

import java.util.List;

/**
 * Created by dennism501 on 4/5/17.
 */

public class SiteHomeAdaptor extends RecyclerView.Adapter<SiteHomeAdaptor.ViewHolder> {


    private Context mcontext;
    private onItemClickListener itemClickListener;
    private List<Course> courseList;

    public SiteHomeAdaptor(Context mcontext, List<Course> courseList) {
        this.mcontext = mcontext;
        this.courseList = courseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Course course = courseList.get(position);
        holder.courseSummary.setText(course.getSummary());
        holder.courseName.setText(course.getFullName());
        holder.courseId.setText(course.getShortName());

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setOnItemClickListener(final onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    public interface onItemClickListener {

        void onItemClick(View v, int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView courseName;
        TextView courseId;
        TextView courseSummary;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            courseName = (TextView) itemView.findViewById(R.id.course_name);
            courseId = (TextView) itemView.findViewById(R.id.course_id);
            courseSummary = (TextView) itemView.findViewById(R.id.course_summary);
            cardView = (CardView) itemView.findViewById(R.id.cv_cardviewid);
            cardView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            if (itemClickListener != null) {

                itemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }
}
