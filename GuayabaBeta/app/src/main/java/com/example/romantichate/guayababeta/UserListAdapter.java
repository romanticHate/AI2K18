package com.example.romantichate.guayababeta;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    public List<User> userList;

    public UserListAdapter(List<User> userList){

        this.userList = userList;
    }

    //@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.nameText.setText( userList.get(i).getName());
        viewHolder.genderText.setText( userList.get(i).getGender());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TextView nameText;
        public TextView genderText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            nameText = (TextView) mView.findViewById(R.id.name_text);
            genderText = (TextView) mView.findViewById(R.id.gender_text);

        }
    }
}
