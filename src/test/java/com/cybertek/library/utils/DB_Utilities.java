package com.cybertek.library.utils;

import java.sql.*;
import java.util.*;

public class DB_Utilities {
  private static Connection con;
  private static Statement stm;
  private static ResultSet rs;
  private static ResultSetMetaData rsmd;

  private static String DB_url;
  private static String DB_userName;
  private static String DB_password;

  static {
    DB_url = ConfigurationReader.getProperty("library1.database.url");
    DB_userName = ConfigurationReader.getProperty("library1.database.username");
    DB_password = ConfigurationReader.getProperty("library1.database.password");
  }

  public static void createConnection() {
    try {
      con = DriverManager.getConnection(DB_url, DB_userName, DB_password);
      stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      System.out.println("DB connection successful!");
    } catch (SQLException e) {
      System.out.println("Connection has FAILED! " + e.getMessage());
    }
  }

  public static void runQuery(String sql) {
    try {
      rs = stm.executeQuery(sql);
      rsmd = rs.getMetaData();
    } catch (SQLException e) {
      System.out.println("Error occurred while running query " + e.getMessage());
    }
  }

  public static void closeConnection() {
    // we have to check if we have valid object first before closing the resource
    // because we can't close object that don't exist
    try {
      if (rs != null) rs.close();
      if (stm != null) stm.close();
      if (con != null) con.close();
      System.out.println("DB connection closed!");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  // find out row count
  public static int getRowCount() {
    int rowCount = 0;
    try {
      rs.last();
      rowCount = rs.getRow();
    } catch (SQLException e) {
      System.out.println("error occurred while getting row count " + e.getMessage());
    } finally {
      resetCursor();
    }
    return rowCount;
  }

  // find column count
  public static int getColumnCount() {
    int columnCount = 0;
    try {
      columnCount = rsmd.getColumnCount();
    } catch (SQLException e) {
      System.out.println("error occurred while getting column count " + e.getMessage());
    } finally {
      resetCursor();
    }
    return columnCount;
  }

  // get all column names into a list object
  public static List<String> getAllColumnNamesAsList() {
    List<String> columnNamesList = new ArrayList<>();
    int columnCount = getColumnCount();
    try {
      for (int col = 1; col <= columnCount; col++) {
        String columnName = rsmd.getColumnName(col);
        columnNamesList.add(columnName);
      }
    } catch (SQLException e) {
      System.out.println("error occurred while getAllColumnNamesAsList " + e.getMessage());
    }
    return columnNamesList;
  }

  // get entire row of data according to row number
  public static List<String> getRowDataAsListByRowNumber(int rowNumber) {
    List<String> rowDataList = new ArrayList<>();
    int colCount = getColumnCount();
    try {
      rs.absolute(rowNumber);
      for (int col = 1; col <= colCount; col++) {
        String cellValue = rs.getString(col);
        rowDataList.add(cellValue);
      }
    } catch (SQLException e) {
      System.out.println("error occurred while getRowDataAsListByRowNumber " + e.getMessage());
    } finally {
      resetCursor();
    }
    return rowDataList;
  }

  // get the cell value at certain row certain column number
  public static String getCellValue(int rowNum, int colIndex) {
    String cellValue = "";
    try {
      rs.absolute(rowNum);
      cellValue = rs.getString(colIndex);
    } catch (SQLException e) {
      System.out.println("error occurred while getCellValueByColumnAndRowNum " + e.getMessage());
    } finally {
      resetCursor();
    }
    return cellValue;
  }

  // get the cell value at certain row certain column name
  public static String getCellValue(int rowNum, String colName) {
    String cellValue = "";
    try {
      rs.absolute(rowNum);
      cellValue = rs.getString(colName);
    } catch (SQLException e) {
      System.out.println("error occurred while getCellValueByColumnAndRowNum " + e.getMessage());
    } finally {
      resetCursor();
    }
    return cellValue;
  }

  // get entire column data as list according to column number
  public static List<String> getColumnDataAsList(int colNumber) {
    List<String> columnValuesList = new ArrayList<>();
    try {
      rs.beforeFirst(); // make sure the cursor is at before first location
      while (rs.next()) {
        String cellValue = rs.getString(colNumber);
        columnValuesList.add(cellValue);
      }
    } catch (SQLException e) {
      System.out.println("error occurred while getColumnDataAsList " + e.getMessage());
    } finally {
      resetCursor();// reset the cursor to before first location
    }
    return columnValuesList;
  }

  // get entire column data as list according to column name
  public static List<String> getColumnDataAsList(String colName) {
    List<String> columnValuesList = new ArrayList<>();
    try {
      rs.beforeFirst(); // make sure the cursor is at before first location
      while (rs.next()) {
        String cellValue = rs.getString(colName);
        columnValuesList.add(cellValue);
      }
    } catch (SQLException e) {
      System.out.println("error occurred while getColumnDataAsList " + e.getMessage());
    } finally {
      resetCursor();
    }
    return columnValuesList;
  }

  // print entire data table
  public static void displayAllData() {
    try {
      rs.beforeFirst(); // make sure the cursor is at before first location
      int colCount = getColumnCount();
      while (rs.next()) {
        for (int col = 1; col <= colCount; col++) {
          String cellValue = rs.getString(col);
          System.out.print(cellValue + " | ");
        }
        System.out.println();
      }
      rs.beforeFirst(); // reset the cursor to before first location
    } catch (SQLException e) {
      System.out.println("error occurred while displayAllData" + e.getMessage());
    } finally {
      resetCursor();
    }
  }

  //reset cursor
  public static void resetCursor() {
    try {
      rs.beforeFirst(); // reset the cursor to before first location
    } catch (SQLException e) {
      System.out.println("error occurred while resetCursor" + e.getMessage());
    }
  }

  // get the row data along with column name as Map object
  public static Map<String, String> getRowMap(int rowNumber) {
    Map<String, String> rowMap = new LinkedHashMap<>();
    int colCount = getColumnCount();
    try {
      rs.absolute(rowNumber);
      for (int col = 1; col <= colCount; col++) {
        String colName = rsmd.getColumnName(col);
        String celValue = rs.getString(col);
        rowMap.put(colName, celValue);
      }
    } catch (SQLException e) {
      System.out.println("error occurred while getRowMap" + e.getMessage());
    } finally {
      resetCursor();
    }
    return rowMap;
  }

  // get all rows as List of Map data
  public static List<Map<String, String>> getAllRowsAsListOfMap() {
    List<Map<String, String>> allRowsListOfMap = new ArrayList<>();
    int rowCount = getRowCount();
    for (int row = 1; row <= rowCount; row++) {
      allRowsListOfMap.add(getRowMap(row));
    }
    return allRowsListOfMap;
  }

  public static void displayAllDataAsTable(String tableName) {
    try {
      int colCount = getColumnCount();
      int[] cellLength = new int[colCount];
      List<String> colNames = getAllColumnNamesAsList();
      String sql2 = "select ";
      for (int i=0; i<colNames.size(); i++) {
        sql2 += "max(length("+colNames.get(i)+"))"+(i!=colNames.size()-1?", ":"");
      }
      sql2 += "from "+tableName;
      Statement stm2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet rs2 = stm2.executeQuery(sql2);
      ResultSetMetaData rsmd2 = rs2.getMetaData();
      int colCount2 = rsmd2.getColumnCount();
      for (int col = 1, i=0; col <=colCount2 ; col++, i++) {
        rs2.first();
        cellLength[i] = Integer.parseInt(rs2.getString(col)) + 3;
      }
      stm2.close();
      rs2.close();
      System.out.println("cellLength = " + Arrays.toString(cellLength));
      stm2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      rs2 = stm2.executeQuery("select * from " + tableName);
      rs2.beforeFirst(); // make sure the cursor is at before first location
      while (rs2.next()) {
        for (int col = 1, i=0; col <= colCount; col++, i++) {
          String cellValue = rs2.getString(col);
          System.out.printf("%-"+cellLength[i]+"s", " | "+ cellValue);
        }
        System.out.println();
      }
      stm2.close();
      rs2.close();
    } catch (SQLException e) {
      System.out.println("error occurred while displayAllDataTable " + e.getMessage());
    }
  }

  // Get First Cell Value at First row First Column
  public static String getFirstRowFirstColumn(){
    return getCellValue(1,1);
  }
}
