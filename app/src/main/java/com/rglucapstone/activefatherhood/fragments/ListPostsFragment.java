package com.rglucapstone.activefatherhood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.ListFragment;

import com.rglucapstone.activefatherhood.R;
import com.rglucapstone.activefatherhood.adapters.ListPostsAdapter;
import com.rglucapstone.activefatherhood.data.Post;

import java.util.ArrayList;

/**
 * Created by ronald on 09/01/16.
 */
public class ListPostsFragment extends ListFragment {

    public ListPostsFragment() {

    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_posts, container, false);
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Post> listPosts = new ArrayList<>();
        this.populate(listPosts);
        ListPostsAdapter postsAdapter = new ListPostsAdapter(getActivity(), listPosts);
        setListAdapter(postsAdapter);
    }

    private void populate(ArrayList posts){
        Post q1 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q1);
        Post q2 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q2);
        Post q3 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q3);
        Post q4 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q4);
        Post q5 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q5);
        Post q6 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q6);
        Post q7 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q7);
        Post q8 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q8);
        Post q9 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q9);
        Post q10 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q10);
        Post q11 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q11);
        Post q12 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q12);
        Post q13 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q13);
        Post q14 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q14);
        Post q15 = new Post("Consejos para ser un buen padre", "¿Cómo y cuándo hablar con los niños acerca de las enfermedades que..", "Pedro Xavier", "hace 45 minutos","Bebes","10 Me Gusta","15 Comentarios");
        posts.add(q15);
    }
}
