package io.github.mths0x5f.guiaufu.util;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public final class MultipleFragments {

    private static String LOGCAT_TAG = "MultipleFragments";
    private static String tagSuffix = "_item#";

    /**
     * This is a static-only-methods-class, no reason to instantiate
     */
    private MultipleFragments() {/* This avoid instantiation */ }


    /**
     * Creates a list with n objects of given Fragment type
     * @param class_ Class representing the Fragment type
     * @param n      Number of objects
     * @return List of Fragments
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
     * Add given list of Fragments to a given container, on a given transaction
     * @param fragments     List of Fragments
     * @param container_id  Resource ID of container
     * @param tag           Tag to easily find fragments later
     * @param transaction   The transaction object
     */
    public static <E extends Fragment> void addToTransaction(List<E> fragments, int container_id,
                                                             String tag,
                                                             FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); i++)
            transaction.add(container_id, fragments.get(i), tag+ tagSuffix +i);

    }

    /**
     * Get all fragments of a list of Fragments once added by {@link #addToTransaction}
     * @param tag       The same tag used on {@link #addToTransaction}
     * @param manager   Instance of FragmentManager
     * @return List of Fragments, null if no elements were found
     */
    public static <E extends Fragment> List<E> findByTag(String tag, FragmentManager manager) {
        E fragment;
        List<E> fragments = new ArrayList<>();
        int i = 0;
        do {
            fragment = (E) manager.findFragmentByTag(tag+ tagSuffix +i);
            if (fragment == null)
                break;
            fragments.add(fragment);
            i++;
        } while (true);
        return fragments;
    }






}
