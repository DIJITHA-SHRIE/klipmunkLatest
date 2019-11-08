package com.telcco.klipmunk;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.telcco.klipmunk.UtilConstants.USER_ID;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CollectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ArrayList<CollectionFolder> collectionResponseArrayList = new ArrayList<>();
    CollectionViewModel collectionViewModel;
    @BindView(R.id.collection_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.add_folder)
    FloatingActionButton add_folder;
    ColllectionAdapter adapter;
    ProgressBar progressBar;
    PopupWindow popupWindow;
    @BindView(R.id.collection_main)
    ConstraintLayout collection_main;


    public CollectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollectionFragment newInstance(String param1, String param2) {
        CollectionFragment fragment = new CollectionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_collection, container, false);
        ButterKnife.bind(this,view);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Collection </font>"));




        collectionViewModel = ViewModelProviders.of(this).get(CollectionViewModel.class);
        collectionViewModel.getCollectionRes(getArguments().getString(USER_ID));

        collectionViewModel.getCollection().observe(this, collectionResponse -> {
            ArrayList<CollectionFolder> collectionFolders =collectionResponse.getTopicThumbnail();
            collectionResponseArrayList.addAll(collectionFolders);
            setupRecyclerView();

        });

        collectionViewModel.getNewFolder().observe(this, newFolderResponse -> {
            String  folderSuccess =newFolderResponse.getFolderName();
            progressBar.setVisibility(View.GONE);
            Log.i("folderSuccess",folderSuccess);

            popupWindow.dismiss();
            Toast.makeText(getContext(),"Folder Created Successfully",Toast.LENGTH_LONG).show();

        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new ColllectionAdapter(collectionResponseArrayList,getActivity(),getArguments().getString(USER_ID));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.add_folder)
    public void onAddFolder(View view){
        createPopUp();

    }

    private void createPopUp() {
        LayoutInflater inflater =(LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        Animation slide = AnimationUtils.loadAnimation(getActivity(), R.anim.anim);
        View popUpView = inflater.inflate(R.layout.newfolder_lay,null);
        popupWindow = new PopupWindow(popUpView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
        popupWindow.showAtLocation(collection_main, Gravity.CENTER,0,0);
        popUpView.setAnimation(slide);

        EditText topic =(EditText)popUpView.findViewById(R.id.folder_topic);
        EditText description =(EditText)popUpView.findViewById(R.id.folder_description);
        Button create_folder =(Button)popUpView.findViewById(R.id.create_folder);
        progressBar =(ProgressBar)popUpView.findViewById(R.id.progress_newfolder);
        TextView newtopic = (TextView)popUpView.findViewById(R.id.newtopic_folder);

        newtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        create_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                HashMap<String,NewFolderRequest> newFolderhash = new HashMap<>();
                newFolderhash.put("newFolder",new NewFolderRequest(topic.getText().toString(),description.getText().toString()));
                collectionViewModel.getNewFolderRes(newFolderhash,getArguments().getString(USER_ID));

            }
        });



    }
}
