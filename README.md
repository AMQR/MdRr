一个简单的小综合demo，分别演示：
* Material Design
* RxJava
* Retrofit
* RxJava+Retrofit


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1083096-ee33032d1d97d7ba.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 一、简单的小架子 


![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1083096-f2c20e93ae49f50d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


布局文件
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/mDrawerLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:fitsSystemWindows="true"
    tools:context="com.am.mdrr.MainActivity">

    <!--抽屉分两部分 一部分内容， 一部分导航-->

    <!--抽屉的 内容部分-->
    <android.support.design.widget.CoordinatorLayout
        android:id="@+id/mCoordinator"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.design.widget.AppBarLayout
            android:id="@+id/mAppbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            >
            <android.support.v7.widget.Toolbar
                android:id="@+id/mToolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />
        </android.support.design.widget.AppBarLayout>

        <FrameLayout
            android:id="@+id/mFrameLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_below="@+id/appbar"
            android:scrollbars="none"
            app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    </android.support.design.widget.CoordinatorLayout>

    <!--抽屉  导航部分
    分为 导航头部和导航主菜单
    可以通过 headerLayout 设置抽出方向-->
    <android.support.design.widget.NavigationView
        android:id="@+id/mNavigation"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_head"
        app:menu="@menu/nav_main_part"
        />
</android.support.v4.widget.DrawerLayout>

```

设置抽屉导航的  头部 和 菜单
.
.
MainActivity
```
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(mToolbar);

        supportFragmentManager = getSupportFragmentManager();

        // 抽屉整体的一些设置，toggle同步，添加toggle同步  没卵用但是一定要加
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mDrawerLayout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open,
                R.string.close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

        // 菜单的头部
        mNavigationView = (NavigationView) findViewById(R.id.mNavigation);
        View headerView = mNavigationView.getHeaderView(0); // 得到菜单 的头部
        if(headerView!=null){
            headerView.findViewById(R.id.mIvPic).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchToRx();
                    mDrawerLayout.closeDrawers();
                    mNavigationView.getMenu().getItem(1).setChecked(true);
                }
            });
        }

        // 菜单的正式部分
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_item_rx:
                        switchToRx();
                        break;
                    case R.id.nav_item_re:
                        switchToRe();
                        break;
                    case R.id.nav_item_rxre:
                        switchToRxRe();
                        break;
                    case R.id.nav_item_md:
                        switchToMd();
                        break;
                    case R.id.nav_item_about:
                        switchToAbout();
                        break;
                }
                menuItem.setChecked(true); // 把当前菜单的item置为选中状态
                mDrawerLayout.closeDrawers(); // 抽屉合起来，隐藏菜单

                // 默认返回flase，自己实现逻辑需要返回true
                return true;
            }
        });

    }

    private void switchToRx() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new RxFragment()).commit();
        mToolbar.setTitle("RxJava");
    }

    private void switchToRe() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new ReFragment()).commit();
        mToolbar.setTitle("Retrofit");
    }

    private void switchToRxRe() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new RxReFragment()).commit();
        mToolbar.setTitle("RxJava Retrofit");
    }

    private void switchToMd() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new MdFragment()).commit();
        mToolbar.setTitle("Material Design");
    }

    private void switchToAbout() {
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout, new AboutFragment()).commit();
        mToolbar.setTitle("About");
    }
}
```

gif

![小架子.gif](http://upload-images.jianshu.io/upload_images/1083096-33a4447826bc7941.gif?imageMogr2/auto-orient/strip)


# 二、 Md Widget

*SnackBar
* CardView
* RecyclerView
* TextInputLayput
* FloatingActionButton
* ToolBar
* CoordinatorLaypout
* CollasoingToolvarLaypout
* NestedScrollView
* TabLayout

简图如下

![MdShow.gif](http://upload-images.jianshu.io/upload_images/1083096-3718c87007f10d89.gif?imageMogr2/auto-orient/strip)

注：
关于RecyclerView没有单独开一个activity，但是其实列表本事就是RecyclerView了，想学参考可以参考这里：[RecyclerView良好参考](http://www.jianshu.com/p/b4d1bfd55ae9)


关于md控件，可以参考这里：
[MD系列2、ToolBar+DrawerLayout + NavigationView](http://www.jianshu.com/p/9471b87f2c61)

[Md系列3、CoordinatorLayout 里 Toobar和TabLayout等发生的一系列故事](http://www.jianshu.com/p/1edeff139de5)

# 三、RxJava

RxJava和Retrofit往往是一起使用的。

所以 build.gradle添加如下引入

```
    compile 'io.reactivex:rxjava:1.1.7'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    compile 'com.google.code.gson:gson:2.7'
```

分点：

* RxJava初认识
* Observer+Observable
* Subscriber+Observable
* 快捷创建事件，just
* 快捷创建事件，from
* 不完整回调ActionX
* Scheduler调度器
* Scheduler切换线程
* 变换入门
* 变换flatMap
* 应用场景


gif


![Rx.gif](http://upload-images.jianshu.io/upload_images/1083096-baa93a20a1c17958.gif?imageMogr2/auto-orient/strip)

RxJava可以参考这里：[旁边那门转左看到一个RxJava](http://www.jianshu.com/p/44d789d8c09c)


# 四、Retrofit

当前只做了一个示例，就是RxJava和Retrofit结合的获取天气的demo页面。

![Retro.gif](http://upload-images.jianshu.io/upload_images/1083096-68df732e144af7f9.gif?imageMogr2/auto-orient/strip)


关键代码
```
        String baseUrl = "http://apistore.baidu.com/";
        // 创建一个  retrofit ，baseUrl必须有
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) // 用到了 com.squareup.retrofit2:adapter-rxjava:2.1.0'
                .build();
        // 利用 retrofit 创建一个接口
        WeatherApi apiService = retrofit.create(WeatherApi.class);

        // 利用接口创建一个Call对象
        Call<WeatherBean> weatherBeanCall = apiService.getWeather("beijing");
        // 请求入队，回调请求成功或者失败
        weatherBeanCall.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                System.out.println("====请求成功："+response.body().retData.weather);
                mTvData.setText(response.body().retData.weather);
            }
            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                System.out.println("====请求失败");
                mTvData.setText(t.getMessage());
            }
        });
```


![Retro.gif](http://upload-images.jianshu.io/upload_images/1083096-eeedd376e191afbb.gif?imageMogr2/auto-orient/strip)

Retrofit可以参考这里：
[Retrofit应用小记](http://www.jianshu.com/p/c1b2af70f24b)

# 五、RxJava + Retrofit

简单的demo，获取文本笑话

```
    compile 'com.squareup.okhttp3:logging-interceptor:3.3.1'
    compile 'com.squareup.okhttp3:okhttp:3.3.1'
    compile 'com.squareup.okhttp3:okhttp:3.3.1'
    compile 'io.reactivex:rxandroid:1.2.1'
```


![Re加Rx.gif](http://upload-images.jianshu.io/upload_images/1083096-72e0d624ac0332de.gif?imageMogr2/auto-orient/strip)



[项目地址](https://github.com/AMQR/MdRr)

先这样，没事再完善。
