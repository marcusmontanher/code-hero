package com.example.codehero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.codehero.databinding.ActivityDetalhesBinding;
import com.example.codehero.model.character.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class DetalhesActivity extends AppCompatActivity {

    ActivityDetalhesBinding binding;
    String personagemNome = "";
    String personagemDescricao = "";
    String personagemImagem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhes);

        personagemNome = getIntent().getStringExtra("personagem_nome");
        personagemDescricao = getIntent().getStringExtra("personagem_descricao").isEmpty() ? getString(R.string.nao_disponivel) : getIntent().getStringExtra("personagem_descricao");
        personagemImagem = getIntent().getStringExtra("personagem_imagem");

        binding.setPersonagemNome(personagemNome);
        binding.setPersonagemDescricao(personagemDescricao);


        Glide.with(this).load(personagemImagem).error(R.drawable.ic_no_image).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(binding.imgCharacter);

        setSupportActionBar(binding.toolbarDetalhes);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}