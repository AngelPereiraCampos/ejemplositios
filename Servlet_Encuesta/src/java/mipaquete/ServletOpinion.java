package mipaquete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import Data.Datos;

public class ServletOpinion extends HttpServlet {

// Declaracion de variables miembro correspondientes a
// los campos del formulario
    private String id = null;
    private String correo = null;
    private String telefono = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Adquisicion de los valores del formulario a travez del objeto req
        id = request.getParameter("id");
        correo = request.getParameter("correo");
        telefono = request.getParameter("telefono");

        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Valores recogidos en el formulario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Valores recogidos del formulario</h1>");
            out.println("<p><b>ID : </b>" + id + "</p>");
            out.println("<p><b>Correo : </b>" + correo + "</p>");
            out.println("<p><b>Telefono : </b>" + telefono + "</p>");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Este servlet lee los datos de un formulario"
                + "y los muestra en pantalla";
    }

    public void Insertar(Datos dts) {

        Connection cn = null; // variable de conexion
        Statement st = null;  // variable de instruccion SQL
        ResultSet rs = null; // variable de registros o filas
        try {
            String connectionUrl = "jdbc:sqlserver://FERNANDO-PC\\SQLEXPRESS:1433;"
                    + "databaseName=clase8; user=usuario; password=123;";
//            String connectionUrl = "jdbc:sqlserver://localhost:1433;databasename=Proyecto3;integratedSecurity=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //return(DriverManager.getConnection(connectionUrl));

            cn = DriverManager.getConnection(connectionUrl);
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

//            rs = st.executeQuery("Select * from Usuario ");
//
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
//            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            CallableStatement csta = cn.prepareCall("{call usp_RegistrarUsuario(?,?,?)}");

            csta.setString(1, dts.getId());
            csta.setString(2, dts.getCorreo());
            csta.setString(3, dts.getTelefono());

            csta.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
