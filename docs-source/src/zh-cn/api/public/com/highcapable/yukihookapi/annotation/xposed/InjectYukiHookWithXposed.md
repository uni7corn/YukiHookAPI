---
pageClass: code-page
---

# InjectYukiHookWithXposed <span class="symbol">- annotation</span>

```kotlin:no-line-numbers
annotation class InjectYukiHookWithXposed(
    val sourcePath: String,
    val modulePackageName: String,
    val entryClassName: String,
    val isUsingXposedModuleStatus: Boolean,
    val isUsingResourcesHook: Boolean
)
```

**变更记录**

`v1.0` `添加`

`v1.0.80` `修改`

新增 `entryClassName` 参数

`v1.0.92` `修改`

新增 `isUsingResourcesHook` 参数

`v1.2.0` `修改`

新增 `isUsingXposedModuleStatus` 参数

**功能描述**

> 标识 `YukiHookAPI` 注入 Xposed 入口的类注解。

**功能示例**

详情请参考 [InjectYukiHookWithXposed 注解](../../../../../../../config/xposed-using#injectyukihookwithxposed-注解)。