package com.example.codehero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.codehero.databinding.ActivityMainBinding;
import com.example.codehero.model.character.Characters;
import com.example.codehero.model.character.Result;
import com.example.codehero.utils.ClickListener;
import com.example.codehero.utils.RecyclerTouchListener;
import com.example.codehero.ws.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Characters characters;
    ArrayList<Result> charactersFiltrados = new ArrayList<>();
    int numPaginas = 0;
    private ArrayList<String> pages = new ArrayList<>();
    private int page = 0;
    private int lastPage = 0;
    private int pageNumber = 4;
    ActivityMainBinding binding;
    private RecyclerAdapterPaginator adapterPaginator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tvNomePagina1.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        fetchData();
        configurePaginacao();
        configureFiltro();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureSelection();
    }

    private void configureSelection() {
        Intent i = new Intent(this, DetalhesActivity.class);
        Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        binding.ibDetalhes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(0).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(0).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(0).getThumbnail().path+"."+charactersFiltrados.get(0).getThumbnail().extension);
                startActivity(i);
            }
        });

        binding.llCharacter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(0).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(0).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(0).getThumbnail().path+"."+charactersFiltrados.get(0).getThumbnail().extension);
                startActivity(i);
            }
        });

        binding.ibDetalhes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(1).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(1).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(1).getThumbnail().path+"."+charactersFiltrados.get(1).getThumbnail().extension);
                startActivity(i);
            }
        });

        binding.llCharacter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(1).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(1).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(1).getThumbnail().path+"."+charactersFiltrados.get(1).getThumbnail().extension);
                startActivity(i);
            }
        });

        binding.ibDetalhes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(2).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(2).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(2).getThumbnail().path+"."+charactersFiltrados.get(2).getThumbnail().extension);
                startActivity(i);
            }
        });

        binding.llCharacter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(2).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(2).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(2).getThumbnail().path+"."+charactersFiltrados.get(2).getThumbnail().extension);
                startActivity(i);
            }
        });

        binding.ibDetalhes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(3).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(3).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(3).getThumbnail().path+"."+charactersFiltrados.get(3).getThumbnail().extension);
                startActivity(i);
            }
        });

        binding.llCharacter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("personagem_nome", charactersFiltrados.get(3).getName());
                i.putExtra("personagem_descricao", charactersFiltrados.get(3).getDescription());
                i.putExtra("personagem_imagem", charactersFiltrados.get(3).getThumbnail().path+"."+charactersFiltrados.get(3).getThumbnail().extension);
                startActivity(i);
            }
        });

    }

    private void configureFiltro() {
        binding.edtBusca.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filter();
                return true;
            }
            return false;
        });

        binding.edtBusca.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    private void configurePaginacao() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvPaginacao.setLayoutManager(layoutManager);
        binding.rvPaginacao.addOnItemTouchListener(new RecyclerTouchListener(Objects.requireNonNull(this).getApplicationContext(), binding.rvPaginacao, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                page = position;

                binding.ibBack.setEnabled(page != 0);

                binding.ibNext.setEnabled(page != numPaginas);

                adapterPaginator.setSelected(position);

                updateView();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        binding.ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page != numPaginas - 1) {
                    page++;
                    adapterPaginator.setSelected(page);
                    updateView();
                }
            }
        });

        binding.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page != 0) {
                    page--;
                    adapterPaginator.setSelected(page);
                    updateView();
                }
            }
        });
    }

    private void fetchData() {
        binding.llMain.setVisibility(View.GONE);
        binding.llLoading.setVisibility(View.VISIBLE);
        binding.svMain.setBackgroundColor(getColor(R.color.white));
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = generateURL();

        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            Log.d("RESULTADO", response);
            Gson gson = new Gson();
            characters = gson.fromJson(response, Characters.class);
            charactersFiltrados.addAll(characters.getData().getResults());

            generatePages();
            updateView();

            binding.llMain.setVisibility(View.VISIBLE);
            binding.llLoading.setVisibility(View.GONE);
            binding.svMain.setBackgroundColor(getColor(R.color.red));

        }, error -> {
            error.printStackTrace();
            Log.d("ERRO", error.getMessage());
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(request);
    }

    private void updateView() {
        int rangePage = page * 4;

        if (charactersFiltrados.size() >= 1) {
            binding.progressImg1.setVisibility(View.VISIBLE);
            binding.llCharacter1.setVisibility(View.VISIBLE);
            binding.setPersonagem1(charactersFiltrados.get(rangePage));
            String urlImg1 = charactersFiltrados.get(rangePage).thumbnail.path + "." + charactersFiltrados.get(rangePage).thumbnail.extension;
            Glide.with(this).load(urlImg1).listener(new RequestListener<Drawable>(){

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    binding.progressImg1.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    binding.progressImg1.setVisibility(View.GONE);
                    return false;
                }
            }).error(R.drawable.ic_no_image).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(binding.imgCharacter1);
        } else {
            binding.llCharacter1.setVisibility(View.GONE);
        }

        if (charactersFiltrados.size() >= 2) {
            binding.progressImg2.setVisibility(View.VISIBLE);
            binding.llCharacter2.setVisibility(View.VISIBLE);
            binding.setPersonagem2(charactersFiltrados.get(rangePage + 1));
            String urlImg2 = charactersFiltrados.get(rangePage + 1).thumbnail.path + "." + charactersFiltrados.get(rangePage + 1).thumbnail.extension;
            Glide.with(this).load(urlImg2).listener(new RequestListener<Drawable>(){

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    binding.progressImg2.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    binding.progressImg2.setVisibility(View.GONE);
                    return false;
                }
            }).error(R.drawable.ic_no_image).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(binding.imgCharacter2);
        } else {
            binding.llCharacter2.setVisibility(View.GONE);
        }

        if (charactersFiltrados.size() >= 3) {
            binding.progressImg3.setVisibility(View.VISIBLE);
            binding.llCharacter3.setVisibility(View.VISIBLE);
            binding.setPersonagem3(charactersFiltrados.get(rangePage + 2));
            String urlImg3 = charactersFiltrados.get(rangePage + 2).thumbnail.path + "." + charactersFiltrados.get(rangePage + 2).thumbnail.extension;
            Glide.with(this).load(urlImg3).listener(new RequestListener<Drawable>(){

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    binding.progressImg3.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    binding.progressImg3.setVisibility(View.GONE);
                    return false;
                }
            }).error(R.drawable.ic_no_image).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(binding.imgCharacter3);
        } else {
            binding.llCharacter3.setVisibility(View.GONE);
        }

        if(charactersFiltrados.size() >= 4) {
            binding.progressImg4.setVisibility(View.VISIBLE);
            binding.llCharacter4.setVisibility(View.VISIBLE);
            binding.setPersonagem4(charactersFiltrados.get(rangePage + 3));
            String urlImg4 = charactersFiltrados.get(rangePage + 3).thumbnail.path + "." + charactersFiltrados.get(rangePage + 3).thumbnail.extension;
            Glide.with(this).load(urlImg4).listener(new RequestListener<Drawable>(){

                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    binding.progressImg4.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    binding.progressImg4.setVisibility(View.GONE);
                    return false;
                }
            }).error(R.drawable.ic_no_image).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(binding.imgCharacter4);
        } else {
            binding.llCharacter4.setVisibility(View.GONE);
        }

        binding.rvPaginacao.scrollToPosition(page + 1);

    }

    private String generateURL() {
        Uri.Builder builder = Uri.parse(Api.CHARACTERS_LINK).buildUpon();
        String timeStamp = String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        String hash = String.format("%s%s%s", timeStamp, Api.PRIVATE_API_KEY, Api.PUBLIC_API_KEY);
        String hashMd5 = getMd5(hash);
        builder.appendQueryParameter("ts", timeStamp);
        builder.appendQueryParameter("apikey", Api.PUBLIC_API_KEY);
        builder.appendQueryParameter("hash", hashMd5);
        return builder.build().toString();
    }

    private void generatePages() {
        page = 0;
        pages.clear();
        int qtdCharacters = charactersFiltrados.size();

        if(qtdCharacters <= 4){
            numPaginas = 1;
        }else {
            numPaginas = (int) Math.ceil(qtdCharacters / 4);
        }

        for (int i = 1; i <= Math.ceil(qtdCharacters / (float) pageNumber); i++) {
            pages.add(String.valueOf(i));
        }

        if (adapterPaginator == null) {
            adapterPaginator = new RecyclerAdapterPaginator(this, pages, 0);
            binding.rvPaginacao.setAdapter(adapterPaginator);
            adapterPaginator.notifyDataSetChanged();
        } else {
            binding.rvPaginacao.setAdapter(adapterPaginator);
            adapterPaginator.notifyDataSetChanged();
        }
    }

    private String getMd5(String value) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(value.getBytes(), 0, value.length());

        return new BigInteger(1, m.digest()).toString(16);
    }

    private void filter() {
        charactersFiltrados.clear();
        charactersFiltrados.addAll(characters.getData().getResults());

        if (!binding.edtBusca.getText().toString().isEmpty()) {
            charactersFiltrados.clear();
            charactersFiltrados.addAll(characters.getData().getResults());
            ArrayList<Result> filtrados = new ArrayList<>();

            for (int i = 0; i < charactersFiltrados.size(); i++) {
                if (charactersFiltrados.get(i).name.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").trim().contains(binding.edtBusca.getText().toString().toLowerCase().replaceAll("[^a-zA-Z0-9]", " ").trim())) {
                    filtrados.add(charactersFiltrados.get(i));
                }
            }

            charactersFiltrados.clear();
            charactersFiltrados.addAll(filtrados);
        }

        generatePages();
        updateView();
        binding.edtBusca.clearFocus();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}