package com.Controller;
import com.View.VentanaRegistro;
import javax.swing.*;

public class RegistroController {
    private final VentanaRegistro ventana;

   public RegistroController(VentanaRegistro ventana) {
        this.ventana = ventana;
        initValidations();
        initActions();
    }

    private void initActions() {
    }

    private void initValidations(){
        ventana.addFieldValidation(ventana.getCampoCorreo(),() -> {
            String correo = String.valueOf(ventana.getCampoCorreo());
            boolean isValid = correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            ventana.setFieldBorder(ventana.getCampoCorreo(), isValid);
        });
    }
}
