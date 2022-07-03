package com.example.fighttime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvRoundAdapter extends RecyclerView.Adapter<RvRoundAdapter.ViewHolder>{

    ArrayList<ItemRound> roundArrayList;
    MyTimer myTimer;

    public RvRoundAdapter(MyTimer _myTimer){
        myTimer = _myTimer;
        roundArrayList = myTimer.getArray();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView roundCount;
        public TextView fightTime;
        public TextView breakTime;
        public ImageButton deleteBtn;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            roundCount = (TextView) itemView.findViewById(R.id.roundCount);
            fightTime = (TextView) itemView.findViewById(R.id.fightTime);
            breakTime = (TextView) itemView.findViewById(R.id.breakTime);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.btnDeleteRound);
        }
    }

    @NonNull
    @Override
    public RvRoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_round, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull RvRoundAdapter.ViewHolder holder, int position) {
        ItemRound itemRound = roundArrayList.get(position);

        TextView roundCount = holder.roundCount;
        TextView fightTime = holder.fightTime;
        TextView breakTime = holder.breakTime;
        ImageButton deleteBtn = holder.deleteBtn;
        int p = position;

        roundCount.setText("Повторений:" + String.valueOf(itemRound.getFightCount()));
        fightTime.setText(itemRound.getFightTime());
        breakTime.setText(itemRound.getBreakTime());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTimer.removeRound(p);
            }
        });

    }

    @Override
    public int getItemCount() {
        return roundArrayList.size();
    }
}
