package com.telcco.klipmunk;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends android.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    @BindView(R.id.take_shots)
    Button take_shots_btn;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.capture_screen)
    Button capture_screen;
    private int PICK_IMAGE_REQUEST = 1;
    String imagepath;
    DataBaseHelper db;
    ArrayList<ScreensModel> getScreens = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    String getNotes;
    NewsAdapter adapter;
    ArrayList<ListItem> consolidatedList;
    private static final int REQUEST_MEDIA_PROJECTION = 1000;

    public static Intent dataG;

    public NewsFragment() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            Log.e("uri",""+selectedImageUri);
            if (selectedImageUri.getHost().contains("com.android.providers.media")) {
                @SuppressLint({"NewApi", "LocalSuppress"}) String wholeID = DocumentsContract.getDocumentId(selectedImageUri);
                // Split at colon, use second item in the array
                String id = wholeID.split(":")[1];

                String[] column = {MediaStore.Images.Media.DATA};

                // where id is equal to
                String sel = MediaStore.Images.Media._ID + "=?";

                try
                {
                    Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            column, sel, new String[]{id}, null);
                    int columnIndex = cursor.getColumnIndex(column[0]);

                    if (cursor.moveToFirst()) {
                        imagepath = cursor.getString(columnIndex);
                        showNotesDialog();
                        Log.e("imagepath1",imagepath);

                    }
                    cursor.close();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }


            }else
            {
                imagepath =getPath(selectedImageUri);
                showNotesDialog();
                Log.e("imagepath2",imagepath);
            }







        }
        if(requestCode == REQUEST_MEDIA_PROJECTION){
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O || Settings.canDrawOverlays(getActivity())) {
                dataG = data;
                Intent in = new  Intent(getActivity(), FloatingWidgetService.class);
                in.putExtra("activity_background", true);
                in.putExtra("resultCode",resultCode);
                getActivity().startService(in);
                getActivity().finish();
            } else {
                errorToast();
            }

        }

        else if (resultCode == RESULT_CANCELED) {

            // user cancelled Image capture
            Toast.makeText(getActivity(),
                    "User cancelled image capture", Toast.LENGTH_SHORT)
                    .show();

        } else {
            // failed to capture image
            Toast.makeText(getActivity(),
                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this,view);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.title_toolbar);
        title.setText(mParam1);
        db = new DataBaseHelper(getActivity());
        getScreens= db.getScreenShotsPath(mParam1);

        consolidatedList= new ArrayList<>();


        HashMap<String, ArrayList<ScreensModel>> groupedHashMap = groupDataIntoHashMap(getScreens);


        for (String date : groupedHashMap.keySet()) {
            DateItem dateItem = new DateItem();
            dateItem.setDate(date);
            consolidatedList.add(dateItem);


            for (ScreensModel pojoOfJsonArray : groupedHashMap.get(date)) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.setScreensModel(pojoOfJsonArray);//setBookingDataTabs(bookingDataTabs);
                consolidatedList.add(generalItem);
            }
        }


        adapter = new NewsAdapter(getActivity(), consolidatedList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (getScreens.size()>0) {
                    filter(editable.toString());
                }

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @OnClick(R.id.take_shots)
    public void onShots(){
        GetImage();
    }
    private void GetImage() {
        List<String> getImageType = new ArrayList<>();

        getImageType.add("Gallery");
        final CharSequence[] ImageTypes = getImageType.toArray(new String[getImageType.size()]);
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        dialogBuilder.setItems(ImageTypes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = ImageTypes[item].toString();

                if(selectedText.equals("Gallery"))
                {

                    showFileChooser();
                }



            }
        });
        androidx.appcompat.app.AlertDialog alertDialogObject = dialogBuilder.create();
        alertDialogObject.show();
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public String getPath(Uri uri) {
        Cursor cursor = null;
        try {

            if ("content".equals(uri.getScheme())) {
                String[] projection = {MediaStore.Images.Media.DATA};
                cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            } else {
                return uri.getPath();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    private void showNotesDialog() {
        LayoutInflater li = LayoutInflater.from(getActivity());
        View view = li.inflate(R.layout.tag_dialog,null);
        TextView addnote =(TextView)view.findViewById(R.id.title_dialog);
        addnote.setText("Add Notes");
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(getActivity());
        alertdialogBuilder.setView(view);
        final EditText tag_edit = (EditText)view.findViewById(R.id.tag_edit);
        tag_edit.setHint("Enter Notes ");
        alertdialogBuilder.setCancelable(false);
        alertdialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(tag_edit.getText().toString().length()>0)
                {
                   getNotes=tag_edit.getText().toString();
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    String sdf_date = df.format(c);
                    db.insertPath(imagepath,mParam1,getNotes,sdf_date);
                    getScreens= db.getScreenShotsPath(mParam1);

                    consolidatedList= new ArrayList<>();


                    HashMap<String, ArrayList<ScreensModel>> groupedHashMap = groupDataIntoHashMap(getScreens);


                    for (String date : groupedHashMap.keySet()) {
                        DateItem dateItem = new DateItem();
                        dateItem.setDate(date);
                        consolidatedList.add(dateItem);


                        for (ScreensModel pojoOfJsonArray : groupedHashMap.get(date)) {
                            GeneralItem generalItem = new GeneralItem();
                            generalItem.setScreensModel(pojoOfJsonArray);//setBookingDataTabs(bookingDataTabs);
                            consolidatedList.add(generalItem);
                        }
                    }


                    adapter = new NewsAdapter(getActivity(), consolidatedList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
                else{
                    tag_edit.setError("Enter Notes");
                }



            }
        });
       /* alertdialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
              dialog.dismiss();
            }
        });*/
        AlertDialog alertDialog = alertdialogBuilder.create();
        alertDialog.show();
    }

    void filter(String text) {
        Log.i("textFilter",text);
        ArrayList<ListItem> temp = new ArrayList();
        for (ScreensModel d : getScreens ) {
            if(d.getNotes()!=null){
            if (d.getNotes().toLowerCase().contains(text.toLowerCase()) ) {
                GeneralItem generalItem = new GeneralItem();
                generalItem.setScreensModel(d);
                temp.add(generalItem);
            }
            else{

            }
            }
        }

        if(temp.size()>0){
       adapter.updateList(temp);
            }


    }

    private HashMap<String, ArrayList<ScreensModel>> groupDataIntoHashMap(ArrayList<ScreensModel> listOfPojosOfJsonArray) {

        HashMap<String, ArrayList<ScreensModel>> groupedHashMap = new HashMap<>();

        for (ScreensModel pojoOfJsonArray : listOfPojosOfJsonArray) {

            String hashMapKey = pojoOfJsonArray.getGroupDate();

            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedHashMap.get(hashMapKey).add(pojoOfJsonArray);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                ArrayList<ScreensModel> list = new ArrayList<>();
                list.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey, list);
            }
        }


        return groupedHashMap;
    }

    @OnClick(R.id.capture_screen)
    public void onCaptureScreen(){
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager)
                getActivity().getSystemService(Context.MEDIA_PROJECTION_SERVICE);

        startActivityForResult(
                mediaProjectionManager.createScreenCaptureIntent(),
                REQUEST_MEDIA_PROJECTION);
    }

    private void errorToast() {
        Toast.makeText(getActivity(), "Draw over other app permission not available. Can't start the application without the permission.", Toast.LENGTH_LONG).show();
    }


}
