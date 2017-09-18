package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import Model.ListItem;
import iamhere.ciapps.net.mytutorial.R;

/**
 * Created by Ofer Dan-On on 9/11/2017
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private MyClickListener mListener;


    public MyAdapter(List listItem, MyClickListener listener) {
        this.listItems = listItem;
        this.mListener = listener;
    }

    //    public MyAdapter(Context context, List listItem){
//        this.context=context;
//        this.listItems= listItem;
//    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        ListItem item = listItems.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.rating.setText(item.getRating());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public TextView rating;

        public ViewHolder(View itemView) {
            super(itemView);
            //make sure this is the specific object
//            itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(listItems.get(getAdapterPosition()));
                }
            });
            name = (TextView) itemView.findViewById(R.id.titleID);
            description = (TextView) itemView.findViewById(R.id.descriptionID);
            rating = (TextView) itemView.findViewById(R.id.ratingID);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ListItem item = listItems.get(position);
            //  Toast.makeText(context, item.getName(), Toast.LENGTH_SHORT).show();

        }
    }

    public interface MyClickListener {
        void onItemClick(ListItem item);
    }
}

