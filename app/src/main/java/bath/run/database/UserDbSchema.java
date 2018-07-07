package bath.run.database;

/**
 * Created by mradl on 07/07/2018.
 */

public class UserDbSchema {

    public static final class UserTable {
        //Set name of table
        public static final String NAME = "user";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE "
                        + NAME + "(week integer, "
                        + Cols.MONDAY + " boolean, "
                        + Cols.TUESDAY + " boolean, "
                        + Cols.WEDNESDAY + " boolean," +
                        "" + Cols.THURSDAY + " boolean, "
                        + Cols.FRIDAY + " boolean, "
                        + Cols.SATURDAY + " boolean, "
                        + Cols.SUNDAY + " boolean)";


        public static final class Cols {
            public static final String WEEK = "week";
            public static final String MONDAY = "monday";
            public static final String TUESDAY = "tuesday";
            public static final String WEDNESDAY = "wednesday";
            public static final String THURSDAY = "thursday";
            public static final String FRIDAY = "friday";
            public static final String SATURDAY = "saturday";
            public static final String SUNDAY = "sunday";
        }
    }
}
