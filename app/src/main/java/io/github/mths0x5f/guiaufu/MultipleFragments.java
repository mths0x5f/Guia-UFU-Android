package io.github.mths0x5f.guiaufu;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public final class MultipleFragments {

    private static final String LOGCAT_TAG = "MultipleFragments";

    /**
     * Creates a list with n objects of given Fragment type
     * @param class_ Class representing the Fragment
     * @param n Number of objects
     * @return List<E>
     */
    public static <E extends Fragment> List<E> create(Class<E> class_, int n) {
        List<E> fragments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                fragments.add(class_.newInstance());
            } catch (InstantiationException e) {
                Log.e(LOGCAT_TAG, "Fragment does not have a no-args constructor", e);
            } catch (IllegalAccessException e) {
                Log.wtf(LOGCAT_TAG, "It should never happened. Really.", e);
            }
        }
        return fragments;
    }

    /**
     * Get all fragments once created and packed as a list by create() method
     * @param tag The same tag used on addToTransaction()
     * @param manager Instance of FragmentManager
     * @return List<E>
     */
    public static <E extends Fragment> List<E> getByTag(String tag, FragmentManager manager) {
        E fragment;
        List<E> fragments = new ArrayList<>();
        int i = 0;
        do {
            fragment = (E) manager.findFragmentByTag(tag+"#"+i);
            if (fragment == null)
                break;
            fragments.add(fragment);
            i++;
        } while (true);
        return fragments;
    }


    /**
     * Add given fragments to a given container, on a given transaction
     * @param fragments List of Fragments
     * @param container_id Resource ID of container
     * @param tag Tag to easily find fragments later. Internally, tag#1 . Where 1 is the index.
     * @param transaction The transaction object
     */
    public static <E extends Fragment> void addToTransaction(List<E> fragments, int container_id,
                                                             String tag,
                                                             FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); i++)
            transaction.add(container_id, fragments.get(i), tag+"#"+i);

    }



}
