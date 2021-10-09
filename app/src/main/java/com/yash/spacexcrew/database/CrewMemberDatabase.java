package com.yash.spacexcrew.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = CrewMember.class, version = 2)
public abstract class CrewMemberDatabase extends RoomDatabase {

    public abstract CrewMemberDAO crewMemberDAO();

    private static CrewMemberDatabase mInstance;

    public static CrewMemberDatabase getInstance(final Context context) {
        if(mInstance == null) {
            synchronized (CrewMemberDatabase.class) {
                if(mInstance == null) {
                    mInstance = buildDatabaseInstance(context);
                }
            }
        }

        return mInstance;
    }

    private static CrewMemberDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                CrewMemberDatabase.class,
                "crew_member_app")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

}
