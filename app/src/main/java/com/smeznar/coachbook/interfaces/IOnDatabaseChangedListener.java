package com.smeznar.coachbook.interfaces;

import io.realm.Realm;

public interface IOnDatabaseChangedListener {
    void onDatabaseChanged(Realm realm);
}
