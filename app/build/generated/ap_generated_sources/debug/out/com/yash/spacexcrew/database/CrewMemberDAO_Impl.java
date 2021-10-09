package com.yash.spacexcrew.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CrewMemberDAO_Impl implements CrewMemberDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CrewMember> __insertionAdapterOfCrewMember;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  public CrewMemberDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCrewMember = new EntityInsertionAdapter<CrewMember>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `CrewMember` (`imageUrl`,`name`,`agency`,`status`,`wikipediaUrl`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CrewMember value) {
        if (value.imageUrl == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.imageUrl);
        }
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.agency == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.agency);
        }
        if (value.status == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.status);
        }
        if (value.wikipediaUrl == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.wikipediaUrl);
        }
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CrewMember";
        return _query;
      }
    };
  }

  @Override
  public void insert(final CrewMember crewMember) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCrewMember.insert(crewMember);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDelete.release(_stmt);
    }
  }

  @Override
  public List<CrewMember> crewMemberExists(final String imageUrl) {
    final String _sql = "SELECT * FROM CrewMember WHERE imageUrl =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (imageUrl == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, imageUrl);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAgency = CursorUtil.getColumnIndexOrThrow(_cursor, "agency");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfWikipediaUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "wikipediaUrl");
      final List<CrewMember> _result = new ArrayList<CrewMember>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CrewMember _item;
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpAgency;
        if (_cursor.isNull(_cursorIndexOfAgency)) {
          _tmpAgency = null;
        } else {
          _tmpAgency = _cursor.getString(_cursorIndexOfAgency);
        }
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        final String _tmpWikipediaUrl;
        if (_cursor.isNull(_cursorIndexOfWikipediaUrl)) {
          _tmpWikipediaUrl = null;
        } else {
          _tmpWikipediaUrl = _cursor.getString(_cursorIndexOfWikipediaUrl);
        }
        _item = new CrewMember(_tmpImageUrl,_tmpName,_tmpAgency,_tmpStatus,_tmpWikipediaUrl);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CrewMember> getAllCrewMember() {
    final String _sql = "SELECT * FROM CrewMember";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAgency = CursorUtil.getColumnIndexOrThrow(_cursor, "agency");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfWikipediaUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "wikipediaUrl");
      final List<CrewMember> _result = new ArrayList<CrewMember>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CrewMember _item;
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpAgency;
        if (_cursor.isNull(_cursorIndexOfAgency)) {
          _tmpAgency = null;
        } else {
          _tmpAgency = _cursor.getString(_cursorIndexOfAgency);
        }
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        final String _tmpWikipediaUrl;
        if (_cursor.isNull(_cursorIndexOfWikipediaUrl)) {
          _tmpWikipediaUrl = null;
        } else {
          _tmpWikipediaUrl = _cursor.getString(_cursorIndexOfWikipediaUrl);
        }
        _item = new CrewMember(_tmpImageUrl,_tmpName,_tmpAgency,_tmpStatus,_tmpWikipediaUrl);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
