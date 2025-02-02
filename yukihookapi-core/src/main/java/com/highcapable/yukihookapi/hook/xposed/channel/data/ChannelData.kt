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
 * This file is created by fankes on 2022/5/16.
 */
package com.highcapable.yukihookapi.hook.xposed.channel.data

import com.highcapable.yukihookapi.hook.xposed.channel.YukiHookDataChannel
import java.io.Serializable

/**
 * 数据通讯桥键值构造类
 *
 * 这个类是对 [YukiHookDataChannel] 的一个扩展用法
 *
 * 详情请参考 [API 文档 - ChannelData](https://fankes.github.io/YukiHookAPI/zh-cn/api/public/com/highcapable/yukihookapi/hook/xposed/channel/data/ChannelData)
 *
 * For English version, see [API Document - ChannelData](https://fankes.github.io/YukiHookAPI/en/api/public/com/highcapable/yukihookapi/hook/xposed/channel/data/ChannelData)
 * @param key 键值
 * @param value 键值数据 - 作为接收数据时可空
 */
data class ChannelData<T>(var key: String, var value: T? = null) : Serializable