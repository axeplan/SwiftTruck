package elkgrove.swifttruck.database;
/* Created by evolanddazly on 1/26/17 */

import android.database.sqlite.SQLiteDatabase;

public class DataBaseOnlineLoader implements OnlineIf {

    private boolean onlineStatus;
    private DataBaseLoader database;
    private SQLiteDatabase db;

    public DataBaseOnlineLoader(){
        /* read in database */
        this.database = new DataBaseLoader();
        this.db = database.getDataBase();
    }

    /* Override for OnLineIf */

    @Override
    public boolean isOnline() {
        /* find username in database , get online status */

        return false;
    }
}
