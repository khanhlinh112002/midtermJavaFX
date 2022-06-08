package com.example.demo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


public class demo implements Initializable{
    @FXML
    private TextField textIDPet;
    @FXML
    private TextField txtSupplies;
    @FXML
    private TableView<PetSupplies> tablePetSupplies;
    @FXML
    private TableColumn<PetSupplies, String> IDPetColmn;

    @FXML
    private TableColumn<PetSupplies, String> IDSupColmn;
    @FXML
    private TableColumn<PetSupplies, String> SuppliesColmn;
    @FXML
    private TextField txtName;
    @FXML
    private TableView<Animal> tableAnimal;
    @FXML
    private TableColumn<Animal,String> IDColmn;
    @FXML
    private TableColumn<Animal,String> NameColmn;
    @FXML
    private Button btnAnimalClose;
    @FXML
    private Button Exit;
    @FXML
    private Button btnAnimal;
    @FXML
    private Button btnPetSupplies;
    @FXML
    private Button btnPetSuppliesClose;
    @FXML
    private Pane formAnimal;
    @FXML
    private Pane formPetSupplies;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddSupplies;

    @FXML
    private Button btnDeleteSupplies;

    @FXML
    private Button btnUpdateSupplies;
    @FXML
    void Add(ActionEvent event){
        String name;
        name = txtName.getText();

        try
        {

            pst = con.prepareStatement("insert into animal(name)values(?)");
            pst.setString(1, name);

            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registation");


            alert.setHeaderText("Student Registation");
            alert.setContentText("Record Addedddd!");

            alert.showAndWait();

            tableAnimal();

            txtName.setText("");
            txtName.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void tableAnimal()
    {
        Connect();
        ObservableList<Animal> animals = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id,name from animal");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Animal st = new Animal();
                    st.setId(rs.getString("id"));
                    st.setName(rs.getString("name"));
                    animals.add(st);
                }
            }
            tableAnimal.setItems(animals);
            IDColmn.setCellValueFactory(f -> f.getValue().idProperty());
            NameColmn.setCellValueFactory(f -> f.getValue().nameProperty());



        }

        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableAnimal.setRowFactory(tv -> {
            TableRow<Animal> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tableAnimal.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tableAnimal.getItems().get(myIndex).getId()));
                    txtName.setText(tableAnimal.getItems().get(myIndex).getName());



                }
            });
            return myRow;
        });


    }
    public void TablePetSupplies()
    {
        Connect();
        ObservableList<PetSupplies> petSupplies = FXCollections.observableArrayList();
        try
        {
            pst = con.prepareStatement("select id,idPet,Supplies from petsupplies");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    PetSupplies st = new PetSupplies();
                    st.setId(rs.getString("id"));
                    st.setIDpet(rs.getString("idPet"));
                    st.setSupplies(rs.getString("supplies"));
                    petSupplies.add(st);
                }
            }
            tablePetSupplies.setItems(petSupplies);
            IDPetColmn.setCellValueFactory(f -> f.getValue().idProperty());
            IDSupColmn.setCellValueFactory(f -> f.getValue().idPetProperty());
            SuppliesColmn.setCellValueFactory(f -> f.getValue().suppliesProperty());



        }

        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tablePetSupplies.setRowFactory(tv -> {
            TableRow<PetSupplies> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  tablePetSupplies.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(tablePetSupplies.getItems().get(myIndex).getId()));
                    textIDPet.setText(tablePetSupplies.getItems().get(myIndex).getIDpet());
                    txtSupplies.setText(tablePetSupplies.getItems().get(myIndex).getSupplies());




                }
            });
            return myRow;
        });


    }

    @FXML
    void Update(ActionEvent event){
        String name;

        myIndex = tableAnimal.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableAnimal.getItems().get(myIndex).getId()));

        name = txtName.getText();

        try
        {
            pst = con.prepareStatement("update animal set name = ? where id = ? ");
            pst.setString(1, name);

            pst.setInt(2, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registationn");


            alert.setHeaderText("Student Registation");
            alert.setContentText("Updateddd!");

            alert.showAndWait();
            tableAnimal();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void Delete(ActionEvent event){
        myIndex = tableAnimal.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableAnimal.getItems().get(myIndex).getId()));


        try
        {
            pst = con.prepareStatement("delete from animal where id = ? ");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registationn");


            alert.setHeaderText("Student Registation");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            tableAnimal();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;



    public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/petsupplies","root","");
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //    public void Click(){
//        Exit.setOnMouseClicked(event -> {
//            System.exit(0);
//        });
//        formAnimal.setTranslateX(0);
//        tableAnimal.setTranslateX(0);
//
//
//        btnAnimal.setOnMouseClicked(event -> {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(formAnimal);
////            slide.setNode(tableAnimal);
//
//
//            slide.setToX(0);
//            slide.play();
//
//            formAnimal.setTranslateX(-600);
//            tableAnimal.setTranslateX(0);
//
//
//            slide.setOnFinished((ActionEvent e)-> {
//                btnAnimal.setVisible(false);
//                btnAnimalClose.setVisible(true);
//            });
//        });
//
//        btnAnimalClose.setOnMouseClicked(event -> {
//            TranslateTransition slide = new TranslateTransition();
//            slide.setDuration(Duration.seconds(0.4));
//            slide.setNode(formAnimal);
//            slide.setNode(tableAnimal);
////            slide.setToX(-600);
//            slide.play();
//
//            formAnimal.setTranslateX(-300);
//            tableAnimal.setTranslateX(-600);
//
//            slide.setOnFinished((ActionEvent e)-> {
//                btnAnimal.setVisible(true);
//                btnAnimalClose.setVisible(false);
//            });
//        });
//
//    }
    public void ClickPetSupplies(){
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        formPetSupplies.setTranslateX(0);
        tablePetSupplies.setTranslateX(0);
        btnAddSupplies.setTranslateX(0);
        btnUpdateSupplies.setTranslateX(0);
        btnDeleteSupplies.setTranslateX(0);




        btnPetSupplies.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(formPetSupplies);
//            slide.setNode(tableAnimal);


            slide.setToX(0);
            slide.play();

            formPetSupplies.setTranslateX(-600);
            tablePetSupplies.setTranslateX(0);


            slide.setOnFinished((ActionEvent e)-> {
                btnPetSupplies.setVisible(false);
                btnPetSuppliesClose.setVisible(true);
            });
        });

        btnPetSuppliesClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(formPetSupplies);
            slide.setNode(tablePetSupplies);
//            slide.setToX(-600);
            slide.play();

            formPetSupplies.setTranslateX(-300);
            tablePetSupplies.setTranslateX(-600);

            slide.setOnFinished((ActionEvent e)-> {
                btnPetSupplies.setVisible(true);
                btnPetSuppliesClose.setVisible(false);
            });
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Connect();
        tableAnimal();
        TablePetSupplies();
//        Click();
        ClickPetSupplies();



    }
}