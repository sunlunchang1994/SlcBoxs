# SlcBoxs
这是一个灵活而强大的Android开发基础框架！
目前处于稳定且不断更新状态。
## 导入方式
````java
implementation 'android.slc:box:lastVersion'
````
仅支持AndroidX
文档仍在补全当中…… 代码是最好的老师，如遇问题，请先查看源码
## 模块介绍
### commonlibrary
这是SlcBoxde 中的常用工具类模块，其中80%Api均来自[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")，相关文档可在[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")中查看；
为了方便与本框架融合，以将[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")中所有类名统一化；
其他相关api可在源码中进行查看。
### code
这是SlcBoxde 核心模块，封装了基于MVVM、MVP设计模式的Activity、Fragment以及对应的ViewModel、Presenter的接口和基础实现类。
#### 使用方式
````java
implementation 'android.slc:box:lastVersion'
````