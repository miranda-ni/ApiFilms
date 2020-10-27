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

public class FilmAdapter  extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    OnClick onClick;

    public ArrayList<FilmModel> filmModelArrayList = new ArrayList<>();

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


    public void SetFilmModel(ArrayList<FilmModel> filmModels){
        this.filmModelArrayList = filmModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new ViewHolder(view);


    }

    @Override // zonpolnyalnaet view
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(filmModelArrayList.get(position).getTitle());
        holder.deck.setText(filmModelArrayList.get(position).getProducer());


    }

    @Override
    public int getItemCount() {
        return filmModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,deck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClick(getAdapterPosition());
                }
            });
            title = itemView.findViewById(R.id.title_textView);
            deck = itemView.findViewById(R.id.deck_textView);
        }
    }
    public  interface OnClick{
        void onClick (int position);
    }

}
