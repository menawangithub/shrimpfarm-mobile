package com.example.shrimpfarm.ui.rencanapanen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.shrimpfarm.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

  public class RencanaAdapter extends ArrayAdapter<Rencana> {
  public RencanaAdapter(Context context, ArrayList<Rencana> rencanaArrayList){
   super(context, R.layout.card_rencana, rencanaArrayList);
  }

  @NonNull
  public View getView(int position,@NotNull View convertView, @NonNull ViewGroup parent){
   Rencana rencana = getItem(position);

   if (rencana == null){
     return super.getView(position, convertView, parent);
   }

   if (convertView == null){
     convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_rencana, parent, false);

   }
   TextView customId = convertView.findViewById(R.id.rencana_customid);
   TextView tanggalPanen = convertView.findViewById(R.id.rencana_tanggalpanen);

   customId.setText(rencana.customId);
   tanggalPanen.setText(rencana.perkiraanPanen);

   return convertView;
  }
}
