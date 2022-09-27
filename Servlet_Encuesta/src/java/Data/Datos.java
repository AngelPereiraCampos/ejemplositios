package Data;

public class Datos {
    
    String id, correo, telefono;

    public Datos(String id, String correo, String telefono) {
        this.id = id;
        this.correo = correo;
        this.telefono = telefono;
    }
    
        public Datos() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
