package com.dannyp.impanuroapp.constants;

import com.dannyp.impanuroapp.utils.DateUtils;

public class ApiLinks {

  private static String getRootApi(){
    return "https://abatozabingo.rw/";
  }
  public static String GET_DATES="https://abatozabingo.rw/app/getdatesapi.php";
  public static String GET_DATES_FOR_SINGLE="https://abatozabingo.rw/app/getdatesforsingleapi.php";

  public static String getAdviceAdviceByMonth(int month){
    return "https://abatozabingo.rw/app/getadvicebymonthapi.php/?month="+ DateUtils.getMonthForQuery(month);
  }
  public static String getAdviceAdviceByMonthForSingle(int month){
    return "https://abatozabingo.rw/app/getmonthbymonthforsingleapi.php/?month="+ DateUtils.getMonthForQuery(month);
  }

  public static String ROOT_IMAGE_LINK=getRootApi()+"images/";

}