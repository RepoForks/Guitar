package com.byandfortechnologies.twistjam.fragments;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.byandfortechnologies.twistjam.MainActivity;
import com.byandfortechnologies.twistjam.R;
import com.byandfortechnologies.twistjam.adapters.SongListAdapter;
import com.byandfortechnologies.twistjam.helper.Song;
import com.byandfortechnologies.twistjam.services.MusicService;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTracks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTracks extends Fragment implements AdapterView.OnItemClickListener {
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
    public static Integer preSongPosition;

    // TODO: Rename and change types of parameters
    private ArrayList<Song> mParam1;
    private String mParam2;


    public FragmentTracks() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTracks.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTracks newInstance(String param1, String param2) {
        FragmentTracks fragment = new FragmentTracks();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentTracks newInstance(ArrayList<Song> param1, String param2) {
        FragmentTracks fragment = new FragmentTracks();
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
//        if (playIntent == null) {
//            playIntent = new Intent(context, MusicService.class);
//            context.startService(playIntent);
//            context.bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
//
//        }
        init();
        return view;
    }

    private void init() {
        mListSongs.setOnItemClickListener(this);
        if (mSongList != null && mSongList.size() != 0) {
            mAdapterListFile = new SongListAdapter(getContext(), mSongList);
            mListSongs.setAdapter(mAdapterListFile);

                    mAdapterListFile.setSongsList(mSongList);
                    mLinearListImportedFiles.setVisibility(View.VISIBLE);
                    mRelativeBtnImport.setVisibility(View.GONE);
                    //serviceMusic.setSongList(mSongList);
                    if (serviceMusic==null){
                        Log.d("serviceMusic is ","null");

                    }else {
                        Log.d("serviceMusic is ","not null");
                    }
        }
    }

//    private ServiceConnection musicConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            MusicService.PlayerBinder binder = (MusicService.PlayerBinder) service;
//            Log.d("serviceMusic is ","created");
//            //get service
//            serviceMusic = binder.getService();
//            serviceMusic.setSongList(mSongList);
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (preSongPosition!=null) {
            Log.d("song_position", "pre:" + preSongPosition+" .current:"+i);
        }
        if (preSongPosition==null){
            //serviceMusic.setSelectedSong(i, MusicService.NOTIFICATION_ID);
            Intent playMusicIntent = new Intent(getActivity(), MainActivity.class);
            playMusicIntent.putExtra("position", i);
            startActivity(playMusicIntent);
        }else if (preSongPosition==i) {
            Intent playMusicIntent = new Intent(getActivity(), MainActivity.class);
            playMusicIntent.putExtra("position", i);
            startActivity(playMusicIntent);
            // context.startService(playIntent);
            //serviceMusic.playPauseSong();
        }else {
            Intent playMusicIntent = new Intent(getActivity(), MainActivity.class);
            playMusicIntent.putExtra("position", i);
            startActivity(playMusicIntent);
            //serviceMusic.setSelectedSong(i, MusicService.NOTIFICATION_ID);
        }

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
    public static void stopMusic(){

    }

}
