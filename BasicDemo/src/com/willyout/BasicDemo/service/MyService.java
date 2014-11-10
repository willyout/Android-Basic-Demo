package com.willyout.BasicDemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyService extends Service {

    private PetBinder petBinder;

    private static Map<Person, List<Pet>> pets = new HashMap<Person, List<Pet>>();

    static {
        ArrayList<Pet> plist = new ArrayList<Pet>();
        plist.add(new Pet("hello", 7.888));
        plist.add(new Pet("hjhj", 4.44));

        pets.put(new Person(1, "sb", "sb"), plist);

        ArrayList<Pet> pplist = new ArrayList<Pet>();
        pplist.add(new Pet("hello2", 7.888));
        pplist.add(new Pet("hjhj2", 4.44));

        pets.put(new Person(2, "sb2", "sb2"), pplist);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        petBinder = new PetBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return petBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class PetBinder extends IPet.Stub {
        @Override
        public List<Pet> getPets(Person owner) throws RemoteException {
            return pets.get(owner);
        }
    }

}
