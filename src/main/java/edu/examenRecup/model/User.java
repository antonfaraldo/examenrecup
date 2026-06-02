package edu.examenRecup.model;

public class User {
    private int id;
    private String nombre;
    private String nickname;
    private String email;
    private int edad;
    private String password;
    private Role role;

    public User() {
    }

    public User(int id, String nombre, String nickname, String email, int edad, String password, Role role) {
        this.id = id;
        this.nombre = nombre;
        this.nickname = nickname;
        this.email = email;
        this.edad = edad;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", role=" + role +
                '}';
    }
}
