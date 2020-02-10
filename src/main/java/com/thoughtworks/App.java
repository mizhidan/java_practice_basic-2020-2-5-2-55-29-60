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
    int saleStrategy = 0;
    int count = 0;
    double finalPrice = 0d;
    double discountPrice = 0d;
    for (String value : selectedInfo) {
      selectedIds[count] = value.split(" x ")[0];
      selectedCounts[count] = value.split(" x ")[1];
      ++count;
    }
    double originalPrice = 0d;
    originalPrice = getOriginalPrice(selectedIds,selectedCounts);
    double overThirtyPrice = 0d;
    if (originalPrice >= 30) {
      overThirtyPrice = originalPrice - 6;
    }
    double halfPrice = 0d;
    halfPrice = getHalfPrice(selectedIds,selectedCounts);
    if (originalPrice - halfPrice == 0) {
      if (overThirtyPrice != 0) {
        saleStrategy = 1;
      }
    } else if (halfPrice < overThirtyPrice) {
      saleStrategy = 2;
    }
    switch (saleStrategy) {
      case 0:
        finalPrice = originalPrice;
        discountPrice = 0d;
        break;
      case 1:
        finalPrice = overThirtyPrice;
        discountPrice = 6d;
        break;
      case 2:
        finalPrice = halfPrice;
        discountPrice = originalPrice - halfPrice;
        break;
      default:
        return "";
    }
    String menuInfo = getMenu(selectedIds,selectedCounts,finalPrice,discountPrice,saleStrategy);
    return menuInfo;
  }

  public static double getOriginalPrice(String[] selectedIds, String[] selectedCounts) {
    String[] allIds = getItemIds();
    String[] allNames = getItemNames();
    double[] allPrices = getItemPrices();
    double[] selectedPrices = new double[selectedIds.length];
    String[] selectedNames = new String[selectedIds.length];
    double originalPrice = 0d;
    int[] newSelectedCounts = getIntCounts(selectedCounts);
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

  public static double getHalfPrice(String[] selectedIds, String[] selectedCounts) {
    String[] allIds = getItemIds();
    String[] halfPriceIds = getHalfPriceIds();
    double[] selectedPrices = new double[selectedIds.length];
    double[] allPrices = getItemPrices();
    double halfPrice = 0d;
    for (int allIndex = 0; allIndex < allIds.length; ++allIndex) {
      for (int selectedIndex = 0; selectedIndex < selectedIds.length; ++selectedIndex) {
        if (selectedIds[selectedIndex].equals(allIds[allIndex])) {
          selectedPrices[selectedIndex] = allPrices[allIndex];
        }
      }
    }
    for (int allIndex = 0; allIndex < halfPriceIds.length; ++allIndex) {
      for (int selectedIndex = 0; selectedIndex < selectedIds.length; ++selectedIndex) {
        if (selectedIds[selectedIndex].equals(halfPriceIds[allIndex])) {
          selectedPrices[selectedIndex] *= 0.5;
        }
      }
    }
    for (double value : selectedPrices) {
      halfPrice += value;
    }
    return halfPrice;
  }

  public static String calcMenu(String[] selectedIds, String[] selectedCounts,double finalPrice,double discountPrice,
                                int saleStrategy) {
    String[] allIds = getItemIds();
    String[] allNames = getItemNames();
    String[] selectedNames = new String[selectedIds.length];
    double[] selectedPrices = new double[selectedIds.length];
    double[] allPrices = getItemPrices();
    int[] intSelectedCounts = getIntCounts(selectedCounts);
    String menuInfo = new String("");
    for (int allIndex = 0; allIndex < allIds.length; ++allIndex) {
      for (int selectedIndex = 0; selectedIndex < selectedIds.length; ++selectedIndex) {
        if (selectedIds[selectedIndex].equals(allIds[allIndex])) {
          selectedNames[selectedIndex] = allNames[allIndex];
        }
      }
    }
    for (int allIndex = 0; allIndex < allIds.length; ++allIndex) {
      for (int selectedIndex = 0; selectedIndex < selectedIds.length; ++selectedIndex) {
        if (selectedIds[selectedIndex].equals(allIds[allIndex])) {
          selectedPrices[selectedIndex] = allPrices[allIndex];
        }
      }
    }
    String selectedInfo = "";
    for (int index = 0; index < selectedNames.length; ++index) {
      selectedInfo += selectedNames[index] + " x " + selectedCounts[index] + "=" + intSelectedCounts[index] *
              selectedPrices[index] + "\n";
    }
    switch (saleStrategy) {
      case 0:
        menuInfo ="============= 订餐明细 =============\n"
                + "肉夹馍 x 4 = 24元\n"
                + "-----------------------------------\n"
                + "总计：24元\n"
                + "===================================";
    }
  }

  public static int[] getIntCounts(String[] prices) {
    int[] newSelectedCounts = new int[prices.length];
    for (int i = 0; i < prices.length; ++i) {
      newSelectedCounts[i] = Integer.parseInt(prices[i]);
    }
    return newSelectedCounts;
  }

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
