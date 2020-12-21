package life.nsu.privatemessenger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EncryptionActivity extends AppCompatActivity {

    TextView mDecryption;
    Button mEncrypt;

    EditText mPhone;
    EditText mSecret;
    EditText mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_encryption);

        mDecryption = findViewById(R.id.tv_decryption);
        mEncrypt = findViewById(R.id.btn_encrypt);

        mPhone = findViewById(R.id.et_phone);
        mSecret = findViewById(R.id.et_secret);
        mMessage = findViewById(R.id.et_message_body);

        mDecryption.setOnClickListener(v -> {
            Intent intent = new Intent(EncryptionActivity.this, DecryptionActivity.class);
            startActivity(intent);
        });

        mEncrypt.setOnClickListener(view -> {
            String phoneNumber = mPhone.getText().toString();
            String message = mMessage.getText().toString();
            String secret = mSecret.getText().toString();

            // validation user input
            if(phoneNumber.length() != 11 || message.isEmpty() || secret.isEmpty()) {
                 return;
            }

            //ToDo
            // Call method to encrypt


            Intent intent = new Intent(EncryptionActivity.this, FurtherActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            intent.putExtra("secret", "key");
            intent.putExtra("cypher", "cypher");
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_in);
        super.onBackPressed();
    }


}