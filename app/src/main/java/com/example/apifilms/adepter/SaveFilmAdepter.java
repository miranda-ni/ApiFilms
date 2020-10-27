package com.example.apifilms.adepter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apifilms.R;
import com.example.apifilms.data.models.FilmModel;

import java.util.ArrayList;

public class SaveFilmAdepter extends RecyclerView.Adapter<SaveFilmAdepter.ViewHolder> {

    private ArrayList<FilmModel>filmModels = new ArrayList<>();
    private FilmAdapter.OnClick onClick;

    public void setFilmModels(ArrayList<FilmModel>filmModels){
        this.filmModels = filmModels;
        notifyDataSetChanged();
    }
    public void setOnClick(FilmAdapter.OnClick onClick) {
        this.onClick = onClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_list,parent,false);

        return new SaveFilmAdepter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(filmModels.get(position).getTitle());
        holder.deck.setText(filmModels.get(position).getProducer());

    }

    @Override
    public int getItemCount() {
        return filmModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,deck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_textView_save);
            deck =itemView.findViewById(R.id.deck_textView_save);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClick(getAdapterPosition());
                }
            });
        }
    }
}
