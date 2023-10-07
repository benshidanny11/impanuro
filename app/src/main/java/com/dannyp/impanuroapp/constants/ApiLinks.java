package com.dannyp.impanuroapp.constants;

import com.dannyp.impanuroapp.utils.DateUtils;

public class ApiLinks {

  private static String getRootApi(){
    return "http://abatozabingo.rw/";
  }
  private static final String ROOT_API="https://abatozabingo.rw";
  public static String GET_DATES=ROOT_API+"/app/getdatesapi.php";
  public static String GET_DATES_FOR_SINGLE=ROOT_API+"/app/getdatesforsingleapi.php";

  public static String GET_ANSWERS=ROOT_API+"/app/getibisubizoapi.php";

  public static String GET_GREATING=ROOT_API+"/app/getgreatingsapi.php";
  public static String GET_MEASURES=ROOT_API+"/app/getibipimoapi.php";
  public static String GET_I=ROOT_API+"/app/getgreatingsapi.php";

  public static String getImages(int entityId, String entityType){
    return ROOT_API+"/app/getimagesapi.php?etype="+entityType+"&eid="+entityId;
  }

  public static String getAdviceAdviceByMonth(int month, String clientId){
    return ROOT_API+"/app/getadvicebymonthapi.php/?month="+ DateUtils.getMonthForQuery(month)+"&userid="+clientId;
  }
  public static String getAdviceAdviceByMonthForSingle(int month, String clientId){
    return ROOT_API+"/app/getmonthbymonthforsingleapi.php/?month="+ DateUtils.getMonthForQuery(month)+"&userid="+clientId;
  }

//  public static String getAdviceAdviceByMonthForSingle(int month, String clientId){
//    return ROOT_API+"/app/getmonthbymonthforsingleapi.php/?month="+ DateUtils.getMonthForQuery(month)+"&userid="+clientId;
//  }

  public static String ROOT_IMAGE_LINK=getRootApi()+"images/";

  public static String PAYMENT_CREATION_URL="https://impanuropayment.herokuapp.com/api/createpayment";
  public static String PAYMENT_VERIFICATION_URL="https://impanuropayment.herokuapp.com/api/verifypayment?ref=";
  public static String PAYMENT_SAVE_DATA_URL=ROOT_API+"/app/createpayment.php";
  public static String USER_URL=ROOT_API+"/app/getuser.php?phonenumber=";
  public static String UPDATE_PAYMENT_API=ROOT_API+"/app/getuser.php?phonenumber=";
  public static String getUpdatePaymentAPI(String adviceId, String clientId, String status){
    return ROOT_API+"/app/updatepaymentstatus.php?clientid="+clientId+"&adviceid="+adviceId+"&status="+status;
  }
}