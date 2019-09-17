
package examples.aaronhoskins.com.mvpdemo.model.randomuser;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Dob implements Parcelable {

    @SerializedName("age")
    private Long mAge;
    @SerializedName("date")
    private String mDate;

    protected Dob(Parcel in) {
        if (in.readByte() == 0) {
            mAge = null;
        } else {
            mAge = in.readLong();
        }
        mDate = in.readString();
    }

    public static final Creator<Dob> CREATOR = new Creator<Dob>() {
        @Override
        public Dob createFromParcel(Parcel in) {
            return new Dob(in);
        }

        @Override
        public Dob[] newArray(int size) {
            return new Dob[size];
        }
    };

    public Long getAge() {
        return mAge;
    }

    public void setAge(Long age) {
        mAge = age;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (mAge == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mAge);
        }
        parcel.writeString(mDate);
    }
}
