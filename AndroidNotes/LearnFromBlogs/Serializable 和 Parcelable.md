# Serializable 和 Parcelable 的区别

## 1. 作用

Serializable 的作用是为了保存对象的属性到本地文件、数据库、网络流、RMI(Remote Method Invocation) 以方便传输数据，当然这种传输可以是程序内的，也可以是两个程序间的。<font color = red>使用了反射技术，并且期间产生了临时对象</font>

Android 的 Parcelable 设计的初衷是因为 Serializable 效率过慢，为了再程序内不同组件间以及不同 Android 程序间(AIDL)高效的传输数据而设计，<font color = red>这些数据仅在内存中存在</font>。Parcelable 是通过 IBinder 通信的消息载体。

## 2. 效率及选择

<font color = red>Parcelable 的性能比 Serializable 好，在内存开销方面较小，所以在内存间数据传输时推荐使用 Parcelable， 如 Activity 间传输数据</font>， 而 Serializable 可将数据持久化方便保存，所以在 <font color = red>需要保存或网络传输数据时选择 Serializable</font>，因为 Android 不同版本 Parcelable 可能不同，所以不推荐使用 Parcelable 进行数据持久化。

## 3. 高级功能

Serializable 序列话不保存静态变量，可使用 transient 关键字对部分字段不进行序列化，也可以覆盖 writeObject、readObject 方法实现序列化过程自定义。如果用 transient 声明一个实例变量，当对象存储时，他的值不需要维持。换句话说，用 <font color = red>transient 关键字标记的成员变量不参与序列化过程。</font>

## 4.编程实现

对于 Serializable, 类只需要实现 Serializable 接口，并提供一个序列化版本id(serialVersionUID)即可。

而 Parcelable 则需要实现 writeToParcel、describeContents函数以及静态的 CREATOR 变量(AS 有相关插件一键生成所需方法)，实际上就是将如何打包和解包的工作自己来定义，而序列化的这些操作完全由底层实现。

Parcel 的写入和读出顺序是一致的，如果顺序不一致会导致数据混乱。

eg:
```java

import android.os.Parcel;
import android.os.Parcelable;

public class ButtonInfo implements Parcelable {
	private String cabinetId;
	private String strChannelId;

	public ButtonInfo () {

	}
	
	public ButtonInfo(Parcel in) {
		cabinetId = in.readString();
		strChannelId = in.readString();
	}
	
	public static final Creator<ButtonInfo> CREATOR = new Creator<ButtonInfo>() {

		@Override
		public ButtonInfo createFromParcel(Parcel in) {
			ButtonInfo btnInfo = new ButtonInfo(in);
			if(InternalUtils.stringIsEmpty(btnInfo.getCabinetId()) 
					|| InternalUtils.stringIsEmpty(btnInfo.getStrChannelId())) {
				return null;
			}
			
			return btnInfo;
		}

		@Override
		public ButtonInfo[] newArray(int size) {
			return new ButtonInfo[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flag) {
		out.writeString(cabinetId);
		out.writeString(strChannelId);
	}

	@Override
	public boolean equals(Object obj) {
		ButtonInfo buttonInfo = (ButtonInfo) obj;
		if(null == buttonInfo) {
			return false;
		}
		
		if(InternalUtils.stringIsEmpty(this.strChannelId)) {
			if(!InternalUtils.stringIsEmpty(buttonInfo.getStrChannelId())) {
				return false;
			} else {
				return true;
			}
		}
		
		if(InternalUtils.stringIsEmpty(this.cabinetId)) {
			if(!InternalUtils.stringIsEmpty(buttonInfo.getCabinetId())) {
				return false;
			} else {
				return true;
			}
		}
		
		if(this.strChannelId.equals(buttonInfo.getStrChannelId())
				&& this.cabinetId.equals(buttonInfo.getCabinetId())) {
			return true;
		}
		
		return false;
	}
}

```