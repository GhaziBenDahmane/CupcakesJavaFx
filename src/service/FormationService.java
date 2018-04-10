/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Formation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import util.DataSource;

/**
 *
 * @author asus
 */
public class FormationService {

    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public FormationService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void AjouterFormation(Formation F) throws SQLException {
        System.out.println("Veuillez Entrer les Donnees");
        String req = "INSERT INTO cupCake_formation (nom,video,start_date_Formation,end_date_Formation,status) VALUES (?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, F.getNom());
        pre.setString(2, F.getVideo());
        pre.setDate(3, (Date) F.getStart_date_formation());
        pre.setDate(4, (Date) F.getEnd_date_formation());
        pre.setString(5, F.getStatus());
        pre.executeUpdate();

        System.out.println("Avis Ajoutée");

    }

    public List<Formation> AfficherFormation() throws SQLException {
        List<Formation> list = new ArrayList<>();
        String req = "SELECT * from cupCake_formation ";
        ResultSet res;
        res = ste.executeQuery(req);
        while (res.next()) {
            Formation F = new Formation(res.getInt("id"), res.getString("nom"), res.getString("video"), res.getDate("start_date_Formation"), res.getDate("end_date_Formation"), res.getString("Status"));
            list.add(F);

        }
        System.out.println(list);
        return list;

    }

    public void ModifierFormation(Formation F, int id) throws SQLException {

        String req = "UPDATE `cupCake_formation` SET `nom`=?,`video`=?,`start_date_Formation`=?,`end_date_Formation`=?,`status`=? WHERE id='" + id + "' ;";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, F.getNom());
        pre.setString(2, F.getVideo());
        pre.setDate(3, (Date) F.getStart_date_formation());
        pre.setDate(4, (Date) F.getEnd_date_formation());
        pre.setString(5, F.getStatus());
        pre.executeUpdate();

        System.out.println("Formation Modifie avec Succees");
    }

    public void supprimerFormation(int IdEvent) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String req = "DELETE from  cupCake_formation  WHERE id =?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, IdEvent);
        pre.executeUpdate();
    }
}
