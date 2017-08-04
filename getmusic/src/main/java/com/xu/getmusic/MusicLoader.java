package com.xu.getmusic;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

/**
 * Created by xuduokai on 2017/6/20.
 */

public class MusicLoader {

    private static final String TAG = ".MusicLoader";

    private static List<MusicInfo> musicList = new ArrayList<MusicInfo>();

    private static MusicLoader musicLoader;

    private StringBuilder mSelection = new StringBuilder("("
            + MediaStore.Files.FileColumns.DATA + " like'%.mp3' )");

    private static ContentResolver contentResolver;
    //Uri，指向external的database
    private Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    //projection：选择的列; where：过滤条件; sortOrder：排序。
    private String[] projection = {
            Media.TITLE,
            Media.ARTIST,
            Media.DATA

    };

    private String foo(String[] keys) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            sb.append("(title like '%")
                    .append(keys[i])
                    .append("%') or (artist like '%")
                    .append(keys[i])
                    .append("%')");
            if (i +1 <keys.length){
                sb.append(" or ");
            }
        }
        return sb.toString();
    }


    private String where = "(_data like'%.mp3')" +
            " and ((title like '%王%') or ("
            + "artist like '%梁%'))";
    private String sortOrder = Media.DATA;

    private String[] lll = {
            "王",
            "梁",
            "陈"
    };

    public static MusicLoader instance(ContentResolver pContentResolver) {
        if (musicLoader == null) {
            contentResolver = pContentResolver;
            musicLoader = new MusicLoader();
        }
        return musicLoader;
    }


    private MusicLoader() {
        String bar = "(_data like'%.mp3') and (" + foo(lll) + ")";

        //利用ContentResolver的query函数来查询数据，然后将得到的结果放到MusicInfo对象中，最后放到数组中
        Cursor cursor = contentResolver.query(contentUri, null, bar, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            Log.v(TAG, "Line(37  )   Music Loader cursor == null.");
        } else if (!cursor.moveToFirst()) {
            Log.v(TAG, "Line(39  )   Music Loader cursor.moveToFirst() returns false.");
        } else {
            int displayNameCol = cursor.getColumnIndex(Media.DISPLAY_NAME);
            int albumCol = cursor.getColumnIndex(Media.ALBUM);
            int idCol = cursor.getColumnIndex(Media._ID);
            int durationCol = cursor.getColumnIndex(Media.DURATION);
            int sizeCol = cursor.getColumnIndex(Media.SIZE);
            int artistCol = cursor.getColumnIndex(Media.ARTIST);
            int urlCol = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                String title = cursor.getString(displayNameCol);
                String album = cursor.getString(albumCol);
                long id = cursor.getLong(idCol);
                int duration = cursor.getInt(durationCol);
                long size = cursor.getLong(sizeCol);
                String artist = cursor.getString(artistCol);
                String url = cursor.getString(urlCol);

                MusicInfo musicInfo = new MusicInfo(id, title);
                musicInfo.setAlbum(album);
                musicInfo.setDuration(duration);
                musicInfo.setSize(size);
                musicInfo.setArtist(artist);
                musicInfo.setUrl(url);
                musicList.add(musicInfo);

            } while (cursor.moveToNext());
        }
    }

    public List<MusicInfo> getMusicList() {
        return musicList;
    }

    public Uri getMusicUriById(long id) {
        Uri uri = ContentUris.withAppendedId(contentUri, id);
        return uri;
    }

    //下面是自定义的一个MusicInfo子类，实现了Parcelable，为的是可以将整个MusicInfo的ArrayList在Activity和Service中传送，=_=!!,但其实不用
    static class MusicInfo implements Parcelable {
        private long id;
        private String title;
        private String album;
        private int duration;
        private long size;
        private String artist;
        private String url;

        public MusicInfo(long pId, String pTitle) {
            id = pId;
            title = pTitle;
        }

        protected MusicInfo(Parcel in) {
            id = in.readLong();
            title = in.readString();
            album = in.readString();
            duration = in.readInt();
            size = in.readLong();
            artist = in.readString();
            url = in.readString();
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(id);
            dest.writeString(title);
            dest.writeString(album);
            dest.writeInt(duration);
            dest.writeLong(size);
            dest.writeString(artist);
            dest.writeString(url);
        }

        public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
            @Override
            public MusicInfo createFromParcel(Parcel in) {
                return new MusicInfo(in);
            }

            @Override
            public MusicInfo[] newArray(int size) {
                return new MusicInfo[size];
            }
        };


        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


    }


/*    private Cursor getCursor(String filePath) {
        String path = null;
        Cursor c = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
// System.out.println(c.getString(c.getColumnIndex("_data")));
        if (c.moveToFirst()) {
            do {
// 通过Cursor 获取路径，如果路径相同则break；
                System.out.println("////////" + filePath);
                path = c.getString(c
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                System.out.println("?????????" + path);
// 查找到相同的路径则返回，此时cursorPosition 便是指向路径所指向的Cursor 便可以返回了
                if (path.equals(filePath)) {
// System.out.println("audioPath = " + path);
// System.out.println("filePath = " + filePath);
// cursorPosition = c.getPosition();
                    break;
                }
            } while (c.moveToNext());
        }
// 这两个没有什么作用，调试的时候用
// String audioPath = c.getString(c
// .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
//
// System.out.println("audioPath = " + audioPath);
        return c;
    }*/

/*    private String getAlbumArt(int album_id) {
        String mUriAlbums = "content://media/external/audio/albums";
        String[] projection = new String[]{"album_art"};
        Cursor cur = this.getContentResolver().query(
                Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)),
                projection, null, null, null);
        String album_art = null;
        if (cur.getCount() > 0 && cur.getColumnCount() > 0) {
            cur.moveToNext();
            album_art = cur.getString(0);
        }
        cur.close();
        cur = null;
        return album_art;
    }

    private void getImage() {
        Cursor currentCursor = getCursor("/mnt/sdcard/" + mp3Info);
        int album_id = currentCursor.getInt(currentCursor
                .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
        String albumArt = getAlbumArt(album_id);
        Bitmap bm = null;
        if (albumArt == null) {
            mImageView.setBackgroundResource(R.drawable.staring);
        } else {
            bm = BitmapFactory.decodeFile(albumArt);
            BitmapDrawable bmpDraw = new BitmapDrawable(bm);
            mImageView.setImageDrawable(bmpDraw);
        }
    }*/


}