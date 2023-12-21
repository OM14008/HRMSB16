package Utils;

import org.json.JSONObject;

public class APIPayLoadConstants {
    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"Julie\",\n" +
                "  \"emp_lastname\": \"Hawk\",\n" +
                "  \"emp_middle_name\": \"M\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2002-07-23\",\n" +
                "  \"emp_status\": \"cry\",\n" +
                "  \"emp_job_title\": \"supervisor\"\n" +
                "}";
        return createEmployeePayload;
    }
    public static String createEmployeeJsonPayload(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","Julie");
        obj.put("emp_lastname","Hawk");
        obj.put("emp_middle_name","M");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","2000-07-23");
        obj.put("emp_status","cry");
        obj.put("emp_job_title","supervisor");
        return obj.toString();
    }
    public static String createEmployeeJsonPayloadDynamic
            (String fn, String ln, String mn, String gender,
             String dob, String status, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", fn);
        obj.put("emp_lastname", ln);
        obj.put("emp_middle_name", mn);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status",status);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();
    }


}
