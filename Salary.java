

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author PAUL BRYAN
 */
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

public class Salary extends javax.swing.JFrame {

    /**
     * Creates new form Salary
     */
    public Salary() {
        initComponents();
        try {
            Connection();
            populateComboBoxes();
            loadTableData();
            restrictDateChooser();
           
        } catch (SQLException ex) {
            Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       Connection con;
    PreparedStatement pst;
    private final static String DbName = "FINAL"; // Updated database name
    private final static String DbDriver = "com.mysql.cj.jdbc.Driver";
    private final static String DbUrl = "jdbc:mysql://localhost:3306/" + DbName;
    private final static String DbUsername = "root";
    private final static String DbPassword = "";
    
  public void Connection() throws SQLException {
        try {
            Class.forName(DbDriver);
            con = DriverManager.getConnection(DbUrl, DbUsername, DbPassword);
            if (con != null) {
                System.out.println("Connection Successful");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(payroll.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "JDBC Driver not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(BIYAHE.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
private void populateComboBoxes() {
    try {
        // Combine data from the "details" table
        String detailsQuery = "SELECT Drivername, Placeoftrip, Rates, Allowance FROM `details]`";
        PreparedStatement detailsPst = con.prepareStatement(detailsQuery);
        ResultSet detailsRs = detailsPst.executeQuery();
        jComboBox1.removeAllItems();
        while (detailsRs.next()) {
            String driverName = detailsRs.getString("Drivername");
            String placeOfTrip = detailsRs.getString("Placeoftrip");
            String rates = detailsRs.getString("Rates");
            String allowance = detailsRs.getString("Allowance");
            String combinedData = driverName + "                         " + placeOfTrip + "                                       " + rates + "                                             " + allowance;
            jComboBox1.addItem(combinedData);
        }

        // Populate jComboBox3 with data from the "cashadvance" table
        String cashAdvanceQuery = "SELECT DRIVERNAME, ADVANCE, Date FROM cashadvance";
        PreparedStatement cashAdvancePst = con.prepareStatement(cashAdvanceQuery);
        ResultSet cashAdvanceRs = cashAdvancePst.executeQuery();
        jComboBox3.removeAllItems();
        while (cashAdvanceRs.next()) {
            String driverName = cashAdvanceRs.getString("DRIVERNAME");
            String cashAdvance = cashAdvanceRs.getString("ADVANCE");
            Date date = cashAdvanceRs.getDate("DATE");
            String combinedData = driverName + "   " + cashAdvance + "   " + date;
            jComboBox3.addItem(combinedData);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



  private void restrictDateChooser() {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Set the minimum selectable date to the current date
        jDateChooser1.setMinSelectableDate(currentDate);

        // Set the maximum selectable date to December 31, 2025
        Calendar maxCalendar = Calendar.getInstance();
        maxCalendar.set(2025, Calendar.DECEMBER, 31);
        jDateChooser1.setMaxSelectableDate(maxCalendar.getTime());
    }
 private void loadTableData() {
    try {
        String query = "SELECT Drivername, Finishedtrip, Rates, Allowance, Fuelconsumption, Driversalary, Comapanymoney, Date FROM salary";
        PreparedStatement pst = con.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        while (rs.next()) {
            String driverName = rs.getString("Drivername");
            String placeOfTrip = rs.getString("Finishedtrip");
            String rates = rs.getString("Rates");
            String distance = rs.getString("Allowance");
            String allowance = rs.getString("Fuelconsumption");
            String salary = rs.getString("Driversalary");
            String cmoney = rs.getString("Comapanymoney");

            // Retrieve the date as a string and then parse it into a java.sql.Date object
            String dateString = rs.getString("Date");
            if (dateString != null && !dateString.isEmpty()) {
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                model.addRow(new Object[]{driverName, placeOfTrip, rates, distance, allowance, salary, cmoney, sqlDate});
            } else {
                // Handle the case where the date string is empty
                // You can skip adding this row to the table or handle it differently based on your requirements
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(BIYAHE.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Failed to load data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (ParseException ex) {
        Logger.getLogger(BIYAHE.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Failed to parse date from the database.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FUEl = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Done = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        Delete = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        ADD = new javax.swing.JButton();
        ADDALLDRIVERSALARY = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        COMAPNYMONEY = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("5 - BROTHER'S PAYROLL");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DirverName", "Finished Trip", "Rates", "Allowance", "Fuel Consumption", "Driver salary", "Company Income", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        jLabel1.setText("Driver Name                     Finished trip");

        jLabel2.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        jLabel2.setText("Rates                                        Allowance                                      Date ");

        jLabel4.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        jLabel4.setText("Fuel Consumption");

        Done.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        Done.setText("ADD TO TABLE");
        Done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoneActionPerformed(evt);
            }
        });

        Delete.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        jLabel7.setText("Cash Advance List");

        ADD.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        ADD.setText("ADD ALL");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });

        ADDALLDRIVERSALARY.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        ADDALLDRIVERSALARY.setText(" See Drivers Salary");
        ADDALLDRIVERSALARY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDALLDRIVERSALARYActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        jLabel8.setText("Driver name");

        jLabel9.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        jLabel9.setText("Cash Advance");

        COMAPNYMONEY.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        COMAPNYMONEY.setText(" COMPANY INCOME");
        COMAPNYMONEY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMAPNYMONEYActionPerformed(evt);
            }
        });

        Exit.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        Exit.setText("EXIT");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("STXihei", 1, 14)); // NOI18N
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(COMAPNYMONEY))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FUEl, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(Done))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(ADDALLDRIVERSALARY)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 986, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(146, 146, 146)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(247, 247, 247)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)))))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FUEl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Done)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addComponent(ADDALLDRIVERSALARY)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ADD)
                            .addComponent(COMAPNYMONEY))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Delete)
                            .addComponent(Exit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1420, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoneActionPerformed
  try {
        // Check if date is selected
        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select a date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate fuel consumption input
        String fuelConsumptionStr = FUEl.getText();
        if (fuelConsumptionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fuel consumption field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!fuelConsumptionStr.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for fuel consumption.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double fuelConsumption = Double.parseDouble(fuelConsumptionStr);

        // Check if fuel consumption is greater than 0
        if (fuelConsumption <= 0) {
            JOptionPane.showMessageDialog(this, "Fuel consumption must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve and validate combined data from the single JComboBox
        String combinedData = (String) jComboBox1.getSelectedItem();
        if (combinedData == null) {
            JOptionPane.showMessageDialog(this, "Please select a driver and trip details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Split the combined data
        String[] dataParts = combinedData.split("\\s{3,}"); // split by 3 or more spaces to handle variable spacing

        if (dataParts.length < 4) {
            JOptionPane.showMessageDialog(this, "Invalid data format in the combo box.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String driverName = dataParts[0];
        String  Finishedtrip = dataParts[1];
        double rates = Double.parseDouble(dataParts[2]);
        double allowance = Double.parseDouble(dataParts[3]);

        // Validate rates and allowance
        if (rates <= 1000 || allowance <= 1000) {
            JOptionPane.showMessageDialog(this, "Rates and allowance must be greater than 1000.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Debugging print statements
        System.out.println("Driver Name: " + driverName);
        System.out.println("Place of Trip: " +  Finishedtrip);
        System.out.println("Rates: " + rates);
        System.out.println("Allowance: " + allowance);
        System.out.println("Fuel Consumption: " + fuelConsumption);

        // Calculate DriverSalary and CompanyMoney
        double driverSalary = rates * 0.1;
        double companyMoney = rates - (fuelConsumption + allowance + driverSalary);

        // Debugging print statements for calculations
        System.out.println("Driver Salary (10% of Rates): " + driverSalary);
        System.out.println("Company Money: " + companyMoney);

        // Convert date to SQL date
        Date date = jDateChooser1.getDate();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        // Insert data into database
        String query = "INSERT INTO salary (Drivername, Finishedtrip, Rates, Allowance, Fuelconsumption, Driversalary, Comapanymoney, Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, driverName);
        pst.setString(2, Finishedtrip);
        pst.setDouble(3, rates);
        pst.setDouble(4, allowance);
        pst.setDouble(5, fuelConsumption);
        pst.setDouble(6, driverSalary);
        pst.setDouble(7, companyMoney);
        pst.setDate(8, sqlDate);
        pst.executeUpdate();

        // Update the table
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{driverName,  Finishedtrip, rates, allowance, fuelConsumption, driverSalary, companyMoney, sqlDate});
     
        // Show success message
        JOptionPane.showMessageDialog(this, "Data saved successfully.");
       FUEl.setText("");
        jDateChooser1.setDate(null);
    } catch (SQLException ex) {
        Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Failed to save data.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid numbers for rates, allowance, and fuel consumption.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_DoneActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
      // Get the selected row index
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Ask for confirmation
    int confirmDialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this data?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if (confirmDialogResult != JOptionPane.YES_OPTION) {
        return;
    }

    // Get the values from the selected row
    String driverName = jTable1.getValueAt(selectedRow, 0).toString();
    String finishedTrip = jTable1.getValueAt(selectedRow, 1).toString();
    java.sql.Date date = java.sql.Date.valueOf(jTable1.getValueAt(selectedRow, 7).toString());

    try {
        // Construct the DELETE query
        String deleteQuery = "DELETE FROM salary WHERE Drivername = ? AND Finishedtrip = ? AND Date = ?";
        pst = con.prepareStatement(deleteQuery);
        pst.setString(1, driverName);
        pst.setString(2, finishedTrip);
        pst.setDate(3, date);
        
        // Execute the DELETE query
        int rowsAffected = pst.executeUpdate();
        
        if (rowsAffected > 0) {
            // Remove the selected row from the table
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Data deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Failed to delete data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_DeleteActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
                                  
    // Get the selected rows from the table
    int[] selectedRows = jTable1.getSelectedRows();
    
    if (selectedRows.length == 0) {
        JOptionPane.showMessageDialog(this, "Please select rows to sum up the driver salaries.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    double totalSalary = 0.0;
    
    // Iterate through the selected rows and sum up the driver salaries
    for (int rowIndex : selectedRows) {
        double driverSalary = Double.parseDouble(jTable1.getValueAt(rowIndex, 5).toString());
        totalSalary += driverSalary;
    }
    
    // Display the total salary in a message dialog
    JOptionPane.showMessageDialog(this, "Total Driver Salary: " + totalSalary);
    }//GEN-LAST:event_ADDActionPerformed

    private void ADDALLDRIVERSALARYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDALLDRIVERSALARYActionPerformed
String driverName = jTextField2.getText().trim();
    String cashAdvanceText = jTextField3.getText().trim();
    double cashAdvance = 0.0;

    if (driverName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a driver name.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    try {
        // If cash advance is provided, parse it
        if (!cashAdvanceText.isEmpty()) {
            try {
                cashAdvance = Double.parseDouble(cashAdvanceText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid cash advance amount.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Prepare the SQL query to fetch driver salaries based on the entered driver name
        String querySalary = "SELECT Driversalary FROM salary WHERE Drivername = ?";
        pst = con.prepareStatement(querySalary);
        pst.setString(1, driverName);
        
        // Execute the query
        ResultSet rsSalary = pst.executeQuery();
        
        // Calculate the total salary for the driver
        double totalSalary = 0.0;
        boolean driverFound = false;
        
        while (rsSalary.next()) {
            double driverSalary = rsSalary.getDouble("Driversalary");
            totalSalary += driverSalary;
            driverFound = true;
        }
        
        if (!driverFound) {
            JOptionPane.showMessageDialog(this, "Driver name not found in the salary database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Fetch the cash advance if applicable
        double totalAdvance = 0.0;
        if (cashAdvanceText.isEmpty()) {
            String queryAdvance = "SELECT ADVANCE FROM cashadvance WHERE Drivername = ?";
            pst = con.prepareStatement(queryAdvance);
            pst.setString(1, driverName);
            ResultSet rsAdvance = pst.executeQuery();
            
            while (rsAdvance.next()) {
                totalAdvance += rsAdvance.getDouble("ADVANCE");
            }
        } else {
            totalAdvance = cashAdvance;
        }

        // Subtract the cash advance from the total salary
        double adjustedSalary = totalSalary - totalAdvance;
        
        // Show the adjusted salary in a message dialog
        JOptionPane.showMessageDialog(this, "Total salary for " + driverName + ": " + adjustedSalary);
    } catch (SQLException ex) {
        Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Failed to fetch data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_ADDALLDRIVERSALARYActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void COMAPNYMONEYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMAPNYMONEYActionPerformed
try {
        // Prepare the SQL query to fetch the sum of Companymoney from the salary table
        String query = "SELECT SUM(Comapanymoney) AS TotalCompanyMoney FROM salary";
        pst = con.prepareStatement(query);
        
        // Execute the query
        ResultSet rs = pst.executeQuery();
        
        // Check if there are any results
        if (rs.next()) {
            // Retrieve the total sum of Companymoney
            double totalCompanyMoney = rs.getDouble("TotalCompanyMoney");
            
            // Display the total sum in a message dialog
            JOptionPane.showMessageDialog(this, "Total Company Income: " + totalCompanyMoney);
        } else {
            // No records found in the table
            JOptionPane.showMessageDialog(this, "No records found in the salary table.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        // Error occurred while executing the SQL query
        Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Failed to fetch total Company Money from the database.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_COMAPNYMONEYActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       SystemMenu menu = new SystemMenu();
       menu.setVisible(true);
       dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
       int response = JOptionPane.showConfirmDialog(null, "Do you really want to Exit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
        dispose();
    }
    }//GEN-LAST:event_ExitActionPerformed




    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Salary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Salary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JButton ADDALLDRIVERSALARY;
    private javax.swing.JButton COMAPNYMONEY;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Done;
    private javax.swing.JButton Exit;
    private javax.swing.JTextField FUEl;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
