package com.realator.dennism501.moodlemobileproject.RecyclerAdaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.realator.dennism501.moodlemobileproject.POJO.CourseContentPOJO;
import com.realator.dennism501.moodlemobileproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dennism501 on 5/23/17.
 */

public class CourseContentAdaptor extends RecyclerView.Adapter<CourseContentAdaptor.ViewHolder> {

    private Context mcontext;
    private onItemClickListener itemClickListener;
    private List<CourseContentPOJO> courseContents;

    public CourseContentAdaptor(List<CourseContentPOJO> courseContents, Context mcontext) {
        this.courseContents = courseContents;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_content_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CourseContentPOJO content = courseContents.get(position);

        holder.txtname.setText(content.getName());
        holder.txtdescription.setText(Html.fromHtml(content.getModdescription()).toString());
        //Picasso.with(mcontext).load(Html.fromHtml(content.getModicon()).toString()).fit().centerCrop().into(holder.imgIcon);
        holder.txtname.setText(content.getModname());

        Log.d("UrlIcon",content.getModicon());


    }

    @Override
    public int getItemCount() {
        return courseContents.size();
    }

    public interface onItemClickListener {

        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtname, txtdescription, txtmodname;
        ImageView imgIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            txtname = (TextView) itemView.findViewById(R.id.course_content_name);
            txtdescription = (TextView)itemView.findViewById(R.id.module_description);
            imgIcon = (ImageView)itemView.findViewById(R.id.module_image);
            txtmodname = (TextView)itemView.findViewById(R.id.mod_name);
        }

        @Override
        public void onClick(View v) {

            if (itemClickListener != null) {

                itemClickListener.onItemClick(itemView, getPosition());
            }

        }
    }
}
