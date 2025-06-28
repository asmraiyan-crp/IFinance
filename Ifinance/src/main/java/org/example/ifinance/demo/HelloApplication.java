package org.example.ifinance.demo;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.ifinance.demo.dao.*;
import org.example.ifinance.demo.db.DBConnection;
import org.example.ifinance.demo.model.Expence;
import org.example.ifinance.demo.model.Income;
import org.example.ifinance.demo.utils.Monthly_Smmery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {
    private double totalSavings = 0;
    private Label savingsLabel;
    private VBox transactionsContainer;
    private Connection dbConnection;
    private IncomeDAO incomeDAO;
    private ExpenceDAO expenceDAO;
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        DBConnection.initializeDatabase(); // create db and tables

        try {
            dbConnection = DBConnection.getConnection(); // connect to DB
            incomeDAO = new IncomeDAOImplement(dbConnection); // initialize DAO
            expenceDAO = new ExpenceDAOImplement(dbConnection); // initialize expense DAO
            Monthly_Smmery m = new Monthly_Smmery();
            m.monthlySummaryAndReset(dbConnection);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            SavingsDAO sa = new SavingsDAO(dbConnection);
            totalSavings = sa.calculateSavings();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Main Layout
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Top Panel
        HBox topPanel = createTopPanel();
        root.setTop(topPanel);

        // Center Tabs
        TabPane tabPane = createMainTabs();
        root.setCenter(tabPane);

        // Transaction List
        VBox transactionLog = createTransactionLog();
        root.setRight(transactionLog);

        primaryStage.setTitle("IFinance");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load transactions from DB
        loadTransactionsFromDB();

        // Initial update
        refreshSavingsLabel();
        updateTransactionLog();
    }

    private HBox createTopPanel() {
        HBox panel = new HBox(20);
        panel.setPadding(new Insets(15));
        panel.setStyle("-fx-background-color: #e8f5e9;");

        savingsLabel = new Label();
        savingsLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #2e7d32;");

        Label transactionLabel = new Label("Recent Transactions");
        transactionLabel.setStyle("-fx-font-weight: bold;");

        panel.getChildren().addAll(savingsLabel, transactionLabel);
        return panel;
    }

    private TabPane createMainTabs() {
        TabPane tabPane = new TabPane();

        // Income Tab
        Tab incomeTab = new Tab("Income");
        incomeTab.setContent(createIncomeForm());
        incomeTab.setClosable(false);

        // Expense Tab
        Tab expenseTab = new Tab("Expense");
        expenseTab.setContent(createExpenseForm());
        expenseTab.setClosable(false);

        // Monthly Report Tab
        Tab monthlyTab = new Tab("Month-by-Month");
        monthlyTab.setContent(createMonthlyReportForm());
        monthlyTab.setClosable(false);

        tabPane.getTabs().addAll(incomeTab, expenseTab, monthlyTab);
        return tabPane;
    }

    private VBox createIncomeForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));

        TextField amountField = new TextField();
        amountField.setPromptText("Enter income amount");

        // Use current date as default
        LocalDate defaultDate = LocalDate.now();

        Button submitBtn = new Button("Add Income");
        submitBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white;");

        Label statusLabel = new Label();

        submitBtn.setOnAction(e -> {
            String amtText = amountField.getText();
            if (amtText == null || amtText.isEmpty()) {
                statusLabel.setText("Please enter an amount.");
                return;
            }
            try {
                double amount = Double.parseDouble(amtText);
                String date = defaultDate.toString();

                Income i = new Income(amount, date);
                incomeDAO.addincome(i);

                // Add to transactions list
                // You might want to retrieve the inserted id here, but for simplicity, we reload later
                loadTransactionsFromDB();

                totalSavings += amount;

                statusLabel.setText("Income added successfully!");
                refreshSavingsLabel();
                updateTransactionLog();

                amountField.clear();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Please enter valid number for amount!");
            }
        });

        form.getChildren().addAll(
                new Label("Set amount of your income"),
                amountField,
                new Label("Month and Year: " + defaultDate.format(DateTimeFormatter.ofPattern("MMM yyyy"))),
                submitBtn,
                statusLabel
        );
        return form;
    }

    private VBox createExpenseForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));

        TextField amountField = new TextField();
        amountField.setPromptText("Enter expense amount");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll(
                "Education", "Transport", "Household",
                "Tour", "Refreshment", "Food", "Others"
        );
        categoryBox.setPromptText("Select category");

        //DatePicker datePicker = new DatePicker();
        //datePicker.setPromptText("Select date (only month/year used)");

        Button submitBtn = new Button("Add Expense");
        submitBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        Label statusLabel = new Label();

        submitBtn.setOnAction(e -> {
            String amtText = amountField.getText();
            String category = categoryBox.getValue();
            LocalDate dateValue = LocalDate.now();
            if (amtText == null || amtText.isEmpty()) {
                statusLabel.setText("Please enter an amount.");
                return;
            }
            if (category == null || category.isEmpty()) {
                statusLabel.setText("Please select a category.");
                return;
            }
//            if (dateValue == null) {
//                statusLabel.setText("Please select a date.");
//                return;
//            }
            try {
                double amount = Double.parseDouble(amtText);
                String date = dateValue.toString();

                Expence expence = new Expence();
                expence.setAmount(amount);
                expence.setDate(date);

                // Add expense using DAO by category
                if (category.equals("Education")) expenceDAO.addExpenceEducation(expence);
                else if (category.equals("Transport")) expenceDAO.addExpenceTransport(expence);
                else if (category.equals("Household")) expenceDAO.addExpenceHousehold(expence);
                else if (category.equals("Tour")) expenceDAO.addExpenceTour(expence);
                else if (category.equals("Refreshment")) expenceDAO.addExpenceRefreshment(expence);
                else if (category.equals("Food")) expenceDAO.addExpenceFood(expence);
                else expenceDAO.addExpenceOthers(expence);

                totalSavings -= amount;

                // Reload transactions after insert
                loadTransactionsFromDB();

                statusLabel.setText("Expense added successfully!");
                refreshSavingsLabel();
                updateTransactionLog();

                amountField.clear();
                categoryBox.setValue(null);
                //datePicker.setValue(null);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Please enter valid number for amount!");
            }
        });

        form.getChildren().addAll(
                new Label("Set amount of expense"),
                amountField,
                new Label("Select category"),
                categoryBox,
               // new Label("Set month and year"),
                //datePicker,
                submitBtn,
                statusLabel
        );
        return form;
    }


    private VBox createMonthlyReportForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));

        ComboBox<Integer> monthBox = new ComboBox<>();
        monthBox.setPromptText("Select Month");
        for (int i = 1; i <= 12; i++) monthBox.getItems().add(i);

        ComboBox<Integer> yearBox = new ComboBox<>();
        yearBox.setPromptText("Select Year");
        int currentYear = LocalDate.now().getYear();
        for (int y = currentYear - 5; y <= currentYear + 5; y++) yearBox.getItems().add(y);

        Button generateBtn = new Button("Generate Report");
        generateBtn.setStyle("-fx-background-color: #2196f3; -fx-text-fill: white;");

        // Table for per-category breakdown
        TableView<CategoryExpense> catTable = new TableView<>();
        catTable.setPrefHeight(200);
        TableColumn<CategoryExpense,String> colCat = new TableColumn<>("Category");
        colCat.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().category));
        colCat.setPrefWidth(150);
        TableColumn<CategoryExpense,Double> colAmt = new TableColumn<>("Amount");
        colAmt.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().amount));
        colAmt.setPrefWidth(150);
        catTable.getColumns().setAll(colCat, colAmt);

        VBox reportArea = new VBox(10);

        generateBtn.setOnAction(e -> {
            Integer month = monthBox.getValue();
            Integer year  = yearBox.getValue();
            if (month == null || year == null) {
                reportArea.getChildren().setAll(new Label("Please select both month and year"));
                return;
            }

            // 1) Overall totals via DAOs
            double income  = incomeDAO.getTotalIncomeByMonth(year, month);
            double expense = 0;
            for (String cat : new String[]{
                    "Education","Transport","Household","Tour","Refreshment","Food","Others"
            }) {
                expense += expenceDAO.getTotalExpenseByCategoryAndMonth(cat, year, month);
            }
            double savings = income - expense;

            // 2) Summary labels
            Label header    = new Label("Month: " + month + " " + year);
            Label incLabel  = new Label(String.format("Total Income: ৳%.2f", income));
            Label expLabel  = new Label(String.format("Total Expense: ৳%.2f", expense));
            Label saveLabel = new Label(String.format("Net Savings: ৳%.2f", savings));

            // 3) Populate per-category table
            ObservableList<CategoryExpense> catData = FXCollections.observableArrayList();
            for (String cat : new String[]{
                    "Education","Transport","Household","Tour","Refreshment","Food","Others"
            }) {
                double total = expenceDAO.getTotalExpenseByCategoryAndMonth(cat, year, month);
                catData.add(new CategoryExpense(cat, total));
            }
            catTable.setItems(catData);

            // 4) Render
            reportArea.getChildren().setAll(
                    header, incLabel, expLabel, saveLabel,
                    new Label("Breakdown by Category:"), catTable
            );
        });

        form.getChildren().addAll(
                new Label("Select Month"), monthBox,
                new Label("Select Year"),  yearBox,
                generateBtn, reportArea
        );
        return form;
    }

    private VBox createTransactionLog() {
        VBox log = new VBox(10);
        log.setPadding(new Insets(15));
        log.setStyle("-fx-background-color: #f5f5f5;");

        Label header = new Label("Transaction History");
        header.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        transactionsContainer = new VBox(8);
        ScrollPane scrollPane = new ScrollPane(transactionsContainer);
        scrollPane.setFitToWidth(true);

        log.getChildren().addAll(header, scrollPane);
        return log;
    }

    private void updateTransactionLog() {
        transactionsContainer.getChildren().clear();
        for (Transaction t : transactions) {
            HBox transactionBox = new HBox(10);
            transactionBox.setStyle("-fx-border-color: #ddd; -fx-padding: 8px;");

            Label typeLabel = new Label(t.type);
            Label amountLabel = new Label(String.format("৳%.2f", t.amount));
            Label dateLabel = new Label(t.date);

            Button deleteBtn = new Button("Delete");
            deleteBtn.setStyle("-fx-background-color: #9e9e9e; -fx-text-fill: white;");

            deleteBtn.setOnAction(e -> {
                // Confirm deletion dialog (optional)
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete this transaction?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        deleteTransactionFromDatabase(t);

                        if (t.amount > 0) totalSavings -= t.amount;
                        else totalSavings += Math.abs(t.amount);

                        transactions.remove(t);
                        refreshSavingsLabel();
                        updateTransactionLog();
                    }
                });
            });

            amountLabel.setTextFill(t.amount > 0 ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);

            transactionBox.getChildren().addAll(typeLabel, amountLabel, dateLabel, deleteBtn);
            transactionsContainer.getChildren().add(0, transactionBox);
        }
        refreshSavingsLabel();
    }

    private void deleteTransactionFromDatabase(Transaction t) {
        String sql = "DELETE FROM " + t.table + " WHERE id = ?";
        try (PreparedStatement st = dbConnection.prepareStatement(sql)) {
            st.setInt(1, t.id);
            st.executeUpdate();
            System.out.println("Deleted from database: " + t.table + ", id=" + t.id);
        } catch (SQLException e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
        }
    }

    private void refreshSavingsLabel() {
        savingsLabel.setText(String.format("Total Savings: ৳%.2f", totalSavings));
    }

    private void loadTransactionsFromDB() {
        transactions.clear();

        // Load income
        String incomeQuery = "SELECT id, amount, date FROM income ORDER BY date DESC";
        try (PreparedStatement ps = dbConnection.prepareStatement(incomeQuery);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        "Income",
                        rs.getDouble("amount"),
                        rs.getString("date"),
                        "income"
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error loading income transactions: " + e.getMessage());
        }

        // Load expenses from each expense table
        String[] expenseTables = {"education", "transport", "household", "tour", "refreshment", "food", "others"};
        for (String table : expenseTables) {
            String expenseQuery = "SELECT id, amount, date FROM " + table + " ORDER BY date DESC";
            try (PreparedStatement ps = dbConnection.prepareStatement(expenseQuery);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new Transaction(
                            rs.getInt("id"),
                            "Expense: " + capitalize(table),
                            rs.getDouble("amount"),
                            rs.getString("date"),
                            table
                    ));
                }
            } catch (SQLException e) {
                System.out.println("Error loading " + table + " expenses: " + e.getMessage());
            }
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Data Models
    static class Transaction {
        int id;
        String type;
        double amount;
        String date;
        String table;

        Transaction(int id, String type, double amount, String date, String table) {
            this.id = id;
            this.type = type;
            this.amount = amount;
            this.date = date;
            this.table = table;
        }
    }

    static class CategoryExpense {
        String category;
        double amount;

        CategoryExpense(String category, double amount) {
            this.category = category;
            this.amount = amount;
        }
    }
}
