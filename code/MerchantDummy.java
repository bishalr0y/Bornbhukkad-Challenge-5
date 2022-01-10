package com.lovafood.bornbhukkad.privatelimited.merchantPortal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.lovafood.bornbhukkad.privatelimited.R;

import java.util.ArrayList;

public class MerchantDummy extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<MerchantModel> merchantModels= new ArrayList<>();
    private SearchView searchView;
    private TextView predictedPriceText, productName;

    private static String[] products = {"Aashirvaad Atta 5kg", "Savlon Handwash herbal 1.5 litres", "Savlon cool hexa bathing soap pack of 5", "Dabur Honey 400 gms", "Aashirvaad Ghee", "Kissan Mixed Fruit Jam 500 gm", "Onion 1kg", "Safola Oats 500gms",  "Surf Excel Top load 500 ml", "Surf Excel Front Load 500ml"};
    private float min = 150;
    private float max = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_dummy);

        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.searchView);
        searchView.setOnClickListener(this);
        predictedPriceText = findViewById(R.id.predictedPrice);
        productName = findViewById(R.id.productName);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("hello", "onQueryTextSubmit: "+ query);
                searchView.setQuery("", false);
                showItems(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showItems(String query) {
        int index = getProduct(query);
        if(index == -1)
            Toast.makeText(this, "Please Enter more specific Input", Toast.LENGTH_SHORT).show();
        else {
            getMinMax(index);
            merchantModels = new ArrayList<>();
            String[] competitors = {"Competitor1", "Competitor2", "Competitor3", "Competitor4", "Competitor5", "Competitor6", "Competitor7", "Competitor8", "Competitor9", "Competitor10"};
            float[] prices = new float[competitors.length];
            for (int i = 0; i < competitors.length; i++) {
                prices[i] = min + (int) (Math.random() * ((max - min) + 1));
                merchantModels.add(new MerchantModel(competitors[i], prices[i]));
            }
            float predictedPrice= prediction(min, max, prices);
            MerchantAdapter adapter = new MerchantAdapter(this, merchantModels, predictedPrice);
            predictedPriceText.setText("Predicted Price: Rs."+ predictedPrice+" /-");
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    }

    private float prediction(float min, float max, float[] prices) {
        float avg_price = (min + max) / 2;
        float max_price = getMax(prices);
        float pre_price = avg_price + ((max_price - avg_price) / (max_price + avg_price)) * avg_price;

        return pre_price;
    }

    private float getMax(float[] arr) {
        float max = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private void getMinMax(int index) {
        switch(index){
            case 0:
                min = 212;
                max = 270;
                break;
            case 1:
                min = 185;
                max = 190;
                break;
            case 2:
                min = 178;
                max = 290;
                break;
            case 3:
                min = 169;
                max = 290;
                break;
            case 4:
                min = 280;
                max = 316;
                break;
            case 5:
                min = 119;
                max = 150;
                break;
            case 6:
                min = 32;
                max = 35;
                break;
            case 7:
                min = 85;
                max = 105;
                break;
            case 8:
                min = 72.5f;
                max = 105;
                break;
            case 9:
                min = 86.2f;
                max = 115;
                break;
            default:
                min = -1;
                max = -1;
        }
    }

    private int getProduct(String input) {
        for (int i = 0; i < products.length; i++) {
            if(products[i].toLowerCase().contains(input.toLowerCase())) {
                productName.setText("Product: "+ products[i]);
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchView:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                break;
        }
    }
}