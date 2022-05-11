package com.isil.finalappplap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isil.finalappplap.adapters.ProveedoresAdapter;
import com.isil.finalappplap.databinding.ActivityNavigationBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class NavigationActivity extends AppCompatActivity implements ProveedoresAdapter.ItemClickListener {
    ArrayList arrayList = new ArrayList<HashMap<String,String>>();

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigation.toolbar);
        //leerDatos();
        //la aplicacion se cae :(
        binding.appBarNavigation.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void leerDatos() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://servicios.campus.pe/servicioclientes.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATOS", response);
                        //Datos llegan correctamente!!
                        llenarLista(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DATOS", "ERROR DE VOLLEY");
            }
        });
        queue.add(stringRequest);
    }

    private void llenarLista(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for(int i=0; i<jsonArray.length(); i++){
                String empresa = jsonArray.getJSONObject(i).getString("empresa");
                String nombres = jsonArray.getJSONObject(i).getString("nombres");
                String cargo = jsonArray.getJSONObject(i).getString("cargo");
                String pais = jsonArray.getJSONObject(i).getString("pais");
                HashMap<String,String> map = new HashMap<>();
                map.put("empresa", empresa);
                map.put("nombres", nombres);
                map.put("cargo", cargo);
                map.put("pais", pais);
                arrayList.add(map);
            }
            RecyclerView mrvProveedores = findViewById(R.id.rvProveedores);
            ProveedoresAdapter proveedoresAdapter = new ProveedoresAdapter(arrayList);
            mrvProveedores.setAdapter(proveedoresAdapter);
            mrvProveedores.setLayoutManager(new LinearLayoutManager(this));
            //proveedoresAdapter.setItemClickListener(this);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onItemClick(int position){
        Toast.makeText(this, "Posicion " + position, Toast.LENGTH_SHORT).show();
//        HashMap<String,String> map = (HashMap<String, String>) arrayList.get(position);
//        Bundle bundle = new Bundle();
//        bundle.putString("empresa", map.get("empresa"));
//        bundle.putString("nombres", map.get("nombre"));
//        bundle.putString("cargo", map.get("cargo"));
//        bundle.putString("pais", map.get("pais"));

        //Intent intent = new Intent(this,ProductosActivity.class);
        //intent.putExtras(bundle);
        //startActivity(intent);
    }
}