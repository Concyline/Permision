package br.com.permision;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        validaPermisoes();
    }

    private void validaPermisoes() {

        List<String> lpermissions = new ArrayList<>();
        lpermissions.add(Manifest.permission.READ_PHONE_STATE);
        lpermissions.add(Manifest.permission.CAMERA);
        lpermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);

        String[] permissions = new String[lpermissions.size()];

        Permissions.check(MainActivity.this, lpermissions.toArray(permissions), null, null, new PermissionHandler() {
            @Override
            public void onGranted() {
              int x = 0;
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Atenção").setMessage("O aplicativo não tem as permições necessárias para prosseguir!").setPositiveButton("Pegar as permições?", (dialog, which) -> validaPermisoes()).setNegativeButton("Cancelar", (dialog, which) -> finish()).show();
            }
        });
    }
}