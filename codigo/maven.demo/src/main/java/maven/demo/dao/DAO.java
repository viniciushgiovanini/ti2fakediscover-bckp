package maven.demo.dao;

import java.sql.*;

public class DAO {
    private static Connection conexao;

    public DAO() {
        conexao = null;
    }

    // estabelecer conexao com o bd da azure
    public static Connection getConexao() {
        String driver = "org.postgresql.Driver";
        String server = "ec2-3-213-106-122.compute-1.amazonaws.com";
        String db = "dakefdj9rt75mm";
        int porta = 5432;
        String url = "jdbc:postgresql://" + server + ":" + porta + "/" + db;
        String user = "ogilsqwflaidwf";
        String pass = "dd446a4fbdc01d1ca41091485b4030c92c4c806a31a8efd0890732a632088416";
    	
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexão feita com o postgres!");
        } catch (ClassNotFoundException e) {
            System.out.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return conexao;
    }

    // fechar conexao com o bd
    public static boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexao -- " + e.getMessage());
        }

        return status;
    }
}

