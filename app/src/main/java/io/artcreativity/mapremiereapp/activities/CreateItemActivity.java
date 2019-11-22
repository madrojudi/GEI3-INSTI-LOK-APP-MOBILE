package io.artcreativity.mapremiereapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.artcreativity.mapremiereapp.entities.ItemDeMaListe;
import io.artcreativity.mapremiereapp.R;

public class CreateItemActivity extends AppCompatActivity {

    TextView errorView;
    public static final String ITEM_CREATED = "new_item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        Button button = findViewById(R.id.my_btn);
        final EditText titleText = findViewById(R.id.title_editor);
        final EditText subTitleText = findViewById(R.id.sub_title_editor);
        errorView = findViewById(R.id.error_zone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasError = false;
                errorView.setVisibility(View.INVISIBLE);
                String erroMsg = "";
                if(titleText.getText().toString().isEmpty()){
                    erroMsg = "Le Titre est obligatoire";
                    hasError = true;
                }

                if(subTitleText.getText().toString().isEmpty()){
                    if(!erroMsg.isEmpty()) erroMsg += "\n";
                    erroMsg += "Le Sous Titre est obligatoire";
                    hasError = true;
                }

                if(hasError){
                    errorView.setText(erroMsg);
                    errorView.setVisibility(View.VISIBLE);
                    return;
                }

                ItemDeMaListe item = new ItemDeMaListe();
                item.title = titleText.getText().toString();
                item.subTitle = subTitleText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(ITEM_CREATED, item);
                setResult(Activity.RESULT_OK, intent);
                finish();


            }
        });

    }
}
