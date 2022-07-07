/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import javafx.scene.image.Image;

public class Configurations {
    String  ClinicName;
    String telefono;
    String correoElectronico;
    String imagen;
    String imagenCorreo;

    public Configurations(String ClinicName, String telefono, String correoElectronico, String imagen, String ImagenCorreo) {
        this.ClinicName = ClinicName;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.imagen = imagen;
        this.imagenCorreo = ImagenCorreo;
    }

  
    

    public String getImagenCorreo() {
        return imagenCorreo;
    }

    public void setImagenCorreo(String imagenCorreo) {
        this.imagenCorreo = imagenCorreo;
    }

    public String getClinicName() {
        return ClinicName;
    }

    public void setClinicName(String ClinicName) {
        this.ClinicName = ClinicName;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }  
}
