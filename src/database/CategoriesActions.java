package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActions {
	public static List<Categories> getCategoriesDB()
	{
		List<Categories> listeCategories = new ArrayList<Categories>();
		
		try {
			Connection cnx = DBConnection.ConnectToDatabase();

			Statement st = cnx.createStatement();

			// Recuperer et ajouter tous les produits
			ResultSet resProduits = st.executeQuery("SELECT * FROM categories");
			while (resProduits.next()) {
				int cate_id = resProduits.getInt("cate_id");
				String cate_libelle = resProduits.getString("cate_libelle");

				
				Categories c = new Categories(cate_id, cate_libelle);
				listeCategories.add(c);
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCategories;
	}
}
