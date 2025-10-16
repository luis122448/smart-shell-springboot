package luis122448.SmartShell.util.constant;

public class MESSAGEConstants {

    public static final String ERROR_UNKNOWN = "An unknown error has occurred";
    public static final String NO_DATA_FOUND = "Search returned no results";
    public static final String ID_NOT_EXISTS = "The identifier of this record does not exists";
    public static final String ID_EXISTS = "The Identifier of this record already exists";

    public static String ID_EXISTS(String id){
        return "Record with ID [ " +  id + " ] already exists and cannot be duplicated";
    }

    public static String ID_NOT_EXISTS(String id){
        return "Record with ID [ " +  id + " ] does not exists";
    }
}
