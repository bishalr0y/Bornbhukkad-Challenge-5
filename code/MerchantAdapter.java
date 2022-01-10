package com.lovafood.bornbhukkad.privatelimited.merchantPortal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lovafood.bornbhukkad.privatelimited.R;
import com.lovafood.bornbhukkad.privatelimited.rectifier.BasicFunctionHandler;

import java.util.ArrayList;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.MerchantViewHolder> {

    private Context context;
    private ArrayList<MerchantModel> merchantModels;
    private LayoutInflater inflater;
    private BasicFunctionHandler handler;
    private float predictedPrice=0f;

    public MerchantAdapter(Context context, ArrayList<MerchantModel> merchantModels, float predictedPrice) {
        this.context = context;
        this.merchantModels = merchantModels;
        this.predictedPrice = predictedPrice;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        handler = new BasicFunctionHandler(context);
    }

    @NonNull
    @Override
    public MerchantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.merchant_dummy_single_view, parent, false);
        return new MerchantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MerchantViewHolder holder, int position) {
        MerchantModel model = merchantModels.get(position);
        holder.competitorName.setText(model.getCompetitorName());
        holder.priceOfProduct.setText("Rs."+model.getProductPrice()+" /-");
    }

    @Override
    public int getItemCount() {
        return merchantModels.size();
    }

    public class MerchantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView competitorName, priceOfProduct;

        public MerchantViewHolder(@NonNull View itemView) {
            super(itemView);
            competitorName = itemView.findViewById(R.id.competitorName);
            priceOfProduct = itemView.findViewById(R.id.priceOfProduct);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
