package com.example.shrimpfarm.ui.infotambak;

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

public class TambakAdapter extends ArrayAdapter<Tambak> {
  public TambakAdapter(Context context, ArrayList<Tambak> tambakArrayList){
	super(context, R.layout.card_tambak, tambakArrayList);
  }

  @NotNull
  @Override
  public View getView(int position, @NotNull View convertView, @NotNull ViewGroup parent){
	Tambak tambak = getItem(position);

	if (tambak == null) {
	  return super.getView(position, convertView, parent);
	}

	if (convertView == null){
	  convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_tambak, parent, false);
	}

	TextView namaTambak = convertView.findViewById(R.id.tambak_nama);
	TextView luasTambak = convertView.findViewById(R.id.tambak_luas);
	TextView ruasTambak = convertView.findViewById(R.id.tambak_ruas);

	namaTambak.setText(tambak.namaTambak);
	luasTambak.setText(String.valueOf(tambak.luasKolam + " m²")); // Konversi ke String jika perlu
	ruasTambak.setText(String.valueOf(tambak.jumlahRuas + " Kolam")); // Konversi ke String jika perlu
	return convertView;
  }
}
