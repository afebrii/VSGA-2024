package com.afebrii.vsga2024;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TambahActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText namaEditText;
    private EditText alamatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambah);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DBHelper(this);
        namaEditText = findViewById(R.id.namaEditText);
        alamatEditText = findViewById(R.id.alamateditText);

    }

    public void cancel(View view) {
        finish();
    }

    public void submit(View view) {
        String nama = namaEditText.getText().toString();
        if (nama.isEmpty()) {
            Toast.makeText(this, "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            return;
        }

        String alamat = alamatEditText.getText().toString();
        if (alamat.isEmpty()) {
            Toast.makeText(this, "Alamat Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
            return;
        }

        dbHelper.tambahKontak(nama, alamat);
        finish();
    }
}