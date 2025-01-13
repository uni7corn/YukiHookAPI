---
pageClass: code-page
---

::: warning

The English translation of this page has not been completed, you are welcome to contribute translations to us.

You can use the **Chrome Translation Plugin** to translate entire pages for reference.

:::

# YLog <span class="symbol">- object</span>

```kotlin:no-line-numbers
object YLog
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 全局 Log 管理类。

## inMemoryData <span class="symbol">- field</span>

```kotlin:no-line-numbers
val inMemoryData: MutableList<YLogData>
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 当前全部已记录的日志数据。

::: danger

获取到的日志数据在 Hook APP (宿主) 及模块进程中是相互隔离的。

:::

## contents <span class="symbol">- field</span>

```kotlin:no-line-numbers
val contents: String
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 获取当前日志文件内容。

如果当前没有已记录的日志会返回空字符串。

::: danger

获取到的日志数据在 Hook APP (宿主) 及模块进程中是相互隔离的。

:::

## contents <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun contents(data: List<YLogData>): String
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 获取、格式化当前日志文件内容。

如果当前没有已记录的日志 (`data` 为空) 会返回空字符串。

::: danger

获取到的日志数据在 Hook APP (宿主) 及模块进程中是相互隔离的。

:::

## clear <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun clear()
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 清除全部已记录的日志。

你也可以直接获取 [inMemoryData](#inmemorydata-field) 来清除。

::: danger

获取到的日志数据在 Hook APP (宿主) 及模块进程中是相互隔离的。

:::

## saveToFile <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun saveToFile(fileName: String, data: List<YLogData>)
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 保存当前日志到文件。

若当前未开启 `Configs.isRecord` 或记录为空则不会进行任何操作。

日志文件会追加到 `fileName` 的文件结尾，若文件不存在会自动创建。

::: danger 

文件读写权限取决于当前宿主、模块已获取的权限。

:::

## Configs <span class="symbol">- object</span>

```kotlin:no-line-numbers
object Configs
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 配置 `YLog`。

### TAG <span class="symbol">- field</span>

```kotlin:no-line-numbers
const val TAG: Int
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 标签。

### PRIORITY <span class="symbol">- field</span>

```kotlin:no-line-numbers
const val PRIORITY: Int
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 优先级。

### PACKAGE_NAME <span class="symbol">- field</span>

```kotlin:no-line-numbers
const val PACKAGE_NAME: Int
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 当前宿主的包名。

### USER_ID <span class="symbol">- field</span>

```kotlin:no-line-numbers
const val USER_ID: Int
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 当前宿主的用户 ID (主用户不显示)。

### tag <span class="symbol">- field</span>

```kotlin:no-line-numbers
var tag: String
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 这是一个调试日志的全局标识。

默认文案为 `YukiHookAPI`。

你可以修改为你自己的文案。

### isEnable <span class="symbol">- field</span>

```kotlin:no-line-numbers
var isEnable: Boolean
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 是否启用调试日志的输出功能。

关闭后将会停用 `YukiHookAPI` 对全部日志的输出。

但是不影响当你手动调用下面这些方法输出日志。

`debug`、`info`、`warn`、`error`。

当 `isEnable` 关闭后 `YukiHookAPI.Configs.isDebug` 也将同时关闭。

### isRecord <span class="symbol">- field</span>

```kotlin:no-line-numbers
var isRecord: Boolean
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 是否启用调试日志的记录功能。

开启后将会在内存中记录全部可用的日志和异常堆栈。

需要同时启用 [isEnable](#isenable-field) 才能有效。

::: danger

过量的日志可能会导致宿主运行缓慢或造成频繁 GC。

:::

开启后你可以调用 [YLog.saveToFile](#savetofile-method) 实时保存日志到文件或使用 [YLog.contents](#contents-field) 获取实时日志文件。

### elements <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun elements(vararg item: Int)
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 自定义调试日志对外显示的元素。

只对日志记录和 (Xposed) 宿主环境的日志生效。

日志元素的排列将按照你在 `item` 中设置的顺序进行显示。

你还可以留空 `item` 以不显示除日志内容外的全部元素。

可用的元素有：`TAG`、`PRIORITY`、`PACKAGE_NAME`、`USER_ID`。

**功能示例**

打印的日志样式将按照你设置的排列顺序和元素内容进行。

> 示例如下

```kotlin
elements(TAG, PRIORITY, PACKAGE_NAME, USER_ID)
```

以上内容定义的日志将显示为如下样式。

> 示例如下

```:no-line-numbers
[YukiHookAPI][D][com.demo.test][999]--> This is a log
```

如果我们调整元素顺序以及减少个数，那么结果又会不一样。

> 示例如下

```kotlin
elements(PACKAGE_NAME, USER_ID, PRIORITY)
```

以上内容定义的日志将显示为如下样式。

> 示例如下

```:no-line-numbers
[com.demo.test][999][D]--> This is a log
```

## debug <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun debug(msg: String, e: Throwable?, tag: String, env: EnvType)
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 打印 Debug 级别 Log。

## info <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun info(msg: String, e: Throwable?, tag: String, env: EnvType)
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 打印 Info 级别 Log。

## warn <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun warn(msg: String, e: Throwable?, tag: String, env: EnvType)
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 打印 Warn 级别 Log。

## error <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun error(msg: String, e: Throwable?, tag: String, env: EnvType)
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 打印 Error 级别 Log。

## EnvType <span class="symbol">- class</span>

```kotlin:no-line-numbers
enum class EnvType
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 需要打印的日志环境类型。

决定于模块与 (Xposed) 宿主环境使用的打印方式。

### LOGD <span class="symbol">- enum</span>

```kotlin:no-line-numbers
LOGD
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 仅使用 `android.util.Log`。

### XPOSED_ENVIRONMENT <span class="symbol">- enum</span>

```kotlin:no-line-numbers
XPOSED_ENVIRONMENT
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 仅在 (Xposed) 宿主环境使用。

::: danger

只能在 (Xposed) 宿主环境中使用，模块环境将不生效。

:::

### SCOPE <span class="symbol">- enum</span>

```kotlin:no-line-numbers
SCOPE
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 分区使用。

(Xposed) 宿主环境仅使用 `XPOSED_ENVIRONMENT`。

模块环境仅使用 `LOGD`。

### BOTH <span class="symbol">- enum</span>

```kotlin:no-line-numbers
BOTH
```

**Change Records**

`v1.2.0` `added`

**Function Illustrate**

> 同时使用。

(Xposed) 宿主环境使用 `LOGD` 与 `XPOSED_ENVIRONMENT`。

模块环境仅使用 `LOGD`。

<h1 class="deprecated">LoggerFactory - kt</h1>

**Change Records**

`v1.0` `first`

`v1.2.0` `deprecated`

请迁移到 `YLog`