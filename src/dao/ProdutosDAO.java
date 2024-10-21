package dao;


import dto.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
        ArrayList<ProdutosDTO> lista = new ArrayList<>();

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

                lista.add(p);
            }
                return lista;
        } catch (SQLException e) {
            System.out.println("Erro ao listar os produtos");
            return null;
    }
}
}