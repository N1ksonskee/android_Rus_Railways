package com.example.rgd_monitor.db;

public class DB_CONSTANTS {
    public static final String DB_NAME = "my_db.db";
    public static final String TABLE_NAME = "my_table";
    public static final int VERSION = 1;

    public static final String _ID = "_id";

    public static final String STATUS = "status";
    public static final String TICKED_ID = "tickedId";
    public static final String REPORTED_BY = "reportedBy";
    public static final String CLASS_ID_MAIN = "classIdMain";
    public static final String CRITICAL_LEVEL = "critical_lvl";
    public static final String IS_KNOWN_ERROR_DATE = "isKnownErrorDate";
    public static final String TARGET_FINISH = "targetFinish";
    public static final String DESCRIPTION = "description";
    public static final String EXT_SYS_NAME = "extSysName";
    public static final String NORM = "norm";
    public static final String LNORM = "lnorm";


    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    STATUS + " TEXT," +
                    TICKED_ID + " TEXT," +
                    REPORTED_BY + " TEXT," +
                    CLASS_ID_MAIN + " TEXT," +
                    CRITICAL_LEVEL + " INTEGER," +
                    IS_KNOWN_ERROR_DATE + " TEXT," +
                    TARGET_FINISH + " TEXT," +
                    DESCRIPTION + " TEXT," +
                    EXT_SYS_NAME + " TEXT," +
                    NORM + " REAL," +
                    LNORM + " INTEGER)";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}
