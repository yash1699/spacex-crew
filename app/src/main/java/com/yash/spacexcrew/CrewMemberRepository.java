package com.yash.spacexcrew;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.yash.spacexcrew.database.CrewMember;
import com.yash.spacexcrew.database.CrewMemberDAO;
import com.yash.spacexcrew.database.CrewMemberDatabase;

import java.util.List;

public class CrewMemberRepository {

    private CrewMemberDAO mCrewMemberDAO;
    private List<CrewMember> mCrewMembers;
    private Application mApplication;

    public CrewMemberRepository(Application application) {
        mApplication = application;
        CrewMemberDatabase db = CrewMemberDatabase.getInstance(mApplication);
        mCrewMemberDAO = db.crewMemberDAO();
        mCrewMembers = mCrewMemberDAO.getAllCrewMember();
    }

    public List<CrewMember> getAllCrewMembers() {
        return mCrewMembers;
    }

    public boolean crewMemberExists(String imageUrl){
        if(mCrewMemberDAO.crewMemberExists(imageUrl) != null){
            return true;
        }
        return false;
    }

    public void insert(CrewMember crewMember) {
        new insertAsyncTask(mCrewMemberDAO, mApplication).execute(crewMember);
    }

    public void delete() {
        new deleteAsyncTask(mCrewMemberDAO).execute();
    }

    private static class insertAsyncTask extends AsyncTask<CrewMember, Void, Boolean> {

        private CrewMemberDAO mAsyncCrewMemberDAO;
        private Application mApplication;

        insertAsyncTask(CrewMemberDAO dao, Application application) {
            mAsyncCrewMemberDAO = dao;
            mApplication = application;
        }


        @Override
        protected Boolean doInBackground(CrewMember... crewMembers) {
            boolean isSuccess = true;
            try {
                mAsyncCrewMemberDAO.insert(crewMembers[0]);
            } catch (SQLiteConstraintException e){
                Log.e("SQLiteInsertError", e.toString());
                isSuccess = false;
            }
            return isSuccess;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(!result){
                Toast.makeText(mApplication.getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private CrewMemberDAO mAsyncCrewMemberDAO;

        deleteAsyncTask(CrewMemberDAO dao) {
            mAsyncCrewMemberDAO = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncCrewMemberDAO.delete();
            return null;
        }
    }

}
