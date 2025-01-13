/*
 * YukiHookAPI - An efficient Hook API and Xposed Module solution built in Kotlin.
 * Copyright (C) 2019 HighCapable
 * https://github.com/HighCapable/YukiHookAPI
 *
 * Apache License Version 2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file is created by fankes on 2023/1/9.
 */
package com.highcapable.yukihookapi.hook.core.api.helper

import com.highcapable.yukihookapi.hook.core.api.compat.HookApiCategoryHelper
import com.highcapable.yukihookapi.hook.core.api.compat.HookCompatHelper
import com.highcapable.yukihookapi.hook.core.api.proxy.YukiHookCallback
import com.highcapable.yukihookapi.hook.core.api.result.YukiHookResult
import com.highcapable.yukihookapi.hook.core.api.store.YukiHookCacheStore
import com.highcapable.yukihookapi.hook.core.finder.base.BaseFinder
import com.highcapable.yukihookapi.hook.core.finder.members.ConstructorFinder
import com.highcapable.yukihookapi.hook.core.finder.members.MethodFinder
import com.highcapable.yukihookapi.hook.log.YLog
import java.lang.reflect.Member

/**
 * Hook 核心功能实现工具类
 */
internal object YukiHookHelper {

    /**
     * Hook [BaseFinder.BaseResult]
     * @param traction 直接调用 [BaseFinder.BaseResult]
     * @param callback 回调
     * @return [YukiHookResult]
     */
    internal fun hook(traction: BaseFinder.BaseResult, callback: YukiHookCallback) = runCatching {
        val member: Member? = when (traction) {
            is MethodFinder.Result -> traction.ignored().give()
            is ConstructorFinder.Result -> traction.ignored().give()
            else -> error("Unexpected BaseFinder result interface type")
        }
        hookMember(member, callback)
    }.onFailure { YLog.innerE("An exception occurred when hooking internal function", it) }.getOrNull() ?: YukiHookResult()

    /**
     * Hook [Member]
     * @param member 需要 Hook 的方法、构造方法
     * @param callback 回调
     * @return [YukiHookResult]
     */
    internal fun hookMember(member: Member?, callback: YukiHookCallback): YukiHookResult {
        runCatching {
            YukiHookCacheStore.hookedMembers.takeIf { it.isNotEmpty() }?.forEach {
                if (it.member.toString() == member?.toString()) return YukiHookResult(isAlreadyHooked = true, it)
            }
        }
        return HookCompatHelper.hookMember(member, callback).let {
            if (it != null) YukiHookCacheStore.hookedMembers.add(it)
            YukiHookResult(hookedMember = it)
        }
    }

    /**
     * 获取当前 [Member] 是否被 Hook
     * @param member 实例
     * @return [Boolean]
     */
    internal fun isMemberHooked(member: Member?): Boolean {
        if (member == null) return false
        return HookApiCategoryHelper.hasAvailableHookApi && YukiHookCacheStore.hookedMembers.any { it.member.toString() == member.toString() }
    }

    /**
     * 执行原始 [Member]
     *
     * 未进行 Hook 的 [Member]
     * @param member 实例
     * @param args 参数实例
     * @return [Any] or null
     * @throws IllegalStateException 如果 [Member] 参数个数不正确
     */
    internal fun invokeOriginalMember(member: Member?, instance: Any?, args: Array<out Any?>?) =
        if (isMemberHooked(member)) member?.let {
            runCatching { HookCompatHelper.invokeOriginalMember(member, instance, args) }.onFailure {
                if (it.message?.lowercase()?.contains("wrong number of arguments") == true) error(it.message ?: it.toString())
                YLog.innerE("Invoke original Member [$member] failed", it)
            }.getOrNull()
        } else null

    /**
     * 使用当前 Hook API 自带的日志功能打印日志
     * @param msg 日志打印的内容
     * @param e 异常堆栈信息 - 默认空
     */
    internal fun logByHooker(msg: String, e: Throwable? = null) {
        if (HookApiCategoryHelper.hasAvailableHookApi) HookCompatHelper.logByHooker(msg, e)
    }
}