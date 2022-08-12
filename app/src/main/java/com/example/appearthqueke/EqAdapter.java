package com.example.appearthqueke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appearthqueke.databinding.EqListItemsBinding;

// Alt + Enter - > Implemente metodos onCreate / onBing
//Alt + Enter -> genere el contructor Diff
public class EqAdapter extends ListAdapter<Earthquake, EqAdapter.EqViewHolder> {

    public static final  DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.getId().equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.equals(newItem);
        }
    };



    // protected Cabiamos el EqAdapter, eliminamos los argumentos de los parentecis
    protected EqAdapter() {
        super(DIFF_CALLBACK);
    }


    //*****************************************************************

    private OnItemClicListener onItemClicListener;
    interface OnItemClicListener{
        void onItemClick(Earthquake earthquake);
    }

    public void setOnItemClicListener(OnItemClicListener onItemClicListener){
        this.onItemClicListener = onItemClicListener;
    }
//********************************************************************************************************************************************


    @NonNull
    @Override
    public EqAdapter.EqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* Implementar con Layaout

         View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.eq_list_items, parent, false
        );

          return view;*/

        //Implementar con Binding
        EqListItemsBinding binding =
                        EqListItemsBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EqViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EqAdapter.EqViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bind(earthquake);//modificado
    }


    public class EqViewHolder extends RecyclerView.ViewHolder {

        //private TextView magnituView;
        //private TextView placetext;

        private EqListItemsBinding binding;

        public EqViewHolder(@NonNull EqListItemsBinding binding) {
            super(binding.getRoot());
            //magnituView = itemView.findViewById(R.id.magnitud_txt);
            //placetext = itemView.findViewById(R.id.pacle_text);
            this.binding=binding;
        }

        public void bind(Earthquake earthquake) {

            binding.magnitudTxt.setText(String.valueOf(earthquake.getMagnitud()));
            binding.pacleText.setText(earthquake.getPlace());


            binding.getRoot().setOnClickListener(view -> {
                onItemClicListener.onItemClick(earthquake);
            });
            binding.executePendingBindings();
        }
    }



  /*  protected EqAdapter(@NonNull DiffUtil.ItemCallback<Earthquake> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public EqAdapter.EqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.eq_list_items, parent, false
        );
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EqAdapter.EqViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bind(earthquake);
    }

    public class EqViewHolder extends RecyclerView {

        private TextView magnituView;
        private TextView placetext;


        public EqViewHolder(@NonNull View itemView) {
            super(itemView);
            magnituView = itemView.findViewById(R.id.magnitud_txt);
            placetext = itemView.findViewById(R.id.pacle_text);
        }

        public void bind(Earthquake earthquake) {
            magnituView.setText(String.valueOf(earthquake.getMagnitud()));
            placetext.setText(earthquake.getPlace());
        }
    }*/
}
