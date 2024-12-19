package com.example.seguimienderutas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los campos del formulario de login
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Configurar el clic para iniciar sesión
        TextView loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> loginUserWithEmailPassword());

        // Configurar el clic para redirigir a RegistroApp
        TextView registerTextView = findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(v -> {
            // Crea un Intent para abrir la actividad de registro
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });
    }

    // Método para manejar el inicio de sesión
    private void loginUserWithEmailPassword() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Correo electrónico requerido");
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Contraseña requerida");
            return;
        }

        // Autenticación local simulada
        if (authenticateUser(email, password)) {
            // Si la autenticación es exitosa, redirigir al mapa
            goToMapActivity();
        } else {
            // Si ocurre un error, mostrar un mensaje
            Toast.makeText(MainActivity.this, "Error de inicio de sesión: Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }


    private boolean authenticateUser(String email, String password) {

        return "usuario@ejemplo.com".equals(email) && "1234".equals(password);
    }

    // Método para redirigir a la actividad del mapa
    private void goToMapActivity() {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
        finish();
    }
}
