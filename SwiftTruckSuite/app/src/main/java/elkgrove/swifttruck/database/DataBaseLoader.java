package elkgrove.swifttruck.database;
/* Created by evolanddazly on 11/21/16 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseLoader {
    /* DATABASE KEYS */
    private static final String KEY_ID = "ID";
    private static final String KEY_USER = "USER";
    private static final String KEY_PASS = "PASS";
    private static final String KEY_ONLINE = "ONLINE";

    /* DATABASE ELEMENTS */
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "user_login.db";
    private static final String DB_TABLE = "user_info_table";

    /* DATABASE COMMANDS */
    private static final String DB_DROP = "DROP TABLE IF EXISTS ";
    private static final String DB_CREATE = "CREATE TABLE ";
    private static final String DB_SEL = "SELECT * FROM ";

    private Context ctx;
    private static DataBaseHelper dbHelper;
    private static SQLiteDatabase db;

    public DataBaseLoader(){
        /* default constructor */
    }

    public DataBaseLoader(Context context) {
        this.ctx = context;
        dbHelper = new DataBaseHelper(ctx);
        this.dbOpen(); //can't have db null
    }
    private DataBaseLoader dbOpen() throws  SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    /* Creating an account */
    public long addAccount(LoginUser info) {
        ContentValues values = new ContentValues();
        values.put(KEY_USER, info.getUserName());
        values.put(KEY_PASS, info.getPassWord());
        values.put(KEY_ONLINE, info.isOnline());

        return db.insert(DB_TABLE, null, values);
    }

    /* Read an account */
    public Cursor getAccount(long row_id) throws SQLException {
        Cursor cursor = db.query(true, DB_TABLE
                , new String[]{KEY_ID, KEY_USER, KEY_PASS, KEY_ONLINE}
                , KEY_ID + "=" + row_id, null, null, null, null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor getAllAccount() {
        return db.query(DB_TABLE, new String[]{KEY_ID, KEY_USER, KEY_PASS, KEY_ONLINE}
                , null, null, null, null, null);
    }

    /* Update an account */
    public boolean updateAccount(LoginUser account) {
        ContentValues values = new ContentValues();
        values.put(KEY_USER, account.getUserName());
        values.put(KEY_PASS, account.getPassWord());
        values.put(KEY_ONLINE, account.isOnline());

        return db.update(DB_TABLE, values, KEY_ID + "=" + account.getId(), null) > 0;
    }

    /* Delete an account */
    private boolean deleteAccount(long row_id) {
        return db.delete(DB_TABLE, KEY_ID + "=" + row_id, null) > 0;
    }

    /* Delete an entire table */
    /* dangerous method ; will require uninstall and re-install from emulator */
    public void deleteAll() {
        Cursor cursor = getAllRows();
        long row_id = cursor.getColumnIndexOrThrow(KEY_ID);
        db.delete(DB_TABLE, null, null); //erase all data
        if (cursor.moveToFirst()) {
            do {
                deleteAccount(cursor.getLong((int) row_id));
            } while (cursor.moveToNext());
        }

        db.execSQL(DB_DROP + DB_TABLE); //destroy the table
    }

    public SQLiteDatabase getDataBase(){
        return db;
    }

    private Cursor getAllRows() {
        String location = null;
        Cursor cursor = db.query(true, DB_TABLE
                , new String[]{KEY_ID, KEY_USER, KEY_PASS, KEY_ONLINE}
                , location, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void dbClose(){
        db.close();
    }

    public boolean isEmpty(){
        Cursor cursor = db.rawQuery(DB_SEL + DB_TABLE, null);
        return cursor.getCount() == 0;
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {
        DataBaseHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            /* DATABASE CREATE COMMAND */
            String SQL_CMD_CREATE_TABLE = DB_CREATE + DB_TABLE
                    + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_USER + " CHAR(10),"
                    + KEY_PASS + " CHAR(10),"
                    + KEY_ONLINE + " INTEGER"
                    + ");";
            db.execSQL(SQL_CMD_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int updateVersion) {
            db.execSQL(DB_DROP + DB_TABLE);
            onCreate(db);
        }
    }
}