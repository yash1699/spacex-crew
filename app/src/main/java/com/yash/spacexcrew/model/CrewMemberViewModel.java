package com.yash.spacexcrew.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.yash.spacexcrew.database.CrewMember;
import com.yash.spacexcrew.CrewMemberRepository;

import java.util.List;

public class CrewMemberViewModel extends AndroidViewModel {

    private CrewMemberRepository mRepository;

    public CrewMemberViewModel(Application application) {
        super(application);
        mRepository = new CrewMemberRepository(application);
    }

    public boolean crewMemberExists(String imageUrl) {
        return mRepository.crewMemberExists(imageUrl);
    }

    public List<CrewMember> getAllCrewMembers() {
        return mRepository.getAllCrewMembers();
    }

    public void insert(CrewMember crewMember) {
        mRepository.insert(crewMember);
    }

    public void delete() {
        mRepository.delete();
    }

}
