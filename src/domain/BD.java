/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kevin Brenes
 */
public class BD {

    private static Connection mConection;
    private static Statement mStatement;
    private static ResultSet mResultSet;
    private final String bd;
    private final String user;
    private final String password;

    public BD(String bd, String user, String password) {
        mConection = null;
        mStatement = null;
        mResultSet = null;
        this.bd = bd;
        this.user = user;
        this.password = password;
    }

    public boolean Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            mConection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/login" + bd, user, password);
            return mConection != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void Desconectar() {
        try {
            BD.mConection.close();
        } catch (Exception e) {
        }
    }

    public Boolean AddUser(Usuario mUsuario) {

        try {
            mStatement = mConection.createStatement();
            mStatement.execute("INSERT INTO registro (Usuario, Nombre, Apellido, Contraseña, Correo ) "
                    + " VALUES ('" + mUsuario.getUsuario() + "', '" + mUsuario.getNombre() + "','" + mUsuario.getApellido()
                    + "','" + mUsuario.getContraseña() + "','" + mUsuario.getCorreo() + "')");
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        }

    }

    public Usuario GetUsuario(String Usuario) {
        Usuario mUsuario = null;
        try {
            mStatement = mConection.createStatement();
            mResultSet = mStatement.executeQuery("SELECT * FROM Usuario WHERE Usuario = '" + Usuario + "' ");
            while (mResultSet.next()) { //iterar a traves del resultado 
                mUsuario = new Usuario();
                mUsuario.setUsuario(mResultSet.getString("Usuario"));
                mUsuario.setNombre(mResultSet.getString("Nombre"));
                mUsuario.setApellido(mResultSet.getString("Apellido"));
                mUsuario.setContraseña(mResultSet.getString("Contraseña"));
                mUsuario.setCorreo(mResultSet.getString("Correo electronico"));

                return mUsuario;
            }
        } catch (SQLException e) {
            return null;
        }
        return mUsuario;
    }

}
