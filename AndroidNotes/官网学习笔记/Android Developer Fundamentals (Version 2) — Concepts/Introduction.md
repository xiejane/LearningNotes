# Build your first app

The SDK includes software libraries of prewritten code, a debugger, a device emulator, documentation, sample code, and tutorials. 

![architecture](../../../resources/architecture.png)

An app's performance is determined by how fast it runs, how easily it connects to the network, and how well it manages battery and memory usage. 

![steps](../../../resources/steps.png)

- notions:
	- Android application package (APK)
	- Android Virtual Device (AVD) 
	- density-independent pixels (dp)


build:grade(Project:apptitle): 不应该添加 app 依赖

`defaultConfig { }` 里面的配置会覆盖 `AndroidManifest.xml` 文件内的配置: The minSdkVersion and targetSdkVersion settings override any AndroidManifest.xml settings for the minimum SDK version and the target SDK version. 

- subView:
	- `TextView` for displaying text
	- `EditText` to enable the user to enter and edit text
	- `Button` and other clickable elements (such as `RadioButton`, `CheckBox`, and `Spinner`) to provide interactive behavior
	- `ScrollView` and `RecyclerView` to display scrollable items
	- `ImageView` for displaying images
	- `ConstraintLayout` and `LinearLayout` for containing other views and positioning them
	

- `ViewGroup` groups
	+ `ConstraintLayout`: A group that places UI elements (child `View` elements) using constraint connections to other elements and to the layout edges (parent `View`).
	+ `ScrollView`: A group that contains one other child `View` element and enables scrolling the child `View` element.
	+ `RecyclerView`: A group that contains a list of other `View` elements or `ViewGroup` groups and enables scrolling them by adding and removing `View` elements dynamically from the screen.
	
![hierarchy](../../../resources/hierarchy.png)

## Layouts and resources for the UI

![layouts](../../../resources/layouts.png)

the @ symbol specifies what kind of resource. For example:
```
	android:background="@color/myBackgroundColor"
```

- LinearLayout is required to have these attributes set:
	- android:layout_width
	- android:layout_height
	- android:orientation

- resource
	+ `drawable`: For images and icons
	+ `layout`: For layout resource files
	+ `menu`: For menu items
	+ `mipmap`: For pre-calculated, optimized collections of app icons used by the Launcher
	+ `values`: For colors, dimensions, strings, and styles (theme attributes)
	
## Text and scrolling views
- TextView 的属性
	- `android:text`: Set the text to display.
	- `android:textColor`: Set the color of the text. You can set the attribute to a color value, a predefined resource, or a theme. Color resources and themes are described in other chapters.
	- `android:textAppearance`: The appearance of the text, including its color, typeface, style, and size. You set this attribute to a predefined style resource or theme that already defines these values.
	- `android:textSize`: Set the text size (if not already set by `android:textAppearance`). Use sp (scaled-pixel) sizes such as 20sp or 14.5sp, or set the attribute to a predefined resource or theme.
	- `android:textStyle`: Set the text style (if not already set by `android:textAppearance`). Use normal, bold, italic, or bold|italic.
	- `android:typeface`: Set the text typeface (if not already set by `android:textAppearance`). Use normal, sans, serif, or monospace.
	- `android:lineSpacingExtra`: Set extra spacing between lines of text. Use `sp` (scaled-pixel) or `dp` (device-independent pixel) sizes, or set the attribute to a predefined resource or theme.
	- `android:autoLink`: Controls whether links such as URLs and email addresses are automatically found and converted to clickable (touchable) links
		+ `none`: Match no patterns (default).
		+ `web`: Match web URLs.
		+ `email`: Match email addresses.
		+ `phone`: Match phone numbers.
		+ `map`: Match map addresses.
		+ `all`: Match all patterns (equivalent to web|email|phone|map).
		

- ScrollView
	- ScrollView 是 FrameLayout 的子类，因此，ScrollView 内只能有一个 View
	- View 可以是 ViewGroup，推荐使用 LinearLayout
	- ScrollView 的内容会存在内存中，即使它没有被显示。因此可能会导致内存问题，建议使用 RelativeLayout 或者 GridLayout 替代 LinearLayout(是嵌套的)来提高性能。并且不建议在 ScrollView 内加载图片

The ScrollView class provides the layout for a vertical scrolling view. (For horizontal scrolling, you would use HorizontalScrollView.) ScrollView is a subclass of FrameLayout, which means that you can place only one View as a child within it; that child contains the entire contents to scroll.

Even though you can place only one child View inside a ScrollView, the child View can be a ViewGroup with a hierarchy of child View elements, such as a LinearLayout. A good choice for a View within a ScrollView is a LinearLayout that is arranged in a vertical orientation.