package dao;


import dto.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try {
            conn = new conectaDAO().connectDB();
            // INSTRUÇÃO SQL PARA EXECUTAR O COMANDO INSERT
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            PreparedStatement query = conn.prepareStatement(sql);
            
            query.setString(1, produto.getNome());
            query.setDouble(2, produto.getValor());
            query.setString(3, produto.getStatus());
            
            // EXECUTAR O COMANDO
            query.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {
            conn = new conectaDAO().connectDB();

            String sql = "SELECT * FROM produtos";
            PreparedStatement query = conn.prepareStatement(sql);

            ResultSet result = query.executeQuery();

            while (result.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(result.getInt("id"));
                p.setNome(result.getString("nome"));
                p.setValor(result.getInt("valor"));
                p.setStatus(result.getString("status"));

                listagem.add(p);
            }
                return listagem;
        } catch (SQLException e) {
            System.out.println("Erro ao listar os produtos");
            return null;
    }
}
    public void venderProdutos(int id){
      try {
            conn = new conectaDAO().connectDB();
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            PreparedStatement query = conn.prepareStatement(sql);
            
            query.setInt(1, id);
            query.execute();
           
        } catch (SQLException e) {
            System.out.println(e);
        }   
    }
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {
            conn = new conectaDAO().connectDB();

            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            PreparedStatement query = conn.prepareStatement(sql);

            ResultSet result = query.executeQuery();

            while (result.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(result.getInt("id"));
                p.setNome(result.getString("nome"));
                p.setValor(result.getInt("valor"));
                p.setStatus(result.getString("status"));

                listagem.add(p);
            }
                return listagem;
        } catch (SQLException e) {
            System.out.println("Erro ao listar os produtos vendidos");
            return null;
    }
}
}