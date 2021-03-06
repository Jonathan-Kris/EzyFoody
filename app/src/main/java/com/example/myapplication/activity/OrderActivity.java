package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ImageView orderImage;
    TextView tvOrderPrice, tvOrderItemName;
    EditText etOrderQty;
    Button btnOrder;
    Integer choice = 0;
    ArrayList<Item> cart = new ArrayList<>();

    public void init(){
        orderImage = findViewById(R.id.img_order_selected);
        tvOrderItemName = findViewById(R.id.txt_order_item_name);
        tvOrderPrice = findViewById(R.id.txt_order_price);
        etOrderQty = findViewById(R.id.editTextOrderQty);
        btnOrder = findViewById(R.id.btn_order);
        cart = getIntent().getParcelableArrayListExtra("CART");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        init();

        choice = getIntent().getIntExtra("item", 1);
        if (choice == 1){
            // Tea
            orderImage.setImageResource(R.drawable.drink_tea);
            tvOrderItemName.setText("Premium Tea");
            tvOrderPrice.setText("Rp 100.000");
        }
        else if (choice == 2){
            // Milkshake
            orderImage.setImageResource(R.drawable.drink_milk);
            tvOrderItemName.setText("Milkshake");
            tvOrderPrice.setText("Rp 50.000");
        }
        else if (choice == 3){
            // Water
            orderImage.setImageResource(R.drawable.drink_water);
            tvOrderItemName.setText("Bottled Water");
            tvOrderPrice.setText("Rp 20.000");
        }
        else if (choice == 4) {
            // Mango Juice
            orderImage.setImageResource(R.drawable.drink_mango_juice);
            tvOrderItemName.setText("Mango Juice");
            tvOrderPrice.setText("Rp 30.000");
        }

        btnOrder.setOnClickListener(view -> processInput(choice));
    }

    private void processInput(Integer choice){
        String _qty = etOrderQty.getText().toString();
        Integer qty = Integer.parseInt(_qty);
        Item order = new Item();

        switch (choice){
            case 1 :
                order.setItemName("Premium Tea");
                order.setItemPrice(100000);
                order.setItemQty(qty);
                order.setItemImageId(R.drawable.drink_tea);
                break;
            case 2 :
                order.setItemName("Milkshake");
                order.setItemPrice(50000);
                order.setItemQty(qty);
                order.setItemImageId(R.drawable.drink_milk);
                break;
            case 3 :
                order.setItemName("Bottled Water");
                order.setItemPrice(20000);
                order.setItemQty(qty);
                order.setItemImageId(R.drawable.drink_water);
                break;
            case 4 :
                order.setItemName("Mango Juice");
                order.setItemPrice(30000);
                order.setItemQty(qty);
                order.setItemImageId(R.drawable.drink_mango_juice);
                break;
        }

        cart.add(order);

        Intent i = new Intent(getApplication(), MenuActivity.class);
        i.putParcelableArrayListExtra("CART", cart);
        startActivity(i);
    }
}