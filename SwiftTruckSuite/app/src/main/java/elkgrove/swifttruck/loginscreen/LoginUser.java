package elkgrove.swifttruck.loginscreen;
/* Created by evolanddazly on 12/24/16 */

public class LoginUser {
    private long id;
    private String username;
    private String password;
    private boolean online;

    public LoginUser(){
        /* default constructor */
    }

    public LoginUser(String username, String password, boolean onlineStatus){
        this.username = username;
        this.password = password;
        this.online = onlineStatus;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setUserName(String un){
        this.username = un;
    }

    public String getUserName(){
        return this.username;
    }

    public void setPassWord(String pw){
        this.password = pw;
    }

    public String getPassWord(){
        return this.password;
    }

    public boolean isOnline(){
        return this.online;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", user=" + username + ", password=" + password + "]";
    }
}
