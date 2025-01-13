---
pageClass: code-page
---

# ConstructorRules <span class="symbol">- class</span>

```kotlin:no-line-numbers
class ConstructorRules internal constructor(private val rulesData: ConstructorRulesData) : BaseRules
```

**变更记录**

`v1.1.0` `新增`

**功能描述**

> `Constructor` 查找条件实现类。

## paramCount <span class="symbol">- field</span>

```kotlin:no-line-numbers
var paramCount: Int
```

**变更记录**

`v1.1.0` `新增`

**功能描述**

> 设置 `Constructor` 参数个数。

你可以不使用 `param` 指定参数类型而是仅使用此变量指定参数个数。

若参数个数小于零则忽略并使用 `param`。

## modifiers <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun modifiers(conditions: ModifierConditions)
```

**变更记录**

`v1.1.0` `新增`

**功能描述**

> 设置 `Constructor` 标识符筛选条件。

可不设置筛选条件。

## emptyParam <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun emptyParam()
```

**变更记录**

`v1.1.0` `新增`

**功能描述**

> 设置 `Constructor` 空参数、无参数。

## param <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun param(vararg paramType: Any)
```

**变更记录**

`v1.1.0` `新增`

**功能描述**

> 设置 `Constructor` 参数。

如果同时使用了 `paramCount` 则 `paramType` 的数量必须与 `paramCount` 完全匹配。

如果 `Constructor` 中存在一些无意义又很长的类型，你可以使用 `VagueType` 来替代它。

::: danger

无参 **Constructor** 请使用 **emptyParam** 设置查找条件。

有参 **Constructor** 必须使用此方法设定参数或使用 **paramCount** 指定个数。

:::

## param <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun param(conditions: ObjectsConditions)
```

**变更记录**

`v1.1.5` `新增`

**功能描述**

> 设置 `Constructor` 参数条件。

::: danger

无参 **Constructor** 请使用 **emptyParam** 设置查找条件。

有参 **Constructor** 必须使用此方法设定参数或使用 **paramCount** 指定个数。

:::

## paramCount <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun paramCount(numRange: IntRange)
```

**变更记录**

`v1.1.0` `新增`

**功能描述**

> 设置 `Constructor` 参数个数范围。

你可以不使用 `param` 指定参数类型而是仅使用此方法指定参数个数范围。

## paramCount <span class="symbol">- method</span>

```kotlin:no-line-numbers
fun paramCount(conditions: CountConditions)
```

**变更记录**

`v1.1.0` `新增`

**功能描述**

> 设置 `Constructor` 参数个数条件。

你可以不使用 `param` 指定参数类型而是仅使用此方法指定参数个数条件。