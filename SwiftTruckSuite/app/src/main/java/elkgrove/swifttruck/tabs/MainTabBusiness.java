package elkgrove.swifttruck.tabs;

/* Created by evolanddazly on 11/13/16. */

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import elkgrove.swifttruck.R;
import elkgrove.swifttruck.database.DataBaseLoader;
import elkgrove.swifttruck.database.LoginUser;

public class MainTabBusiness extends Fragment {
    private View rootView;
    private EditText mtbUserName;
    private EditText mtbPassWord;
    private Button mtbLogInBtn;
    private Button mtbSignUpBtn;
    private DataBaseLoader database;

    private static final String BUSINESS_NAME = "SwiftTruck";
    private static final String BUSINESS_TAG = "Business";

    /* Override for Fragment */

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.main_tab_business, container, false);

        database = new DataBaseLoader(this.getContext());
        //database.deleteAll();

        mtbUserName = (EditText) this.rootView.findViewById(R.id.username_et);
        mtbUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFieldsEffect();
            }
        });
        mtbPassWord = (EditText) this.rootView.findViewById(R.id.password_et);
        mtbPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFieldsEffect();
            }
        });

        mtbLogInBtn = (Button) this.rootView.findViewById(R.id.logInBtn);
        mtbLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

        mtbSignUpBtn = (Button) this.rootView.findViewById(R.id.signUpBtn);
        mtbSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        return rootView;
    }

    public void logIn(){

        if(database.isEmpty()){
            createBusinessRegistrationDialog();
            return;
        }

        String temp = "";
        Cursor cursor = database.getAllAccount();
        if(cursor.moveToFirst()){

            do {
                if(cursor.getString(cursor.getColumnIndex("USER")).equals(mtbUserName.getText().toString())
                    && cursor.getString(cursor.getColumnIndex("PASS")).equals(mtbPassWord.getText().toString())) {
                    temp = "verified";
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        this.createFieldsEffect();

        String empty = "";
        if(temp.equals("verified")){
            // TODO: tag online user when they log in
            LoginUser account = new LoginUser(
                    mtbUserName.getText().toString()
                    , mtbPassWord.getText().toString()
                    , true
            );
            database.updateAccount(account);

            /* transition to business account after log in*/
            /* generate fragment manager ; begin transaction */
            /* container id of replacee , replacer */
            /* preserve fragment so user can go back to it */
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mtb_relative_layout_1, new mtbBusinessAccountLoader());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if(empty.equals(mtbUserName.getText().toString())
                || empty.equals(mtbPassWord.getText().toString())) {
            /* fields are blank ; toggle effect */
            createFieldsEffect();
        } else {
            /* credentials are wrong */
            createFieldsEffect();
            createUsernameIncorrectDialog();
        }
    }

    public void signUp(){

        if(database.isEmpty()){
            createBusinessRegistrationDialog();
            return;
        }

        String user = "";
        String pass = "";
        Cursor cursor = database.getAllAccount();
        if(cursor.moveToFirst()){
            do {
                if(cursor.getString(cursor.getColumnIndex("USER")).equals(mtbUserName.getText().toString())){
                    user = cursor.getString(cursor.getColumnIndex("USER"));
                }
            } while(cursor.moveToNext());
        }
        cursor.close();

        String empty = "";
        if(user.equals(mtbUserName.getText().toString())
                        || user.equals(mtbPassWord.getText().toString())){
            /* user already exists */
            createFieldsEffect();
            createUsernameUnavailableDialog();
        } else if(empty.equals(mtbUserName.getText().toString())
                    || empty.equals(mtbPassWord.getText().toString())) {
            /* fields are blank ; toggle effect */
            createFieldsEffect();
        } else {
            /* add notifier for truck type */
            createBusinessRegistrationDialog();
        }
    }

    public Dialog createUsernameUnavailableDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.UsernameUnavailableTheme);
        /* view is inside fragment ; therefore , infalte, view, set */
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view = inflater.inflate(R.layout.alert_username_unavailable, null);
        builder.setView(view);

        /* view descriptors */
        builder.setTitle(BUSINESS_NAME);
        builder.setMessage(BUSINESS_TAG);

        builder.setNegativeButton(R.string.cancelinfo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return builder.show();
    }

    public Dialog createUsernameIncorrectDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CredentialsIncorrectTheme);
        /* view is inside fragment ; therefore , infalte, view, set */
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view = inflater.inflate(R.layout.alert_credentials_incorrect, null);
        builder.setView(view);

        /* view descriptors */
        builder.setTitle(BUSINESS_NAME);
        builder.setMessage(BUSINESS_TAG);

        builder.setNegativeButton(R.string.cancelinfo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return builder.show();
    }

    public Dialog createBusinessRegistrationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.SignUpBusinessTheme);

        /* view is inside fragment ; therefore , infalte, view, set */
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view = inflater.inflate(R.layout.alert_number_and_type, null);
        builder.setView(view);

        /* view descriptors */
        builder.setTitle(BUSINESS_NAME);
        builder.setMessage(BUSINESS_TAG);

        /* view is now the focus of attention */
        /* after this method ends, the dialog goes away, and our focus of attention changes */
        final EditText truckNumber = (EditText) view.findViewById(R.id.truck_number_et);
        final EditText truckType = (EditText) view.findViewById(R.id.truck_type_et);

        builder.setPositiveButton(R.string.addinfo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if(truckNumber.getText().toString().equals("")
                        || truckType.getText().toString().equals("")){
                    dialog.cancel();
                }

                LoginUser account = new LoginUser(
                        mtbUserName.getText().toString()
                        , mtbPassWord.getText().toString()
                        , false); //new user is always offline

                long row_id = database.addAccount(account);
                account.setId(row_id);
                database.getAccount(account.getId());
            }
        });

        builder.setNegativeButton(R.string.cancelinfo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return builder.show();
    }

    public void createFieldsEffect(){
        mtbUserName.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        mtbPassWord.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
    }

    public void removeFieldsEffect(){
        mtbUserName.getBackground().clearColorFilter();
        mtbPassWord.getBackground().clearColorFilter();
    }
}
