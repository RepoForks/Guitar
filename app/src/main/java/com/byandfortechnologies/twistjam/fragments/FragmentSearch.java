package com.byandfortechnologies.twistjam.fragments;


import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.byandfortechnologies.twistjam.R;
import com.byandfortechnologies.twistjam.adapters.SongListAdapter;
import com.byandfortechnologies.twistjam.helper.Song;
import com.byandfortechnologies.twistjam.services.MusicService;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSearch extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button mBtnImport;
    private ListView mListSongs;
    private LinearLayout mLinearListImportedFiles;
    private RelativeLayout mRelativeBtnImport;
    private SongListAdapter mAdapterListFile;
    private String[] STAR = {"*"};
    private ArrayList<Song> mSongList;
    private MusicService serviceMusic;
    private Intent playIntent;
    Context context;

    // TODO: Rename and change types of parameters
    private ArrayList<Song> mParam1;
    private String mParam2;


    public FragmentSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSearch.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentSearch newInstance(ArrayList<Song> param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ArrayList<Song>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mSongList = mParam1;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_display_songs, container, false);
        // Inflate the layout for this fragment

        mBtnImport = (Button) view.findViewById(R.id.btn_import_files);
        mBtnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLinearListImportedFiles = (LinearLayout) view.findViewById(R.id.linear_list_imported_files);
        mRelativeBtnImport = (RelativeLayout) view.findViewById(R.id.relative_btn_import);
        mListSongs = (ListView) view.findViewById(R.id.list_songs_actimport);
        if (playIntent == null) {
            playIntent = new Intent(context, MusicService.class);
            context.bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            context.startService(playIntent);
        }
        init();
        return view;
    }

    private void init() {
        mListSongs.setOnItemClickListener(this);
        if (mSongList != null && mSongList.size() != 0) {
            mAdapterListFile = new SongListAdapter(getContext(), mSongList);
            mListSongs.setAdapter(mAdapterListFile);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapterListFile.setSongsList(mSongList);
                    mLinearListImportedFiles.setVisibility(View.VISIBLE);
                    mRelativeBtnImport.setVisibility(View.GONE);
                    serviceMusic.setSongList(mSongList);
                }
            }, 1000);

        }
    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.PlayerBinder binder = (MusicService.PlayerBinder) service;
            //get service
            serviceMusic = binder.getService();
            serviceMusic.setSongList(mSongList);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        serviceMusic.setSelectedSong(i, MusicService.NOTIFICATION_ID);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        //Start service

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        //Stop service
        getActivity().stopService(playIntent);
        serviceMusic = null;
        super.onDestroy();
    }

}
