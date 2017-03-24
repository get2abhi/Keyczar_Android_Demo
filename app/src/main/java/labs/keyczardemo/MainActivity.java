package labs.keyczardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.keyczar.Crypter;
import org.keyczar.exceptions.KeyczarException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Crypter crypter = null;
        try {
            crypter = new Crypter(new AndroidKeyczarReader(getResources(), "keys"));
        } catch (KeyczarException e) {
            e.printStackTrace();
        }
        final Crypter finalCrypter = crypter;
        ((Button)findViewById(R.id.encrypt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ((TextView)findViewById(R.id.encrypted)).setText(finalCrypter.encrypt((((TextView)findViewById(R.id.secret)).getText().toString())));
                } catch (KeyczarException e) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                };
            }
        });

        ((Button)findViewById(R.id.decrypt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ((TextView)findViewById(R.id.decrypted)).setText(finalCrypter.decrypt(((TextView)findViewById(R.id.encrypted)).getText().toString()));
                } catch (KeyczarException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
