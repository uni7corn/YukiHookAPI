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
 * This file is created by fankes on 2022/9/20.
 */
package com.highcapable.yukihookapi.bean

/**
 * 生成的模板数据实例
 * @param entryPackageName 入口类包名
 * @param modulePackageName 模块包名 (命名空间)
 * @param customMPackageName 自定义模块包名
 * @param entryClassName 入口类名
 * @param xInitClassName xposed_init 入口类名
 * @param isEntryClassKindOfObject 入口类种类 (类型) 是否为 object (单例)
 * @param isUsingResourcesHook 是否启用 Resources Hook
 */
data class GenerateData(
    var entryPackageName: String = "",
    var modulePackageName: String = "",
    var customMPackageName: String = "",
    var entryClassName: String = "",
    var xInitClassName: String = "",
    var isEntryClassKindOfObject: Boolean = false,
    var isUsingResourcesHook: Boolean = true
)