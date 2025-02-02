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
 * This file is created by fankes on 2023/1/11.
 */
package com.highcapable.yukihookapi.hook.xposed.bridge.proxy

import android.content.pm.ApplicationInfo
import com.highcapable.yukihookapi.hook.xposed.bridge.resources.YukiResources
import com.highcapable.yukihookapi.hook.xposed.bridge.type.HookEntryType

/**
 * Xposed 模块生命周期实现接口
 */
internal interface IYukiXposedModuleLifecycle {

    /**
     * 当 Xposed 模块开始装载
     * @param packageName 当前 Xposed 模块包名
     * @param appFilePath 当前 Xposed 模块自身 APK 路径
     */
    fun onStartLoadModule(packageName: String, appFilePath: String)

    /** 当 Xposed 模块装载完成 */
    fun onFinishLoadModule()

    /**
     * 当可用的 Hook APP (宿主) 开始装载
     * @param type 当前正在进行的 Hook 类型
     * @param packageName 宿主包名
     * @param processName 宿主进程名
     * @param appClassLoader 宿主 [ClassLoader]
     * @param appInfo 宿主 [ApplicationInfo]
     * @param appResources 宿主 [YukiResources]
     */
    fun onPackageLoaded(
        type: HookEntryType,
        packageName: String?,
        processName: String? = "",
        appClassLoader: ClassLoader? = null,
        appInfo: ApplicationInfo? = null,
        appResources: YukiResources? = null
    )
}