package com.thoughtworks;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("请点餐（菜品Id x 数量，用逗号隔开）：");
    String selectedItems = scan.nextLine();
    String summary = bestCharge(selectedItems);
    System.out.println(summary);
  }

  public static String bestCharge(String selectedItems) {
    String[] selectedInfo = selectedItems.split(",");
    String[] selectedIds = new String[selectedInfo.length];
    String[] selectedCounts = new String[selectedInfo.length];
    double originalPrice = 0d;
    double overThirtyPrice = 0d;
    int count = 0;
    for (String value : selectedInfo) {
      selectedIds[count] = value.split(" x ")[0];
      selectedCounts[count] = value.split(" x ")[1];
      ++count;
    }
    originalPrice = getOriginalPrice(selectedIds,selectedCounts);
    if ()
    String menuInfo = calcMenu(selectedIds,selectedCounts);
    return menuInfo;
  }

  public static double getOriginalPrice(String[] selectedIds, String[] selectedCounts) {
    String[] allIds = getItemIds();
    String[] allNames = getItemNames();
    double[] allPrices = getItemPrices();
    double[] selectedPrices = new double[selectedIds.length];
    String[] selectedNames = new String[selectedIds.length];
    double originalPrice = 0d;
    int[] newSelectedCounts = getIntPrices(selectedCounts);
    for (int allIndex = 0; allIndex < allIds.length; ++allIndex) {
      for (int selectedIndex = 0; selectedIndex < selectedIds.length; ++selectedIndex) {
        if (selectedIds[selectedIndex].equals(allIds[allIndex])) {
          selectedPrices[selectedIndex] = allPrices[allIndex];
          selectedNames[selectedIndex] = allNames[allIndex];
        }
      }
    }
    for (int index = 0; index < selectedIds.length; ++index) {
      originalPrice += selectedPrices[index] * newSelectedCounts[index];
    }
    return originalPrice;
  }

//  public static String calcMenu(String[] selectedIds, String[] selectedCounts) {
//    String[] allIds = getItemIds();
//    String[] allNames = getItemNames();
//    double[] allPrices = getItemPrices();
//    String[] halfPriceIds = getHalfPriceIds();
//    double[] selectedPrices = new double[selectedIds.length];
//    String[] selectedNames = new String[selectedIds.length];
//    double originalPrice = 0d;
//    double overThirtyPrice = 0d;
//    int[] newSelectedCounts = getIntPrices(selectedCounts);
//    for (int allIndex = 0; allIndex < allIds.length; ++allIndex) {
//      for (int selectedIndex = 0; selectedIndex < selectedIds.length; ++selectedIndex) {
//        if (selectedIds[selectedIndex].equals(allIds[allIndex])) {
//          selectedPrices[selectedIndex] = allPrices[allIndex];
//          selectedNames[selectedIndex] = allNames[allIndex];
//        }
//      }
//    }
//    for (int index = 0; index < selectedIds.length; ++index) {
//      originalPrice += selectedPrices[index] * newSelectedCounts[index];
//    }
//    if (originalPrice >= 30) {
//      overThirtyPrice = originalPrice - 6;
//    }
//    for (int allIndex = 0; allIndex < halfPriceIds.length; ++allIndex) {
//      for (int selectedIndex = 0; selectedIndex < selectedIds.length; ++selectedIndex) {
//        if (selectedIds[selectedIndex].equals(halfPriceIds[allIndex])) {
//          selectedPrices[selectedIndex] *= 0.5;
//        }
//      }
//    }
//
//    return "";
//  }

//  public static int[] getIntPrices(String[] prices) {
//    int[] newSelectedCounts = new int[prices.length];
//    for (int i = 0; i < prices.length; ++i) {
//      newSelectedCounts[i] = Integer.parseInt(prices[i]);
//    }
//    return newSelectedCounts;
//  }

  public static String[] getItemIds() {
    return new String[]{"ITEM0001", "ITEM0013", "ITEM0022", "ITEM0030"};
  }


  public static String[] getItemNames() {
    return new String[]{"黄焖鸡", "肉夹馍", "凉皮", "冰粉"};
  }

  public static double[] getItemPrices() {
    return new double[]{18.00, 6.00, 8.00, 2.00};
  }

  public static String[] getHalfPriceIds() {
    return new String[]{"ITEM0001", "ITEM0022"};
  }
}
