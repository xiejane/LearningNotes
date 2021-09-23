# User Interface

## Button and clickable Image
>[codelab](http://clmirror.storage.googleapis.com/codelabs/android-training-clickable-images/index.html#0)

Button
- text only
	```
	<Button
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="@string/button_text"
	    <!-- more attributes ... -->
	/>
	```
- icon only
	```
	<ImageButton
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:src="@drawable/button_icon"
	    <!-- more attributes ... -->
	/>
	```
- text and icon
 	```
	 <Button
	    android:layout_width="wrap_content"
	    android:layout_height="wrap_content"
	    android:text="@string/button_text"
	    android:drawableLeft="@drawable/button_icon"
	    <!-- more attributes ... -->
	/>
	```

A _flat button_, also known as a text button or borderless button, is a text-only button that looks flat and doesn't have a shadow. 

To create a flat button, use the `Button` class. Add a `Button` to your XML layout, and apply `"?android:attr/borderlessButtonStyle"` as the `style` attribute:
```
<Button
    android:id="@+id/button_send"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/button_send"
    android:onClick="sendMessage"
    style="?android:attr/borderlessButtonStyle" />
```

An event listener is an interface in the View class that contains a single callback method.

- Adding `onClick() `to the layout element: The callback method for the `android:onClick` attribute must be `public`, return `void`, and define a `View` as its only parameter (this is the `View` that was tapped).
- Using the button-listener design pattern
	```
	Button button = findViewById(R.id.button_send);
	button.setOnClickListener(new View.OnClickListener() {
	     @Override
	     public void onClick(View v) {
	         // Do something in response to button click
	     }
	 }
	```

The following are some of the listeners available in the Android framework and the callback methods associated with each one:

- `onClick()` from `View.OnClickListener`: Handles a click event in which the user touches and then releases an area of the device display occupied by a `View`. The `onClick()` callback has no return value.

- `onLongClick()` from `View.OnLongClickListener`: Handles an event in which the user maintains touch on a `View` for an extended period. This method returns a `boolean` to indicate whether you have consumed the event, and the event should not be carried further. **That is, return `true` to indicate that you have handled the event and the event should stop here. Return false if you have not handled the event, or if the event should continue to any other listeners.**

- `onTouch() `from `View.OnTouchListener`: Handles any form of touch contact with the screen including individual or multiple touches and gesture motions, including a press, a release, or any movement gesture on the screen (within the bounds of the UI element). A `MotionEvent` is passed as an argument, which includes directional information, and it returns a `boolean` to indicate whether your listener consumes this event.

- `onFocusChange()` from` View.OnFocusChangeListener`: Handles when focus moves away from the current `View` as the result of interaction with a trackball or navigation key.

- `onKey()` from `View.OnKeyListener`: Handles when a key on a hardware device is pressed while a `View` has focus.

### Using a floating action button

Only one floating action button is recommended per screen.

To create a floating action button yourself, use the `FloatingActionButton` class, which extends the `ImageButton` class. You can add a floating action button to your XML layout as follows:
```
<android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        android:src="@drawable/ic_fab_chat_button_white" />
```
- Floating action buttons, by default, are **56 x 56 dp** in size. It is best to use the default size unless you need the smaller version to create visual continuity with other screen elements.
- You can set the **mini size (30 x 40 dp)** with the app:fabSize attribute: `app:fabSize="mini"`
- To set it back to the default size (56 x 56 dp): `app:fabSize="normal"`

### Detecting common gestures
`GestureDetectorCompat` lets you detect common gestures without processing the individual touch events yourself. It detects various gestures and events using MotionEvent objects, which report movements by a finger (or mouse, pen, or trackball).

If your app uses common gestures such as **double tap, long press, fling,** and so on, you can take advantage of the `GestureDetector` class for detecting common gestures. Use `GestureDetectorCompat`, which is provided as a compatibility implementation of the framework's `GestureDetector` class which guarantees the newer focal point scrolling behavior from Jellybean MR1 on all platform versions. This class should be used only with motion events reported for touch devices—don't use it for trackball or other hardware events.


> The gesture starts when the user first touches the screen, continues as the system tracks the position of the user's finger or fingers, and ends when the system captures the event of the user's fingers leaving the screen.

