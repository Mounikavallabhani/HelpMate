package com.arkainfoteck.helpmate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkainfoteck.helpmate.Activitys.CookDetails;
import com.arkainfoteck.helpmate.Model.ProductModel;
import com.arkainfoteck.helpmate.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdopter extends RecyclerView.Adapter<ProductAdopter.Myview> {

    Context context;
    List<ProductModel>productModels;
    ProductModel productModel;

    public ProductAdopter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
      View view = layoutInflater.inflate(R.layout.gird_items,viewGroup,false);

        return new Myview(view,context,productModels);
    }

    @Override
    public void onBindViewHolder(@NonNull Myview myview, int i) {
         productModel=productModels.get(i);
   //    myview.imageView.setText(productModel.getId());
       myview.textView.setText("  "+productModel.getName());
       myview.offerprice.setText(""+productModel.getContent3());
       myview. contant.setPaintFlags(myview.contant.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
       myview.strickoff.setText(productModel.getContent2());
       myview.contant.setText(productModel.getContent());
        Picasso.with(context)
                .load("http://broomsticks.in/assets/ns/"+productModel.getImage() )
                .fit()
                .into(myview.imageView);
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class Myview extends RecyclerView.ViewHolder  implements View.OnClickListener{
        ImageView imageView;
        TextView textView,contant,strickoff,offerprice;
        Context context;
        List<ProductModel>productModels;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        public Myview(@NonNull View itemView ,Context context,List<ProductModel>productModels) {
            super(itemView);
             this.context=context;
             this.productModels=productModels;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            imageView=(ImageView)itemView.findViewById(R.id.imageview);
            contant=(TextView) itemView.findViewById(R.id.contant);
            strickoff=(TextView)itemView.findViewById(R.id.strickoff);
            textView=(TextView)itemView.findViewById(R.id.text12);
            offerprice=(TextView)itemView.findViewById(R.id.offerprice);
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            String names,subdata,subproductname;

            int position=getAdapterPosition();
            ProductModel productModel=this.productModels.get(position);
            sharedPreferences = context.getSharedPreferences("Nameoftype", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("nameofimage", productModel.getName());
            editor.putString("imagedata",productModel.getImage());
            editor.putString("productcost",productModel.getPrice());

            editor.commit();
            editor.apply();

            intent=new Intent(this.context,CookDetails.class);
                   intent.putExtra("cookdetails", productModel.getDetails());
                   intent.putExtra("noofhours", productModel.getHours());
                   intent.putExtra("noofcooks",productModel.getType());
                   context.startActivity(intent);

           }
        }
    }

