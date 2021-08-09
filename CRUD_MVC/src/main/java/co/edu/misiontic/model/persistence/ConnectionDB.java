package co.edu.misiontic.model.persistence;

import java.sql.*;
import java.util.logging.*;
public class ConnectionDB {
// Configuracion de la conexion a la base de datos
private String url = "";
public Connection con = null;
private Statement stmt = null;
private ResultSet rs = null;

//Constructor sin parmetros
public ConnectionDB() {
    url = "reto5db.db";
    try {
    // Realizar la conexion
    con = DriverManager.getConnection("jdbc:sqlite:"+url);
    if (con != null) {
    DatabaseMetaData meta = con.getMetaData();
    System.out.println("Base de datos conectada " + meta.getDriverName());
    }
    } catch (SQLException ex) {
    System.out.println(ex.getMessage());
    }
}
//Retornar la conexin
public Connection getConnection() {
    return con;
}
//Cerrar la conexin
public void closeInternalConnection(Connection con) {
    if (con != null) {
    try {
    con.close();
    } catch (SQLException ex) {
    Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
// Mtodo que devuelve un ResultSet de una consulta (tratamiento de SELECT)
public ResultSet consultDB(String statement) {
    try {
        stmt = con.createStatement();
        rs = stmt.executeQuery(statement);
    } catch (SQLException sqlex) {
        System.out.println(sqlex.getMessage());
    } catch (RuntimeException rex) {
        System.out.println(rex.getMessage());
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    return rs;
}
// Metodo que realiza un INSERT y devuelve TRUE si la operacin fue existosa
public boolean insertDB(String statement) {
    try {
        stmt = con.createStatement();
        stmt.execute(statement);
    } catch (SQLException | RuntimeException sqlex) {
        System.out.println("ERROR RUTINA: " + sqlex);
        return false;
    }
    return true;
}
public boolean deleteDB(String statement) {
    try {
        stmt = con.createStatement();
        stmt.execute(statement);
    } catch (SQLException | RuntimeException sqlex) {
        System.out.println("ERROR RUTINA: " + sqlex);
        return false;
    }
    return true;
}
// Mtodo que realiza una operacin como UPDATE, DELETE, CREATE TABLE, entre otras
// y devuelve TRUE si la operacin fue existosa
public boolean updateDB(String statement) {
    try {
        stmt = con.createStatement();
        stmt.executeUpdate(statement);
    } catch (SQLException | RuntimeException sqlex) {
        System.out.println("ERROR RUTINA: " + sqlex);
        return false;
    }
    return true;
}

public boolean setAutoCommitBD(boolean parametro) {
    try {
        con.setAutoCommit(parametro);
    } catch (SQLException sqlex) {
        System.out.println("Error al configurar el autoCommit " + sqlex.getMessage());
        return false;
    }
    return true;
}

public void closeConnection() {
    closeInternalConnection(con);
}
public boolean commitDB() {
    try {
        con.commit();
        return true;
    } catch (SQLException sqlex) {
        System.out.println("Error al hacer commit " + sqlex.getMessage());
        return false;
    }
}
public boolean rollbackDB() {
    try {
        con.rollback();
        return true;
    } catch (SQLException sqlex) {
        System.out.println("Error al hacer rollback " + sqlex.getMessage());
        return false;
    }
}
}