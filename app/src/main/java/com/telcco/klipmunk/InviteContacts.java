package com.telcco.klipmunk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteContacts extends AppCompatActivity {

    public  static final int RequestPermissionCode  = 952019 ;
    Cursor cursor ;
    String name, phonenumber;
    ArrayList<InviteContMod> inviteContModArrayList;
    @BindView(R.id.contacts_recycle)
    RecyclerView contacts_recycle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_contacts);
        ButterKnife.bind(this);

        inviteContModArrayList = new ArrayList<>();

        EnableRuntimePermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case RequestPermissionCode:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    GetContactsIntoArrayList();
                }
                else{

                }
                break;
        }
    }

    private void GetContactsIntoArrayList() {
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            inviteContModArrayList.add(new InviteContMod(name,phonenumber));

        }



        cursor.close();
        Log.i("ContactsSize",inviteContModArrayList.size()+"");


        InviteContactAdapter adapter = new InviteContactAdapter(inviteContModArrayList,InviteContacts.this);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        contacts_recycle.setLayoutManager(lm);
        contacts_recycle.setAdapter(adapter);


    }

    private void EnableRuntimePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(InviteContacts.this, Manifest.permission.READ_CONTACTS)){

        }
        else{
            ActivityCompat.requestPermissions(InviteContacts.this,new String[]{Manifest.permission.READ_CONTACTS},RequestPermissionCode);
        }
    }
}
