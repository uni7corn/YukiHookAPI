/*
 * YukiHookAPI - An efficient Hook API and Xposed Module solution built in Kotlin.
 * Copyright (C) 2019-2023 HighCapable
 * https://github.com/fankes/YukiHookAPI
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is created by fankes on 2022/4/9.
 * This file is modified by fankes on 2023/1/9.
 */
package com.highcapable.yukihookapi.hook.core.api.proxy

import com.highcapable.yukihookapi.hook.core.api.priority.YukiHookPriority
import java.lang.reflect.Member

/**
 * Hook 方法回调接口抽象类
 * @param priority Hook 优先级 - 默认 [YukiHookPriority.DEFAULT]
 */
internal abstract class YukiMemberHook(override val priority: YukiHookPriority = YukiHookPriority.DEFAULT) : YukiHookCallback(priority) {

    /**
     * 在方法执行之前注入
     * @param param Hook 结果回调接口
     */
    internal open fun beforeHookedMember(param: Param) {}

    /**
     * 在方法执行之后注入
     * @param param Hook 结果回调接口
     */
    internal open fun afterHookedMember(param: Param) {}

    /**
     * 已经 Hook 且可被解除 Hook 的 [Member] 实现接口抽象类
     */
    internal abstract class HookedMember internal constructor() {

        /**
         * 当前被 Hook 的 [Member]
         * @return [Member] or null
         */
        internal abstract val member: Member?

        /** 解除 Hook */
        internal abstract fun remove()
    }
}