package Main;

import java.util.Scanner;
import java.sql.*;

public class Main {
  private static final String password = "";
  private static final String user = System.getenv("userDB");
  private static final String url = "jdbc:postgresql://localhost:5432/teste";
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    createTable();
    boolean programActive = true;
    
    while (programActive) {
      try {
        
        System.out.println("O que deseja fazer?");
        System.out.println();
        System.out.printf("1 - Criar novo produto %n2 - Editar produto existente %n3 - Mostrar tabela de produtos %n4 - Apagar produto selecionado %nDigite qualquer outra tecla para encerrar%n");
        String chose = sc.next();
        
        if (chose.equals("1")) {
          sc.nextLine();
          System.out.println("Qual o nome do produto?");
          String nameUser = sc.nextLine();
          
          System.out.println("E qual o preço do produto?");
          double valueUser = sc.nextDouble();
          
          create(nameUser, valueUser);
        
        } else if (chose.equals("2"))  {
          System.out.println("Escolha o id de qual produto quer alterar:");
          int idUser = sc.nextInt();
          
          sc.nextLine();
          System.out.println("Qual o novo nome? (pode ser o anterior)");
          String nameUser = sc.nextLine();
          
          System.out.println("Qual o novo valor? (pode ser o anterior)");
          double valueUser = sc.nextDouble();
          
          update(idUser, nameUser, valueUser);
        
        } else if (chose.equals("3")) {
          System.out.println("PRODUTOS:");
          read();
          
        } else if (chose.equals("4")) {
          System.out.println("Qual o id do produto desejado?");
          int idUser = sc.nextInt();
          delete(idUser);
          
        } else {
          System.out.println("Encerrando....");
          programActive = false;
          break;
          
        }
      
      } catch (Exception e) {
        
        System.out.println("Erro no sistema: " + e.getMessage());
      }
    }
  }
  
  public static void createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                      "id SERIAL PRIMARY KEY," +
                      "nome VARCHAR(100) NOT NULL," +
                      "valor NUMERIC(10,2) NOT NULL)";
    
    try (Connection conn = DriverManager.getConnection(url, user, password);
      Statement stmt = conn.createStatement()) {
        
        stmt.execute(sql);
        
    } catch (SQLException e) {
      
      System.out.println("Erro no banco de dados: " + e.getMessage());
      System.out.println();
        
      }
  }
  
  public static void read() {
    System.out.println();
    
    String sql = "SELECT id, nome, valor FROM produtos ORDER BY id;";
      
    try (Connection conn = DriverManager.getConnection(url, user, password);
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql)) {
        
        System.out.printf("%-2s | %-10s | %-6s%n", "ID", "NOME", "VALOR");
        
        System.out.println("------------------------");
        
        while (rs.next()) {
          int id = rs.getInt("id");
          String name = rs.getString("nome");
          double value = rs.getDouble("valor");
          
          System.out.printf("%-2d | %-10s | %-4.2f%n", id, name, value);
        }
        System.out.println();
      } catch(SQLException e) {
        System.err.print("Erro no banco de dados: " + e.getMessage());
        System.out.println();
      }
  }
  
  public static void update(int id, String newName, double newValue) {
    String sql = "UPDATE produtos SET nome = ?, valor = ? WHERE id = ?";
    
    try (Connection conn = DriverManager.getConnection(url, user, password);
      PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, newName);
        pstmt.setDouble(2, newValue);
        pstmt.setInt(3, id);
        
        int result = pstmt.executeUpdate();
        
        if (result > 0) {
          System.out.println();
          System.out.printf("O produto de id %d foi alterado com sucesso!%n", id);
        }
      } catch (SQLException e) {
        
        System.out.println("Erro no banco de dados: " + e.getMessage());
        System.out.println();
        
      }
  }
  
  public static void create(String name, double value) {
    String sql = "INSERT INTO produtos (nome, valor) VALUES (?, ?)";
    
    try (Connection conn = DriverManager.getConnection(url, user, password);
      PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, name);
        pstmt.setDouble(2, value);
        
        pstmt.executeUpdate();
        
        System.out.println("Produto criado com sucesso!");
        
      } catch (SQLException e) {
        
        System.out.println("Erro no banco de dados: " + e.getMessage());
        System.out.println();
        
      }
  }
  
  public static void delete(int id) {
    String sql = "DELETE FROM produtos WHERE id = ?";
    
    try (Connection conn = DriverManager.getConnection(url, user, password);
      PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, id);
        int result = pstmt.executeUpdate();
        
        if (result > 0) {
          System.out.println();
          System.out.printf("Produto de id %d foi excluído com sucesso!", id);
          System.out.println();
        }
      } catch (SQLException e) {
        
        System.out.println("Erro no banco de dados: " + e.getMessage());
        System.out.println();
        
      }
    }
    
  public static void dropTable() {
    String sql = "DROP TABLE IF EXISTS produtos";
    
    try (Connection conn = DriverManager.getConnection(url, user, password);
      Statement stmt = conn.createStatement()) {
        
        stmt.execute(sql);
        
    } catch (SQLException e) {
      
      System.out.println("Erro no banco de dados: " + e.getMessage());
      
    }
  }
}