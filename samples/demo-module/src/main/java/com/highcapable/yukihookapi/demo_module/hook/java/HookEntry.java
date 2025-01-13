/*
 * YukiHookAPI - An efficient Kotlin version of the Xposed Hook API.
 * Copyright (C) 2019-2022 HighCapable
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
 * This file is Created by fankes on 2022/5/25.
 */
package com.highcapable.yukihookapi.demo_module.hook.java;

import android.app.Activity;
import android.os.Bundle;

import com.highcapable.yukihookapi.YukiHookAPI;
import com.highcapable.yukihookapi.hook.core.api.priority.YukiHookPriority;
import com.highcapable.yukihookapi.hook.factory.ReflectionFactoryKt;
import com.highcapable.yukihookapi.hook.log.YLog;
import com.highcapable.yukihookapi.hook.xposed.bridge.event.YukiXposedEvent;
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit;

import kotlin.Unit;

// ========
// This only demonstrates how to use it in the Java case (Java 1.8+ only)
// The code here is for demonstration only， does not mean that it will work in the future， Demo will only sync the latest Kotlin usage
// It is recommended to use Kotlin to complete the writing of the Hook part
// Please remove the code note "//" below to use this demo, but make sure to comment out the HookEntry annotation on the Kotlin side
// 这里仅演示了 Java 情况下的使用方式 (仅限 Java 1.8+)
// 这里的代码仅供演示 - 并不代表今后都可以正常运行 - Demo 只会同步最新的 Kotlin 使用方法
// 建议还是使用 Kotlin 来完成 Hook 部分的编写
// 请删除下方的注释 "//" 以使用此 Demo - 但要确保注释掉 Kotlin 一边的 HookEntry 的注解
// ========
// @InjectYukiHookWithXposed(isUsingResourcesHook = true)
public class HookEntry implements IYukiHookXposedInit {

    @Override
    public void onInit() {
        YukiHookAPI.Configs configs = YukiHookAPI.Configs.INSTANCE;
        YLog.Configs logConfigs = YLog.Configs.INSTANCE;
        logConfigs.setTag("YukiHookAPI-Demo");
        logConfigs.setEnable(true);
        logConfigs.setRecord(false);
        logConfigs.elements(
                YLog.Configs.TAG,
                YLog.Configs.PRIORITY,
                YLog.Configs.PACKAGE_NAME,
                YLog.Configs.USER_ID
        );
        configs.setDebug(true);
        configs.setEnableModuleAppResourcesCache(true);
        configs.setEnableDataChannel(true);
    }

    @Override
    public void onHook() {
        // Here is the Java writing method that is more similar to the Kotlin writing method, just for reference
        // Calling Kotlin's lambda in Java also needs to return Unit.INSTANCE in the Unit case
        // 这里介绍了比较近似于 Kotlin 写法的 Java 写法 - 仅供参考
        // 在 Java 中调用 Kotlin 的 lambda 在 Unit 情况下也需要 return Unit.INSTANCE
        YukiHookAPI.INSTANCE.encase(e -> {
            e.loadZygote(l -> {
                var result = ReflectionFactoryKt.method(Activity.class, m -> {
                    m.setName("onCreate");
                    m.param(Bundle.class);
                    return Unit.INSTANCE;
                });
                l.hook(result, YukiHookPriority.DEFAULT, h -> {
                    h.after(a -> {
                        Activity instance = ((Activity) a.getInstance());
                        instance.setTitle(instance.getTitle() + " [Active]");
                        return Unit.INSTANCE;
                    });
                    return Unit.INSTANCE;
                });
                return Unit.INSTANCE;
            });
            // The rest of the code has been omitted, you can continue to refer to the above method to complete
            // 余下部分代码已略 - 可继续参考上述方式完成
            // ...
            return Unit.INSTANCE;
        });
    }

    @Override
    public void onXposedEvent() {
        // Since Java does not support some methods that do not override Kotlin Interface
        // So this method is not needed here, you can leave the content blank
        // 由于 Java 不支持不重写 Kotlin Interface 的部分方法
        // 所以不需要此方法这里可以不填写内容
        YukiXposedEvent event = YukiXposedEvent.INSTANCE;
        event.onInitZygote(startupParam -> {
            // Write the startupParam method here
            // 这里编写 startupParam 方法
            return Unit.INSTANCE;
        });
        event.onHandleLoadPackage(loadPackageParam -> {
            // Write the loadPackageParam method here
            // 这里编写 loadPackageParam 方法
            return Unit.INSTANCE;
        });
        event.onHandleInitPackageResources(resourcesParam -> {
            // Write the resourcesParam method here
            // 这里编写 resourcesParam 方法
            return Unit.INSTANCE;
        });
    }
}