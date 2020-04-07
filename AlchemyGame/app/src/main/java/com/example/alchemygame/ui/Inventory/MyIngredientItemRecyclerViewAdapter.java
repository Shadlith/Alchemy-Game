package com.example.alchemygame.ui.Inventory;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alchemygame.InventoryActivity;
import com.example.alchemygame.Model.Database;
import com.example.alchemygame.Model.IngredientItem;
import com.example.alchemygame.R;
import com.example.alchemygame.ui.Inventory.IngredientItemFragment.OnListFragmentInteractionListener;
import com.example.alchemygame.ui.Inventory.ItemTypes.Cash;
import com.example.alchemygame.ui.Inventory.dummy.DummyContent.DummyItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyIngredientItemRecyclerViewAdapter extends RecyclerView.Adapter<MyIngredientItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<IngredientItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private int tempID;
    private Cash money;

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
        final int x = position;


        holder.sellIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempID = holder.mItem.getIDValue();
                notifyItemRemoved(x);
                holder.db.deleteIngredient(tempID);
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
        public Button sellIngredientButton;
        private Database db;

        public ViewHolder(View view) {
            super(view);
            db = new Database(getApplicationContext());
            mView = view;
            mType = view.findViewById(R.id.item_type);
            mQuality = view.findViewById(R.id.item_quality);
            mValue = view.findViewById(R.id.item_value);
            sellIngredientButton = view.findViewById(R.id.sell_button);
        }

        @Override
        public String toString() {
            return super.toString() + " ''";
        }
    }
}
