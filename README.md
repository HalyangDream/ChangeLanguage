# ChangeLanguage
<br>Android 应用内切换语言，兼容7.0以及上</br>
<br>在研究了 微信，支付宝，微博这三款软件，发现它们切换语言基本都是属于重启了APP或者跳到了RootActivity</br>
<br>如：微信和支付宝，在切换语言时都是回到了首页的第一个界面,微博是回到了闪屏页</br>

<br>这个例子的，没用重启任何的界面，都是自动更新，因为使用了Activity.recreate()方法，有的手机会黑屏一下。</br>
<br>这个方法应该不是最优使用，所以建议大家还是仿照以上3款应用。重启APP或清除其他Activity,从RootActivity 或者需要的Activity重启。</br>
<br>谨记一定要，如果要重启Activity，要清除所有Activity</br>
