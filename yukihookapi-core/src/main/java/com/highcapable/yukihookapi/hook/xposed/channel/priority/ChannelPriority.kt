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
 * This file is created by fankes on 2023/1/3.
 */
package com.highcapable.yukihookapi.hook.xposed.channel.priority

import com.highcapable.yukihookapi.hook.xposed.channel.YukiHookDataChannel

/**
 * 数据通讯桥响应优先级构造类
 *
 * 这个类是对 [YukiHookDataChannel] 的一个扩展用法
 * @param conditions 条件方法体
 */
class ChannelPriority(private val conditions: () -> Boolean) {

    /**
     * 获取条件方法体结果
     * @return [Boolean]
     */
    internal val result get() = conditions()
}