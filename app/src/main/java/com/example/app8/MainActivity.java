package com.example.app8;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.app8.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final int REQUEST_CALL = 1;
    private EditText mEditPhoneNumber;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = (ActivityMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_main);

        Button buttonNine = findViewById(R.id.buttonNine);
        Button buttonEight = findViewById(R.id.buttonEight);
        Button buttonSeven = findViewById(R.id.buttonSeven);
        Button buttonSix = findViewById(R.id.buttonSix);
        Button buttonFive = findViewById(R.id.buttonFive);
        Button buttonFour = findViewById(R.id.buttonFour);
        Button buttonThree = findViewById(R.id.buttonThree);
        Button buttonTwo = findViewById(R.id.buttonTwo);
        Button buttonOne = findViewById(R.id.buttonOne);
        Button buttonZero = findViewById(R.id.buttonZero);
        Button buttonClear = findViewById(R.id.buttonDel);
        Button buttonCall = findViewById(R.id.buttonCall);
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonStar = findViewById(R.id.buttonStar);
        Button buttonPound = findViewById(R.id.buttonHash);

        EditText text = (EditText) findViewById(R.id.resultphonetext);
        PhoneNumberUtils.formatNumber(text.getText().toString());

        binding.resultphonetext.setShowSoftInputOnFocus(false);

        binding.buttonZero.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "0"));
        binding.buttonOne.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "1"));
        binding.buttonTwo.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "2"));
        binding.buttonThree.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "3"));
        binding.buttonFour.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "4"));
        binding.buttonFive.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "5"));
        binding.buttonSix.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "6"));
        binding.buttonSeven.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "7"));
        binding.buttonEight.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "8"));
        binding.buttonNine.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "9"));
        binding.buttonStar.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "*"));
        binding.buttonHash.setOnClickListener(v -> binding.resultphonetext.setText(binding.resultphonetext.getText() + "#"));

        binding.buttonCall.setOnClickListener(v -> {
            if (binding.resultphonetext.getText().length() > 0) {
                String phStr = "tel:" + binding.resultphonetext.getText().toString();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    makePhoneCall(phStr);
                }
            } else {
                Toast.makeText(MainActivity.this, "Unable to call", Toast.LENGTH_SHORT).show();
            }
        });
        binding.buttonDel.setOnClickListener(v -> {
            if (binding.resultphonetext.getText().length() > 0) {
                CharSequence currentText = binding.resultphonetext.getText();
                binding.resultphonetext.setText(currentText.subSequence(0, currentText.length() - 1));
            } else {
                binding.resultphonetext.setText("");
            }
        });
        binding.buttonSave.setOnClickListener(v -> {
            if (!binding.resultphonetext.getText().toString().isEmpty()) {
                String phoneNumber = binding.resultphonetext.getText().toString();
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No app to support", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void makePhoneCall(String PhStr) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(PhStr));
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(callIntent);
        }
    }

}
