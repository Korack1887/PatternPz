package com.company.DAO.autoInc;

import com.company.entities.Headphones.Headphones;

import java.util.ArrayList;

public class headphoneCounter {
    public static int autoInc(ArrayList<Headphones> arrayList){
        int arr[] = new int[arrayList.size()];
        boolean match=true;
        for (int i = 0; i < arrayList.size(); i++) {
            arr[i]=arrayList.get(i).getId();
        }
        for(int i=0; i<Integer.MAX_VALUE;i++) {
            match=true;
            for (int j = 0; j < arr.length; j++) {
                if (i ==arr[j]){
                    match=false;
                }
            }
            if(match){
                return i;
            }
        }
        return 0;
    }
}
