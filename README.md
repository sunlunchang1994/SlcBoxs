# SlcBoxs
###### 这是一个灵活而强大的Android开发基础框架！
###### 目前处于稳定且不断更新状态。
## 导入方式
````java
implementation 'android.slc:box:lastVersion'
````
###### 仅支持Android X
###### 文档仍在补全当中…… 代码是最好的老师，如遇问题，请先查看源码
## 模块介绍
### commonlibrary
###### 这是SlcBoxde 中的常用工具类模块，其中80%Api均来自[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")，相关文档可在[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")中查看；
###### 为了方便与本框架融合，以将[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")中所有类名统一化；
###### 其他相关api可在源码中进行查看。
#### 如果仅使用该模块，可以使用以下方式导入
````java
implementation 'android.slc:commonlibrary:lastVersion'
````
### toolbar
###### toolbar模块是对Android原生toolbar相关api进行二次封装的模块；
###### 继承[SlcToolBarDelegate](https://github.com/sunlunchang1994/SlcBoxs/blob/master/toolbar/src/main/java/android/slc/toolbar/SlcToolBarDelegate.java "SlcToolBarDelegate")抽象类可对默认配置进行更改与扩展，可使用toolbar相关隐藏api来应对不遵循MD设计规范的需求。
###### 集成[BaseActionProviderImp](https://github.com/sunlunchang1994/SlcBoxs/blob/master/toolbar/src/main/java/androidx/core/view/BaseActionProviderImp.java "BaseActionProviderImp")抽象类可实现各种风格的菜单item。
#### 集成方式
````java
implementation 'android.slc:toolbar:lastVersion'
````
### code
###### 这是SlcBoxde 核心模块，封装了基于MVVM、MVP设计模式的Activity、Fragment以及对应的ViewModel、Presenter的接口和基础实现类。
###### 该模块使用了commonlibrary、toolbar相关api，所以已经集成了commonlibrary、toolbar模块，无需再次集成。
#### 集成方式
````java
implementation 'android.slc:code:lastVersion'
````
### rx
###### 该模块封装了rxjava和rxAndroid一些基础操作类，如转换器、简单的Observer，方便在开发中快速调用。
#### 集成方式
````java
implementation 'android.slc:rx:lastVersion'
````
### rxlifecycle
###### 基于[com.trello.rxlifecycle3:rxlifecycle](https://github.com/trello/RxLifecycle "com.trello.rxlifecycle3:rxlifecycle")的基础模块进行封装，结合androidx.lifecycle，使其可以脱离activity、fragment使用。
#### 集成方式
````java
implementation 'android.slc:rx:lastVersion'
````
### codelifecycle
###### 将code模块中的presenter和viewmodel与rxlifecycle中的生命周期进行结合，使mvp得presenter层和mvvm的vm层具有对生命周期具有感知能力，在使用rxjava进行网络访问和耗时操作时可轻松避免内存泄漏。
#### 集成方式
````java
implementation 'android.slc:rx:lastVersion'
````