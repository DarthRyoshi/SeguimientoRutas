package com.example.seguimienderutas;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class Register extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText, usernameEditText;
    private Button registerButton;

    // TextViews para mostrar los requisitos de la contraseña
    private TextView uppercaseTextView, numberTextView, specialCharTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar las vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        usernameEditText = findViewById(R.id.nameEditText);
        registerButton = findViewById(R.id.registerButton);

        // Inicializar los TextViews para los requisitos de la contraseña
        uppercaseTextView = findViewById(R.id.uppercaseTextView);
        numberTextView = findViewById(R.id.numberTextView);
        specialCharTextView = findViewById(R.id.specialCharTextView);

        // Configurar el botón de registro
        registerButton.setOnClickListener(v -> registerUser());

        // Validación de la contraseña mientras el usuario escribe
        passwordEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String password = passwordEditText.getText().toString().trim();

                // Verificar mayúsculas
                if (!password.matches(".*[A-Z].*")) {
                    uppercaseTextView.setVisibility(TextView.VISIBLE);
                } else {
                    uppercaseTextView.setVisibility(TextView.GONE);
                }

                // Verificar números
                if (!password.matches(".*\\d.*")) {
                    numberTextView.setVisibility(TextView.VISIBLE);
                } else {
                    numberTextView.setVisibility(TextView.GONE);
                }

                // Verificar caracteres especiales
                if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                    specialCharTextView.setVisibility(TextView.VISIBLE);
                } else {
                    specialCharTextView.setVisibility(TextView.GONE);
                }
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {}
        });
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();

        // Validaciones de campos
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Ingresa un correo válido");
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError("La contraseña debe tener al menos 6 caracteres");
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Las contraseñas no coinciden");
            return;
        }

        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("Ingresa un nombre de usuario");
            return;
        }

        // Autenticación local simulada
        if (registerLocalUser(email, password, username)) {
            // Si el registro es exitoso, redirigir al mapa
            goToMapActivity();
        } else {
            Toast.makeText(this, "Error en el registro: Usuario ya existente", Toast.LENGTH_LONG).show();
        }
    }

    // Método simulado para registrar usuarios localmente
    private boolean registerLocalUser(String email, String password, String username) {
        // Aquí puedes implementar lógica para guardar las credenciales en una base de datos local
        // Por ahora, se devuelve true como ejemplo
        return true;
    }

    private void goToMapActivity() {
        // Redirigir al mapa
        Intent intent = new Intent(Register.this, MapActivity.class);

        startActivity(intent);
        finish();
    }
}
