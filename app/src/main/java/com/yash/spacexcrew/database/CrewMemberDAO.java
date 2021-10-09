package com.yash.spacexcrew.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CrewMemberDAO {

    @Query("SELECT * FROM CrewMember WHERE imageUrl =:imageUrl")
    List<CrewMember> crewMemberExists(String imageUrl);

    @Query("SELECT * FROM CrewMember")
    List<CrewMember> getAllCrewMember();

    @Query("DELETE FROM CrewMember")
    void delete();

    @Insert
    void insert(CrewMember crewMember);

}
