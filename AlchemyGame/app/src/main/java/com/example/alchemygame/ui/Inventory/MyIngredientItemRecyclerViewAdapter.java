package com.example.alchemygame.ui.Inventory;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alchemygame.Model.IngredientItem;
import com.example.alchemygame.R;
import com.example.alchemygame.ui.Inventory.IngredientItemFragment.OnListFragmentInteractionListener;
import com.example.alchemygame.ui.Inventory.dummy.DummyContent.DummyItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyIngredientItemRecyclerViewAdapter extends RecyclerView.Adapter<MyIngredientItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<IngredientItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyIngredientItemRecyclerViewAdapter(ArrayList<IngredientItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ingredientitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mType.setText("" + mValues.get(position).getType());
        holder.mValue.setText("" +mValues.get(position).getValue());
        holder.mQuality.setText("" + mValues.get(position).getQuality());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mQuality;
        public final TextView mType;
        public final TextView mValue;
        public IngredientItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mType = view.findViewById(R.id.item_type);
            mQuality = view.findViewById(R.id.item_quality);
            mValue = view.findViewById(R.id.item_value);
        }

        @Override
        public String toString() {
            return super.toString() + " ''";
        }
    }
}
